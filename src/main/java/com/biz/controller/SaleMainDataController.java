package com.biz.controller;

import com.biz.common.ResultDTO;
import com.biz.constant.DonePolicymaker;
import com.biz.constant.DonePolicymakerPosition;
import com.biz.domain.SaleMainData;
import com.biz.service.ISaleMainDataClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * sale_main_data表的对应操作
 */
@Controller
@ResponseBody
@RequestMapping("/api/saleMainData/")
public class SaleMainDataController {

    private static final Logger LOG = LoggerFactory.getLogger(SaleMainDataController.class);

    @Resource
    private ISaleMainDataClient saleMainDataClient;

    /**
     * /api/saleMainData/insert
     * 新增
     */
    @GetMapping("insert")
    public Object insert(SaleMainData saleMainData){

        ResultDTO<Boolean> result = saleMainDataClient.insert(saleMainData);

        return result;
    }

    /**
     * /api/saleMainData/deleteById
     * 根据id删除数据
     */
    @GetMapping("deleteById/{id}")
    public Object deleteById(@PathVariable String id){

        ResultDTO<Boolean> deleteResult = saleMainDataClient.deleteById(id);

        return deleteResult;
    }

    /**
     * /api/saleMainData/updataById
     * 根据id修改数据
     */
    @GetMapping("updataById")
    public Object updataById(SaleMainData saleMainData){

        ResultDTO<Boolean> updataResult = saleMainDataClient.updata(saleMainData);

        return updataResult;
    }

    /**
     * /api/saleMainData/findById
     * 根据id查询单个
     * @param id
     * @return
     */
    @GetMapping("findById/{id}")
    public Object findById(@PathVariable String id){

        ResultDTO<SaleMainData> findResult = saleMainDataClient.findById(id);

        if (findResult.isSuccess() && findResult.getData() != null) {

            SaleMainData saleMainData = findResult.getData();

            /*if (saleMainData.getDonePolicymaker() != null) {
                DonePolicymaker donePolicymaker = DonePolicymaker.getByValue(saleMainData.getDonePolicymaker());
                if (donePolicymaker != null) {
                    saleMainData.setDonePolicymaker(donePolicymaker.getDesc());
                }
            }

            if (saleMainData.getDonePolicymakerPosition() != null) {
                DonePolicymakerPosition donePolicymakerPosition = DonePolicymakerPosition.getByValue(saleMainData.getDonePolicymakerPosition());
                if (donePolicymakerPosition != null) {
                    saleMainData.setDonePolicymaker(donePolicymakerPosition.getDesc());
                }
            }*/

            /*saleMainData.setDonePolicymaker(?"":saleMainData.getDonePolicymaker());
            saleMainData.setDonePolicymakerPosition(saleMainData.getDonePolicymakerPosition() == null?"":saleMainData.getDonePolicymakerPosition());
            saleMainData.setFiveUserUp();
            saleMainData.setFiveUserUpComment();
            saleMainData.setIsReal();
            saleMainData.setIsRealComment();
            saleMainData.setNextPolicymakerAction();
            saleMainData.setNextReqAction();
            saleMainData.setPolicymakerPosition();
            saleMainData.setStatus();
            saleMainData.setSeenPolicymaker();*/
        }

        return findResult;
    }

    /**
     * /api/saleMainData/findAll/1/10
     * 查询所有
     */
    @GetMapping("findAll")
    public Object findAll(@RequestParam int page, @RequestParam int rows){

        ResultDTO resultDTO = saleMainDataClient.findAll(page ,rows);

        return resultDTO;
    }

    /**
     * /api/saleMainData/countQuery
     * 统计查询
     */
    @GetMapping("countQuery")
    public Object countQuery(){

        ResultDTO seenPolicymakerResult = saleMainDataClient.countQuery();

        return seenPolicymakerResult;
    }
}
