package com.wukong.examples.service;

import com.wukong.core.util.IdUtil;
import com.wukong.examples.model.Course;
import com.wukong.security.model.User;
import com.wukong.security.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@SpringBootTest
@SuppressWarnings("unchecked")
public class SnowFlakeKeyTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @SuppressWarnings("all")
    private CourseService courseService;

    @Autowired
    @SuppressWarnings("all")
    private UserService userService;

    //@Test(priority = 1)
    public void test1Insert() {
        Long snowId=IdUtil.getSnowId();
        Long snowId2=IdUtil.getSnowId();
        Course course = new Course();
        String courseName= RandomStringUtils.randomAlphanumeric(10);
        course.setCourseId(snowId);
        course.setCourseName(courseName);
        courseService.insertSelective(course);
        System.out.println(course.toString());
    }


}
