package com.wukong.examples.mapper;


import com.wukong.examples.mapper.base.StudentInfoMapper;
import com.wukong.examples.model.StudentInfo;
import com.wukong.security.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.mybatis.dynamic.sql.where.render.WhereClauseProvider;

import java.util.List;
import java.util.Map;

/**
 * 下面写了一些测试的代码，用来演示动态sql语句如何使用
 */
@Mapper
public interface StudentInfoDao extends StudentInfoMapper {

    /**
     * 输入where条件，返回一个对象List
     * @param whereClause  where 条件
     * @return List<StudentInfo>
     */
    @Select({
            "select * ",
            "from wk_student_info",
            "${whereClauseProvider.whereClause}",
            " ${orderBy} "
    })
    @ResultMap("StudentInfoResult")
    List<StudentInfo> selectByWhere(@Param("whereClauseProvider")WhereClauseProvider whereClause
            ,@Param("orderBy") String orderBy);


    /**
     * 输入where条件，返回一个Map List
     * @param whereClause  where 条件
     * @return List<Map>
     */
    @Select({
            "select * ",
            "from wk_student_info",
            "${whereClause}"
    })
    List<Map> selectByWhereMap(WhereClauseProvider whereClause);


    /**
     * 输入任意一个sql语句，返回一个对象List，这个演示了自动将数据库返回结果转成一个对象List
     * @param sql  一个sql语句
     * @return List<User>
     */
    @Select({
            " ${sql} "
    })
    List<User> selectToObjectList(@Param("sql") String sql);


    /**
     * 演示从两个表中取得数据
     * @param whereClause  where 条件
     * @return List<Map>
     */
    @Select({
            "select a.*,b.qq ",
            "from wk_student a JOIN wk_student_info b  on a.student_id=b.student_id",
            "${whereClause}"
    })
    List<Map> selectFromTowTable(WhereClauseProvider whereClause);
}
