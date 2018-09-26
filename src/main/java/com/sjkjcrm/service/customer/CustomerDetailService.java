package com.sjkjcrm.service.customer;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjkjcrm.bean.customer.CustomerDetail;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 删除客户信息
     * @param id
     * @return
     */
    public int deleteCustomer(String id);

    /**
     * 导入excel
     * @param fileName
     * @param file
     */
    void importExcel(String fileName, MultipartFile file);

    /**
     * 根据ID查询客户信息
     * @param id
     * @return
     */
    public CustomerDetail selectCustomerDetailById(String id);

    /**
     * 更新客户信息
     * @param customerDetail
     */
    void updateCustomer(CustomerDetail customerDetail);

    /**
     * 获取总记录数
     * @return
     */
    String getDataCount();
}
