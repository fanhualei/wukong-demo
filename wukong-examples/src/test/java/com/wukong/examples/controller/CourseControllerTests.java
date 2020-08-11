package com.wukong.examples.controller;

import com.wukong.core.controller.dao.SelectMapper;
import com.wukong.core.result.ListResponseResult;
import com.wukong.core.result.Pagination;
import com.wukong.examples.model.Course;
import com.wukong.testNg.AbstractWkBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.MultiValueMap;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author fan
 * testNg has a bug:  priority is not good work
 * testNg的bug，所以如果按照顺序来执行，那么每个函数的名字必须要用数字开头，这样能排序。
 *
 */
@SpringBootTest
@SuppressWarnings("unchecked")
public class CourseControllerTests extends AbstractWkBaseController {

    private final String baseUrl="/example/course";

    @Autowired
    @SuppressWarnings("all")
    SelectMapper selectMapper;

    private  Course lastCourse=new Course();


    @BeforeClass
    @AfterClass
    public void clearData() {
        String sql="delete from wk_course";
        selectMapper.select(sql);
        System.out.println("clean the  table wk_course");
    }


    @Test(priority = 1)
    public void test1Insert() throws Exception {
        String url=baseUrl+ "/insert";
        List<Course> list = getMockData(Course.class,"mock/Course.json");
        int i=1;
        for (Course course:list) {
            ResultActions resultActions = postJsonForResultActions(url,course);
            resultActions.andExpect(status().isOk());
            if(i==1){
                createDoc(resultActions,url);
                i=2;
            }
            Course renCourse= getBody(resultActions,Course.class);
            lastCourse=renCourse;
        }
    }



    @Test(priority = 2)
    public void test2SelectByPrimaryKey() throws Exception {
        String url=baseUrl+ "/selectByPk";
        MultiValueMap paraMap= new HttpHeaders();
        paraMap.add("courseId",String.valueOf(lastCourse.getCourseId()));
        ResultActions resultActions = getForResultActions(url,paraMap);
        resultActions.andExpect(status().isOk());
        createDoc(resultActions,url);

        Course course=getBody(resultActions,Course.class);
        assert course != null;
        assertThat(course.getCourseId()).isEqualTo(lastCourse.getCourseId());

    }

    @Test(priority = 3)
    public void test3UpdateByPK() throws Exception {
        String url=baseUrl+ "/updateByPK";

        ResultActions resultActions = postJsonForResultActions(url,lastCourse);
        resultActions.andExpect(status().isOk());
        createDoc(resultActions,url);
        Integer ren= getBody(resultActions,Integer.class);
        assertThat(ren).isGreaterThan(0);
    }

    @Test(priority = 5)
    public void test5SelectAll() throws Exception {
        String url=baseUrl+ "/selectAll";

        ResultActions resultActions = getForResultActions(url);
        resultActions.andExpect(status().isOk());
        createDoc(resultActions,url);

        List<Course> list = getListBody(resultActions,Course.class);
        for(Course course: list){
            assertThat(course.getCourseId()).isGreaterThan(0);
        }
    }

    private MultiValueMap getWhereMap(){
        MultiValueMap paraMap= new HttpHeaders();
        paraMap.add("pageNum", "1");
        paraMap.add("pageSize", "10");
        paraMap.add("where_courseId", String.valueOf(lastCourse.getCourseId()));
        paraMap.add("orderBy", "courseId");
        return paraMap;
    }

    @Test(priority = 5)
    public void test5SelectByWhere() throws Exception {
        String url=baseUrl+ "/selectByWhere";

        ResultActions resultActions = postForResultActions(url,getWhereMap());
        resultActions.andExpect(status().isOk());
        createDoc(resultActions,url);

        List<Course> list = getListBody(resultActions,Course.class);
        for(Course course: list){
            assertThat(course.getCourseId()).isGreaterThan(0);
        }
    }

    @Test(priority = 5)
    public void test5SelectByWhereByPage() throws Exception {
        String url=baseUrl+ "/selectByWhereByPage";
        ResultActions resultActions = postForResultActions(url,getWhereMap());
        resultActions.andExpect(status().isOk());
        createDoc(resultActions,url);
        ListResponseResult<Course>  listResponseResult=
                getListResponseResultBody(resultActions,Course.class);

        List list = listResponseResult.getList();
        assertThat(list.size()).isGreaterThanOrEqualTo(0);
    }


    @Test(priority = 6)
    public void test6SelectAllByPage() throws Exception {
        String url=baseUrl+ "/selectAllByPage";
        MultiValueMap paraMap= new HttpHeaders();
        paraMap.add("pageNum","2");
        paraMap.add("pageSize","10");


        ResultActions resultActions = getForResultActions(url,paraMap);
        resultActions.andExpect(status().isOk());
        createDoc(resultActions,url);

        ListResponseResult<Course>  listResponseResult=
                getListResponseResultBody(resultActions,Course.class);

        Pagination pagination = listResponseResult.getPagination();
        assertThat(pagination.getTotal()).isEqualTo(35);
        assertThat(pagination.getTotalPages()).isEqualTo(4);

        List<Course> list = listResponseResult.getList();
        for(Course course:list){
            assertThat(course.getCourseId()).isGreaterThan(0);
        }
    }

    @Test(priority = 7)
    public void test7DeleteByPK() throws Exception {
        String url=baseUrl+ "/deleteByPK";

        MultiValueMap paraMap= new HttpHeaders();
        paraMap.add("courseId",String.valueOf(lastCourse.getCourseId()));

        ResultActions resultActions = getForResultActions(url,paraMap);
        resultActions.andExpect(status().isOk());
        createDoc(resultActions,url);

        Integer ren= getBody(resultActions,Integer.class);
        assertThat(ren).isGreaterThan(0);
    }

}
