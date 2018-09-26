create database sjkj;

#客户信息详情表
create table customer_detail(
    customer_id varchar(50) not null comment '客户详情ID',
    name varchar(30) not null comment '客户名称',
    number varchar(30) comment '客户编号',
    type varchar(10) comment '客户类型',
    phone varchar(20) comment '客户电话',
    corp_tel varchar(20) comment '公司电话',
    mail varchar(20) comment '邮箱',
    address varchar(50) comment '通信地址',
    corp_type varchar(30) comment '公司类型',
    corp_grade varchar(30) comment '公司级别',
    visit_date varchar(10) comment '回访日期',
    linkman varchar(10) comment '联系人',
    salesperson varchar(10) comment '销售员',
    create_time timestamp default CURRENT_TIMESTAMP comment '创建时间',
    primary key (customer_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户信息详情表';

#客户信息详情表(沟通修改版)
create table customer_detail(
    customer_id varchar(50) not null comment '客户详情ID',
    corp_name varchar(30) not null comment '企业名称',
    legal_person varchar(30) comment '法人',
    corp_tel varchar(20) comment '联系电话',
    other_linkman varchar(20) comment '其他联系人',
    linkman_phone varchar(20) comment '其他联系人电话',
    address varchar(50) comment '地址',
    registration_time varchar(10) comment '注册时间',
    registration_capital varchar(10) comment '注册资金',
    weburl varchar(30) comment '网址',
    customer_status varchar(5) comment '客户状态',
    visit_date varchar(20) comment '回访日期',
    remarks varchar(100) comment '备注',
    loginuser varchar(10) comment '登录人',
    create_time timestamp default CURRENT_TIMESTAMP comment '创建时间',
    primary key (customer_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户信息详情表';