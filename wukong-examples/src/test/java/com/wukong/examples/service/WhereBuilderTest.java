package com.wukong.examples.service;

import com.wukong.core.mybatis.dynamic.builder.WhereBuilder;
import com.wukong.core.mybatis.dynamic.builder.WhereObject;
import com.wukong.core.mybatis.dynamic.request.SqlMaps;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlCriterion;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.mybatis.dynamic.sql.where.WhereDSL;
import org.mybatis.dynamic.sql.where.condition.IsEqualTo;
import org.mybatis.dynamic.sql.where.render.WhereClauseProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

@SpringBootTest
public class WhereBuilderTest extends AbstractTestNGSpringContextTests {
    @Autowired
    WhereBuilder whereBuilder;

//    @Test
    public void buildTest(){
        String str=whereBuilder.toString();
        String tableName="wk_student";
        Map<String,String> whereMap=new HashMap<>();
        whereMap.put("studentName_like","%1%");
        whereMap.put("studentId","1");
        String orderStr="studentName,studentSex desc";

        SqlMaps sqlMaps=SqlMaps.builder()
                .whereMap(whereMap)
                .orderStr(orderStr)
                .build();

        WhereObject whereObject = whereBuilder.build(tableName,sqlMaps);
        WhereClauseProvider whereClause = whereObject.getWhereDSL()
                .build()
                .render(RenderingStrategies.MYBATIS3);

        String expected = "where student_id = #{parameters.p1,jdbcType=BIGINT} " +
                "and student_name like #{parameters.p2,jdbcType=VARCHAR}";
        String orderBy="student_name,student_sex desc";

        assertThat(whereObject.getOrder()).isEqualTo(orderBy);
        assertThat(whereClause.getWhereClause()).isEqualTo(expected);
        assertThat(whereClause.getParameters().get("p1")).isEqualTo(1L);
        assertThat(whereClause.getParameters().get("p2")).isEqualTo("%1%");
    }

    @Test
    public void buildTest1() {
//        String str = whereBuilder.toString();
//        String tableName = "wk_student";
//        Map<String, String> whereMap = new HashMap<>();
//        whereMap.put("studentName_like", "%1%");
//        whereMap.put("studentId", "1");
//        String orderStr = "studentName,studentSex desc";
//
//        SqlMaps sqlMaps = SqlMaps.builder()
//                .whereMap(whereMap)
//                .orderStr(orderStr)
//                .build();
//
//        WhereObject whereObject = whereBuilder.build(tableName, sqlMaps);

    }








}
