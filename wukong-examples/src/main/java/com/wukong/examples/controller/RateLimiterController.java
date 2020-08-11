package com.wukong.examples.controller;

import com.wukong.core.util.concurrent.RateLimit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example/limiter")
@SuppressWarnings("unchecked")
public class RateLimiterController {
    @RequestMapping("/hello")
    @RateLimit(limit = 1, timeout = 50)
    public String hello() {
        return "hello limiter";
    }
}
