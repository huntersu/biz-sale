package com.biz.mapper;

import com.biz.domain.SaleLoginUser;
import com.biz.domain.SaleLoginUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaleLoginUserMapper {
    int countByExample(SaleLoginUserExample example);

    int deleteByExample(SaleLoginUserExample example);

    int deleteByPrimaryKey(String id);

    int insert(SaleLoginUser record);

    int insertSelective(SaleLoginUser record);

    List<SaleLoginUser> selectByExample(SaleLoginUserExample example);

    SaleLoginUser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SaleLoginUser record, @Param("example") SaleLoginUserExample example);

    int updateByExample(@Param("record") SaleLoginUser record, @Param("example") SaleLoginUserExample example);

    int updateByPrimaryKeySelective(SaleLoginUser record);

    int updateByPrimaryKey(SaleLoginUser record);

    List<SaleLoginUser> findAllUser();
}