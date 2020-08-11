package com.wukong.examples.controller;

import com.wukong.core.mybatis.dynamic.builder.WhereBuilder;
import com.wukong.core.mybatis.dynamic.builder.WhereObject;
import com.wukong.core.mybatis.dynamic.request.RequestAnalysis;
import com.wukong.core.mybatis.dynamic.request.SqlMaps;
import com.wukong.core.util.RequestContextHolderUtil;
import com.wukong.core.result.ListResponseResult;
import com.wukong.examples.model.StudentScores;
import com.wukong.examples.service.StudentScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/example/studentScores")
public class StudentScoresController {

    @Autowired
    private WhereBuilder whereBuilder;

    @Autowired
    private StudentScoresService studentScoresService;

    @RequestMapping("/count")
    public long count() {
        return studentScoresService.count();
    }


    @RequestMapping("/insert")
    public StudentScores insert(@RequestBody StudentScores record) {
         studentScoresService.insert(record);
        return record;
    }

    @RequestMapping("/selectByPK")
    public StudentScores selectByPK(@RequestParam Long studentScoresId) {
        return studentScoresService.selectByPK(studentScoresId);
    }


    @RequestMapping("/updateByPK")
    public int updateByPK(@RequestBody StudentScores record) {
        return studentScoresService.updateByPK(record);
    }

    @RequestMapping("/deleteByPK")
    public int deleteByPK(@RequestParam Long studentScoresId) {
        return studentScoresService.deleteByPK(studentScoresId);
    }

    @RequestMapping("/selectAll")
    public List<StudentScores> selectAll() {
        return studentScoresService.selectAll();
    }

    @RequestMapping("/selectAllByPage")
    public ListResponseResult selectAllByPage(Integer pageNum,Integer pageSize) {
        return studentScoresService.selectAllByPage(pageNum,pageSize);
    }

    @RequestMapping("/selectByWhere")
    public List<StudentScores> selectByWhere() {
        HttpServletRequest request=  RequestContextHolderUtil.getRequest();
        SqlMaps sqlMaps= RequestAnalysis.analysis(request);
        WhereObject sqlObject=whereBuilder.build("wk_student_scores",sqlMaps);
        return studentScoresService.selectByWhere(sqlObject.getWhereDSL(),sqlObject.getOrder());
    }

    @RequestMapping("/selectByWhereByPage")
    public ListResponseResult selectByWhereByPage(Integer pageNum,Integer pageSize) {
        HttpServletRequest request=  RequestContextHolderUtil.getRequest();
        SqlMaps sqlMaps= RequestAnalysis.analysis(request);
        WhereObject sqlObject=whereBuilder.build("wk_student_scores",sqlMaps);
        return studentScoresService.selectByWhereByPage(sqlObject.getWhereDSL()
                ,sqlObject.getOrder()
                ,pageNum
                ,pageSize);
    }

}
