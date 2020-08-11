package com.wukong.examples.service;

import com.wukong.core.exceptions.BusinessException;
import com.wukong.core.mybatis.dynamic.utils.DynamicUtils;
import com.wukong.examples.mapper.CourseDao;
import com.wukong.examples.mapper.StudentScoresDao;
import com.wukong.examples.model.Course;
import com.wukong.examples.model.StudentScores;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.dynamic.sql.BindableColumn;
import org.mybatis.dynamic.sql.SqlCriterion;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.mybatis.dynamic.sql.where.WhereDSL;
import org.mybatis.dynamic.sql.where.render.RenderedCriterion;
import org.mybatis.dynamic.sql.where.render.WhereRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * 用来测试Springboot 事物处理的代码
 */
@Service
@Slf4j
public class TransactService {

    @SuppressWarnings("all")
    @Autowired
    private CourseDao courseDao;

    @SuppressWarnings("all")
    @Autowired
    private StudentScoresDao studentScoresDao;


    @SuppressWarnings("all")
    @Autowired
    private StudentScoresService studentScoresService;

    @SuppressWarnings("all")
    @Autowired
    private CourseService courseService;


    /**
     * 测试调一个dao,然后抛出错误，这是否数据库回滚
     */
    @Transactional
    public void oneTable(Long courseId){
        Course course=new Course();
        course.setCourseId(courseId);
        course.setCourseName("test-transaction");
        courseDao.insert(course);
        throw new BusinessException("测试一个事物抛错");
    }

    /**
     * 测试调用2个dao,然后抛出错误，这是否数据库回滚
     */
    @Transactional
    public void twoTable(Long courseId){
        Course course=new Course();
        course.setCourseId(courseId);
        course.setCourseName("test-transaction");
        courseDao.insert(course);

        StudentScores studentScores=new StudentScores();
        studentScores.setStudentScoresId(courseId);
        studentScores.setCourseId(courseId);
        studentScores.setStudentInfoId(courseId);
        studentScores.setScore(new BigDecimal(100));
        studentScoresDao.insert(studentScores);

        throw new BusinessException("测试一个事物抛错-two tables");
    }

    /**
     * 测试调用2个rvice,然后抛出错误，这是否数据库回滚
     */
    @Transactional
    public void otherService(Long courseId){
        Course course=new Course();
        course.setCourseName("test-transaction");
        course.setCourseId(courseId);
        courseService.insertSelective(course);


        StudentScores studentScores=new StudentScores();
        studentScores.setStudentScoresId(courseId);
        studentScores.setCourseId(courseId);
        studentScores.setStudentInfoId(courseId);
        studentScores.setScore(new BigDecimal(100));
        studentScoresService.insert(studentScores);
        throw new BusinessException("测试一个事物抛错-two tables");
    }


    public long count(WhereDSL whereDSL){
        List<WhereApplier>  whereAppliers = DynamicUtils.getWhereApplier(whereDSL);
        List<WhereApplier>  whereAppliers2 = DynamicUtils.getWhereApplierByLambda(whereDSL);

        long ren = courseDao.count(c-> {
            whereAppliers.forEach(c::applyWhere);
            return c;
        });
        return ren;
    }


}
