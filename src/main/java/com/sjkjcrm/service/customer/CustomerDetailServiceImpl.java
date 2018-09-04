package com.sjkjcrm.service.customer;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjkjcrm.bean.customer.CustomerDetail;
import com.sjkjcrm.dao.customer.CustomerDetailDao;
import com.sjkjcrm.util.excel.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {

    @Autowired
    private CustomerDetailDao customerDetailDao;

    @Override
    public List<CustomerDetail> getCustomerByCondition(Page<CustomerDetail> page) {
        return customerDetailDao.getCustomerByCondition(page);
    }

    @Override
    public int insertCustomer(CustomerDetail customerDetail) {
        return customerDetailDao.insertCustomer(customerDetail);
    }

    @Override
    public int deleteCustomer(String id) {
        return customerDetailDao.deleteCustomer(id);
    }

    @Override
    public void importExcel(String fileName, MultipartFile file) {
//        String filePath = "D:\\customertemplate.xls";
        // 解析excel，
        try {
            List<CustomerDetail> customerDetailList = ExcelUtils.importExcel(file, 1, 1, CustomerDetail.class);
            System.out.println("导入数据一共【" + customerDetailList.size() + "】行");
            BatchPreparedStatementSetter batchPreparedStatementSetter = new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    CustomerDetail customerDetail = customerDetailList.get(i);
                    preparedStatement.setString(1, customerDetail.getName());
                    preparedStatement.setString(2, customerDetail.getNumber());
                    preparedStatement.setString(3, customerDetail.getType());
                    preparedStatement.setString(4, customerDetail.getPhone());
                    preparedStatement.setString(5, customerDetail.getCorpTel());
                    preparedStatement.setString(6, customerDetail.getMail());
                    preparedStatement.setString(7, customerDetail.getAddress());
                    preparedStatement.setString(8, customerDetail.getCorpType());
                    preparedStatement.setString(9, customerDetail.getCorpGrade());
                    preparedStatement.setString(10, customerDetail.getVisitDate());
                    preparedStatement.setString(11, customerDetail.getLinkman());
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
}
