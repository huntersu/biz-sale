package com.biz.service.impl;

import com.biz.common.ResultDTO;
import com.biz.common.ResultDTOBuilder;
import com.biz.domain.SaleMainData;
import com.biz.domain.SaleMainDataWithBLOBs;
import com.biz.mapper.SaleMainDataMapper;
import com.biz.service.ISaleMainDataClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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
     * 查询
     * @return
     */
    public ResultDTO findAll() {

        List<SaleMainDataWithBLOBs> lists = saleMainDataMapper.selectAll();

        return ResultDTOBuilder.success(lists);
    }
}
