package com.biz.controller;

import com.biz.common.ResultDTO;
import com.biz.domain.SaleLoginUser;
import com.biz.service.IUserClient;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
//@ResponseBody
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private IUserClient userClient;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 用户注册
     * api/user/register
     * @return
     */
    @RequestMapping("/register")
    public Object userRegister(){

        SaleLoginUser saleLoginUser = new SaleLoginUser();
        saleLoginUser.setId(UUID.randomUUID().toString());
        saleLoginUser.setName("zhangsan");
        saleLoginUser.setPassword("123456");
        saleLoginUser.setDepartment("销售部");
        saleLoginUser.setLoginname("登录名");

        ResultDTO registerResult = userClient.userRegister(saleLoginUser);

        return registerResult;
    }

    /**
     * api/user/login
     * 用户登录
     */
    @RequestMapping("/login/{userId}")
    public Object userLogin(@PathVariable String userId){

        ResultDTO selectResult = userClient.userLogin(userId);

        return selectResult;
    }
}
