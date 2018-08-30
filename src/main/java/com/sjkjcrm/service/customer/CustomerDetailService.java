package com.sjkjcrm.service.customer;

import com.sjkjcrm.bean.customer.CustomerDetail;

import java.util.List;

public interface CustomerDetailService {

    /**
     * 获取所有的客户信息
     * @return
     */
    public List<CustomerDetail> getAll();

    /**
     * 新增客户信息
     * @param customerDetail
     */
    public int insertCustomer(CustomerDetail customerDetail);
}
