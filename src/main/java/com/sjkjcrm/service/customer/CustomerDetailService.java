package com.sjkjcrm.service.customer;

import com.sjkjcrm.bean.customer.CustomerDetail;

import java.util.List;

public interface CustomerDetailService {

    /**
     * 获取所有的客户信息
     * @return
     */
    List<CustomerDetail> getAll();
}
