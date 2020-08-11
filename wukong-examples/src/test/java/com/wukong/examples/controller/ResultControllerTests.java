package com.wukong.examples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResultControllerTests extends AbstractTestNGSpringContextTests {


    @Autowired
    private TestRestTemplate restTemplate;




    @Test
    public void testSuccess() {
        String url="/example/result/success";
        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.printf(entity.getBody().toString());
    }


    @Test
    public void testFail() {
        String url="/example/result/para?name=11&email=email&cellPhone=3333";
        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isNotEqualTo(HttpStatus.OK);
        System.out.printf(entity.getBody().toString());
    }

}
