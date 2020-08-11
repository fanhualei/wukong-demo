package com.wukong.examples.service;

import com.google.common.collect.Lists;
import com.wukong.core.mybatis.dynamic.mapper.CommonMapper;
import com.wukong.security.mapper.UserDao;
import com.wukong.security.mapper.base.UserDynamicSqlSupport;
import static com.wukong.security.mapper.base.UserDynamicSqlSupport.*;
import com.wukong.security.mapper.base.UserExDynamicSqlSupport;
import static com.wukong.security.mapper.base.UserExDynamicSqlSupport.*;

import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
// SqlBuilder is very useful
import java.util.*;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

@SpringBootTest
@SuppressWarnings("all")
public class JionSelectTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CommonMapper commonMapper;


    /**
     * 测试User查询的功能
     */
    @Test
    public void selectUserTest(){
        userDao.select(c->c).forEach(System.out::println);
    }

    /**
     * 两个表Json查询出List
     */
    @Test
    public void selectManyTest(){
        List<BasicColumn> columns =Lists.newArrayList(userDao.selectList);
        columns.add(nickname);

        SelectStatementProvider selectStatement = select(columns)
                .from(user)
                .join(userEx).on(user.userId,equalTo(userEx.userId))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        System.out.println(selectStatement.getSelectStatement());
        List<Map> aaa = commonMapper.selectMany(selectStatement);
        System.out.println("ddddddddddddddddd");
    }

    /**
     * 两个表Json查询出一条记录
     */
    @Test
    public void selectOneTest(){

        List<BasicColumn> columns =Lists.newArrayList(userDao.selectList);
        columns.add(nickname);

        SelectStatementProvider selectStatement = select(columns)
                .from(user)
                .join(userEx).on(user.userId,equalTo(userEx.userId))
                .where(UserExDynamicSqlSupport.userId,isEqualTo(1L))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        System.out.println(selectStatement.getSelectStatement());
        Optional<Map>  one = commonMapper.selectOne(selectStatement);
        System.out.println(one.get());
    }

}
