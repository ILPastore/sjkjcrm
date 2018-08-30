package com.sjkjcrm.service.customer;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjkjcrm.bean.customer.CustomerDetail;

import java.util.List;

public interface CustomerDetailService {

    /**
     * 根据条件获取客户信息
     * @return
     */
    public List<CustomerDetail> getCustomerByCondition(Page<CustomerDetail> page);

    /**
     * 新增客户信息
     * @param customerDetail
     */
    public int insertCustomer(CustomerDetail customerDetail);
}
