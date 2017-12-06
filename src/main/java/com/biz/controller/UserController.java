package com.biz.controller;

import com.biz.common.CookieUtils;
import com.biz.common.JsonUtil;
import com.biz.common.ResultDTO;
import com.biz.common.ResultDTOBuilder;
import com.biz.domain.SaleLoginUser;
import com.biz.service.IUserClient;
import com.biz.util.AES;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

@Controller
@ResponseBody
@RequestMapping("/api/user/")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    //用户登录后的session过期时间(7天)
    private static final int SESSION_TIMEOUT = 604800;

    @Resource
    private IUserClient userClient;

    @Resource
    private HttpSession session;

    @Resource
    private MessageSource messageSource;

    /**
     * 用户注册
     * api/user/register
     *
     * @return
     */
    @GetMapping("register")
    public Object userRegister(SaleLoginUser saleLoginUser) {

        //手动设置id
        saleLoginUser.setId(System.currentTimeMillis() + "");

        if (StringUtils.isBlank(saleLoginUser.getLoginname())) {
            return ResultDTOBuilder.failure("10002", "用户名不能为空");
        }
        if (StringUtils.isBlank(saleLoginUser.getPassword())) {
            return ResultDTOBuilder.failure("10002", "密码不能为空");
        }

        //注册
        ResultDTO registerResult = userClient.userRegister(saleLoginUser);

        if (registerResult.getSuccess()) {
            return ResultDTOBuilder.success(registerResult);
        } else {
            return ResultDTOBuilder.failure(registerResult.getErrCode(), registerResult.getErrMsg());
        }
    }

    /**
     * api/user/login
     * 用户登录
     */
    @GetMapping("login/{loginName}/{password}")
    public Object userLogin(HttpServletResponse response, HttpServletRequest request, @PathVariable String loginName, @PathVariable String password) throws Exception {

        ResultDTO selectResult = userClient.userLogin(loginName, password);

        //登录成功后将用户信息存入cookie中
        if (selectResult.getSuccess() && selectResult.getData() != null) {
            SaleLoginUser saleLoginUser = (SaleLoginUser) selectResult.getData();

            String userName = saleLoginUser.getLoginname();

            String userFlag = null;

            userFlag = AES.aesEncrypt(userName+"<split>"+saleLoginUser.getPassword(), "");


            //cookie过期时间7天
            CookieUtils.setCookie(request, response, "USN", userFlag, SESSION_TIMEOUT, true);


        }

        //String message = messageSource.getMessage("604", (Object[])null, Locale.getDefault());

        return selectResult;
    }
}
