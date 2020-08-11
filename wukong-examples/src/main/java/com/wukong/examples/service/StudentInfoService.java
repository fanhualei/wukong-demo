package com.wukong.examples.service;

import com.github.pagehelper.PageHelper;
import com.wukong.core.result.ListResponseResult;
import com.wukong.examples.mapper.StudentInfoDao;
import com.wukong.examples.model.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@SuppressWarnings("unchecked")
public class StudentInfoService {
    @SuppressWarnings("all")
    @Autowired
    private StudentInfoDao studentInfoDao;

    /**
     * count of all rows
     * @return  number of rows
     */
    public long count(){
        return studentInfoDao.count(c->c);
    }

    /**
     * insert selective - ignores null properties
     * @param record object of insert
     * @return  insert number
     */
    public int insert(StudentInfo record) {
        return studentInfoDao.insertSelective(record);
    }

    /**
     * Select by Primary Key
     * @param studentInfoId primary key
     * @return selected object
     */
    public StudentInfo selectByPK(Long studentInfoId) {
        Optional<StudentInfo>  opt= studentInfoDao.selectByPrimaryKey(studentInfoId);
        return opt.get();
    }

    /**
     * Update by Primary Key Selective - ignores null values
     * @param record the object will update
     * @return update number
     */
    public int updateByPK(StudentInfo record) {
        return studentInfoDao.updateByPrimaryKeySelective(record);
    }


    /**
     * delete by Primary Key
     * @param studentInfoId primary key
     * @return deleted number
     */
    public  int deleteByPK(Long studentInfoId) {
        return studentInfoDao.deleteByPrimaryKey(studentInfoId);
    }

    /**
     * select all recorders
     * @return objects of list
     */
    public List<StudentInfo> selectAll() {
        return studentInfoDao.select(c -> c);
    }

    /**
     * select row by page
     * @param pageNum page number
     * @param pageSize page size
     * @return list of result
     */
    public ListResponseResult selectAllByPage(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentInfo> list = studentInfoDao.select(c->c);
        return new ListResponseResult(list);
    }

}
