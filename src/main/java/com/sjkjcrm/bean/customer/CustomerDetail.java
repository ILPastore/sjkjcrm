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
     * 企业名称
     */
    @Excel(name = "企业名称", orderNum = "1", width = 20)
    private String corpName;

    /**
     * 法人
     */
    @Excel(name = "法人", orderNum = "2", width = 20)
    private String legalPerson;

    /**
     * 联系电话
     */
    @Excel(name = "联系电话", orderNum = "3", width = 20)
    private String corpTel;

    /**
     * 其他联系人
     */
    @Excel(name = "其他联系人", orderNum = "4", width = 20)
    private String otherLinkman;

    /**
     * 其他联系人电话
     */
    @Excel(name = "其他联系人电话", orderNum = "5", width = 20)
    private String linkmanPhone;

    /**
     * 地址
     */
    @Excel(name = "地址", orderNum = "6", width = 20)
    private String address;

    /**
     * 注册时间
     */
    @Excel(name = "注册时间", orderNum = "7", width = 20)
    private String registrationTime;

    /**
     * 注册资金
     */
    @Excel(name = "注册资金", orderNum = "8", width = 20)
    private String registrationCapital;

    /**
     * 网址
     */
    @Excel(name = "网址", orderNum = "9", width = 20)
    private String webUrl;

    /**
     * 客户状态
     */
    @Excel(name = "客户状态", orderNum = "10", width = 20)
    private String customerStatus;

    /**
     * 回访日期
     */
    @Excel(name= "回访日期", orderNum = "11", width = 20)
    private String visitDate;

    /**
     * 备注
     */
    @Excel(name= "备注", orderNum = "12", width = 20)
    private String remarks;

    /**
     * 登录人
     */
    private String loginUser;

    /**
     * 创建时间
     */
    private Timestamp createTime;
}
