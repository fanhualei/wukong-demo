package com.wukong.examples.dao;

import com.wukong.examples.model.Student;
import com.wukong.examples.model.StudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface StudentMapper {
    @SelectProvider(type=StudentSqlProvider.class, method="countByExample")
    long countByExample(StudentExample example);

    @DeleteProvider(type=StudentSqlProvider.class, method="deleteByExample")
    int deleteByExample(StudentExample example);

    @Delete({
        "delete from wk_student",
        "where student_id = #{studentId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long studentId);

    @Insert({
        "insert into wk_student (student_name, student_age, ",
        "student_sex)",
        "values (#{studentName,jdbcType=VARCHAR}, #{studentAge,jdbcType=INTEGER}, ",
        "#{studentSex,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="studentId", before=false, resultType=Long.class)
    int insert(Student record);

    @InsertProvider(type=StudentSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="studentId", before=false, resultType=Long.class)
    int insertSelective(Student record);

    @SelectProvider(type=StudentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="student_id", property="studentId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="student_name", property="studentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="student_age", property="studentAge", jdbcType=JdbcType.INTEGER),
        @Result(column="student_sex", property="studentSex", jdbcType=JdbcType.INTEGER)
    })
    List<Student> selectByExample(StudentExample example);

    @Select({
        "select",
        "student_id, student_name, student_age, student_sex",
        "from wk_student",
        "where student_id = #{studentId,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="student_id", property="studentId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="student_name", property="studentName", jdbcType=JdbcType.VARCHAR),
        @Result(column="student_age", property="studentAge", jdbcType=JdbcType.INTEGER),
        @Result(column="student_sex", property="studentSex", jdbcType=JdbcType.INTEGER)
    })
    Student selectByPrimaryKey(Long studentId);

    @UpdateProvider(type=StudentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

    @UpdateProvider(type=StudentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

    @UpdateProvider(type=StudentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Student record);

    @Update({
        "update wk_student",
        "set student_name = #{studentName,jdbcType=VARCHAR},",
          "student_age = #{studentAge,jdbcType=INTEGER},",
          "student_sex = #{studentSex,jdbcType=INTEGER}",
        "where student_id = #{studentId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Student record);
}