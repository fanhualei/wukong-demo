# 集成Flyway

Flayway是一款数据库版本控制管理工具，，支持数据库版本自动升级。

> 参考文档

* [Flyway说明及使用规范](https://blog.csdn.net/beautyofmath/article/details/106194433)
* [SpringBoot 集成数据库迁移工具 Flyway（基于Gradle构建）](https://blog.csdn.net/mgl_1/article/details/88393641)
* [flyway官网](https://flywaydb.org/)



# 1. 集成



## 1.1 引入插件和依赖

build.gradle

```groovy
plugins {
    id "org.springframework.boot" version "${springBootVersion}"
    id "io.spring.dependency-management" version "${springDependencyManagementVersion}"
    id "war"
    id "org.flywaydb.flyway" version "6.4.4"  // Flyway 插件
}

dependencies {
    // flyway
    implementation "org.flywaydb:flyway-core:6.4.4"
}
```



## 1.2 创建数据库

手工创建数据库

```sql
create database wk_example
```



> 也可以使用自动的方式创建数据库

有两种方法：

* 在springboot中撰写一个函数来创建。
* 在flyway连接的是sys数据库，然后在sql文件中创建数据库（不推荐）





## 1.3 编写配置文件

主要的内容有三个

* 添加`application-flyway.yml`文件
* 修改`application-flyway.yml`中的数据库配置
* 修改`application.yml` 追加flyway配置文件



> application.yml

```yml
active: sdb,flyway
```



>  application-flyway.yml

```yml
spring:
  flyway:
    url: jdbc:mysql://127.0.0.1:3306/wk_example?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
    user: root
    password: 123456
    driver: com.mysql.cj.jdbc.Driver
    locations: classpath:db/migration # 指定sql文件存放的目录
```



## 1.3 编写sql文件

文件需要放在db/migration目录下

V1__Create_student_table.sql

![](imgs/flyway-version.png)

- V 为固定前缀分隔符，代表数据库版本化；
- 2_1 为 SQL 脚本版本，’_’ 翻译为小数点，2_1 即为 2.1 版本；
- __为两个下划线，代表中间分隔符；
- init_request 为 SQL 脚本名，概述本脚本要进行的操作；
- .sql 为固定后缀。



## 1.4 启动项目

启动springboot后，会自动建立数据库表



## 1.5 其他

Flyway的其他参数配置

```yml
spring:
  flyway:
    baseline-version: 1 # 开始执行基准迁移时对现有的schema的版本打标签，默认值为1.
    baseline-description: first migration # 对执行迁移时基准版本的描述
    baseline-on-migrate: true # 当迁移时发现目标schema非空，而且带有没有元数据的表时，是否自动执行基准迁移，默认false.
    check-location: true # 检查迁移脚本的位置是否存在，默认false.
    clean-on-validation-error: false # 当发现校验错误时是否自动调用clean，默认false.
    encoding: UTF-8 # 设置迁移时的编码，默认UTF-8.
    ignore-future-migrations: true # 在读取模式历史记录表时是否忽略将来的迁移。
    init-sqls: # 当初始化好连接时要执行的SQL.
    out-of-order: false # 是否允许无序的迁移，默认false.
    placeholder-prefix: # 设置每个placeholder的前缀，默认${.
    placeholder-suffix: # 设置每个placeholder的后缀，默认}.
    schemas: # 设定需要flyway迁移的schema，大小写敏感，默认为连接默认的schema.
    sql-migration-prefix: # 迁移文件的前缀，默认为V.
    sql-migration-separator: # 迁移脚本的文件名分隔符，默认__
    sql-migration-suffixes: # 迁移脚本的后缀，默认为.sql
    table: # 使用的元数据表名，默认为schema_version
    validate-on-migrate: true # 迁移时是否校验，默认为true.
```

