package com.wukong.examples.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fanhl
 */
@RestController
@RequestMapping("/security")
public class SecurityController {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
