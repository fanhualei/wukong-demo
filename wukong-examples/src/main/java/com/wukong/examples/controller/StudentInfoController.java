package com.wukong.examples.controller;

import com.wukong.core.result.ListResponseResult;
import com.wukong.examples.model.StudentInfo;
import com.wukong.examples.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/example/studentInfo")
public class StudentInfoController {
    @Autowired
    private StudentInfoService studentInfoService;

    @RequestMapping("/count")
    public long count() {
        return studentInfoService.count();
    }


    @RequestMapping("/insert")
    public int insert(@RequestBody StudentInfo record) {
        return studentInfoService.insert(record);
    }

    @RequestMapping("/selectByPK")
    public StudentInfo selectByPK(@RequestParam Long studentInfoId) {
        return studentInfoService.selectByPK(studentInfoId);
    }


    @RequestMapping("/updateByPK")
    public int updateByPK(@RequestBody StudentInfo record) {
        return studentInfoService.updateByPK(record);
    }

    @RequestMapping("/deleteByPK")
    public int deleteByPK(@RequestParam Long studentInfoId) {
        return studentInfoService.deleteByPK(studentInfoId);
    }

    @RequestMapping("/selectAll")
    public List<StudentInfo> selectAll() {
        return studentInfoService.selectAll();
    }

    @RequestMapping("/selectAllByPage")
    public ListResponseResult selectAllByPage(Integer pageNum,Integer pageSize) {
        return studentInfoService.selectAllByPage(pageNum,pageSize);
    }


}
