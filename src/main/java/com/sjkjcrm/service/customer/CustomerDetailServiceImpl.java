package com.sjkjcrm.service.customer;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjkjcrm.bean.customer.CustomerDetail;
import com.sjkjcrm.dao.customer.CustomerDetailDao;
import com.sjkjcrm.util.excel.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {

    @Autowired
    private CustomerDetailDao customerDetailDao;

    @Autowired
    private HttpSession session;

    @Override
    public List<CustomerDetail> getCustomerByCondition(Page<CustomerDetail> page) {
        return customerDetailDao.getCustomerByCondition(page);
    }

    @Override
    public int insertCustomer(CustomerDetail customerDetail) {
        customerDetail.setLoginUser(session.getAttribute("user").toString());
        return customerDetailDao.insertCustomer(customerDetail);
    }

    @Override
    public int deleteCustomer(String id) {
        return customerDetailDao.deleteCustomer(id);
    }

    @Override
    public void importExcel(String fileName, MultipartFile file) {
        try {
            List<CustomerDetail> customerDetailList = ExcelUtils.importExcel(file, 1, 1, CustomerDetail.class);

            for (CustomerDetail customerDetail : customerDetailList) {
                if ("意向客户".equals(customerDetail.getCustomerStatus())) {
                    customerDetail.setCustomerStatus("0");
                } else if ("重点客户".equals(customerDetail.getCustomerStatus())) {
                    customerDetail.setCustomerStatus("1");
                } else if ("公海客户".equals(customerDetail.getCustomerStatus())) {
                    customerDetail.setCustomerStatus("2");
                } else if ("合作客户".equals(customerDetail.getCustomerStatus())) {
                    customerDetail.setCustomerStatus("3");
                } else if ("保护客户".equals(customerDetail.getCustomerStatus())) {
                    customerDetail.setCustomerStatus("4");
                } else {
                    customerDetail.setCustomerStatus("");
                }
            }

            BatchPreparedStatementSetter batchPreparedStatementSetter = new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    CustomerDetail customerDetail = customerDetailList.get(i);
                    preparedStatement.setString(1, customerDetail.getCorpName());
                    preparedStatement.setString(2, customerDetail.getLegalPerson());
                    preparedStatement.setString(3, customerDetail.getCorpTel());
                    preparedStatement.setString(4, customerDetail.getOtherLinkman());
                    preparedStatement.setString(5, customerDetail.getLinkmanPhone());
                    preparedStatement.setString(6, customerDetail.getAddress());
                    preparedStatement.setString(7, customerDetail.getRegistrationTime());
                    preparedStatement.setString(8, customerDetail.getRegistrationCapital());
                    preparedStatement.setString(9, customerDetail.getWebUrl());
                    preparedStatement.setString(10, customerDetail.getCustomerStatus());
                    preparedStatement.setString(11, customerDetail.getVisitDate());
                    preparedStatement.setString(12, customerDetail.getRemarks());
                    preparedStatement.setString(13, session.getAttribute("user").toString());
                }

                @Override
                public int getBatchSize() {
                    return customerDetailList.size();
                }
            };
            customerDetailDao.batchInsertCustomer(batchPreparedStatementSetter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CustomerDetail selectCustomerDetailById(String id) {
        return customerDetailDao.selectCustomerDetailById(id);
    }

    @Override
    public void updateCustomer(CustomerDetail customerDetail) {
        customerDetailDao.updateCustomer(customerDetail);
    }

    @Override
    public String getDataCount() {
        return customerDetailDao.getDataCount();
    }
}
