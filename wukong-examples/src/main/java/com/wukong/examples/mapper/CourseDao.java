package com.wukong.examples.mapper;

import com.wukong.examples.mapper.base.CourseMapper;
import com.wukong.examples.model.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.mybatis.dynamic.sql.where.render.WhereClauseProvider;

import java.util.List;


@Mapper
public interface CourseDao extends CourseMapper {
    /**
     * 输入where条件，返回一个对象List
     * @param whereClause  where 条件
     * @return List<Course>
     */
    @Select({
            "select * ",
            "from wk_course",
            "${whereClauseProvider.whereClause}",
            " ${orderBy} "
    })
    @ResultMap("CourseResult")
    List<Course> selectByWhere(@Param("whereClauseProvider")WhereClauseProvider whereClause
            ,@Param("orderBy") String orderBy);
}
