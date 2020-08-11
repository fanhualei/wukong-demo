wukong-framework 2.0.x
===

`license:LGPL` `springboot2.0` `springsecurity` `testng`  `mybatis` `jwt` `https` `redis` `gitlab` 



## 快速掌握



* [更新日志](reference/changelog.md)    
  
    * [2.0.0版本主要功能](reference/v2-0-0.md)
    
    
    
* 环境配置
    * 开发环境
        * [开发环境配置](reference/readme.md "开打环境配置文档")
        * [ubuntu20安装说明](ubuntu-usage/ubuntu-install.md) 
        * [数据库文档生成](wukong-tools/wukong-dbdoc/readme.md)
        * 第三方工具
          * [一键安装mysql redis rabbitmq脚本](docker/readme.md)
          * [一键安装mysql主从](docker/ms/readme.md)
          *  [私有maven服务器](reference/nexus.md)
    * 服务器
        * [服务器环境配置](reference/webEnvironment.md "开打环境配置文档")
        * [Https配置](reference/https.md)
        * [服务器安全配置](reference/webSecurity.md)
        * [部署到服务器上](reference/ci.md "部署到服务器上")
        
        
    
* 基本开发

    * [开发流程](reference/myfirst_controller.md)
    * [完整的功能示例](wukong-projects/wukong-examples/readme.md)
    * [debug技巧](reference/debug.md)


* 撰写数据库相关代码    
    * [动态Sql+事物+读写分离](wukong-projects/wukong-examples/readme.md)
    * [快速上手](reference/mybitsDynamic-qt.md)
    * [数据库设计10条规范](reference/mysql-design.md)
    * [主键生成器](reference/keygen.md)
    * 其他
        * [Mysql使用](reference/mysql.md)
        * [如何撰写数据库操作-老版本](reference/database.md)
        * [Mysql对json字段的查询](https://www.2cto.com/database/201708/664660.html)

  

* API接口开发
    * [基本API用法](reference/controller.md)
    * [返回格式及异常处理](reference/result.md)
    * [数据校验](reference/validator.md)
    * [配置访问权限](reference/controller.md#配置访问权限)
    * [i18n多语言](reference/i18n.md)
    * [中文乱码的问题](reference/encoding.md)
    * [HTTP状态码对照表](http://tools.jb51.net/table/http_status_code)
    



* 网络爬虫
    * [集成百度语音接口](wukong-projects/wukong-hanzi/reference/baidu-audio.md)
    * [crawler4j网络爬虫](wukong-projects/wukong-hanzi/reference/crawler4j.md)
    * [jsoupHtml解析](wukong-projects/wukong-hanzi/reference/jsoup.md)
    * [selenium-js网络爬虫](wukong-projects/wukong-hanzi/reference/selenium.md)
    * [调用chrome抓图](wukong-projects/wukong-screenshot/readme.md)



* 使用微信
   * [基本使用方法](wukong-projects/wukong-hanzi/reference/weixin.md)



* 单元测试
    * [如何使用TestNG测试](reference/testng.md)
    * [如何使用postMan测试](reference/postman.md)
    * [如何使用swagger进行测试](reference/swagger2.md)
    * [如何使用mock进行测试](reference/mock.md)
    
    


* 安全
    * [security功能说明](reference/security.md)
    * [如何配置权限](reference/security-config.md)
    * [防止重复提交](reference/preventrepeat.md)
    * [关闭与开启security认证](reference/tip.md) `见·关闭security认证·小节`
    * [多用户表情况](reference/security-multi-user.md)
    
    
    
* gradle

    * [gradle 使用说明](reference/ubuntu-usage/gradle.md)
    * [gradle 学习](reference/ubuntu-usage/gradle-study.md)
    * [开发gradle插件](reference/gradle-plugin.md)
    
    
    
* 规范
    * 开发人员的规范
        * [java开发规范](reference/specification_java.md)
        * [记录log规范](reference/uselog.md)
        * [git提交代码规范](reference/specification_git.md)
        * [redis-key命名规范](reference/redis.md#redis-key命名规范)  
        * [controller与service命名规范](reference/result.md#悟空框架api返回规范)
        * [Java与Mysql数据对应关系](reference/mysql_java.md)
        * [alibaba Idea代码检查插件安装](https://github.com/alibaba/p3c/blob/master/idea-plugin/README_cn.md)
    * 配置文件汇总
        * [系统属性命名规范](reference/specification_properties.md)
        * [多语言配置](reference/specification_language.md)   
        * [redis-key配置](reference/specification_reids.md)           
    * 第三方工具使用    
        * [Apache Commons工具库](reference/ApacheCommons.md)
        * [google guava工具库](reference/guava.md)
    * java8的新功能    
        * [JAVA lambda使用](http://www.jdon.com/idea/java/10-example-of-lambda-expressions-in-java8.html)
        * [JAVA Streams](reference/java_streams.md)
        * [使用 Java8 Optional 的正确姿势](http://www.importnew.com/22060.html)
* [Java中SPI机制](https://www.jianshu.com/p/46b42f7f593c)
        
  
* 服务器部署人员的规范
    * [代码分支规范](reference/gitlab_branch.md)
    * [服务器端口规范](reference/specification_server.md#端口规范)
    * [docker命名规范](reference/specification_server.md#docker命名规范)
    * [tomcat工程目录规范](reference/specification_server.md#tomcat工程目录规范])
    
        
    
* 持续集成
    * [使用gitLab提交代码](reference/gitlab.md)
    * [gitlab niginx方向代理实例](reference/gitlab_apache.md)
    * [gitlab常用命令及运维](reference/gitlab_opt.md)
    
    


* docker使用
    * [docker基本用法](reference/docker.md)
    * [dockerQ&A](reference/docker_qa.md)
    
    
    
* linux常用技巧
    * [linux常用命令](reference/cmd.md)
    * [ssh免密码登录](reference/cmd.md#ssh免密码登录)
    
    
    
* tomcat使用技巧
    * [判断tomcat是否启动](reference/tomcat.md#判断tomcat是否启动)
    * [tomcat添加成自启动](reference/tomcat.md#tomcat自启动)
    * [热加载](reference/tomcat.md#热加载)   
    * [其他](reference/tomcat.md)    
    



* 其他
    * 组件使用
        * [如何使用Redis缓存](reference/redis.md)
        * [websocket开发](reference/websocket.md)
        * [rabbitMq使用](reference/rabbitmq.md)
        * [mqtt](reference/mqtt_design.md)
        * [如何使用elasticsearch](reference/elasticsearch.md)
    * 开发技巧
        * [SpringBoot使用小技巧](reference/tip.md)
        * [bugs一些错误](reference/bugs.md)
        * [maven 使用技巧](reference/maven.md)
        * [shell脚本相关技巧](reference/sh.md)
        * [地图相关信息](reference/map.md)
        * [Springboot idea调试热部署--redis冲突](https://blog.csdn.net/u013042707/article/details/78648259)
        * [注解用法详解——@SuppressWarnings](https://www.cnblogs.com/fsjohnhuang/p/4040785.html)
    * 其他的其他
        * [一张图看懂开源许可协议,开源许可证GPL、BSD、MIT、Mozilla、Apache和LGPL的区别](https://blog.csdn.net/testcs_dn/article/details/38496107)
        * [提问的智慧](http://www.dianbo.org/9238/stone/tiwendezhihui.htm)
        * [通用缩写表](https://blog.csdn.net/liu_yude/article/details/45317307)
        * [甲醛传感器的开发思路](reference/hcho_websocket_mqtt.md)



​    


