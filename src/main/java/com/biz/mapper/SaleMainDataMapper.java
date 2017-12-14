package com.biz.mapper;

import com.biz.domain.SaleMainData;
import java.util.List;

public interface SaleMainDataMapper {
    /**
     * 新增数据
     * @param saleMainData
     * @return
     */
    int save(SaleMainData saleMainData);

    /**
     * 查询用户表中的所有数据
     * @return
     */
    List<SaleMainData> selectAll();

    /**
     * 关联查询用户表中的所有数据
     * @return
     */
    List<SaleMainData> associativeSelectAll();

    /**
     * 多条件查询
     * @param saleMainData
     * @return
     */
    List<SaleMainData> findSaleMainDataBySelective(SaleMainData saleMainData);

    /**
     * 根据id查询
     * @param userId
     * @return
     */
    SaleMainData selectById(String userId);

    /**
     * 多条件统计查询
     * @param saleMainData
     * @return
     */
    int countBySelective(SaleMainData saleMainData);


    /**
     * 统计状态 status不等于close
     * @return
     */
    int countStatusIsNotCloseNum();
    /**
     * 统计seen_policy_maker状态是已经见到决策人,并且status!=cloes的数据
     * @return
     */
    int countSeenPolicymakerNum();
    /**
     * 统计is_real字段状态不是null并且status!=cloes的数据
     * @return
     */
    int countIsRealNum();
    /**
     * 统计five_user_up字段状态不是null并且status!=cloes的数据
     * @return
     */
    int countFiveUserupIsRealNum();


    /**
     * 根据ID修改数据
     * @param saleMainData
     * @return
     */
    int updateById(SaleMainData saleMainData);

    /**
     * 根据ID动态修改数据
     * @param saleMainData
     * @return
     */
    int updateByIdSelective(SaleMainData saleMainData);

    /**
     * 根据ID删除数据
     * @param userId
     * @return
     */
    int deleteById(String userId);

    /**
     * 删除所有数据
     * @return
     */
    int deleteAll();

    /**
     * 关闭/重新打开status状态
     * @param id
     * @param status
     * @return
     */
    int resetSaleMainDataStatusById(String id, String status);
}