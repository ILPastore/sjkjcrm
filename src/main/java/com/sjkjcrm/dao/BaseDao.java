package com.sjkjcrm.dao;

import com.sjkjcrm.bean.customer.CustomerDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * 封装dao的基本操作
 * @author Zhang Qiao
 */
@Repository
public class BaseDao extends JdbcDaoSupport {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    /**
     * 查询单条记录
     *
     * @param sql
     * @return
     */
    public Map<String, Object> queryForMap(String sql) {
        List<Map<String, Object>> row = getWrapTemplate().queryForList(sql);
        if (row == null || row.size() == 0) {
            return null;
        }
        return row.get(0);
    }

    /**
     * 查询单条记录（可传参数）
     *
     * @param sql
     * @param obj
     * @return
     */
    public Map<String, Object> queryForMap(String sql, Object[] obj) {
        List<Map<String, Object>> row = getWrapTemplate().queryForList(sql, obj);
        if (row == null || row.size() == 0) {
            return null;
        }
        return row.get(0);
    }

    @SuppressWarnings("")
    public List<Class<?>> queryList(String sql, Object[] args, Class<?> cls) {
        return getWrapTemplate().query(sql, args, new BeanPropertyRowMapper(cls));
    }

    public List<Map<String, Object>> queryForList(String sql) {
        List<Map<String, Object>> row = getWrapTemplate().queryForList(sql);
        return row;
    }

    public List<Map<String, Object>> queryForList(String sql, int timeout) {
        List<Map<String, Object>> row = getWrapTemplate(timeout).queryForList(sql);
        return row;
    }

    public List<Map<String, Object>> queryForList(String sql, Object[] obj) {
        List<Map<String, Object>> row = getWrapTemplate().queryForList(sql, obj);
        return row;
    }

    public List<Map<String, Object>> queryForList(String sql, Object[] obj, int timeout) {
        List<Map<String, Object>> row = getWrapTemplate(timeout).queryForList(sql, obj);
        return row;
    }

    public int update(String sql) {
        int ret = getWrapTemplate().update(sql);
        return ret;
    }

    public int update(String sql, int timeout) {
        return getWrapTemplate(timeout).update(sql);
    }

    /**
     * 绑定参数更新
     *
     * @param sql SQL语句
     * @param obj 绑定参数
     * @return 结果
     */
    public int update(String sql, Object[] obj) {
        return getWrapTemplate().update(sql, obj);
    }

    public int update(String sql, Object[] obj, int timeout) {
        return getWrapTemplate(timeout).update(sql, obj);
    }

    /**
     * 批量执行绑定参数
     *
     * @param sql    语句
     * @param setter 绑定参数
     * @return 执行结果
     */
    public int[] executeBatch(String sql, BatchPreparedStatementSetter setter) {
        return getWrapTemplate().batchUpdate(sql, setter);
    }

    /**
     * 批量执行绑定参数
     *
     * @param sql     语句
     * @param setter  绑定参数
     * @param timeout 超时时间，单位秒
     * @return 执行结果
     */
    public int[] executeBatch(String sql, BatchPreparedStatementSetter setter, int timeout) {
        return getWrapTemplate(timeout).batchUpdate(sql, setter);
    }

    private JdbcTemplate getWrapTemplate() {
        JdbcTemplate template = this.getJdbcTemplate();
        // 设置超时时间
        template.setQueryTimeout(10);
        return template;
    }

    private JdbcTemplate getWrapTemplate(int timeout) {
        JdbcTemplate template = this.getJdbcTemplate();
        // 设置超时时间，单位秒
        template.setQueryTimeout(timeout);
        return template;
    }
}
