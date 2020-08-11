package com.wukong.examples.mapper.base;

import static com.wukong.examples.mapper.base.StudentScoresDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.wukong.examples.model.StudentScores;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
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
public interface StudentScoresMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    BasicColumn[] selectList = BasicColumn.columnList(studentScoresId, studentInfoId, courseId, score);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<StudentScores> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<StudentScores> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("StudentScoresResult")
    Optional<StudentScores> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="StudentScoresResult", value = {
        @Result(column="student_scores_id", property="studentScoresId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="student_info_id", property="studentInfoId", jdbcType=JdbcType.BIGINT),
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.BIGINT),
        @Result(column="score", property="score", jdbcType=JdbcType.DECIMAL)
    })
    List<StudentScores> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, studentScores, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, studentScores, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    default int deleteByPrimaryKey(Long studentScoresId_) {
        return delete(c -> 
            c.where(studentScoresId, isEqualTo(studentScoresId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    default int insert(StudentScores record) {
        return MyBatis3Utils.insert(this::insert, record, studentScores, c ->
            c.map(studentScoresId).toProperty("studentScoresId")
            .map(studentInfoId).toProperty("studentInfoId")
            .map(courseId).toProperty("courseId")
            .map(score).toProperty("score")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    default int insertMultiple(Collection<StudentScores> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, studentScores, c ->
            c.map(studentScoresId).toProperty("studentScoresId")
            .map(studentInfoId).toProperty("studentInfoId")
            .map(courseId).toProperty("courseId")
            .map(score).toProperty("score")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    default int insertSelective(StudentScores record) {
        return MyBatis3Utils.insert(this::insert, record, studentScores, c ->
            c.map(studentScoresId).toPropertyWhenPresent("studentScoresId", record::getStudentScoresId)
            .map(studentInfoId).toPropertyWhenPresent("studentInfoId", record::getStudentInfoId)
            .map(courseId).toPropertyWhenPresent("courseId", record::getCourseId)
            .map(score).toPropertyWhenPresent("score", record::getScore)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    default Optional<StudentScores> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, studentScores, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    default List<StudentScores> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, studentScores, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    default List<StudentScores> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, studentScores, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    default Optional<StudentScores> selectByPrimaryKey(Long studentScoresId_) {
        return selectOne(c ->
            c.where(studentScoresId, isEqualTo(studentScoresId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, studentScores, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    static UpdateDSL<UpdateModel> updateAllColumns(StudentScores record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(studentScoresId).equalTo(record::getStudentScoresId)
                .set(studentInfoId).equalTo(record::getStudentInfoId)
                .set(courseId).equalTo(record::getCourseId)
                .set(score).equalTo(record::getScore);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(StudentScores record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(studentScoresId).equalToWhenPresent(record::getStudentScoresId)
                .set(studentInfoId).equalToWhenPresent(record::getStudentInfoId)
                .set(courseId).equalToWhenPresent(record::getCourseId)
                .set(score).equalToWhenPresent(record::getScore);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    default int updateByPrimaryKey(StudentScores record) {
        return update(c ->
            c.set(studentInfoId).equalTo(record::getStudentInfoId)
            .set(courseId).equalTo(record::getCourseId)
            .set(score).equalTo(record::getScore)
            .where(studentScoresId, isEqualTo(record::getStudentScoresId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    default int updateByPrimaryKeySelective(StudentScores record) {
        return update(c ->
            c.set(studentInfoId).equalToWhenPresent(record::getStudentInfoId)
            .set(courseId).equalToWhenPresent(record::getCourseId)
            .set(score).equalToWhenPresent(record::getScore)
            .where(studentScoresId, isEqualTo(record::getStudentScoresId))
        );
    }
}