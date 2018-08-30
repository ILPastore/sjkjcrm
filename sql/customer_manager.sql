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
    visit_date datetime comment '回访日期',
    linkman varchar(10) comment '联系人',
    create_time timestamp not null commont '创建时间',
    primary key (customer_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户信息详情表';