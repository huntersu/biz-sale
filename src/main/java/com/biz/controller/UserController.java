package com.biz.controller;

import com.biz.common.ResultDTO;
import com.biz.common.ResultDTOBuilder;
import com.biz.domain.SaleLoginUser;
import com.biz.service.IUserClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Random;
import java.util.UUID;

@Controller
@ResponseBody
@RequestMapping("/api/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Resource
    private IUserClient userClient;

    /**
     * 用户注册
     * api/user/register
     * @return
     */
    @GetMapping("/register")
    public Object userRegister(SaleLoginUser saleLoginUser){

       //手动设置id
        saleLoginUser.setId((System.currentTimeMillis())+"");

        if (StringUtils.isBlank(saleLoginUser.getLoginname())){
            return ResultDTOBuilder.failure("10002", "用户名不能为空");
        }
        if (StringUtils.isBlank(saleLoginUser.getPassword())){
            return ResultDTOBuilder.failure("10002", "密码不能为空");
        }

        //注册前校验用户名是否已经存在
        ResultDTO userInfoByUserName = userClient.findUserInfoByUserName(saleLoginUser.getLoginname());

        if (userInfoByUserName.getSuccess() && userInfoByUserName.getData() == null) {

            ResultDTO registerResult = userClient.userRegister(saleLoginUser);
            if (registerResult.getSuccess()){
                return ResultDTOBuilder.success(registerResult);
            }else {
                return ResultDTOBuilder.failure(registerResult.getErrCode(), registerResult.getErrMsg());
            }
        }

        return ResultDTOBuilder.failure(userInfoByUserName.getErrCode(), userInfoByUserName.getErrMsg());
    }

    /**
     * api/user/login
     * 用户登录
     */
    @GetMapping("/login/{userId}")
    public Object userLogin(@PathVariable String userId){

        ResultDTO selectResult = userClient.userLogin(userId);

        return selectResult;
    }
}
