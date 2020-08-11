
package com.wukong.examples.controller;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wukong.examples.entity.City;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.testng.annotations.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;


import java.util.List;
import java.util.Map;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.map;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SuppressWarnings("unchecked")
public class HelloControllerTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String baseUrl="/example/hello/";


    /**
     * 测试没有登陆后，访问/security/hello,提示403
     */
    @Test
    public void testSecurityHello() {
        String url="/security/hello";
        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }


    @Test
    public void testHello() {
        String url="/example/hello";
        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo("Hello World");
    }


    @Test
    public void testInfoByGet() {
        String url=baseUrl+"info?name=张三";
        Map<String, String> map = this.restTemplate.getForObject(url,Map.class);
        String value=map.get("name");
        assertThat(value).isEqualTo("张三");
    }


    @Test
    public void testJson() {
        String url=baseUrl+"json";
        List<Map<String, String>> list = this.restTemplate.getForObject(url,List.class);

        for(int i=0;i<list.size();i++){
            Map<String, String> map =list.get(i);
            String value=map.get("name");
            assertThat(value).isEqualTo("Shanhy-"+(i+1));
        }
    }


    /**
     * post a para
     */
    @Test
    public void testInfoByPost() {
        String url=baseUrl+"info";

        MultiValueMap<String, String> paraMap= new LinkedMultiValueMap<String, String>();
        paraMap.add("name", "张三");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request
                = new HttpEntity<MultiValueMap<String, String>>(paraMap, headers);

        ResponseEntity<Map> entity = this.restTemplate.postForEntity(url, request,Map.class);

        Map<String, String> returnMap=entity.getBody();
        String value=returnMap.get("name");
        assertThat(value).isEqualTo("张三");
    }


    @Test
    public void testLogo() {
        String url=baseUrl+"logo";
        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo("show logo");
    }

    @Test
    public void testGetCityList() throws JsonProcessingException {
        String url=baseUrl+"getCityList";

        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        String jsonArrayStr=entity.getBody();
        ObjectMapper mapper=new ObjectMapper();
        JavaType javaType =  mapper.getTypeFactory().constructParametricType(List.class, City.class);
        List<City> cityList = mapper.readValue(jsonArrayStr,javaType);
        int i=1;
        for(City city:cityList){
            assertThat(city.getId()).isEqualTo(i);
            i++;
        }


    }

    /**
     * 重点  test post json and return json
     * 1 add header
     * 2 set city object to formEntify
     * 3 post and get city object
     *
     */
    @Test
    public void testAddCity(){
        String url=baseUrl+"addCity";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);

        City postCity=new City(1,"city1","001");
        HttpEntity<City> formEntity = new HttpEntity<City>(postCity, headers);


        ResponseEntity<City> entity = this.restTemplate.postForEntity(url, formEntity,City.class);

        City returnCity=entity.getBody();
        assertThat(returnCity.getCode()).isEqualTo(postCity.getCode()+"ok");
    }

}
