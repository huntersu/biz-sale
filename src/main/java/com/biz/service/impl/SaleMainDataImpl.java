package com.biz.service.impl;

import com.biz.common.ResultDTO;
import com.biz.common.ResultDTOBuilder;
import com.biz.constant.SaleMainStatus;
import com.biz.constant.SeenPolicymaker;
import com.biz.domain.SaleMainData;
import com.biz.domain.SaleMainDataExample;
import com.biz.domain.SaleMainDataWithBLOBs;
import com.biz.mapper.SaleMainDataMapper;
import com.biz.service.ISaleMainDataClient;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SaleMainDataImpl implements ISaleMainDataClient{

    @Resource
    private SaleMainDataMapper saleMainDataMapper;

    /**
     * 插入
     * @param saleMainDataWithBLOBs
     * @return
     */
    public ResultDTO insert(SaleMainDataWithBLOBs saleMainDataWithBLOBs) {

        int insert = saleMainDataMapper.insert(saleMainDataWithBLOBs);

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
     * @param saleMainDataWithBLOBs
     * @return
     */
    public ResultDTO updata(SaleMainDataWithBLOBs saleMainDataWithBLOBs) {

        int isUpdata = saleMainDataMapper.updateByPrimaryKeyWithBLOBs(saleMainDataWithBLOBs);

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

        SaleMainDataWithBLOBs saleMainDataWithBLOBs = saleMainDataMapper.selectByPrimaryKey(id);

        return ResultDTOBuilder.success(saleMainDataWithBLOBs);
    }

    /**
     * 查询全部
     * @return
     */
    public ResultDTO findAll(int page, int rows) {

        //查询前设置分页信息
        PageHelper.startPage(page, rows);

        List<SaleMainDataWithBLOBs> saleMainDatas = saleMainDataMapper.findAll();

        //创建PageInfo对象
        PageInfo<SaleMainDataWithBLOBs> pageInfo = new PageInfo<SaleMainDataWithBLOBs>(saleMainDatas);

        return ResultDTOBuilder.success(pageInfo);
    }

    /**
     * 统计状态不是close的数据
     * @return
     */
    public ResultDTO countStatusIsNotClose() {
        SaleMainDataExample example = new SaleMainDataExample();
        example.createCriteria().andStatusNotEqualTo(SaleMainStatus.CLOSE.getValue());

        List<SaleMainData> saleMainData = saleMainDataMapper.selectByExample(example);

        return ResultDTOBuilder.success(saleMainData);
    }

    /**
     * 统计状态是已经见到决策人的数据的数据
     */
    public ResultDTO countHaveSeenPolicymaker() {

        SaleMainDataExample example = new SaleMainDataExample();

        SaleMainDataExample.Criteria criteria = example.createCriteria();

        criteria.andSeenPolicymakerEqualTo(SeenPolicymaker.YES.getValue());
        criteria.andStatusNotEqualTo(SaleMainStatus.CLOSE.getValue());

        List<SaleMainData> saleMainData = saleMainDataMapper.selectByExample(example);

        return ResultDTOBuilder.success(saleMainData);
    }

    public ResultDTO countIsReal() {
        return null;
    }
}
