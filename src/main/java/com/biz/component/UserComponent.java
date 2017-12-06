package com.biz.component;

import com.biz.common.CookieUtils;
import com.biz.common.ResultDTO;
import com.biz.domain.SaleLoginUser;
import com.biz.service.IUserClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by suyi on 2017/12/6.
 */
@Component
public class UserComponent {


    @Resource
    private IUserClient userClient;



    public SaleLoginUser checkUser(HttpServletRequest request){



        String userSign = CookieUtils.getCookieValue(request,"USN",true);


        if(StringUtils.isBlank(userSign)){
            return null;
        }

        String[] userNameAndPass = userSign.split("<split>");

        ResultDTO selectResult = userClient.userLogin(userNameAndPass[0], userNameAndPass[1]);

        //登录成功后将用户信息存入cookie中
        if (selectResult.getSuccess() && selectResult.getData() != null) {

            return (SaleLoginUser)selectResult.getData();

        }

        return null;

    }

}
