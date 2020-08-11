# 基本开发流程


> 目录

[TOC]





<br>

# 1. 需求分析

需要完成的工作：

* 用例描述
* 制作原型



# 2. 撰写Sql语句



## 2.1 基本方法

* 定义主键
    * 要加上表明
    * 预计记录超过1亿，那么组建用BIGINT
* 新加与变更字段
    * gmt_create
    * gmt_modified
* 定义唯一索引(可选)
* 定义索引(可选)
* 所有索引必须加上表明

> 示例


```sql
DROP TABLE IF EXISTS wk_student;

create table wk_student (
  student_id     BIGINT unsigned       NOT NULL   AUTO_INCREMENT    COMMENT '学生id',
  student_name   varchar(255)          NOT NULL                     COMMENT '学生姓名',
  student_age    int                                                COMMENT '学生年龄',
  student_sex    int                                                COMMENT '学生性别',
  gmt_create    DATETIME                DEFAULT CURRENT_TIMESTAMP NULL   COMMENT '记录创建时间',
  gmt_modified  DATETIME                DEFAULT CURRENT_TIMESTAMP NULL   COMMENT '记录修改时间',
  PRIMARY KEY (student_id),
  UNIQUE KEY `wk_student_unique` (`student_name`)  
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='学生表';
```



## 2.2 进行测试

先在一个测试库上测试一下是否有语法错误。





## 2.3 追加flyway

如果在项目中使用了flyway的版本控制，那么还要麻烦一点。文件需要放在db/migration目录下

**V1__Create_student_table.sql**

![](imgs/flyway-version.png)

- V 为固定前缀分隔符，代表数据库版本化；
- 2_1 为 SQL 脚本版本，’_’ 翻译为小数点，2_1 即为 2.1 版本；
- __为两个下划线，代表中间分隔符；
- init_request 为 SQL 脚本名，概述本脚本要进行的操作；
- .sql 为固定后缀。



# 3. 生成代码

建议使用`wukong-generator2`来生成代码，如果是V1.0的项目，可以使用`wukong-generator`生成代码。

生成的代码有：

* bean
* dao
* service
* constroller
* test
* mock.json



[详细的使用可以参考](wukong-projects/wukong-examples/readme.md)



## 3.1 修改配置文件

修改`config.properties`，主要修改的内容如下：

* 要连接的数据库
* 要生成的表
* 要生成的包



## 3.2 执行生成

在`idea的Terminal中`执行：

```sh
#执行下面的命令
./gradlew gen2
```

生成的文件在`log`，系统会先删除上次生成的代码，避免重复生成。

生成完毕后，建议去`log`目录中检查一下代码。



## 3.3 测试代码

将生成的代码复制到项目中，执行`build`，在代码中有自带的单元测试脚本，如果通过就表示脚本没有问题。



# 4. 追加新功能

尽量不要修改自动生成的代码

## 4.1 复杂Sql查询

* 动态Sql语句
* 修改`mapper`中的`dao`

[参考代码：wukong-examples/src/main/java/com/wukong/examples/mapper/StudentInfoDao.java](../wukong-examples/src/main/java/com/wukong/examples/mapper/StudentInfoDao.java)



## 4.2 其他注意事项

建议每追加一个controller的函数，就追加了测试方法，提高测试覆盖率。







# 5. 代码归档

commit代码，并且push到服务器上。