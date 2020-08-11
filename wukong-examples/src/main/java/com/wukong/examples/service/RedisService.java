package com.wukong.examples.service;


import com.wukong.core.util.RedisUtils;
import com.wukong.examples.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 实现了两个例子：
 * 1：如个单独的添加或得到redis
 * 2：如何在函数上添加注释，实现缓存
 */
@Service
@CacheConfig(cacheNames="wukong:example:redis")
@SuppressWarnings("unchecked")
public class RedisService {

    @Autowired
    private StringRedisTemplate template;
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private  CourseService courseService;

    public void setValue(String key,String value){
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key,value);
    }

    public String getValue(String key){
        ValueOperations<String, String> ops = template.opsForValue();
        if(template.hasKey(key)){
            return ops.get(key);
        }
        return "";
    }

    public boolean delete(String key){
        return   redisUtils.delete(key);
    }

    public Map showAll(String pattern){
        return redisUtils.select(pattern);
    }

    /**
     *
     * @return 注意：key上面不能少了单引号
     */
    @Cacheable(key="'getCourseList'")
    public List<Course> getCourseList(){
        return   courseService.selectAll();
    }

}
