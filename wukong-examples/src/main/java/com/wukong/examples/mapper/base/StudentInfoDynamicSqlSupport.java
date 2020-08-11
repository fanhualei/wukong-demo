package com.wukong.examples.mapper.base;

import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class StudentInfoDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    public static final StudentInfo studentInfo = new StudentInfo();

    /**
     * Database Column Remarks:
     *   学生详细id
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: wk_student_info.student_info_id")
    public static final SqlColumn<Long> studentInfoId = studentInfo.studentInfoId;

    /**
     * Database Column Remarks:
     *   学生id
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: wk_student_info.student_id")
    public static final SqlColumn<Long> studentId = studentInfo.studentId;

    /**
     * Database Column Remarks:
     *   qq号
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: wk_student_info.qq")
    public static final SqlColumn<String> qq = studentInfo.qq;

    /**
     * Database Column Remarks:
     *   wx号
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: wk_student_info.wx")
    public static final SqlColumn<String> wx = studentInfo.wx;

    /**
     * Database Column Remarks:
     *   手机号
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: wk_student_info.mobil")
    public static final SqlColumn<String> mobil = studentInfo.mobil;

    /**
     * Database Column Remarks:
     *   地址
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: wk_student_info.address")
    public static final SqlColumn<String> address = studentInfo.address;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_info")
    public static final class StudentInfo extends SqlTable {
        public final SqlColumn<Long> studentInfoId = column("student_info_id", JDBCType.BIGINT);

        public final SqlColumn<Long> studentId = column("student_id", JDBCType.BIGINT);

        public final SqlColumn<String> qq = column("qq", JDBCType.VARCHAR);

        public final SqlColumn<String> wx = column("wx", JDBCType.VARCHAR);

        public final SqlColumn<String> mobil = column("mobil", JDBCType.VARCHAR);

        public final SqlColumn<String> address = column("address", JDBCType.VARCHAR);

        public StudentInfo() {
            super("wk_student_info");
        }
    }
}