package com.wukong.examples.controller;

import com.wukong.core.controller.dao.SelectMapper;
import com.wukong.core.result.ListResponseResult;
import com.wukong.core.result.Pagination;
import com.wukong.examples.model.StudentScores;
import com.wukong.testNg.AbstractWkBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.MultiValueMap;
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
public class StudentScoresControllerTests extends AbstractWkBaseController {

    private final String baseUrl="/example/studentScores";

    @Autowired
    @SuppressWarnings("all")
    SelectMapper selectMapper;


    @BeforeClass
    public void clearData() {
        String sql="delete from wk_student_scores";
        selectMapper.select(sql);
        System.out.println("clean the  table wk_student_scores");
    }


    @Test(priority = 1)
    public void test1Insert() throws Exception {
        String url=baseUrl+ "/insert";
        List<StudentScores> list = getMockData(StudentScores.class,"mock/StudentScores.json");
        int i=1;
        for (StudentScores studentScores:list) {
            ResultActions resultActions = postJsonForResultActions(url,studentScores);
            resultActions.andExpect(status().isOk());
            if(i==1){
                createDoc(resultActions,url);
                i=2;
            }
            StudentScores renStudentScores= getBody(resultActions,StudentScores.class);
            assertThat(renStudentScores.getStudentScoresId()).isEqualTo(studentScores.getStudentScoresId());
        }
    }



    @Test(priority = 2)
    public void test2SelectByPK() throws Exception {
        String url=baseUrl+ "/selectByPK";
        MultiValueMap paraMap= new HttpHeaders();
        paraMap.add("studentScoresId","1");
        ResultActions resultActions = getForResultActions(url,paraMap);
        resultActions.andExpect(status().isOk());
        createDoc(resultActions,url);

        StudentScores studentScores=getBody(resultActions,StudentScores.class);
        assert studentScores != null;
        assertThat(studentScores.getStudentScoresId()).isEqualTo(1);

    }

    @Test(priority = 3)
    public void test3UpdateByPK() throws Exception {
        String url=baseUrl+ "/updateByPK";
        List<StudentScores> list = getMockData(StudentScores.class,"mock/StudentScores.json");
        StudentScores studentScores=list.get(1);

        ResultActions resultActions = postJsonForResultActions(url,studentScores);
        resultActions.andExpect(status().isOk());
        createDoc(resultActions,url);
        Integer ren= getBody(resultActions,Integer.class);
        assertThat(ren).isGreaterThan(0);
    }

    @Test(priority = 4)
    public void test4Count() throws Exception {
        String url=baseUrl+ "/count";
        ResultActions resultActions = getForResultActions(url);
        resultActions.andExpect(status().isOk());
        createDoc(resultActions,url);
        Integer ren= getBody(resultActions,Integer.class);
        assertThat(ren).isEqualTo(35L);
    }


    @Test(priority = 5)
    public void test5SelectAll() throws Exception {
        String url=baseUrl+ "/selectAll";

        ResultActions resultActions = getForResultActions(url);
        resultActions.andExpect(status().isOk());
        createDoc(resultActions,url);

        List<StudentScores> list = getListBody(resultActions,StudentScores.class);
        for(StudentScores studentScores: list){
            assertThat(studentScores.getStudentScoresId()).isGreaterThan(0);
        }
    }

    private MultiValueMap getWhereMap(){
        MultiValueMap paraMap= new HttpHeaders();
        paraMap.add("pageNum", "1");
        paraMap.add("pageSize", "10");
        paraMap.add("where_studentScoresId", "1");
        paraMap.add("orderBy", "studentScoresId");
        return paraMap;
    }

    @Test(priority = 5)
    public void test5SelectByWhere() throws Exception {
        String url=baseUrl+ "/selectByWhere";

        ResultActions resultActions = postForResultActions(url,getWhereMap());
        resultActions.andExpect(status().isOk());
        createDoc(resultActions,url);

        List<StudentScores> list = getListBody(resultActions,StudentScores.class);
        for(StudentScores studentScores: list){
            assertThat(studentScores.getStudentScoresId()).isGreaterThan(0);
        }
    }

    @Test(priority = 5)
    public void test5SelectByWhereByPage() throws Exception {
        String url=baseUrl+ "/selectByWhereByPage";
        ResultActions resultActions = postForResultActions(url,getWhereMap());
        resultActions.andExpect(status().isOk());
        createDoc(resultActions,url);
        ListResponseResult<StudentScores>  listResponseResult=
                getListResponseResultBody(resultActions,StudentScores.class);

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

        ListResponseResult<StudentScores>  listResponseResult=
                getListResponseResultBody(resultActions,StudentScores.class);

        Pagination pagination = listResponseResult.getPagination();
        assertThat(pagination.getTotal()).isEqualTo(35);
        assertThat(pagination.getTotalPages()).isEqualTo(4);

        List<StudentScores> list = listResponseResult.getList();
        for(StudentScores studentScores:list){
            assertThat(studentScores.getStudentScoresId()).isGreaterThan(0);
        }
    }

    @Test(priority = 7)
    public void test7DeleteByPK() throws Exception {
        String url=baseUrl+ "/deleteByPK";

        MultiValueMap paraMap= new HttpHeaders();
        paraMap.add("studentScoresId","33");

        ResultActions resultActions = getForResultActions(url,paraMap);
        resultActions.andExpect(status().isOk());
        createDoc(resultActions,url);

        Integer ren= getBody(resultActions,Integer.class);
        assertThat(ren).isGreaterThan(0);
    }

}
