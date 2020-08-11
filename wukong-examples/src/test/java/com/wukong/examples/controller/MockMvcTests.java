package com.wukong.examples.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcTests extends AbstractTestNGSpringContextTests {

    @Autowired
    private MockMvc mvc;

    private final String baseUrl="/example/hello/";

    @Test
    public void testSecurityHello() throws Exception {
        String url="/example/hello";
        mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World"));

    }

}
