package com.sjkjcrm.service.customer;

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
    public List<CustomerDetail> getAll() {
        return customerDetailDao.findAll();
    }
}
