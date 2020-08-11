package com.wukong.examples.mapper;

import com.wukong.examples.mapper.base.StudentScoresMapper;
import com.wukong.examples.model.StudentScores;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.mybatis.dynamic.sql.where.render.WhereClauseProvider;

import java.util.List;


@Mapper
public interface StudentScoresDao extends StudentScoresMapper {
    /**
     * 输入where条件，返回一个对象List
     * @param whereClause  where 条件
     * @return List<StudentScores>
     */
    @Select({
            "select * ",
            "from wk_student_scores",
            "${whereClauseProvider.whereClause}",
            " ${orderBy} "
    })
    @ResultMap("StudentScoresResult")
    List<StudentScores> selectByWhere(@Param("whereClauseProvider")WhereClauseProvider whereClause
            ,@Param("orderBy") String orderBy);
}
