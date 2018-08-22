package com.sjkjcrm.dao.customer;

import com.sjkjcrm.bean.customer.CustomerDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客户详情dao
 */
@Repository
public class CustomerDetailDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 查询所有的客户信息
     * @return
     */
    public List<CustomerDetail> findAll() {
        String sql = "select * from customer_detail";
        List<CustomerDetail> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(CustomerDetail.class));
        return list;
    }
}
