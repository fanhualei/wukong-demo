例子工程说明
===



wukong-example 主要是为了测试`wukong-boot-core`与`wukong-boot-security`的主要功能。



# 1. 项目配置

生成项目后，不进行如下配置，`程序运行不了`。

## 1.1 未集成security

### ① 安装第三方工具

- [x] 安装mysql
- [x] 初始化空数据库

### ② 配置yml文件

> application.yml

- [x] upload-path 为了开发方便，配置到log下。



> application-sdb.yml

- [x] 配置数据库、用户名、密码



> application-flyway.yml

- [x] 配置数据库、用户名、密码



## 1.2 集成security

除了配置上述内容，还需要配置下面的内容

### ① 安装第三方工具

- [x] 安装redis

### ②  配置yml文件

> application.yml

- [x] 配置redis的密码，如果设置了密码



## 1.3 集成wukong-boot-testNg

由于生成代码中包含了测试的代码，所以也要把这个包引入。



## 1.4 配置日志



> logback-spring.xml

- [x] 在生产环境中配置log的目录，由于是测试环境，log的日志放在了一个不安区的地方。
- [x] 配置`<logger name="com.wukong.examples" level="debug"/>` 这样可以输出mybatis sql语句。



## 1.5 配置数据库

* 程序一连接数据库，如果发现database不存在会自动生成。
* 将sql语句放到flyway的默认目录中，程序启动后，会自动建立表结构。




# 2. 基本示例



## 2.1 基本API

HelloController.java

| URL                         | 输入   | 输出                      | 说明                     |
| --------------------------- | ------ | ------------------------- | ------------------------ |
| /example/hello              | null   | String                    | hello world              |
| /example/hello/info         | String | Map<String, String>       | 返回一个Map              |
| /example/hello/json         | null   | List<Map<String, String>> | 返回一个List             |
| /example/hello/logo         | null   | String                    | 与hello功能重复          |
| /example/hello/getCityList  | null   | List<City>                | 返回一个List对象         |
| /example/hello/getCityList1 | null   | ListResponseResult<City>  | 返回一个带分页的功能     |
| /example/hello/addCity      | City   | City                      | 往服务器提交一个Json对象 |
|                             |        |                           |                          |

*未来会使用新的例子，撰写关于文件上传和下载的例子。*



## 2.2 返回与异常处理

ResultController.java

| URL                       | 输入     | 输出   | 说明                                                         |
| ------------------------- | -------- | ------ | ------------------------------------------------------------ |
| /example/result/success   | null     | City   | 正常返回一个对象                                             |
| /example/result/para      | 多个参数 | String | 测试参数异常：@Email @Length                                 |
| /example/result/type      | Integer  | Object | 根据输入参数，返回不同的类型：<br> int string map list date exception. |
| /example/result/exception | Integer  | String | 根据输入参数，返回不同的类型：<br>RuntimeException <br/>NullPointerException <br/>NumberFormatException <br/>BusinessException(一般类型) <br/>BusinessException(模拟参数校验) <br/>BusinessException(字符串) <br/> |
|                           |          |        |                                                              |
|                           |          |        |                                                              |



## 2.3 参数校验

ValidatorController.java

关于各种参数校验，以及模拟拦截spring的校验结果

| URL                                                  | 输入           | 输出   | 说明                                                    |
| ---------------------------------------------------- | -------------- | ------ | ------------------------------------------------------- |
| /example/validator/para1 or para2                    | @Length @Email | Map    | 长度与Email的校验                                       |
| /example/validator/bean0 or bean1                    | City           | City   | 对定义在City对象中的校验规则进行校验.<br>这里模拟不校验 |
| /example/validator/bean2  or bean3                   | City           | City   | 对定义在City对象中的校验规则进行校验.<br/>模拟校验      |
| /example/validator/method1 or <br>method2<br>method3 | BindingResult  | Object | 模拟拦截spring的校验结果，让自己的程序处理错误。        |
| /example/validator/phone                             | User           | String | 针对对象中的电话号码进行测试                            |
| /example/validator/phone2                            | String         | String | 对传入参数的电话号码进行测试。                          |
|                                                      |                |        |                                                         |
|                                                      |                |        |                                                         |
|                                                      |                |        |                                                         |



## 2.4 数据库操作



### 2.4.1 自动生成的代码

CourseController.java



