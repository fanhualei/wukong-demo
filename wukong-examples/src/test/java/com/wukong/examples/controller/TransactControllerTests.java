//package com.wukong.examples.controller;
//
//
//import com.wukong.examples.model.Course;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.*;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.testng.annotations.Test;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SuppressWarnings("unchecked")
//public class TransactControllerTests extends AbstractTestNGSpringContextTests {
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    private final String baseUrl="/example/course";
//    private final String transactBaseUrl="/example/transact";
//
//
//    /**
//     * 测试调用1个dao,然后抛出错误，这是否数据库回滚
//     */
//    @Test(priority = 1)
//    public void oneTable()  {
//        Long courseId=123L;
//        String url=transactBaseUrl+ "/oneTable?courseId="+courseId;
//        ResponseEntity<String> result = this.restTemplate.getForEntity(url,String.class);
//        assertThat(result.getStatusCodeValue()).isEqualTo(600);
//
//        //查看是否添加了
//        ifInserted(courseId);
//    }
//
//    /**
//     * 测试调用2个dao,然后抛出错误，这是否数据库回滚
//     */
//    @Test(priority = 1)
//    public void twoTable()  {
//        Long courseId=123L;
//        String url=transactBaseUrl+ "/twoTable?courseId="+courseId;
//        ResponseEntity<String> result = this.restTemplate.getForEntity(url,String.class);
//        assertThat(result.getStatusCodeValue()).isEqualTo(600);
//        //查看是否添加了
//        ifInserted(courseId);
//    }
//
//    /**
//     * 测试调用2个service,然后抛出错误，这是否数据库回滚
//     */
//    @Test(priority = 1)
//    public void otherService()  {
//        Long courseId=123L;
//        String url=transactBaseUrl+ "/otherService?courseId="+courseId;
//        ResponseEntity<String> result = this.restTemplate.getForEntity(url,String.class);
//        assertThat(result.getStatusCodeValue()).isEqualTo(600);
//        //查看是否添加了
//        ifInserted(courseId);
//    }
//
//
//    private void ifInserted(Long courseId){
//        //查询数据
//        String url=baseUrl+ "/selectByPK?courseId="+courseId;
//        ResponseEntity<Course> entity = this.restTemplate.getForEntity(url, Course.class);
//        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        Course course=entity.getBody();
//        assertThat(course).isNull();
//    }
//
//}
