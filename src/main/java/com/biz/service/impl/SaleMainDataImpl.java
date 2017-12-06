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
import java.util.List;

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
     * 统计status字段状态不是close的数据
     */
    public ResultDTO countStatusIsNotClose() {
        SaleMainDataExample example = new SaleMainDataExample();
        example.createCriteria().andStatusNotEqualTo(SaleMainStatus.CLOSE.getValue());

        List<SaleMainData> countStatusIsNotCloseResult = saleMainDataMapper.selectByExample(example);

        int countStatusIsNotCloseNum = 0;

        if (countStatusIsNotCloseResult != null) {
            countStatusIsNotCloseNum = countStatusIsNotCloseResult.size();
        }

        return ResultDTOBuilder.success(countStatusIsNotCloseNum);
    }

    /**
     * 统计seen_policy_maker状态是已经见到决策人的数据
     */
    public ResultDTO countHaveSeenPolicymaker() {

        SaleMainDataExample example = new SaleMainDataExample();

        SaleMainDataExample.Criteria criteria = example.createCriteria();

        criteria.andSeenPolicymakerEqualTo(SeenPolicymaker.YES.getValue());
        criteria.andStatusNotEqualTo(SaleMainStatus.CLOSE.getValue());

        List<SaleMainData> haveSeenPolicymakerList = saleMainDataMapper.selectByExample(example);

        int haveSeenPolicymakerNum = 0;

        if (haveSeenPolicymakerList != null) {
            haveSeenPolicymakerNum = haveSeenPolicymakerList.size();
        }

        return ResultDTOBuilder.success(haveSeenPolicymakerNum);
    }

    /**
     * 统计is_real字段状态不是null的数据
     */
    public ResultDTO countIsReal() {

        SaleMainDataExample example = new SaleMainDataExample();
        SaleMainDataExample.Criteria criteria = example.createCriteria();

        criteria.andStatusNotEqualTo(SaleMainStatus.CLOSE.getValue());
        criteria.andIsRealIsNotNull();

        List<SaleMainData> countIsRealList = saleMainDataMapper.selectByExample(example);

        int isRealNum = 0;

        if (countIsRealList != null) {
            isRealNum = countIsRealList.size();
        }

        return ResultDTOBuilder.success(isRealNum);
    }

    /**
     * 统计five_user_up字段状态不是null的数据
     */
    public ResultDTO countFiveUserupResult() {

        SaleMainDataExample example = new SaleMainDataExample();
        SaleMainDataExample.Criteria criteria = example.createCriteria();

        criteria.andStatusNotEqualTo(SaleMainStatus.CLOSE.getValue());
        criteria.andFiveUserUpIsNotNull();

        List<SaleMainData> fiveUserupList = saleMainDataMapper.selectByExample(example);

        int isRealNum = 0;

        if (fiveUserupList != null) {
            isRealNum = fiveUserupList.size();
        }

        return ResultDTOBuilder.success(isRealNum);
    }
}
