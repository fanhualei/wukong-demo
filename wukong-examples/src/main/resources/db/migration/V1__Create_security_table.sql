DROP TABLE IF EXISTS ec_admin;
DROP TABLE IF EXISTS ec_admin_log;
DROP TABLE IF EXISTS ec_admin_group;
DROP TABLE IF EXISTS ec_admin_route;


create table ec_admin
(
    admin_id         bigint  unsigned   auto_increment                               comment '管理员ID' ,
    admin_name       varchar(50)                                     not null        comment '管理员名称',
    alias            varchar(150)                                                    comment '别名',
    admin_password   varchar(100)       default ''                   not null        comment '管理员密码',
    admin_login_time datetime           default CURRENT_TIMESTAMP    null            comment '登录时间',
    admin_login_num  int                default 0                    not null        comment '登录次数',
    admin_is_super   boolean            default false                not null        comment '是否超级管理员',
    enabled          boolean            default true                                 comment '是否有效',
    phone            varchar(30)                                                     comment '手机号码',
    email            varchar(50)                                                     comment '邮箱地址',
    avatar           varchar(200)                                                    comment '头像',
    admin_gid        int                                             null            comment '权限组ID',
    tags             varchar(300)                                                    comment '标签',
    gmt_create       datetime           default CURRENT_TIMESTAMP                    comment '记录创建时间',
    gmt_modified     datetime           default CURRENT_TIMESTAMP                    comment '记录修改时间',
    PRIMARY KEY (admin_id),
    UNIQUE KEY `uq_admin_admin_name` (`admin_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8  COMMENT='管理员表';

-- 用来保存管理员的一些扩展信息
DROP TABLE IF EXISTS ec_admin_extend;
create table ec_admin_extend
(
    admin_id         bigint  unsigned                                                comment '管理员ID' ,
    shortcut         text                                                            comment '快捷键信息',
    gmt_create       datetime           default CURRENT_TIMESTAMP                    comment '记录创建时间',
    gmt_modified     datetime           default CURRENT_TIMESTAMP                    comment '记录修改时间',
    PRIMARY KEY (admin_id)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8  COMMENT='管理员扩展信息表';


create table ec_admin_log
(
    id         bigint  unsigned        auto_increment                                comment 'id' ,
    content    varchar(50)                                           not null        comment '操作内容',
    createtime datetime                default CURRENT_TIMESTAMP     null            comment '发生时间',
    admin_name char(20)                                              not null        comment '管理员',
    admin_id   bigint  unsigned        default 0                     not null        comment '管理员ID',
    ip         char(15)                                              not null        comment 'IP',
    url        varchar(50)             default ''                    not null        comment 'act&op',
    PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8  COMMENT='管理员操作日志';


create table ec_admin_group
(
    group_id    int  auto_increment             not null                             comment '自增id' ,
    group_name  varchar(50)                                           null            comment '组名',
    limits text                                                       null            comment '权限内容',
    gmt_create       datetime           default CURRENT_TIMESTAMP                     comment '记录创建时间',
    gmt_modified     datetime           default CURRENT_TIMESTAMP                     comment '记录修改时间',
    PRIMARY KEY (group_id)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8  COMMENT='权限组';

-- 是系统初始化表
create table ec_admin_route
(
    id               int       auto_increment                                      not null        comment 'id'  ,
    cname            char(50)                                        not null        comment '中文名称',
    name             char(50)                                        not null        comment '名称，用来多语言标记',
    path             char(100)                                       not null        comment '前台的url路径，后台controller也通用',
    parent_id        int unsigned       default 0                    not null        comment '上个节点编号'   ,
    hide_in_menu     boolean            default false                not null        comment 'false：不需要在前台显示',
    auto_add         boolean            default false                not null        comment 'true：后台controller的路径，这个不出现在前台，并且在后台自动添加',
    PRIMARY KEY (id),
    UNIQUE KEY `uq_admin_route_path` (`path`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8  COMMENT='路由表';



insert into ec_admin(admin_id,admin_name,admin_password,admin_is_super)
  values(1,'admin','$2a$10$iWIebpXWvbLyu4jYaDthdOfGcuQ99IgQBTkizHvVn6YwO94qjN9vq',true);


-- 先定义一级与二级权限
insert into ec_admin_route values(1,'总览','welcome','/welcome',0,false,false);
insert into ec_admin_route values(2,'首页','welcome','/welcome/home',1,false,false);

-- 商品    分类管理   品牌管理   商品总览   类型管理   规格管理   图片空间
insert into ec_admin_route values(100,'商品','goods','/goods',0,false,false);
insert into ec_admin_route values(101,'分类管理','class','/goods/class',100,false,false);
insert into ec_admin_route values(102,'品牌管理','brand','/goods/brand',100,false,false);
insert into ec_admin_route values(103,'商品总览','list','/goods/list',100,false,false);
insert into ec_admin_route values(104,'类型管理','type','/goods/type',100,false,false);
insert into ec_admin_route values(105,'规格管理','spec','/goods/spec',100,false,false);
insert into ec_admin_route values(106,'图片空间','image','/goods/image',100,false,false);


--  内容    文章分类   文章管理   会员协议   导航管理   广告管理   首页管理   推荐位   商品推荐   专题管理

insert into ec_admin_route values(200,'内容','website','/website',0,false,false);
insert into ec_admin_route values(201,'文章分类','articleClass','/website/articleClass',200,false,false);
insert into ec_admin_route values(202,'文章管理','article','/website/article',200,false,false);
insert into ec_admin_route values(203,'会员协议','agreement','/website/agreement',200,false,false);
insert into ec_admin_route values(204,'导航管理','navigation','/website/navigation',200,false,false);
insert into ec_admin_route values(205,'广告管理','adv','/website/adv',200,false,false);
insert into ec_admin_route values(206,'首页管理','homepage','/website/homepage',200,false,false);
insert into ec_admin_route values(207,'推荐位','recPosition','/website/recPosition',200,false,false);
insert into ec_admin_route values(208,'商品推荐','recommend','/website/recommend',200,false,false);
insert into ec_admin_route values(209,'专题管理','special','/website/special',200,false,false);
