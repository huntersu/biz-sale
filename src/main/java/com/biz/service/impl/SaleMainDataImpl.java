package com.biz.service.impl;

import com.biz.common.JsonUtil;
import com.biz.common.ResultDTO;
import com.biz.common.ResultDTOBuilder;
import com.biz.common.UUIDUtils;
import com.biz.constant.FiveUserupResult;
import com.biz.constant.IsReal;
import com.biz.constant.SaleMainStatus;
import com.biz.constant.SeenPolicymaker;
import com.biz.domain.SaleMainData;
import com.biz.domain.SaleMainDataExample;
import com.biz.domain.SaleMainDataWithBLOBs;
import com.biz.mapper.SaleMainDataMapper;
import com.biz.service.ISaleMainDataClient;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleMainDataImpl implements ISaleMainDataClient{

    private static final Logger log = LoggerFactory.getLogger(SaleMainDataImpl.class);

    @Resource
    private SaleMainDataMapper saleMainDataMapper;

    /**
     * 插入
     * @param saleMainDataWith
     * @return
     */
    public ResultDTO<Boolean> insert(SaleMainDataWithBLOBs saleMainDataWith) {
        saleMainDataWith.setId(UUIDUtils.genratorCode());
        saleMainDataWith.setBeginDate(new Date());
        saleMainDataWith.setUpdateDate(new Date());
        saleMainDataWith.setStatus(SaleMainStatus.OPEN.getValue());

        log.info("impl — 保存sale_main_data对象数据内容：" + JsonUtil.toJson(saleMainDataWith));
        try {
            int insert = saleMainDataMapper.insert(saleMainDataWith);

            log.info("保存sale_main_data对象数据 - 结果：******" + insert + "******");

            if (insert != 1) {
                return ResultDTOBuilder.failure("10007", "数据添加失败，请稍后重试");
            }

            return ResultDTOBuilder.success(true);
        } catch (Exception e) {
            log.error("保存sale_main_data对象数据(异常)：", e);
            return ResultDTOBuilder.failure("10007", "服务异常，请稍后重试");
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public ResultDTO<Boolean> deleteById(String id) {
        log.info("impl - 根据ID删除sale_main_data表中数据时的ID：***" + id + "***");
        try {
            int isDetlete = saleMainDataMapper.deleteByPrimaryKey(id);
            log.info("impl - 根据ID删除sale_main_data表中数据时的结果：***" + isDetlete + "***");

            if (isDetlete != 1) {
                return ResultDTOBuilder.failure("10007", "数据添加失败，请稍后重试");
            }

            return ResultDTOBuilder.success(true);
        } catch (Exception e) {
            log.error("impl - 根据ID删除sale_main_data表中数据时的结果(异常)：", e);
            return ResultDTOBuilder.failure("10007", "服务异常，请稍后重试");
        }
    }

    /**
     * 修改
     * @param saleMainDataWith
     * @return
     */
    public ResultDTO<Boolean> updata(SaleMainDataWithBLOBs saleMainDataWith) {
        log.info("impl - 根据ID修改sale_main_data表中数据时的参数：" + JsonUtil.toJson(saleMainDataWith));
        try {

            saleMainDataWith.setUpdateDate(new Date());
            int isUpdata = saleMainDataMapper.updateByPrimaryKeyWithBLOBs(saleMainDataWith);

            log.info("impl - 根据ID修改sale_main_data表中数据时的返回结果：***" + isUpdata + "***");

            if (isUpdata != 1) {
                return ResultDTOBuilder.failure("10007", "修改失败，请稍后重试");
            }

            return ResultDTOBuilder.success(true);
        } catch (Exception e) {
            log.error("impl - 根据ID修改sale_main_data表中数据时的返回结果(异常)：", e);
            return ResultDTOBuilder.failure("10007", "服务异常，请稍后重试");
        }
    }

    /**
     * 根据id查询单个
     * @param id
     * @return
     */
    public ResultDTO<SaleMainDataWithBLOBs> findById(String id) {
        log.info("impl - 根据ID查询sale_main_data表中单个数据时的方法入参ID：***" + id + "***");
        try {
            SaleMainDataWithBLOBs saleMainDataWith = saleMainDataMapper.selectByPrimaryKey(id);

            log.info("impl - 根据ID查询sale_main_data表中单个数据的查询结果：" + JsonUtil.toJson(saleMainDataWith));

            return ResultDTOBuilder.success(saleMainDataWith);
        } catch (Exception e) {
            log.info("impl - 根据ID查询sale_main_data表中单个数据的查询结果(异常)：", e);
            return ResultDTOBuilder.failure("10007", "服务异常，请稍后重试");
        }
    }

    /**
     * 分页查询全部
     * @return
     */
    public ResultDTO<PageInfo<SaleMainData>> findAll(int page, int rows) {
        log.error("impl - 分页查询全部(sale_main_data表)的查询方法入参：page = " + page + "、rows = " + rows);
        try {
            //查询前设置分页信息
            PageHelper.startPage(page, rows);

            List<SaleMainData> saleMainDatas = saleMainDataMapper.selectAll();
            log.error("impl - 分页查询全部(sale_main_data表)的查询结果：", JsonUtil.toJson(saleMainDatas));

            //创建PageInfo对象
            PageInfo<SaleMainData> pageInfo = new PageInfo<SaleMainData>(saleMainDatas);
            log.error("impl - 分页查询全部(sale_main_data表)的查询结果 - 分页以后的结果：", JsonUtil.toJson(pageInfo));

            return ResultDTOBuilder.success(pageInfo);
        } catch (Exception e) {
            log.error("impl - 分页查询全部(sale_main_data表)的查询结果(异常)：", e);
            return ResultDTOBuilder.failure("10007", "服务异常，请稍后重试");
        }
    }

    /**
     * 统计查询
     */
    public ResultDTO countQuery(){

        int StatusNum = 0;
        int seenPolicymakerNum = 0;
        int isRealNum = 0;
        int fiveUserupIsRealNum = 0;

        try {
            //统计状态不是关闭的数据
            SaleMainDataExample StatusExample = new SaleMainDataExample();
            StatusExample.createCriteria().andStatusNotEqualTo(SaleMainStatus.CLOSE.getValue());

            StatusNum = saleMainDataMapper.countByExample(StatusExample);

        } catch (Exception e) {
            log.error("统计状态不是关闭的数据(异常)", e);
        }

        try {
            //统计seen_policy_maker状态是已经见到决策人,并且status!=cloes的数据
            SaleMainDataExample seenPolicymakerExample = new SaleMainDataExample();
            SaleMainDataExample.Criteria seenPolicymakerCriteria = seenPolicymakerExample.createCriteria();

            seenPolicymakerCriteria.andSeenPolicymakerEqualTo(SeenPolicymaker.YES.getValue());
            seenPolicymakerCriteria.andStatusNotEqualTo(SaleMainStatus.CLOSE.getValue());

            seenPolicymakerNum = saleMainDataMapper.countByExample(seenPolicymakerExample);

        } catch (Exception e) {
            log.error("统计seen_policy_maker状态是已经见到决策人(异常)", e);
        }

        try {
            //统计is_real字段状态不是null并且status!=cloes的数据
            SaleMainDataExample isRealExample = new SaleMainDataExample();
            SaleMainDataExample.Criteria isRealCriteria = isRealExample.createCriteria();

            isRealCriteria.andStatusNotEqualTo(SaleMainStatus.CLOSE.getValue());
            isRealCriteria.andIsRealIsNotNull();
            isRealCriteria.andIsRealNotEqualTo(IsReal.NO.getValue());

            isRealNum = saleMainDataMapper.countByExample(isRealExample);

        } catch (Exception e) {
            log.error("统计is_real字段状态不是null(异常)", e);
        }

        try {
            //统计five_user_up字段状态不是null并且status!=cloes的数据
            SaleMainDataExample fiveUserupExample = new SaleMainDataExample();
            SaleMainDataExample.Criteria fiveUserupCriteria = fiveUserupExample.createCriteria();

            fiveUserupCriteria.andStatusNotEqualTo(SaleMainStatus.CLOSE.getValue());
            fiveUserupCriteria.andFiveUserUpIsNotNull();
            fiveUserupCriteria.andFiveUserUpNotEqualTo(FiveUserupResult.NO.getValue());

            fiveUserupIsRealNum = saleMainDataMapper.countByExample(fiveUserupExample);

        } catch (Exception e) {
            log.error("统计five_user_up字段状态不是null(异常)", e);
        }

        Map<String, Integer> finalMap = new LinkedHashMap<String, Integer>();
        finalMap.put("statusIsNotCloseNum", StatusNum);
        finalMap.put("seenPolicymakerNum", seenPolicymakerNum);
        finalMap.put("isRealNum", isRealNum);
        finalMap.put("fiveUserupNum", fiveUserupIsRealNum);

        log.info("统计查询sale_main_data表的最终结果：", JsonUtil.toJson(finalMap));

        return ResultDTOBuilder.success(finalMap);
    }

    /**
     * 关闭/重新打开status状态
     */
    public ResultDTO resetStatusById(String id, String status) {
        log.info("impl - 重置状态方法入参：id = " + id + "、status = " + status);

        try {
            int closeResult = saleMainDataMapper.resetSaleMainDataStatusById(id, status);
            log.info("impl - 重置状态结果：***" + closeResult + "***");

            if (closeResult != 1) {
                return ResultDTOBuilder.failure("10007", status.equalsIgnoreCase("close")? "关闭":"打开" + "失败，请稍后重试");
            }

            return ResultDTOBuilder.success(closeResult);
        } catch (Exception e) {
            log.info("impl - 重置状态结果(异常)：", e);
            return ResultDTOBuilder.failure("10009", "服务异常，请稍后重试");
        }
    }
}
