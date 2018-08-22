package com.sjkjcrm.bean.customer;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Getter;
import lombok.Setter;

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
    @Excel(name = "ID")
    private String customerId;

    /**
     * 客户姓名
     */
    @Excel(name = "客户姓名", orderNum = "1")
    private String name;

    /**
     * 客户编号
     */
    @Excel(name = "客户编号", orderNum = "2")
    private String number;

    /**
     * 客户类型
     */
    @Excel(name = "客户类型", orderNum = "3")
    private String type;

    /**
     * 客户电话
     */
    @Excel(name = "客户电话", orderNum = "4")
    private String phone;

    /**
     * 公司电话
     */
    @Excel(name = "公司电话", orderNum = "5")
    private String corpTel;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱", orderNum = "6")
    private String mail;

    /**
     * 通信地址
     */
    @Excel(name = "通信地址", orderNum = "7")
    private String address;

    /**
     * 公司类型
     */
    @Excel(name = "公司类型", orderNum = "8")
    private String corpType;

    /**
     * 公司级别
     */
    @Excel(name = "公司级别", orderNum = "9")
    private String corpGrade;

    /**
     * 回访日期
     */
    @Excel(name = "回访日期", exportFormat = "yyyy-MM-dd", orderNum = "10")
    private Date visitDate;
}
