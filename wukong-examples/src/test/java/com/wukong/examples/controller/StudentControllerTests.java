package com.wukong.examples.controller;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wukong.examples.model.Course;
import com.wukong.examples.model.Student;
import com.wukong.examples.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * 学生类测试函数
 *
 * @author ljy
 * @date 2018/6/7
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StudentService studentService;

    private final String baseUrl="/example/student/";

    @Test(priority = 0)
    public void testAdd(){
        String url=baseUrl+"add";

        /**
         *HTTP头文件
         */
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);

        String testName = "小白";
        /**
         *定义一个测试用发送的student实体
         */
        Student student = new Student();
        student.setStudentName(testName);
        student.setStudentSex(1);
        student.setStudentAge(25);

        /**
         *HTTP发送student实体
         *和接收返回的信息
         */
        for(int i = 1; i <= 5; i++) {
            student.setStudentName(testName+i);
            HttpEntity<Student> formEntity = new HttpEntity<Student>(student, headers);
            ResponseEntity<Long> result = this.restTemplate.postForEntity(url, formEntity, Long.class);
            /**
             *返回结果小于0表示出现错误
             */
            assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(result.getBody()).isGreaterThanOrEqualTo(0L);
        }
    }

    @Test(priority = 2)
    public void testSelectAll() throws JsonProcessingException {
        String url=baseUrl+"selectAll";

        /**
         *HTTP
         */
        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        String jsonArrayStr=entity.getBody();

        ObjectMapper mapper=new ObjectMapper();
        JavaType javaType =  mapper.getTypeFactory().constructParametricType(List.class, Student.class);
        List<Student> studentList =  mapper.readValue(jsonArrayStr,javaType);

        /**
         *数据库读取所有学生List信息
         *并对比两个List数组的id信息（只对比List中的Student实体会因为hash不同出错）
         */
        List<Student> studentsFromMysql = studentService.selectByExample(null);
        for(int i=1;i<studentsFromMysql.size();i++){
            assertThat(studentList.get(i).getStudentId()).isEqualTo(studentsFromMysql.get(i).getStudentId());
        }

    }

    @Test(priority = 3)
    public void testDeleteByName() {
        String url=baseUrl+"deleteByName";

        String testName = "小白";

        /**
         *HTTP发送测试信息testName,并得到返回结果
         */
        for(int i = 1; i <= 5; i++) {
            MultiValueMap<String, String> paraMap = new LinkedMultiValueMap<String, String>();
            paraMap.add("studentName", testName+i);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            HttpEntity<MultiValueMap<String, String>> request
                    = new HttpEntity<MultiValueMap<String, String>>(paraMap, headers);
            ResponseEntity<Integer> result = this.restTemplate.postForEntity(url, request, Integer.class);

            /**
             *返回结果小于0表示出现错误
             */
            assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(result.getBody()).isGreaterThan(0);
        }
    }

    @Test(priority = 1)
    public void testUpdate() {
        String url=baseUrl+"update";

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);

        String testName = "小白1";
        /**
         *定义一个测试用发送的student实体
         */
        Student student = new Student();
        student.setStudentName(testName);
        student.setStudentSex(0);
        student.setStudentAge(20);

        /**
         *HTTP发送student实体
         *并接受返回的信息
         *返回结果为负数表示出现错误
         */
        HttpEntity<Student> formEntity = new HttpEntity<Student>(student, headers);
        ResponseEntity<Integer> result = this.restTemplate.postForEntity(url, formEntity,Integer.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(result.getBody()).isGreaterThanOrEqualTo(0);
    }
}
