package com.wukong.examples.mapper.base;

import java.sql.JDBCType;
import java.util.Date;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class CourseDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    public static final Course course = new Course();

    /**
     * Database Column Remarks:
     *   课程id
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: wk_course.course_id")
    public static final SqlColumn<Long> courseId = course.courseId;

    /**
     * Database Column Remarks:
     *   课程
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: wk_course.course_name")
    public static final SqlColumn<String> courseName = course.courseName;

    /**
     * Database Column Remarks:
     *   记录创建时间
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: wk_course.gmt_create")
    public static final SqlColumn<Date> gmtCreate = course.gmtCreate;

    /**
     * Database Column Remarks:
     *   记录修改时间
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: wk_course.gmt_modified")
    public static final SqlColumn<Date> gmtModified = course.gmtModified;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_course")
    public static final class Course extends SqlTable {
        public final SqlColumn<Long> courseId = column("course_id", JDBCType.BIGINT);

        public final SqlColumn<String> courseName = column("course_name", JDBCType.VARCHAR);

        public final SqlColumn<Date> gmtCreate = column("gmt_create", JDBCType.TIMESTAMP);

        public final SqlColumn<Date> gmtModified = column("gmt_modified", JDBCType.TIMESTAMP);

        public Course() {
            super("wk_course");
        }
    }
}