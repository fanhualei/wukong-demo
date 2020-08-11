package com.wukong.examples.service.base;

import com.github.pagehelper.PageHelper;
import com.wukong.core.result.ListResponseResult;
import com.wukong.core.util.WkStringUtils;
import com.wukong.examples.mapper.CourseDao;
import com.wukong.examples.model.Course;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.where.WhereDSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@SuppressWarnings("unchecked")
public class BaseCourseService {
    @SuppressWarnings("all")
    @Autowired
    protected  CourseDao courseDao;

    /**
     * insert selective - ignores null properties
     * @param record object of insert
     * @return  insert number
     */
    @Transactional
    public int insertSelective(Course record) {
        return courseDao.insertSelective(record);
    }

    /**
     * Select by Primary Key
     * @param courseId primary key
     * @return selected object
     */
    public Optional<Course> selectByPrimaryKey(Long courseId) {
        return courseDao.selectByPrimaryKey(courseId);
    }

    /**
     * Update by Primary Key Selective - ignores null values
     * @param record the object will update
     * @return update number
     */
    @Transactional
    public int updateByPrimaryKeySelective(Course record) {
        return courseDao.updateByPrimaryKeySelective(record);
    }


    /**
     * delete by Primary Key
     * @param courseId primary key
     * @return deleted number
     */
    @Transactional
    public  int deleteByPrimaryKey(Long courseId) {
        return courseDao.deleteByPrimaryKey(courseId);
    }

    /**
     * select all recorders
     * @return objects of list
     */
    public List<Course> selectAll() {
        return courseDao.select(c -> c);
    }

    /**
     * select row by page
     * @param pageNum page number
     * @param pageSize page size
     * @return list of result
     */
    public ListResponseResult selectAllByPage(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Course> list = courseDao.select(c->c);
        return new ListResponseResult(list);
    }

    /**
    * 根据前台传入的数据进行查询
    * @param whereDSL  where条件
    * @param orderBy   不带order by 的排序字符串
    * @return  list
    */
    public List<Course>  selectByWhere(WhereDSL whereDSL
            , String orderBy){
        return courseDao.selectByWhere(whereDSL.build().render(RenderingStrategies.MYBATIS3,"whereClauseProvider")
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
        List<Course> list = courseDao.selectByWhere(whereDSL.build().render(RenderingStrategies.MYBATIS3,"whereClauseProvider")
                ,WkStringUtils.getOrderBySql(orderBy));
        return new ListResponseResult(list);
    }

}
