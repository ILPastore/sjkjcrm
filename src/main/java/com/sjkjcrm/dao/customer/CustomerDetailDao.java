package com.sjkjcrm.dao.customer;

import com.sjkjcrm.bean.customer.CustomerDetail;
import com.sjkjcrm.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 客户详情dao
 */
@Repository
public class CustomerDetailDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Resource
    private BaseDao baseDao;

    /**
     * 查询所有的客户信息
     * @return
     */
    public List<CustomerDetail> findAll() {
        String sql = "select * from customer_detail";
        List<CustomerDetail> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper(CustomerDetail.class));
        return list;
    }

    /**
     * 新增客户信息
     * @param customerDetail
     */
    public int insertCustomer(CustomerDetail customerDetail) {
        String sql = "insert into customer_detail(customer_id, name, number, type, phone, corp_tel, mail, address, corp_type, corp_grade, visit_date, linkman, create_time) values(UUID_SHORT(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
        return this.baseDao.update(sql, new Object[] {customerDetail.getName(), customerDetail.getNumber(), customerDetail.getType(), customerDetail.getPhone(), customerDetail.getCorpTel(), customerDetail.getMail(), customerDetail.getAddress(), customerDetail.getCorpType(), customerDetail.getCorpGrade(), customerDetail.getVisitDate(), customerDetail.getLinkman()});
    }
}
