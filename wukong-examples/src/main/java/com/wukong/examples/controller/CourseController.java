package com.wukong.examples.controller;

import com.wukong.core.mybatis.dynamic.builder.WhereBuilder;
import com.wukong.core.mybatis.dynamic.builder.WhereObject;
import com.wukong.core.mybatis.dynamic.request.RequestAnalysis;
import com.wukong.core.mybatis.dynamic.request.SqlMaps;
import com.wukong.core.util.RequestContextHolderUtil;
import com.wukong.core.result.ListResponseResult;
import com.wukong.examples.model.Course;
import com.wukong.examples.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/example/course")
public class CourseController {

    @Autowired
    private WhereBuilder whereBuilder;

    @Autowired
    private CourseService courseService;



    @RequestMapping("/insert")
    public Course insert(@RequestBody Course record) {
         courseService.insertSelective(record);
        return record;
    }

    @RequestMapping("/selectByPk")
    public Course selectByPrimaryKey(@RequestParam Long courseId) {
        return courseService.selectByPrimaryKey(courseId).get();
    }


    @RequestMapping("/updateByPK")
    public int updateByPrimaryKeySelective(@RequestBody Course record) {
        return courseService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping("/deleteByPK")
    public int deleteByPrimaryKey(@RequestParam Long courseId) {
        return courseService.deleteByPrimaryKey(courseId);
    }

    @RequestMapping("/selectAll")
    public List<Course> selectAll() {
        return courseService.selectAll();
    }

    @RequestMapping("/selectAllByPage")
    public ListResponseResult selectAllByPage(Integer pageNum,Integer pageSize) {
        return courseService.selectAllByPage(pageNum,pageSize);
    }

    @RequestMapping("/selectByWhere")
    public List<Course> selectByWhere() {
        HttpServletRequest request=  RequestContextHolderUtil.getRequest();
        SqlMaps sqlMaps= RequestAnalysis.analysis(request);
        WhereObject sqlObject=whereBuilder.build("wk_course",sqlMaps);
        return courseService.selectByWhere(sqlObject.getWhereDSL(),sqlObject.getOrder());
    }

    @RequestMapping("/selectByWhereByPage")
    public ListResponseResult selectByWhereByPage(Integer pageNum,Integer pageSize) {
        HttpServletRequest request=  RequestContextHolderUtil.getRequest();
        SqlMaps sqlMaps= RequestAnalysis.analysis(request);
        WhereObject sqlObject=whereBuilder.build("wk_course",sqlMaps);
        return courseService.selectByWhereByPage(sqlObject.getWhereDSL()
                ,sqlObject.getOrder()
                ,pageNum
                ,pageSize);
    }

}
