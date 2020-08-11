package com.wukong.examples.controller;


import com.wukong.testNg.AbstractWkBaseController;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SuppressWarnings("unchecked")
public class RateLimiterControllerTest extends AbstractWkBaseController {
    private final String baseUrl="/example/limiter";



    @Test(enabled=false, threadPoolSize=2, invocationCount=5)
    public void test1Insert() throws Exception {
        String url=baseUrl+ "/hello";

        ResultActions resultActions = getForResultActions(url);
        resultActions.andExpect(status().isOk());
        //String aa =getBody(resultActions,String.class);

        System.out.println("aa");

    }

    @AfterClass
    public void afterClass(){
        System.out.println("End Time: " );
    }

}
