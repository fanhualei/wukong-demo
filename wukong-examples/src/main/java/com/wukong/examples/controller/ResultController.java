package com.wukong.examples.controller;


import com.google.common.collect.Lists;
import com.wukong.core.exceptions.BusinessException;
import com.wukong.core.util.LocaleMessageSourceUtil;
import com.wukong.examples.entity.City;


import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.*;


/**
 * @author fanhl
 */
@RestController
@RequestMapping("/example/result")
@Validated
@SuppressWarnings("unchecked")
public class ResultController {


    @Autowired
    private LocaleMessageSourceUtil messageUtil;



    /**
     * 一个对象的自动包裹
     * http://localhost:8080/result/success
     */

    @RequestMapping("/success")
    public City success1() {
        return success();
    }



    private City success(){
        return new City(1,"city1","001");
    }

    /**
     * 测试参数异常
     */
    @RequestMapping("/para")
    public String para1(@RequestParam @Valid @Length(min = 6,max = 50)  String name
            ,@RequestParam @Valid @Email String email
            ,@RequestParam String cellPhone
    ) {
        return "name:"+name+";"+"email:"+email+";"+"cellPhone:"+cellPhone+";";
    }



    /**
     * 测试返回类型
     */

    @RequestMapping("/type")
    public Object type1(@RequestParam Integer code)
    {
        return type(code);
    }


    private Object type(Integer code){
        if(code==1){
            return "测试返回的函数";
        }else if(code==2) {
            return 1;
        }else if(code==3){
            return null;
        }else if(code==4){
            Map<String,String> ren= new HashMap<>();
            ren.put("key1","value1");
            ren.put("key2","value2");
            return ren;
        }else if(code==5){
            List<String> list = new ArrayList<>();
            list.add("user1");
            list.add("user2");
            return list;
        }else if(code==6){
            return new Date();
        }else if(code==7){
            return new Exception("dddd");
        }
        return null;
    }



    /**
     * 异常的自动包裹
     * http://localhost:8080/result/exception?code=1
     */
    @RequestMapping("/exception")
    public String exception1(Integer code) {
        return exception(code);
    }




    /**
     * 通过不同的code 得到错误信息
     * @param code 编号
     * @return  返回数据
     */
    private String exception(Integer code){
        if(code==1){
            throw new RuntimeException("my throws error");
        }else if(code==2){
            throw new NullPointerException();
        }else if(code==3){
            throw new NumberFormatException();
        }else if(code==4) {
            //throw new RuntimeException("my throws 11111111111111111111");
            throw new BusinessException(messageUtil.getMessage("welcome",null,"缺省字符串"));
        }else if(code==5){
            Map p=new HashMap();
            p.put("email","不是一个合法的电子邮件地址");
            p.put("name","长度需要在6和50之间");


            throw new BusinessException(p);
        }
        else if(code==6){

            String message=messageUtil.getMessage("welcome1"
                    ,Lists.newArrayList("admin").toArray());
            throw new BusinessException(message);
        }
        else if(code==7){
            String message=messageUtil.getMessage("welcome2"
                    ,Lists.newArrayList("admin","manage").toArray());
            throw new BusinessException(message);
        }
        return "ok";
    }







}
