package com.biz.controller;

import com.biz.common.JsonUtil;
import com.biz.common.ResultDTO;
import com.biz.common.ResultDTOBuilder;
import com.biz.domain.SaleLoginUser;
import com.biz.service.IUserClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

@Controller
@ResponseBody
@RequestMapping("/api/user/")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IUserClient userClient;

    @Resource
    private HttpSession session;

    @Resource
    MessageSource messageSource;

    /**
     * 用户注册
     * api/user/register
     * @return
     */
    @GetMapping("register")
    public Object userRegister(SaleLoginUser saleLoginUser){

       //手动设置id
        saleLoginUser.setId(System.currentTimeMillis()+"");

        if (StringUtils.isBlank(saleLoginUser.getLoginname())){
            return ResultDTOBuilder.failure("10002", "用户名不能为空");
        }
        if (StringUtils.isBlank(saleLoginUser.getPassword())){
            return ResultDTOBuilder.failure("10002", "密码不能为空");
        }

        //注册
        ResultDTO registerResult = userClient.userRegister(saleLoginUser);

        if (registerResult.getSuccess()){
            return ResultDTOBuilder.success(registerResult);
        }else {
            return ResultDTOBuilder.failure(registerResult.getErrCode(), registerResult.getErrMsg());
        }
    }

    /**
     * api/user/login
     * 用户登录
     */
    @GetMapping("login/{loginName}/{password}")
    public Object userLogin(@PathVariable String loginName, @PathVariable String password){

        ResultDTO selectResult = userClient.userLogin(loginName, password);

        String usernfo = JsonUtil.toJson(selectResult.getData());

        log.info("登录成功后的用户信息："+usernfo);

        //登录成功后将用户信息存入cookie中
        if (selectResult.getSuccess() && selectResult.getData() != null) {

            Cookie cookie = new Cookie("userInfo", usernfo);
        }

        //String message = messageSource.getMessage("604", (Object[])null, Locale.getDefault());

        return selectResult;
    }
}
