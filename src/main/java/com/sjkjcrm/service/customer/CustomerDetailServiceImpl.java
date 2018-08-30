package com.sjkjcrm.service.customer;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjkjcrm.bean.customer.CustomerDetail;
import com.sjkjcrm.dao.customer.CustomerDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
