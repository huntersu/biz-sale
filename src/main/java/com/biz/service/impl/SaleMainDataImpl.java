package com.biz.service.impl;

import com.biz.common.ResultDTO;
import com.biz.common.ResultDTOBuilder;
import com.biz.common.UUIDUtils;
import com.biz.constant.SaleMainStatus;
import com.biz.constant.SeenPolicymaker;
import com.biz.domain.SaleMainData;
import com.biz.domain.SaleMainDataExample;
import com.biz.mapper.SaleMainDataMapper;
import com.biz.service.ISaleMainDataClient;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleMainDataImpl implements ISaleMainDataClient{

    @Resource
    private SaleMainDataMapper saleMainDataMapper;

    /**
     * 插入
     * @param saleMainData
     * @return
     */
    public ResultDTO insert(SaleMainData saleMainData) {
        saleMainData.setId(UUIDUtils.genratorCode());
        saleMainData.setBeginDate(new Date());
        saleMainData.setUpdateDate(new Date());

        int insert = saleMainDataMapper.insert(saleMainData);

        if (insert != 1) {
            return ResultDTOBuilder.failure("10007", "数据添加失败，请稍后重试");
        }

        return ResultDTOBuilder.success(true);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public ResultDTO deleteById(String id) {

        int isDetlete = saleMainDataMapper.deleteByPrimaryKey(id);

        if (isDetlete != 1) {
            return ResultDTOBuilder.failure("10007", "数据添加失败，请稍后重试");
        }

        return ResultDTOBuilder.success(true);
    }

    /**
     * 修改
     * @param saleMainData
     * @return
     */
    public ResultDTO updata(SaleMainData saleMainData) {

        int isUpdata = saleMainDataMapper.updateByPrimaryKey(saleMainData);

        if (isUpdata != 1) {
            return ResultDTOBuilder.failure("10007", "修改失败，请稍后重试");
        }

        return ResultDTOBuilder.success(true);
    }

    /**
     * 根据id查询单个
     * @param id
     * @return
     */
    public ResultDTO findById(String id) {

        SaleMainData saleMainData = saleMainDataMapper.selectByPrimaryKey(id);

        return ResultDTOBuilder.success(saleMainData);
    }

    /**
     * 查询全部
     * @return
     */
    public ResultDTO findAll(int page, int rows) {
        //查询前设置分页信息
        PageHelper.startPage(page, rows);

        List<SaleMainData> saleMainDatas = saleMainDataMapper.selectAll();

        //创建PageInfo对象
        PageInfo<SaleMainData> pageInfo = new PageInfo<SaleMainData>(saleMainDatas);

        return ResultDTOBuilder.success(pageInfo);
    }

    /**
     * 统计查询
     */
    public ResultDTO countQuery(){
        Map<String, Integer> finalMap = new LinkedHashMap<String, Integer>();

        //统计状态不是关闭的数据
        SaleMainDataExample StatusExample = new SaleMainDataExample();
        StatusExample.createCriteria().andStatusNotEqualTo(SaleMainStatus.CLOSE.getValue());

        int StatusNum = saleMainDataMapper.countByExample(StatusExample);
        finalMap.put("statusIsNotCloseNum", StatusNum);

        //统计seen_policy_maker状态是已经见到决策人,并且status!=cloes的数据
        SaleMainDataExample seenPolicymakerExample = new SaleMainDataExample();
        SaleMainDataExample.Criteria seenPolicymakerCriteria = seenPolicymakerExample.createCriteria();
        seenPolicymakerCriteria.andSeenPolicymakerEqualTo(SeenPolicymaker.YES.getValue());
        seenPolicymakerCriteria.andStatusNotEqualTo(SaleMainStatus.CLOSE.getValue());

        int seenPolicymakerNum = saleMainDataMapper.countByExample(seenPolicymakerExample);
        finalMap.put("seenPolicymakerNum", seenPolicymakerNum);

        //统计is_real字段状态不是null并且status!=cloes的数据
        SaleMainDataExample isRealExample = new SaleMainDataExample();
        SaleMainDataExample.Criteria isRealCriteria = isRealExample.createCriteria();
        isRealCriteria.andStatusNotEqualTo(SaleMainStatus.CLOSE.getValue());
        isRealCriteria.andIsRealIsNotNull();

        int isRealNum = saleMainDataMapper.countByExample(isRealExample);
        finalMap.put("isRealNum", isRealNum);

        //统计five_user_up字段状态不是null并且status!=cloes的数据
        SaleMainDataExample fiveUserupExample = new SaleMainDataExample();
        SaleMainDataExample.Criteria fiveUserupCriteria = fiveUserupExample.createCriteria();
        fiveUserupCriteria.andStatusNotEqualTo(SaleMainStatus.CLOSE.getValue());
        fiveUserupCriteria.andFiveUserUpIsNotNull();

        int fiveUserupIsRealNum = saleMainDataMapper.countByExample(fiveUserupExample);
        finalMap.put("fiveUserupNum", fiveUserupIsRealNum);

        return ResultDTOBuilder.success(finalMap);
    }
}
