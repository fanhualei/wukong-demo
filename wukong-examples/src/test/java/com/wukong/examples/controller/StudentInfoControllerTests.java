package com.wukong.examples.controller;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wukong.core.controller.dao.SelectMapper;
import com.wukong.core.result.Pagination;
import com.wukong.examples.entity.City;
import com.wukong.examples.model.StudentInfo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.commons.beanutils.BeanUtils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author fan
 * testNg has a bug:  priority is not good work
 * testNg的bug，所以如果按照顺序来执行，那么每个函数的名字必须要用数字开头，这样能排序。
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SuppressWarnings("unchecked")
public class StudentInfoControllerTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String baseUrl="/example/studentInfo";

    @Autowired
    @SuppressWarnings("all")
    SelectMapper selectMapper;



    @BeforeClass
    public void clearData() {
        String sql="delete from wk_student_info";
        selectMapper.select(sql);
        System.out.println("clean the  table wk_student_info");
    }


    private List<StudentInfo> getMockData(){
        URL path=StudentInfoControllerTests.class.getResource("/");
        File jsonFile = new File(path.getPath()+"mock/StudentInfo.json");
        if(!jsonFile.exists()){
            throw new RuntimeException("没有找到：mock/StudentInfo.json");
        }
        String jsonStr ;
        try{
            jsonStr= FileUtils.readFileToString(jsonFile,"UTF-8");

            ObjectMapper mapper=new ObjectMapper();
            JavaType javaType =  mapper.getTypeFactory().constructParametricType(List.class, StudentInfo.class);
            return mapper.readValue(jsonStr,javaType);

        }catch (Exception e){
            throw new RuntimeException(e.toString());
        }
    }

    @Test(priority = 1)
    public void test1Insert()  {
        String url=baseUrl+ "/insert";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        List<StudentInfo> list = getMockData();
        for (StudentInfo studentInfo:list) {
            HttpEntity<StudentInfo> formEntity = new HttpEntity<>(studentInfo, headers);
            ResponseEntity<Integer> result = this.restTemplate.postForEntity(url, formEntity, Integer.class);
            assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(result.getBody()).isGreaterThanOrEqualTo(0);
        }
    }



    @Test(priority = 2)
    public void test2SelectByPK() {
        String url=baseUrl+ "/selectByPK?studentInfoId=1";
        ResponseEntity<StudentInfo> entity = this.restTemplate.getForEntity(url, StudentInfo.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        StudentInfo studentInfo=entity.getBody();
        assert studentInfo != null;
    }

    @Test(priority = 3)
    public void test3UpdateByPK() {
        String url=baseUrl+ "/updateByPK";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);


        List<StudentInfo> list=  getMockData();
        StudentInfo studentInfo=list.get(1);

        HttpEntity<StudentInfo> formEntity = new HttpEntity<>(studentInfo, headers);
        ResponseEntity<Integer> result = this.restTemplate.postForEntity(url, formEntity, Integer.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isGreaterThan(0);
    }




    @Test(priority = 4)
    public void test4Count() {
        String url=baseUrl+ "/count";
        ResponseEntity<Long> entity = this.restTemplate.getForEntity(url, Long.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo(35L);
    }

    @Test(priority = 5)
    public void test5SelectAll() throws InvocationTargetException, IllegalAccessException {
        String url=baseUrl+ "/selectAll";
        ResponseEntity<List> entity = this.restTemplate
                .getForEntity(url, List.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        long i=1L;
        for(Object obj: Objects.requireNonNull(entity.getBody())){
            StudentInfo studentInfo=new StudentInfo();
            BeanUtils.populate(studentInfo,(Map)obj);
            Long studentInfoId=studentInfo.getStudentInfoId();
            assertThat(studentInfoId).isEqualTo(i);
            i=i+1;
        }
    }

    @Test(priority = 6)
    public void test6SelectAllByPage() throws InvocationTargetException, IllegalAccessException {
        String url=baseUrl+ "/selectAllByPage?pageNum=2&pageSize=10";
        ResponseEntity<Object> entity = this.restTemplate
                .getForEntity(url, Object.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Map resultMap =(Map)entity.getBody();
        assert resultMap != null;
        Map pageMap = (Map) resultMap.get("pagination");
        Pagination pagination = new Pagination();
        BeanUtils.populate(pagination,pageMap);
        assertThat(pagination.getTotal()).isEqualTo(35);
        assertThat(pagination.getTotalPages()).isEqualTo(4);

        List list = (List) resultMap.get("list");
        int i=11;
        for(Object obj:list){
            StudentInfo studentInfo=new StudentInfo();
            BeanUtils.populate(studentInfo,(Map)obj);
            i=i+1;
        }
    }

    @Test(priority = 7)
    public void test7DeleteByPK() {
        String url=baseUrl+ "/deleteByPK?studentInfoId=33";
        ResponseEntity<Integer> entity = this.restTemplate.getForEntity(url, Integer.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(entity.getBody()).intValue()).isGreaterThan(0);
    }


}
