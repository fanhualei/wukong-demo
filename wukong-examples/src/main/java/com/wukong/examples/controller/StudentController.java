package com.wukong.examples.controller;

import com.wukong.examples.model.Student;
import com.wukong.examples.model.StudentExample;

import com.wukong.examples.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* 学生类
*
* @author ljy
* @date 2018/6/7
*
*/
@RestController
@RequestMapping("/example/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/add")
    public Long add(@RequestBody Student student) {
        studentService.insert(student);
        return student.getStudentId();
    }

    @RequestMapping("/selectAll")
    public List<Student> selectAll() {
        return studentService.selectByExample(null);
    }


    /**
     *根据姓名删除学生信息
     */
    @RequestMapping("/deleteByName")
    public int deleteByName(@RequestParam String studentName){

        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStudentNameLike(studentName);

        return studentService.deleteByExample(studentExample);
    }
    /**
     * 根据名字找到数据库里的student实体
     * 例子代码，逻辑混乱，不要模仿
     */
    @RequestMapping("/update")
    public int update(@RequestBody Student student) {

        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andStudentNameEqualTo(student.getStudentName());
        List<Student> s1 = studentService.selectByExample(studentExample);
        //没找到返回更新0条
        if(s1.isEmpty()){
            return 0;
        }else if (s1.size()>1){
            return -1;
        }
        else{
            //接收的student是没有id的，插入id
            student.setStudentId(s1.get(0).getStudentId());
            return  studentService.updateByPrimaryKey(student);
        }
    }
}