| URL                                 | 输入                                            | 输出               | 说明                     |
| ----------------------------------- | ----------------------------------------------- | ------------------ | ------------------------ |
| /example/course/count               |                                                 | Long               | 得到总记录数             |
| /example/course/insert              | Course                                          | Course             | insert                   |
| /example/course/selectByPK          | courseId                                        | Course             | 查询一条记录             |
| /example/course/updateByPK          | courseId                                        | int                | 更新一条记录             |
| /example/course/deleteByPK          | courseId                                        | int                | 删除一条记录             |
| /example/course/selectAll           | 可变参数                                        | List<Course>       | 查询全部记录             |
| /example/course/selectAllByPage     | Integer pageNum<br/>Integer pageSize<br/>       | List<Course>       | 按照分页查询全部记录     |
| /example/course/selectByWhere       | 可变参数                                        | List<Course>       | 按照条件查询全部记录     |
| /example/course/selectByWhereByPage | Integer pageNum<br>Integer pageSize<br>可变参数 | ListResponseResult | 按照条件分页查询全部记录 |
|                                     |                                                 |                    |                          |
|                                     |                                                 |                    |                          |



### 2.4.2 动态拼装Sql语句

DynamicSqlController.java

2.5章节中描述到，可以从前台动态传入一些参数，这样可以自动拼接成sql语句。具体如下：

```
当前只实现了单表的Sql拼接。
未来会实现多表的例子。
```



> **前台传递参数说明**

例如：where_studentId=123&where_studentName_like=%tom%&orderBy=studentName,studentSex desc

* where分为三段
  * 第一段：必须用where开头
  * 第二段：字段名，中间用驼峰形式的属性名，区分大小写，系统自动转成字段名。
  * 第三段：对比条件，如果为空表示=
* 排序
  * 用orderBy开头
  * 用驼峰的形式写字段名，并且会按照字符串的形式传入后台。





> controller接受

调用RequestAnalysis，自动解析出request中的查询条件

调用whereBuilder，进行sql语句的拼装。

```java
    @RequestMapping("/hello")
    public List<StudentInfo> hello() {
        HttpServletRequest request=  RequestContextHolderUtil.getRequest();
        SqlMaps sqlMaps= RequestAnalysis.analysis(request);
        WhereObject sqlObject=whereBuilder.build("wk_student_info",sqlMaps);
        List<StudentInfo>  list =dynamicSqlService.selectByWhere(sqlObject.getWhereDSL(),sqlObject.getOrder());
        return list;
    }
```





> 支持的查询



| 标记     | sql         | 说明          |
| -------- | ----------- | ------------- |
| equal    | =           |               |
| nequal   | !=          |               |
| greater  | >           |               |
| egreater | =>          |               |
| less     | <           |               |
| eless    | =<          |               |
| in       | in          | 按照,进行分割 |
| nin      | not in      | 按照,进行分割 |
| between  | between     | 按照,进行分割 |
| nbetween | not between | 按照,进行分割 |
| like     | liken       | 需要自己添加% |
| nlike    | not like    | 需要自己添加% |
| isnull   | is null     | 忽略value     |
| nisnull  | is not null | 忽略value     |



### 2.4.3 事物处理

TransactService.java

> 具体原则

* 在可能出现错误的service函数上添加：`@Transactional`
* 添加了`@Transactional`无论调用dao或者其他service，只要有错误抛出，就进行事物回滚。
* 默认情况下，不再service上添加`@Transactional`，只在需要事物处理的地方，添加`@Transactional`





### 2.4.4  读写分离与分库



### 2.4.5 自动记录修改时间

建议每个表都添加下面两个字段：

```sql
  gmt_create    DATETIME                DEFAULT CURRENT_TIMESTAMP NULL   COMMENT '记录创建时间',
  gmt_modified  DATETIME                DEFAULT CURRENT_TIMESTAMP NULL   COMMENT '记录修改时间',
```



在配置文件中可以配置是否启用自动填充功能，以及要填充的字段。

```yml
wukong:
    auto-fill-date-column:
        enabled: true
        columns: gmt_modified,gmt_test
```





## 2.5 文件上传



| URL                 | 输入          | 输出   | 说明                       |
| ------------------- | ------------- | ------ | -------------------------- |
| /hello/upload       | MultipartFile | String | 上传文件例子               |
| /hello/upload/batch | request       | String | 上传多个文件               |
| /hello/delFile      | fileName      | String | 输入一个文件名，进行删除。 |
| /hello/download     | response      | String | 下载文件                   |



> 下载文件

