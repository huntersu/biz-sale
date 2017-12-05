package com.biz.service.impl;

import com.biz.common.ResultDTO;
import com.biz.common.ResultDTOBuilder;
import com.biz.domain.SaleLoginUser;
import com.biz.domain.SaleLoginUserExample;
import org.apache.commons.lang3.StringUtils;
import com.biz.mapper.SaleLoginUserMapper;
import com.biz.service.IUserClient;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户登录测试类
 */
@Service
public class UserLoginImpl implements IUserClient {

    @Resource
    private SaleLoginUserMapper saleLoginUserMapper;

    /**
     * 用户注册
     * @param saleLoginUser
     * @return
     */
    public ResultDTO userRegister(SaleLoginUser saleLoginUser) {

        if (StringUtils.isNotBlank(saleLoginUser.getPassword())){
            //DigestUtils是spring提供的工具类
            saleLoginUser.setPassword(DigestUtils.md5DigestAsHex(saleLoginUser.getPassword().getBytes()));
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
            return ResultDTOBuilder.failure("10000", "用户信息重复，请联系管理员");
        }

        return ResultDTOBuilder.success(saleLoginUsers.get(0));
    }

    /**
     * 用户登录
     * @param userId
     * @return
     */
    public ResultDTO userLogin(String userId) {

        SaleLoginUserExample example = new SaleLoginUserExample();

        SaleLoginUserExample.Criteria criteria = example.createCriteria();

        criteria.andIdEqualTo(userId);

        List<SaleLoginUser> saleLoginUsers = saleLoginUserMapper.selectByExample(example);

        Boolean isSuccess = true;
        if (saleLoginUsers == null || saleLoginUsers.size() < 1 || saleLoginUsers.isEmpty()){
            isSuccess = false;
        }

        ResultDTO result = new ResultDTO();
        result.setSuccess(isSuccess);
        result.setErrCode(isSuccess?"000000":"-1");
        result.setErrMsg(isSuccess?"接口调用成功":"接口调用失败");
        result.setData(saleLoginUsers);

        return result;
    }
}
