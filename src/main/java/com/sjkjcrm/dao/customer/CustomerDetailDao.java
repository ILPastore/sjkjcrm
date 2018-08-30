package com.sjkjcrm.dao.customer;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjkjcrm.bean.customer.CustomerDetail;
import com.sjkjcrm.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;

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
        Map<String, Object> paraConditions = page.getCondition();
        List<Object> conditionValues = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select * from customer_detail where 1 = 1 ");

        if (!Objects.isNull(paraConditions) && paraConditions.get("name") != null) {
            sql.append("and name like ? ");
            conditionValues.add("%" + paraConditions.get("name") + "%");
        }

        if (!Objects.isNull(paraConditions) && paraConditions.get("linkman") != null) {
            sql.append("and linkman like ? ");
            conditionValues.add("%" + paraConditions.get("linkman") + "%");
        }

        if (!Objects.isNull(paraConditions) && paraConditions.get("phone") != null) {
            sql.append("and phone like ? ");
            conditionValues.add(paraConditions.get("phone") + "%");
        }

        if (!Objects.isNull(paraConditions) && paraConditions.get("type") != null) {
            sql.append("and type = ? ");
            conditionValues.add(paraConditions.get("type"));
        }

        return baseDao.queryList(sql.toString(), conditionValues.toArray(), CustomerDetail.class);
    }

    /**
     * 新增客户信息
     * @param customerDetail
     */
    public int insertCustomer(CustomerDetail customerDetail) {
        String sql = "insert into customer_detail(customer_id, name, number, type, phone, corp_tel, mail, address, corp_type, corp_grade, visit_date, linkman) values(UUID_SHORT(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return this.baseDao.update(sql, new Object[] {customerDetail.getName(), customerDetail.getNumber(), customerDetail.getType(), customerDetail.getPhone(), customerDetail.getCorpTel(), customerDetail.getMail(), customerDetail.getAddress(), customerDetail.getCorpType(), customerDetail.getCorpGrade(), customerDetail.getVisitDate(), customerDetail.getLinkman()});
    }

    /**
     * 删除客户信息
     * @param id
     * @return
     */
    public int deleteCustomer(String id) {
        String sql = "delete from customer_detail where customer_id = ?";
        return this.baseDao.update(sql, new Object[]{id});
    }
}
