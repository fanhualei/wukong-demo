package com.wukong.examples.service;

import com.github.pagehelper.PageHelper;
import com.wukong.core.result.ListResponseResult;
import com.wukong.core.util.WkStringUtils;
import com.wukong.examples.mapper.StudentScoresDao;
import com.wukong.examples.model.StudentScores;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.where.WhereDSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@SuppressWarnings("unchecked")
public class StudentScoresService {
    @SuppressWarnings("all")
    @Autowired
    private StudentScoresDao studentScoresDao;

    /**
     * count of all rows
     * @return  number of rows
     */
    public long count(){
        return studentScoresDao.count(c->c);
    }

    /**
     * insert selective - ignores null properties
     * @param record object of insert
     * @return  insert number
     */
    @Transactional
    public int insert(StudentScores record) {
        return studentScoresDao.insertSelective(record);
    }

    /**
     * Select by Primary Key
     * @param studentScoresId primary key
     * @return selected object
     */
    public StudentScores selectByPK(Long studentScoresId) {
        Optional<StudentScores>  opt= studentScoresDao.selectByPrimaryKey(studentScoresId);
        return opt.orElse(null);
    }

    /**
     * Update by Primary Key Selective - ignores null values
     * @param record the object will update
     * @return update number
     */
    @Transactional
    public int updateByPK(StudentScores record) {
        return studentScoresDao.updateByPrimaryKeySelective(record);
    }


    /**
     * delete by Primary Key
     * @param studentScoresId primary key
     * @return deleted number
     */
    @Transactional
    public  int deleteByPK(Long studentScoresId) {
        return studentScoresDao.deleteByPrimaryKey(studentScoresId);
    }

    /**
     * select all recorders
     * @return objects of list
     */
    public List<StudentScores> selectAll() {
        return studentScoresDao.select(c -> c);
    }

    /**
     * select row by page
     * @param pageNum page number
     * @param pageSize page size
     * @return list of result
     */
    public ListResponseResult selectAllByPage(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentScores> list = studentScoresDao.select(c->c);
        return new ListResponseResult(list);
    }

    /**
    * 根据前台传入的数据进行查询
    * @param whereDSL  where条件
    * @param orderBy   不带order by 的排序字符串
    * @return  list
    */
    public List<StudentScores>  selectByWhere(WhereDSL whereDSL
            , String orderBy){
        return studentScoresDao.selectByWhere(whereDSL.build().render(RenderingStrategies.MYBATIS3,"whereClauseProvider")
                ,WkStringUtils.getOrderBySql(orderBy));
    }

    /**
     * select row by page
     * @param pageNum page number
     * @param pageSize page size
     * @return list of result
     */
    public ListResponseResult selectByWhereByPage(WhereDSL whereDSL
            , String orderBy
            ,Integer pageNum
            ,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentScores> list = studentScoresDao.selectByWhere(whereDSL.build().render(RenderingStrategies.MYBATIS3,"whereClauseProvider")
                ,WkStringUtils.getOrderBySql(orderBy));
        return new ListResponseResult(list);
    }

}
