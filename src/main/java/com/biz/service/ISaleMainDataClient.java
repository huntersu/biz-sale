package com.biz.service;

import com.biz.common.ResultDTO;
import com.biz.domain.SaleMainData;
import com.biz.domain.SaleMainDataWithBLOBs;

public interface ISaleMainDataClient {

    /**
     * 新增
     */
    ResultDTO insert(SaleMainDataWithBLOBs saleMainDataWithBLOBs);

    /**
     * 根据id删除数据
     */
    ResultDTO deleteById(String id);

    /**
     * 根据id修改数据
     */
    ResultDTO updata(SaleMainDataWithBLOBs saleMainDataWithBLOBs);

    /**
     * 根据id查询
     */
    ResultDTO findById(String id);

    /**
     * 查询所有
     */
    ResultDTO findAll(int page, int rows);
}
