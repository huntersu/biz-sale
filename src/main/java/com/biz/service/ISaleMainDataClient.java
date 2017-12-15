package com.biz.service;

import com.biz.common.ResultDTO;
import com.biz.domain.SaleMainData;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ISaleMainDataClient {

    /**
     * 新增
     */
    ResultDTO<Boolean> insert(SaleMainData saleMainData);

    /**
     * 根据id删除数据
     */
    ResultDTO<Boolean> deleteById(String id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    ResultDTO<Boolean> deleteByIds(List<String> ids);

    /**
     * 根据id修改数据
     */
    ResultDTO<Boolean> updata(SaleMainData saleMainData);

    /**
     * 根据id查询
     */
    ResultDTO<SaleMainData> findById(String id);

    /**
     * 多条件查询
     */
    ResultDTO<List<SaleMainData>> findUserBySelective(SaleMainData saleMainData);

    /**
     * 查询所有
     */
    ResultDTO<PageInfo<SaleMainData>> associativeSelectAll(int page, int rows);

    /**
     * 统计查询
     */
    ResultDTO countQuery();

    /**
     * 关闭/重新打开status状态
     */
    ResultDTO resetStatusById(String id, String status);
}