[基于Spring Boot 2.2.6实现Rest风格的文件上传&下载APIs-附源码](https://www.fususu.com.cn/blog/article/6)



> 演示例子

download不能包含imgs，因为这个是根目录，系统会自动加上

http://127.0.0.1:8080/hello/download?filename=/examples/上传文件2.txt



> 通过静态文件，不通过controller来下载文件

http://127.0.0.1:8080/imgs/examples/上传文件2.txt











# 3 单元测试与文档生成



## 3.1 单元测试

在gradle中可以控制是否启用单元测试

```groovy
test {
//    enabled=false
    outputs.dir snippetsDir
    useTestNG()
}
```



尽量别取消，因为打包的是否，引用了这个功能。



测试完毕后，可以在这里看到测试结果：

`build/reports/tests/test/index.html`



## 3.2 文档生成



### 3.2.1 swagger

配置是否启用，以及需要的类

```yml
wukong:
    swagger:
        enable: true
        basePackage: com.wukong.examples.controller
```



### 3.2.2 Spring rest docs

Spring Rest Docs好像是鸡肋。

* 第一步：利用Spring rest docs生成文件放入："build/generated-snippets"
* 第二步：在src\docs\asciidoc\ 建立index.adoc
* 第三步：将形成的"build/generated-snippets"合并，这里使用了`genApiAsciidoc`
* 第四步：使用id "org.asciidoctor.jvm.convert" version "3.1.0" ，来转换成index.html ，使用了asciidoctor



实际上没有那么复杂，只用build就可以了，然后打开index.html，就能看到文档了。

```groovy

plugins {
    id "org.springframework.boot" version "${springBootVersion}"
    id "io.spring.dependency-management" version "${springDependencyManagementVersion}"
    id "org.flywaydb.flyway" version "${flywayVersion}"            // Flyway 插件
    id "org.asciidoctor.jvm.convert" version "3.1.0"  // 转换插件
    id "war"
}

description = "wukong-examples"

dependencies {
    implementation project(":wukong-boot:wukong-boot-core")
    implementation project(":wukong-boot:wukong-boot-security")

    //数据库版本控制
    implementation "org.flywaydb:flyway-core:${flywayVersion}"
    //------------------test------------------------
    testImplementation project(":wukong-boot:wukong-boot-testNg")
}

ext {
    set('snippetsDir', file("build/generated-snippets"))
}


test {
//    enabled=false
    outputs.dir snippetsDir
    useTestNG()
}

//会自动拼接build/generated-snippets下的文件 到build/generated-snippets/aip.adoc
//赖test生成generated-snippets
task genApiAsciidoc(type: JavaExec) {
    dependsOn test
    description '合并asciidoc文章片段'
    classpath sourceSets.test.runtimeClasspath
    main = 'com.wukong.testNg.GenAipDoc'
    args snippetsDir.getPath()
}

//生成index.html
asciidoctor {
    baseDir(snippetsDir)
    dependsOn genApiAsciidoc
}

//打包的时候生成测试文件，也可以打倒webapp下的static
bootWar {
    dependsOn asciidoctor
}

```



> 参考文档

* [Spring Rest Docs WebTestClient自动生成接口文档Gradle版---介绍了模板与汇总](https://www.jianshu.com/p/977a591c8d82)
* [提交了一种汇总的方法，感觉不成熟](https://blog.csdn.net/a87922072/article/details/77164111)
* [使用gradle来生成pdf](https://www.liangzl.com/get-article-detail-139372.html)
* [SpringBoot项目生成RESTfull API的文档](https://www.jianshu.com/p/af7a6f29bf4f?wm=14010)



> Spring官方

* [rest docs说明](https://docs.spring.io/spring-restdocs/docs/2.0.x/reference/html5/)

- [AsciiDoc 语法快速参考（中文版）](https://asciidoctor.cn/docs/asciidoc-syntax-quick-reference/)
- [AsciiDoc 官网](https://asciidoctor.org/)
- [gradle插件](https://asciidoctor.org/docs/asciidoctor-gradle-plugin/)



# 4. 第三方组件说明



## 4.1 集成Flyway

Flyway是一个数据库版本控制的功能，由于`Example`中用到了数据库，所以这么集成这么一个功能。

[具体内容看Flyway集成文档](reference/flyway.md)



## 4.2 Mybatis Dynamic Sql 使用

[官方使用说明](https://mybatis.org/mybatis-dynamic-sql/docs/introduction.html)



> 单表

* insert 
  * 单条
  * 多条
* update（附带更新一些字段）
  * 按主键
  * 按条件
* delete
  * 按主键
  * 按条件
* select
  * 按主键
  * 按条件（附带排序与分页）



> 多表

多表，建议就写sql语句吧。

关键问题是，返回的对象，到底是Map 还是对象。 如果是对象，那么怎么往对象中添加数值。









