package com.biz.service;

import com.biz.common.ResultDTO;
import com.biz.common.ResultDTOBuilder;
import com.biz.domain.SaleMainData;
import com.github.pagehelper.PageInfo;

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
     * 根据id修改数据
     */
    ResultDTO<Boolean> updata(SaleMainData saleMainData);

    /**
     * 根据id查询
     */
    ResultDTO<SaleMainData> findById(String id);

    /**
     * 查询所有
     */
    ResultDTO<PageInfo<SaleMainData>> findAll(int page, int rows);

    /**
     * 统计查询
     */
    ResultDTO countQuery();

    /**
     * 关闭/重新打开status状态
     */
    ResultDTO resetStatusById(String id, String status);
}
