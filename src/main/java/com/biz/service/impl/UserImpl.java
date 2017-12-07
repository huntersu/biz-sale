package com.biz.service.impl;

import com.biz.common.ResultDTO;
import com.biz.common.ResultDTOBuilder;
import com.biz.common.UUIDUtils;
import com.biz.domain.SaleLoginUser;
import com.biz.domain.SaleLoginUserExample;
import com.biz.util.AES;
import org.apache.commons.lang3.StringUtils;
import com.biz.mapper.SaleLoginUserMapper;
import com.biz.service.IUserClient;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户登录测试类
 */
@Service
public class UserImpl implements IUserClient {

    @Resource
    private SaleLoginUserMapper saleLoginUserMapper;

    /**
     * 用户注册
     * @param saleLoginUser
     * @return
     */
    public ResultDTO userRegister(SaleLoginUser saleLoginUser) {

        //手动设置id
        saleLoginUser.setId(UUIDUtils.genratorCode());

        if (StringUtils.isBlank(saleLoginUser.getLoginname())) {
            return ResultDTOBuilder.failure("10002", "用户名不能为空");
        }
        if (StringUtils.isBlank(saleLoginUser.getPassword())) {
            return ResultDTOBuilder.failure("10002", "密码不能为空");
        }

        //DigestUtils是spring提供的工具类
        saleLoginUser.setPassword(AES.base64Encode(saleLoginUser.getPassword().getBytes()));

        ResultDTO userInfo = this.findUserInfoByUserName(saleLoginUser.getLoginname());

        if (userInfo.getSuccess() && userInfo.getData() != null) {
            return ResultDTOBuilder.failure("10006", "用户名已存在");
        } else if(!userInfo.getSuccess()){
            return ResultDTOBuilder.failure(userInfo.getErrCode(), userInfo.getErrMsg());
        }

        int insertNum = saleLoginUserMapper.insert(saleLoginUser);

        if (insertNum == 1){
            return ResultDTOBuilder.success(true);
        }

        return ResultDTOBuilder.failure("10003", "注册失败，请稍后重试");
    }

    /**
     * 根据用户名查询用户信息
     * @param loginName
     * @return
     */
    public ResultDTO findUserInfoByUserName(String loginName) {

        SaleLoginUserExample example = new SaleLoginUserExample();
        example.createCriteria().andLoginnameEqualTo(loginName);

        //查询用户信息
        List<SaleLoginUser> saleLoginUsers = saleLoginUserMapper.selectByExample(example);

        if(saleLoginUsers == null || saleLoginUsers.isEmpty() || saleLoginUsers.size() <= 0){
            return ResultDTOBuilder.success(null);
        }

        if (saleLoginUsers.size() > 1){
            return ResultDTOBuilder.failure("10005", "用户信息重复，请联系管理员");
        }

        return ResultDTOBuilder.success(saleLoginUsers.get(0));
    }

    /**
     * 用户登录
     * @param loginName
     * @param password
     * @return
     */
    public ResultDTO userLogin(String loginName, String password) {

        SaleLoginUserExample example = new SaleLoginUserExample();

        example.createCriteria().andLoginnameEqualTo(loginName);

        List<SaleLoginUser> saleLoginUsers = saleLoginUserMapper.selectByExample(example);

        if (saleLoginUsers == null || saleLoginUsers.size() < 1 || saleLoginUsers.isEmpty()){
            return ResultDTOBuilder.failure("10004", "用户名不存在，请核对后重试");
        }

        if (saleLoginUsers.size() > 1) {
            return ResultDTOBuilder.failure("10005", "用户信息重复，请联系管理员");
        }

        //String userPass = DigestUtils.md5DigestAsHex(password.getBytes());

        String userPass = null;
        try {
            userPass = AES.aesEncrypt(password,"");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDTOBuilder.failure("-99999", "系统出错");
        }

        if (!saleLoginUsers.get(0).getPassword().equals(userPass)) {
            return ResultDTOBuilder.failure("10004", "密码错误，请重新输入");
        }

        return ResultDTOBuilder.success(saleLoginUsers.get(0));
    }
}
