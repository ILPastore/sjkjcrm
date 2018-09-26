package com.sjkjcrm.dao.customer;

import com.baomidou.mybatisplus.plugins.Page;
import com.sjkjcrm.bean.customer.CustomerDetail;
import com.sjkjcrm.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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

    @Autowired
    private HttpSession session;

    /**
     * 根据条件查询客户信息
     * @return
     */
    public List<CustomerDetail> getCustomerByCondition(Page<CustomerDetail> page) {
        Map<String, Object> paraConditions = page.getCondition();
        List<Object> conditionValues = new ArrayList<>();
        StringBuilder sql = new StringBuilder("select * from customer_detail where 1 = 1 ");

        if (!Objects.isNull(paraConditions) && paraConditions.get("corpName") != null) {
            sql.append("and corp_name like ? ");
            conditionValues.add("%" + paraConditions.get("corpName") + "%");
        }

        if (!Objects.isNull(paraConditions) && paraConditions.get("legalPerson") != null) {
            sql.append("and legal_person like ? ");
            conditionValues.add("%" + paraConditions.get("legalPerson") + "%");
        }

        if (!Objects.isNull(paraConditions) && paraConditions.get("corpTel") != null) {
            sql.append("and corp_tel like ? ");
            conditionValues.add(paraConditions.get("corpTel") + "%");
        }

        if (!Objects.isNull(paraConditions) && paraConditions.get("customerStatus") != null) {
            sql.append("and customer_status = ? ");
            conditionValues.add(paraConditions.get("customerStatus"));
        }

        if (session.getAttribute("user") != null) {
            sql.append("and loginuser = ? ");
            conditionValues.add(session.getAttribute("user"));
        }

        // 按照时间倒序
        sql.append("order by create_time desc ");

        // 分页语句
        sql.append("limit ?, ?");
        conditionValues.add((page.getCurrent() - 1)*page.getSize());
        conditionValues.add(page.getSize());

        return baseDao.queryList(sql.toString(), conditionValues.toArray(), CustomerDetail.class);
    }

    /**
     * 新增客户信息
     * @param customerDetail
     */
    public int insertCustomer(CustomerDetail customerDetail) {
        String sql = "insert into customer_detail(customer_id, corp_name, legal_person, corp_tel, other_linkman, linkman_phone, address, registration_time, registration_capital, weburl, customer_status, visit_date, remarks, loginuser) values(UUID_SHORT(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return this.baseDao.update(sql, new Object[] {customerDetail.getCorpName(), customerDetail.getLegalPerson(), customerDetail.getCorpTel(), customerDetail.getOtherLinkman(), customerDetail.getLinkmanPhone(), customerDetail.getAddress(), customerDetail.getRegistrationTime(), customerDetail.getRegistrationCapital(), customerDetail.getWebUrl(), customerDetail.getCustomerStatus(), customerDetail.getVisitDate(), customerDetail.getRemarks(), customerDetail.getLoginUser()});
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

    /**
     * 批量新增客户信息
     * @return
     */
    public int[] batchInsertCustomer(BatchPreparedStatementSetter batchPreparedStatementSetter) {
        String sql = "insert into customer_detail(customer_id, corp_name, legal_person, corp_tel, other_linkman, linkman_phone, address, registration_time, registration_capital, weburl, customer_status, visit_date, remarks, loginuser) values(UUID_SHORT(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return baseDao.executeBatch(sql, batchPreparedStatementSetter);
    }

    /**
     * 根据id查询客户信息
     * @param id
     * @return
     */
    public CustomerDetail selectCustomerDetailById(String id) {
        String sql = "select * from customer_detail where customer_id = ?";
        return (CustomerDetail) baseDao.queryObject(sql, new Object[]{id}, CustomerDetail.class);
    }

    public void updateCustomer(CustomerDetail customerDetail) {
        String sql = "update customer_detail set corp_name = ?, legal_person = ?, corp_tel = ?, other_linkman = ?, linkman_phone = ?, address = ?, registration_time = ?, registration_capital = ?, weburl = ?, customer_status = ?, visit_date = ?, remarks = ? where customer_id = ?";
        baseDao.update(sql, new Object[] {customerDetail.getCorpName(), customerDetail.getLegalPerson(), customerDetail.getCorpTel(), customerDetail.getOtherLinkman(), customerDetail.getLinkmanPhone(), customerDetail.getAddress(), customerDetail.getRegistrationTime(), customerDetail.getRegistrationCapital(), customerDetail.getWebUrl(), customerDetail.getCustomerStatus(), customerDetail.getVisitDate(), customerDetail.getRemarks(), customerDetail.getCustomerId()});
    }

    /**
     * 获取总记录数量
     * @return
     */
    public String getDataCount() {
        String sql = "select count(*) as count from customer_detail";
        Map<String, Object> resultMap = baseDao.queryForMap(sql);
        return resultMap.get("count").toString();
    }
}
