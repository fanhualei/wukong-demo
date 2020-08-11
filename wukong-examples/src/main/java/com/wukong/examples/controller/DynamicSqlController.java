package com.wukong.examples.controller;

import com.wukong.core.mybatis.dynamic.builder.WhereBuilder;
import com.wukong.core.mybatis.dynamic.builder.WhereObject;
import com.wukong.core.mybatis.dynamic.request.RequestAnalysis;
import com.wukong.core.mybatis.dynamic.request.SqlMaps;
import com.wukong.core.util.RequestContextHolderUtil;
import com.wukong.examples.model.StudentInfo;
import com.wukong.examples.service.DynamicSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author fanhl
 */
@RestController
@RequestMapping("/example/dynamic/")
public class DynamicSqlController {

    @Autowired
    private WhereBuilder whereBuilder;

    @Autowired
    DynamicSqlService dynamicSqlService;

    @RequestMapping("/hello")
    public List<StudentInfo> hello() {
        HttpServletRequest request=  RequestContextHolderUtil.getRequest();
        SqlMaps sqlMaps= RequestAnalysis.analysis(request);
        WhereObject sqlObject=whereBuilder.build("wk_student_info",sqlMaps);
        List<StudentInfo>  list =dynamicSqlService.selectByWhere(sqlObject.getWhereDSL(),sqlObject.getOrder());
        return list;
    }
}
