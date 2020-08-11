package com.wukong.examples.mapper.base;

import static com.wukong.examples.mapper.base.StudentInfoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.wukong.examples.model.StudentInfo;
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
public interface StudentInfoMapper {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    BasicColumn[] selectList = BasicColumn.columnList(studentInfoId, studentId, qq, wx, mobil, address);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<StudentInfo> insertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<StudentInfo> multipleInsertStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("StudentInfoResult")
    Optional<StudentInfo> selectOne(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="StudentInfoResult", value = {
        @Result(column="student_info_id", property="studentInfoId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="student_id", property="studentId", jdbcType=JdbcType.BIGINT),
        @Result(column="qq", property="qq", jdbcType=JdbcType.VARCHAR),
        @Result(column="wx", property="wx", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobil", property="mobil", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR)
    })
    List<StudentInfo> selectMany(SelectStatementProvider selectStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, studentInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, studentInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    default int deleteByPrimaryKey(Long studentInfoId_) {
        return delete(c -> 
            c.where(studentInfoId, isEqualTo(studentInfoId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    default int insert(StudentInfo record) {
        return MyBatis3Utils.insert(this::insert, record, studentInfo, c ->
            c.map(studentInfoId).toProperty("studentInfoId")
            .map(studentId).toProperty("studentId")
            .map(qq).toProperty("qq")
            .map(wx).toProperty("wx")
            .map(mobil).toProperty("mobil")
            .map(address).toProperty("address")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    default int insertMultiple(Collection<StudentInfo> records) {
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, studentInfo, c ->
            c.map(studentInfoId).toProperty("studentInfoId")
            .map(studentId).toProperty("studentId")
            .map(qq).toProperty("qq")
            .map(wx).toProperty("wx")
            .map(mobil).toProperty("mobil")
            .map(address).toProperty("address")
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    default int insertSelective(StudentInfo record) {
        return MyBatis3Utils.insert(this::insert, record, studentInfo, c ->
            c.map(studentInfoId).toPropertyWhenPresent("studentInfoId", record::getStudentInfoId)
            .map(studentId).toPropertyWhenPresent("studentId", record::getStudentId)
            .map(qq).toPropertyWhenPresent("qq", record::getQq)
            .map(wx).toPropertyWhenPresent("wx", record::getWx)
            .map(mobil).toPropertyWhenPresent("mobil", record::getMobil)
            .map(address).toPropertyWhenPresent("address", record::getAddress)
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    default Optional<StudentInfo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, studentInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    default List<StudentInfo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, studentInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    default List<StudentInfo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, studentInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    default Optional<StudentInfo> selectByPrimaryKey(Long studentInfoId_) {
        return selectOne(c ->
            c.where(studentInfoId, isEqualTo(studentInfoId_))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, studentInfo, completer);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    static UpdateDSL<UpdateModel> updateAllColumns(StudentInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(studentInfoId).equalTo(record::getStudentInfoId)
                .set(studentId).equalTo(record::getStudentId)
                .set(qq).equalTo(record::getQq)
                .set(wx).equalTo(record::getWx)
                .set(mobil).equalTo(record::getMobil)
                .set(address).equalTo(record::getAddress);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    static UpdateDSL<UpdateModel> updateSelectiveColumns(StudentInfo record, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(studentInfoId).equalToWhenPresent(record::getStudentInfoId)
                .set(studentId).equalToWhenPresent(record::getStudentId)
                .set(qq).equalToWhenPresent(record::getQq)
                .set(wx).equalToWhenPresent(record::getWx)
                .set(mobil).equalToWhenPresent(record::getMobil)
                .set(address).equalToWhenPresent(record::getAddress);
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    default int updateByPrimaryKey(StudentInfo record) {
        return update(c ->
            c.set(studentId).equalTo(record::getStudentId)
            .set(qq).equalTo(record::getQq)
            .set(wx).equalTo(record::getWx)
            .set(mobil).equalTo(record::getMobil)
            .set(address).equalTo(record::getAddress)
            .where(studentInfoId, isEqualTo(record::getStudentInfoId))
        );
    }

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    default int updateByPrimaryKeySelective(StudentInfo record) {
        return update(c ->
            c.set(studentId).equalToWhenPresent(record::getStudentId)
            .set(qq).equalToWhenPresent(record::getQq)
            .set(wx).equalToWhenPresent(record::getWx)
            .set(mobil).equalToWhenPresent(record::getMobil)
            .set(address).equalToWhenPresent(record::getAddress)
            .where(studentInfoId, isEqualTo(record::getStudentInfoId))
        );
    }
}