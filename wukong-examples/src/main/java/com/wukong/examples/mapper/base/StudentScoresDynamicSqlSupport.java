package com.wukong.examples.mapper.base;

import java.math.BigDecimal;
import java.sql.JDBCType;
import javax.annotation.Generated;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class StudentScoresDynamicSqlSupport {
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    public static final StudentScores studentScores = new StudentScores();

    /**
     * Database Column Remarks:
     *   学生详细id
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: wk_student_scores.student_scores_id")
    public static final SqlColumn<Long> studentScoresId = studentScores.studentScoresId;

    /**
     * Database Column Remarks:
     *   学生id
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: wk_student_scores.student_info_id")
    public static final SqlColumn<Long> studentInfoId = studentScores.studentInfoId;

    /**
     * Database Column Remarks:
     *   课程编号
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: wk_student_scores.course_id")
    public static final SqlColumn<Long> courseId = studentScores.courseId;

    /**
     * Database Column Remarks:
     *   数值：带小数点的
     */
    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source field: wk_student_scores.score")
    public static final SqlColumn<BigDecimal> score = studentScores.score;

    @Generated(value="org.mybatis.generator.api.MyBatisGenerator", comments="Source Table: wk_student_scores")
    public static final class StudentScores extends SqlTable {
        public final SqlColumn<Long> studentScoresId = column("student_scores_id", JDBCType.BIGINT);

        public final SqlColumn<Long> studentInfoId = column("student_info_id", JDBCType.BIGINT);

        public final SqlColumn<Long> courseId = column("course_id", JDBCType.BIGINT);

        public final SqlColumn<BigDecimal> score = column("score", JDBCType.DECIMAL);

        public StudentScores() {
            super("wk_student_scores");
        }
    }
}