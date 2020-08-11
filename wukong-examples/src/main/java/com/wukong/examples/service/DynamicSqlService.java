package com.wukong.examples.service;

import com.wukong.core.util.WkStringUtils;
import com.wukong.examples.mapper.StudentInfoDao;
import com.wukong.examples.model.StudentInfo;
import com.wukong.security.model.User;
import lombok.extern.slf4j.Slf4j;

import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.render.TableAliasCalculator;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.where.WhereDSL;
import org.mybatis.dynamic.sql.where.render.WhereClauseProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.JDBCType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wukong.examples.mapper.base.StudentInfoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import static org.mybatis.dynamic.sql.select.SelectDSL.select;



@Service
@Slf4j
@SuppressWarnings("unchecked")
public class DynamicSqlService {
    @SuppressWarnings("all")
    @Autowired
    private StudentInfoDao studentInfoDao;


    /**
     * 演示如何平装select与where条件。
     * 平装select是用来少一点字段，我感觉用途不大。
     * 如果为了减少数据量，还不如返回一个Map List
     * @return List<StudentInfo>
     */
    public List<StudentInfo>  selectAllSql(){
        SelectStatementProvider selectStatement = select(studentInfo.allColumns())
                .from(studentInfo)
                .where(studentId, isIn(2L,3L,4L))
                .orderBy(qq,wx.descending())
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return studentInfoDao.selectMany(selectStatement);
    }

    /**
     * 演示如何传入一个动态的where条件，这里使用了官方的拼装where用法
     * @return List<StudentInfo>
     */
    public List<StudentInfo>  selectByWhere(){
        WhereClauseProvider whereClause = where(studentId,isNotBetween(1L).and(5L))
                .build()
                .render(RenderingStrategies.MYBATIS3,"whereClauseProvider");
        return studentInfoDao.selectByWhere(whereClause,"");
    }

    /**
     * 演示如何传入一个动态的where条件,返回的是Map list，这里使用了官方的拼装where用法
     * @return List<StudentInfo>
     */
    public List<Map>  selectByWhereMap(){
        WhereDSL whereDSL=where(studentId,isNotBetween(1L).and(5L));
//        whereDSL.or(studentId,isNotBetween(1L).and(5L),and(studentId,isNotBetween(1L).and(5L)));

        WhereClauseProvider whereClause = whereDSL
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return studentInfoDao.selectByWhereMap(whereClause);
    }

    /**
     * 自己生成sqlTable与sqlColumn，然后同mybatis dynamic来拼装where条件。
     * 这样就将动态Sql语句做成了可能性，未来会大大简化代码。
     * @return List<StudentInfo>
     */
    public List<StudentInfo>  selectByWhereUseWk(){
        WhereDSL builder =where();
        WkSqlTable sqlTable = new WkSqlTable("wk_studentbb");
        SqlColumn<Long> tempField =SqlColumn.of("student_id", sqlTable, JDBCType.BIGINT);
        builder.and(tempField,isNotBetween(1L).and(5L));
        return studentInfoDao.selectByWhere(builder.build().render(RenderingStrategies.MYBATIS3,"whereClauseProvider"),"order by student_id");
    }

    public List<StudentInfo>  selectByWhere(WhereDSL whereDSL
            , String orderBy){
        return studentInfoDao.selectByWhere(whereDSL.build().render(RenderingStrategies.MYBATIS3,"whereClauseProvider")
                ,WkStringUtils.getOrderBySql(orderBy)
        );
    }


    /**
     * 模拟了如何从两个表中查询数据
     * 这里没有使用官方的方法，自己组装了SqlTable与SqlColumn对象，来动态拼装sql.
     * @return List<Map> 建议使用Map list作为返回值
     */
    public List<Map> selectByTowTable(){
        WhereDSL builder =where();
        WkSqlTable sqlTableA = new WkSqlTable("wk_student");
        WkSqlTable sqlTableB = new WkSqlTable("wk_student_info");
        Map a = new HashMap();
        a.put(sqlTableA,"a");
        a.put(sqlTableB,"b");
        SqlColumn<Long> tempFieldAia =SqlColumn.of("student_id", sqlTableA, JDBCType.BIGINT);
        SqlColumn<Long> tempFieldAib=SqlColumn.of("student_id", sqlTableB, JDBCType.BIGINT);
        WhereClauseProvider whereClause=builder.and(tempFieldAia,isEqualTo(1L))
                .and(tempFieldAia,isEqualTo(tempFieldAib))
                .build()
                .render(RenderingStrategies.MYBATIS3, TableAliasCalculator.of(a));
        return studentInfoDao.selectFromTowTable(whereClause);
    }

    /**
     * 模拟只要sql语句写的对，就可以返回一个对象，大大简化了相关代码
     * @return List<User>
     */
    public List<User>  selectToObjectList(){
        return studentInfoDao.selectToObjectList("select * from wk_user");
    }
}
