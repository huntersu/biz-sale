package com.biz.mapper;

import com.biz.domain.SaleMainData;
import com.biz.domain.SaleMainDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaleMainDataMapper {
    int countByExample(SaleMainDataExample example);

    int deleteByExample(SaleMainDataExample example);

    int deleteByPrimaryKey(String id);

    int insert(SaleMainData record);

    int insertSelective(SaleMainData record);

    List<SaleMainData> selectByExampleWithBLOBs(SaleMainDataExample example);

    List<SaleMainData> selectByExample(SaleMainDataExample example);

    SaleMainData selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SaleMainData record, @Param("example") SaleMainDataExample example);

    int updateByExampleWithBLOBs(@Param("record") SaleMainData record, @Param("example") SaleMainDataExample example);

    int updateByExample(@Param("record") SaleMainData record, @Param("example") SaleMainDataExample example);

    int updateByPrimaryKeySelective(SaleMainData record);

    int updateByPrimaryKeyWithBLOBs(SaleMainData record);

    int updateByPrimaryKey(SaleMainData record);

    //分页查询所有
    List<SaleMainData> selectAll();

    //关闭/重新打开status状态
    int resetSaleMainDataStatusById(String id, String status);
}