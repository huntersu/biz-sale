package com.biz.service.impl;

import com.biz.common.JsonUtil;
import com.biz.common.ResultDTO;
import com.biz.common.ResultDTOBuilder;
import com.biz.common.UUIDUtils;
import com.biz.domain.SaleLoginUser;
import com.biz.domain.SaleLoginUserExample;
import com.biz.util.AES;
import org.apache.commons.lang3.StringUtils;
import com.biz.mapper.SaleLoginUserMapper;
import com.biz.service.IUserClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户登录测试类
 */
@Service
public class UserImpl implements IUserClient {
    private static final Logger log = LoggerFactory.getLogger(UserImpl.class);
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

        log.info("impl - 用户注册法入参：" + JsonUtil.toJson(saleLoginUser));
        if (StringUtils.isBlank(saleLoginUser.getLoginname())) {
            return ResultDTOBuilder.failure("10002", "用户名不能为空");
        }
        if (StringUtils.isBlank(saleLoginUser.getPassword())) {
            return ResultDTOBuilder.failure("10002", "密码不能为空");
        }

        //DigestUtils是spring提供的工具类
        saleLoginUser.setPassword(AES.base64Encode(saleLoginUser.getPassword().getBytes()));

        ResultDTO<SaleLoginUser> userInfo = this.findUserInfoByUserName(saleLoginUser.getLoginname());

        if (userInfo.isSuccess() && userInfo.getData() != null) {
            return ResultDTOBuilder.failure("10006", "用户名已存在");
        } else if(!userInfo.isSuccess()){
            return ResultDTOBuilder.failure(userInfo.getErrCode(), userInfo.getErrMsg());
        }

        try {
            int insertNum = saleLoginUserMapper.insert(saleLoginUser);
            log.info("impl - 用户注册返回结果：***" + insertNum + "***");

            if (insertNum != 1){
                return ResultDTOBuilder.failure("10003", "注册失败，请稍后重试");
            }

            return ResultDTOBuilder.success(true);
        } catch (Exception e) {
            log.error("impl - 用户注册(异常)：", e);
            return ResultDTOBuilder.failure("10003", "服务异常，请稍后重试");
        }
    }

    /**
     * 根据用户名查询用户信息
     * @param loginName
     * @return
     */
    public ResultDTO<SaleLoginUser> findUserInfoByUserName(String loginName) {

        SaleLoginUserExample example = new SaleLoginUserExample();
        example.createCriteria().andLoginnameEqualTo(loginName);

        try {
            //查询用户信息
            List<SaleLoginUser> saleLoginUsers = saleLoginUserMapper.selectByExample(example);
            log.info("impl - 根据用户名查询用户信息返回结果：" + JsonUtil.toJson(saleLoginUsers));

            if (saleLoginUsers.size() > 1){
                return ResultDTOBuilder.failure("10005", "用户信息重复，请联系管理员");
            }

            return ResultDTOBuilder.success(saleLoginUsers.get(0));
        } catch (Exception e) {
            log.error("impl - 根据用户名查询过用户信息(异常)：", e);
            return ResultDTOBuilder.failure("10003", "服务异常，请稍后重试");
        }
    }

    /**
     * 用户登录
     * @param loginName
     * @param password
     * @return
     */
    public ResultDTO<SaleLoginUser> userLogin(String loginName, String password) {

        log.info("impl - 用户登录时的方法入参：loginName = " + loginName + "、password = " + password);

        ResultDTO<SaleLoginUser> saleLoginUsers = this.findUserInfoByUserName(loginName);

        if (!saleLoginUsers.isSuccess()){
            return saleLoginUsers;
        }

        if (saleLoginUsers == null){
            return ResultDTOBuilder.failure("10004", "用户名不存在，请核对后重试");
        }

        String userPass = null;
        try {
            userPass = AES.aesEncrypt(password,"");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultDTOBuilder.failure("-99999", "系统出错");
        }

        SaleLoginUser saleLoginUser = saleLoginUsers.getData();

        if (!saleLoginUser.getPassword().equals(userPass)) {
            return ResultDTOBuilder.failure("10004", "密码错误，请重新输入");
        }

        return ResultDTOBuilder.success(saleLoginUser);
    }
}
