package com.sjkjcrm.dao.customer;

import com.baomidou.mybatisplus.plugins.Page;
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
     * 根据条件查询客户信息
     * @return
     */
    public List<CustomerDetail> getCustomerByCondition(Page<CustomerDetail> page) {
        String sql = "select * from customer_detail";
        List<CustomerDetail> list = baseDao.queryList(sql, new Object[]{}, CustomerDetail.class);
        return list;
    }

    /**
     * 新增客户信息
     * @param customerDetail
     */
    public int insertCustomer(CustomerDetail customerDetail) {
        String sql = "insert into customer_detail(customer_id, name, number, type, phone, corp_tel, mail, address, corp_type, corp_grade, visit_date, linkman) values(UUID_SHORT(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return this.baseDao.update(sql, new Object[] {customerDetail.getName(), customerDetail.getNumber(), customerDetail.getType(), customerDetail.getPhone(), customerDetail.getCorpTel(), customerDetail.getMail(), customerDetail.getAddress(), customerDetail.getCorpType(), customerDetail.getCorpGrade(), customerDetail.getVisitDate(), customerDetail.getLinkman()});
    }
}
