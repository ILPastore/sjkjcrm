package com.sjkjcrm.bean.customer;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @Description 客户信息实体类
 * @Author Zhang Qiao
 * @Date 2018-08-22 9:40
 **/
@Getter
@Setter
public class CustomerDetail {

    /**
     * 主键
     */
    @Id
    private String customerId;

    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名", orderNum = "1", width = 20)
    private String name;

    /**
     * 客户编号
     */
    @Excel(name = "客户编号", orderNum = "2", width = 20)
    private String number;

    /**
     * 客户类型
     */
    @Excel(name = "客户类型", orderNum = "3", width = 20)
    private String type;

    /**
     * 客户电话
     */
    @Excel(name = "客户电话", orderNum = "4", width = 20)
    private String phone;

    /**
     * 公司电话
     */
    @Excel(name = "公司电话", orderNum = "5", width = 20)
    private String corpTel;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱", orderNum = "6", width = 20)
    private String mail;

    /**
     * 通信地址
     */
    @Excel(name = "通信地址", orderNum = "7", width = 20)
    private String address;

    /**
     * 公司类型
     */
    @Excel(name = "公司类型", orderNum = "8", width = 20)
    private String corpType;

    /**
     * 公司级别
     */
    @Excel(name = "公司级别", orderNum = "9", width = 20)
    private String corpGrade;

    /**
     * 回访日期
     */
    @Excel(name = "回访日期", exportFormat = "yyyy-MM-dd", orderNum = "10", width = 20)
    private String visitDate;

    /**
     * 联系人
     */
    @Excel(name= "联系人", orderNum = "11", width = 20)
    private String linkman;

    /**
     * 销售员
     */
    private String salesperson;

    /**
     * 创建时间
     */
    private Timestamp createTime;
}
