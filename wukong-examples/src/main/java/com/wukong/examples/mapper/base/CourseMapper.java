package com.wukong.examples.mapper.base;

import static com.wukong.examples.mapper.base.CourseDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.wukong.examples.model.Course;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface CourseMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    BasicColumn[] selectList = BasicColumn.columnList(courseId, courseName, gmtCreate, gmtModified);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="record.courseId", before=false, resultType=Long.class)
    int insert(InsertStatementProvider<Course> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("CourseResult")
    Optional<Course> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="CourseResult", value = {
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="course_name", property="courseName", jdbcType=JdbcType.VARCHAR),
        @Result(column="gmt_create", property="gmtCreate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Course> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, course, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, course, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    default int deleteByPrimaryKey(Long courseId_) {
        return delete(c -> 
            c.where(courseId, isEqualTo(courseId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    default int insert(Course record) {
        return MyBatis3Utils.insert(this::insert, record, course, c ->
            c.map(courseName).toProperty("courseName")
            .map(gmtCreate).toProperty("gmtCreate")
            .map(gmtModified).toProperty("gmtModified")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    default int insertSelective(Course record) {
        return MyBatis3Utils.insert(this::insert, record, course, c ->
            c.map(courseName).toPropertyWhenPresent("courseName", record::getCourseName)
            .map(gmtCreate).toPropertyWhenPresent("gmtCreate", record::getGmtCreate)
            .map(gmtModified).toPropertyWhenPresent("gmtModified", record::getGmtModified)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    default Optional<Course> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, course, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    default List<Course> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, course, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    default List<Course> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, course, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    default Optional<Course> selectByPrimaryKey(Long courseId_) {
        return selectOne(c ->
            c.where(courseId, isEqualTo(courseId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, course, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    static UpdateDSL<UpdateModel> updateAllColumns(Course record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(courseName).equalTo(record::getCourseName)
                .set(gmtCreate).equalTo(record::getGmtCreate)
                .set(gmtModified).equalTo(record::getGmtModified);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Course record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(courseName).equalToWhenPresent(record::getCourseName)
                .set(gmtCreate).equalToWhenPresent(record::getGmtCreate)
                .set(gmtModified).equalToWhenPresent(record::getGmtModified);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    default int updateByPrimaryKey(Course record) {
        return update(c ->
            c.set(courseName).equalTo(record::getCourseName)
            .set(gmtCreate).equalTo(record::getGmtCreate)
            .set(gmtModified).equalTo(record::getGmtModified)
            .where(courseId, isEqualTo(record::getCourseId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    default int updateByPrimaryKeySelective(Course record) {
        return update(c ->
            c.set(courseName).equalToWhenPresent(record::getCourseName)
            .set(gmtCreate).equalToWhenPresent(record::getGmtCreate)
            .set(gmtModified).equalToWhenPresent(record::getGmtModified)
            .where(courseId, isEqualTo(record::getCourseId))
        );
    }
}