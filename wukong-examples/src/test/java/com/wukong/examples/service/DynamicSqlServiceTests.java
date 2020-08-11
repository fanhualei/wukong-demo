package com.wukong.examples.service;

import com.wukong.examples.model.StudentInfo;
import com.wukong.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DynamicSqlServiceTests extends AbstractTestNGSpringContextTests {

    @Autowired
    DynamicSqlService dynamicSqlService;

    /**
     * 演示如何平装select与where条件。
     * 平装select是用来少一点字段，我感觉用途不大。
     * 如果为了减少数据量，还不如返回一个Map List
     */
    @Test
    public void selectAllSqlTest(){
        List<StudentInfo> list = dynamicSqlService.selectAllSql();
        assertThat(list.size()).isGreaterThanOrEqualTo(0);
    }

    /**
     * 演示如何传入一个动态的where条件，这里使用了官方的拼装where用法
     */
    @Test
    public void selectByWhereTest(){
        List<StudentInfo> list = dynamicSqlService.selectByWhere();
        assertThat(list.size()).isGreaterThanOrEqualTo(0);
    }

    /**
     * 演示如何传入一个动态的where条件,返回的是Map list，这里使用了官方的拼装where用法
     */
    @Test
    public void selectByWhereMapTest(){
        List<Map> list = dynamicSqlService.selectByWhereMap();
        assertThat(list.size()).isGreaterThanOrEqualTo(0);
    }

    /**
     * 自己生成sqlTable与sqlColumn，然后同mybatis dynamic来拼装where条件。
     * 这样就将动态Sql语句做成了可能性，未来会大大简化代码。
     */
    @Test
    public void selectByWhereUseWkTest(){
        List<StudentInfo> list = dynamicSqlService.selectByWhereUseWk();
        assertThat(list.size()).isGreaterThanOrEqualTo(0);
    }
    /**
     * 模拟了如何从两个表中查询数据
     * 这里没有使用官方的方法，自己组装了SqlTable与SqlColumn对象，来动态拼装sql.
     */
    @Test
    public void selectByTowTableTest(){
        List<Map> list = dynamicSqlService.selectByTowTable();
        assertThat(list.size()).isGreaterThanOrEqualTo(0);
    }

    /**
     * 模拟只要sql语句写的对，就可以返回一个对象，大大简化了相关代码
     */
    @Test
    public void selectToObjectListTest(){
        List<User> list = dynamicSqlService.selectToObjectList();
        assertThat(list.size()).isGreaterThanOrEqualTo(0);
    }


}
