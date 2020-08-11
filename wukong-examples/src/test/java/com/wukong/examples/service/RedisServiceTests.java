package com.wukong.examples.service;

import com.wukong.examples.model.Course;
import com.wukong.testNg.AbstractWkBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SuppressWarnings("unchecked")
public class RedisServiceTests extends AbstractWkBaseController {
    @Autowired
    private RedisService redisService;


    private String key="wukong:example:test";
    private String value="hello redis";

    @Test
    public void test1SetValue(){
        redisService.setValue(key,value);
    }


    @Test
    public void test2GetValue(){
        assertThat(redisService.getValue(key)).isEqualTo(value);
    }

    @Test
    public void test3GetCourseList(){
        redisService.getCourseList();
        List<Course> list= redisService.getCourseList();
        list.forEach(System.out::println);
    }

    @Test
    public void test4ShowAll(){
        Map map=redisService.showAll("*");
        map.forEach((k,v)->System.out.println(key+":"+v.toString()));

    }

    @Test
    public void test5Delete(){
        redisService.delete(key);
    }

}
