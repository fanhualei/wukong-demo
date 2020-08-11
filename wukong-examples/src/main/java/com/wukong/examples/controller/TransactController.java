package com.wukong.examples.controller;


import com.wukong.core.mybatis.dynamic.builder.WhereBuilder;
import com.wukong.core.mybatis.dynamic.builder.WhereObject;
import com.wukong.core.mybatis.dynamic.request.RequestAnalysis;
import com.wukong.core.mybatis.dynamic.request.SqlMaps;
import com.wukong.examples.service.TransactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/example/transact")
public class TransactController {

    @Autowired
    TransactService transactService;


    @Autowired
    private WhereBuilder whereBuilder;

    /**
     * 测试调用1个dao,然后抛出错误，这是否数据库回滚
     */
    @RequestMapping ("/oneTable")
    public String oneTable(@RequestParam Long courseId ){
        transactService.oneTable(courseId);
        return "oneTable";
    }



    /**
     * 测试调用2个dao,然后抛出错误，这是否数据库回滚
     */
    @RequestMapping ("/twoTable")
    public String twoTable(@RequestParam Long courseId ){
        transactService.twoTable(courseId);
        return "twoTable";
    }
    /**
     * 测试调用2个service,然后抛出错误，这是否数据库回滚
     */
    @RequestMapping ("/otherService")
    public String otherService(@RequestParam Long courseId ){
        transactService.otherService(courseId);
        return "oneTable";
    }


    @RequestMapping ("/getCount")
    public Long getCount(@RequestParam Long courseId ){
        Map<String,String> map=new HashMap<>();
        map.put("courseId",courseId.toString());
        SqlMaps sqlMaps= SqlMaps.builder().whereMap(map).build();
        WhereObject sqlObject=whereBuilder.build("wk_course",sqlMaps);
        return transactService.count(sqlObject.getWhereDSL());
    }

}
