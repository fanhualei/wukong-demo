package com.wukong.examples.controller;

import com.wukong.security.util.BasicJwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SuppressWarnings("unchecked")
public class SecurityControllerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private TestRestTemplate restTemplate;

    private String token="";
    private BasicJwtInterceptor basicJwtInterceptor;
    private String verifyCode;
    private String baseUrl="/author/private/";

    /**
     * 模拟登陆
     */
    @BeforeClass
    public void testLogin(){
        String url ="/author/public/login?username=admin&password=admin";
        ResponseEntity<Map> entity = this.restTemplate.getForEntity(url, Map.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        String token=((Map<String,String>)entity.getBody()).get("token");
        assertThat(token).isNotNull();
        this.token=token;
        basicJwtInterceptor=new BasicJwtInterceptor(token);
        restTemplate.getRestTemplate().setInterceptors(Collections.singletonList(basicJwtInterceptor));
    }

    /**
     * 测试没有登陆后，访问/security/hello,提示403
     */
    @Test
    public void testSecurityHello() {
        String url="/security/hello";
        ResponseEntity<String> entity = this.restTemplate.getForEntity(url, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo("Hello World");
    }


}
