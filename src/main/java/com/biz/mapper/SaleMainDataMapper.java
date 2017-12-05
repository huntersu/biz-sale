package com.biz.mapper;

import com.biz.domain.SaleMainData;
import com.biz.domain.SaleMainDataExample;
import com.biz.domain.SaleMainDataWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SaleMainDataMapper {
    int countByExample(SaleMainDataExample example);

    int deleteByExample(SaleMainDataExample example);

    int deleteByPrimaryKey(String id);

    int insert(SaleMainDataWithBLOBs record);

    int insertSelective(SaleMainDataWithBLOBs record);

    List<SaleMainDataWithBLOBs> selectByExampleWithBLOBs(SaleMainDataExample example);

    List<SaleMainData> selectByExample(SaleMainDataExample example);

    SaleMainDataWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SaleMainDataWithBLOBs record, @Param("example") SaleMainDataExample example);

    int updateByExampleWithBLOBs(@Param("record") SaleMainDataWithBLOBs record, @Param("example") SaleMainDataExample example);

    int updateByExample(@Param("record") SaleMainData record, @Param("example") SaleMainDataExample example);

    int updateByPrimaryKeySelective(SaleMainDataWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SaleMainDataWithBLOBs record);

    int updateByPrimaryKey(SaleMainData record);

    List<SaleMainDataWithBLOBs> selectAll();
}