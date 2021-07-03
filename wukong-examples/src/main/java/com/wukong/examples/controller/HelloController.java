package com.wukong.examples.controller;


import org.springframework.web.bind.annotation.*;

/**
*
* @author fanhl
*/
@RestController
@RequestMapping("/example/hello")
@SuppressWarnings("unchecked")
public class HelloController  {

    /**
     * 默认映射，可不填二级
     * <p>
     * 地址：http://localhost:8080/hello
     */
    @RequestMapping
    public String hello() {
        return "Hello World";
    }
}