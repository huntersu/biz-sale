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

    /**
     * 统计status字段状态不是close的数据
     */
    ResultDTO countStatusIsNotClose();

    /**
     * 统计seen_policy_maker状态是已经见到决策人的数据
     */
    ResultDTO countHaveSeenPolicymaker();

    /**
     * 统计is_real字段状态不是null的数据
     */
    ResultDTO countIsReal();
}
