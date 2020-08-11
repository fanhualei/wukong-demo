package com.wukong.examples.service;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlCriterion;
import org.mybatis.dynamic.sql.SqlTable;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.render.TableAliasCalculator;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.where.WhereApplier;
import org.mybatis.dynamic.sql.where.WhereDSL;
import org.mybatis.dynamic.sql.where.render.WhereClauseProvider;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * 这个类是用来测试Mybatis Dynamic的主要功能的，主要是学习相关用法
 */
@SpringBootTest
@SuppressWarnings("unchecked")
public class MybatisDynamicTest extends AbstractTestNGSpringContextTests {
    SqlTable personTable = SqlTable.of("person");
    SqlColumn<Integer> id = SqlColumn.of("id",personTable);
    SqlColumn<String> firstName = SqlColumn.of("first_name",personTable);
    SqlColumn<String> lastName = SqlColumn.of("last_name",personTable);

    SqlTable order = SqlTable.of("order");
    SqlColumn<Integer> personId = SqlColumn.of("person_id",order);
    SqlColumn<Date> orderDate = SqlColumn.of("order_date",order);


    /**
     * 测试and()可以添加一个SqlCriterion List.
     * 实现下面的功能 and( id=1 and first_name='tom')
     */
    @Test
    public void testAndList(){
        WhereDSL builder=where();
        List list = getScList();
        builder.and(id,isEqualTo(1),list);

        String expected="where (id = #{parameters.p1} " +
                "and first_name = #{parameters.p2} " +
                "or last_name = #{parameters.p3})";

        printSql(builder,expected);
    }

    /**
     * 测试builder.applyWhere的用法
     */
    @Test
    public void testApplyWhere(){
        //模拟一个已经有的where
        WhereDSL builder =where(id,isEqualTo(1));
        List list = getScList();
        //下面代码也可以这样用 d -> d.where(  ,这样就没有and
        WhereApplier commonWhere = d -> d.and(id, isEqualTo(1),list);
        builder.applyWhere(commonWhere);
        String expected="where id = #{parameters.p1} and (id = #{parameters.p2} and first_name = #{parameters.p3} or last_name = #{parameters.p4})" ;
        printSql(builder,expected);
    }

    /**
     * builder.applyWhere的另外一种实现方式，感觉比applyWhere更将好用。
     */
    @Test
    public void testApplyWhereOtherImp(){
        //模拟一个已经有的where
        WhereDSL builder =where(id,isEqualTo(1));
        List list = getScList();
        builder.and(id, isEqualTo(1),list);
        String expected="where id = #{parameters.p1} and (id = #{parameters.p2} and first_name = #{parameters.p3} or last_name = #{parameters.p4})" ;
        printSql(builder,expected);
    }

    /**
     * 两个表的sql语句，缺陷是没有别名，字段重复了，会出现错误。
     *
     */
    @Test
    public void testTowTableWhere(){
        WhereDSL builder =where(id,isEqualTo(1));
        builder.and(firstName,isEqualTo("tom"));
        builder.and(personId,isEqualTo(2));
        String expected="where id = #{parameters.p1}" +
                " and first_name = #{parameters.p2}" +
                " and person_id = #{parameters.p3}" ;
        printSql(builder,expected);
    }


    /**
     * 与 testTowTableWhere()的区别是，这个代上了别名。
     * 当然可以指定为缩写。
     */
    @Test
    public void testTowTableWhereWithTableName(){
        WhereDSL builder =where(id,isEqualTo(1));
        builder.and(firstName,isEqualTo("tom"));
        builder.and(personId,isEqualTo(2));

        Map aliasMap = new HashMap();
        aliasMap.put(personTable,personTable.tableNameAtRuntime());
        aliasMap.put(order,order.tableNameAtRuntime());


        String expected="where person.id = #{parameters.p1}" +
                " and person.first_name = #{parameters.p2}" +
                " and order.person_id = #{parameters.p3}" ;

        WhereClauseProvider whereClause = builder
                .build()
                .render(RenderingStrategies.MYBATIS3, TableAliasCalculator.of(aliasMap));
        String sql =whereClause.getWhereClause();
        System.out.println(sql);
        assertThat(sql).isEqualTo(expected);

    }

    /**
     * 一個完整的sql語句
     */
    @Test
    public void testTowTableFullSql(){
        String fName = "Fred";
        String lName = null;
        QueryExpressionDSL<SelectModel>.QueryExpressionWhereBuilder
                builder = select(id, firstName, lastName, orderDate)
                .from(personTable).join(order).on(id, equalTo(personId))
                .where();

        builder.and(firstName, isEqualTo(fName).when(Objects::nonNull));
        builder.and(lastName, isEqualTo(lName).when(Objects::nonNull));

        String expected = "select person.id, person.first_name, person.last_name, order.order_date"
                + " from person"
                + " join order on person.id = order.person_id"
                + " where person.first_name = #{parameters.p1}";

        SelectStatementProvider selectStatement = builder.build().render(RenderingStrategies.MYBATIS3);
        assertThat(selectStatement.getSelectStatement()).isEqualTo(expected);
        System.out.println(selectStatement.getSelectStatement());
    }


    /**
     * 返回一个list sqlCriterion
     * @return
     */
    private List getScList(){
        SqlCriterion sc1= and(firstName,isEqualTo("tom"));
        SqlCriterion sc2= or(lastName,isEqualTo("tom"));
        List list = new ArrayList();
        list.add(sc1);
        list.add(sc2);
        return list;
    }


    /**
     * print sql and test
     * @param builder
     * @param expected
     */
    private void printSql(WhereDSL builder,String expected){
        WhereClauseProvider whereClause = builder
                .build()
                .render(RenderingStrategies.MYBATIS3);
        String sql =whereClause.getWhereClause();
        System.out.println(sql);
        assertThat(sql).isEqualTo(expected);
    }

}
