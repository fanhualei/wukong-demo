package com.wukong.examples.service;

import com.wukong.examples.model.Student;
import com.wukong.examples.model.StudentExample;
import com.wukong.examples.dao.StudentMapper;

import org.apache.ibatis.annotations.Param;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentService {
    //利用SpingIOC注入DAO变量
    @SuppressWarnings("all")
    @Autowired
    private StudentMapper studentMapper;

    @Transactional(readOnly=true)
    public long countByExample(StudentExample example) {
        return studentMapper.countByExample(example);
    }

    @Transactional
    public int deleteByExample(StudentExample example) {
        return studentMapper.deleteByExample(example);
    }

    @Transactional
    public int deleteByPrimaryKey(Long studentId) {
        return studentMapper.deleteByPrimaryKey(studentId);
    }

    @Transactional
    public int insert(Student record) {
        return studentMapper.insert(record);
    }

    @Transactional
    public int insertSelective(Student record) {
        return studentMapper.insertSelective(record);
    }

    @Transactional(readOnly=true)
    public List<Student> selectByExample(StudentExample example) {
        return studentMapper.selectByExample(example);
    }

    @Transactional(readOnly=true)
    public Student selectByPrimaryKey(Long studentId) {
        return studentMapper.selectByPrimaryKey(studentId);
    }

    @Transactional
    public int updateByExampleSelective(@Param("record") Student record,@Param("example") StudentExample example) {
        return studentMapper.updateByExampleSelective(record,example);
    }

    @Transactional
    public int updateByExample(@Param("record") Student record,@Param("example") StudentExample example) {
        return studentMapper.updateByExample(record,example);
    }

    @Transactional
    public int updateByPrimaryKeySelective(Student record) {
        return studentMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    public int updateByPrimaryKey(Student record) {
        return studentMapper.updateByPrimaryKey(record);
    }

}