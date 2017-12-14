package com.biz.controller;

import com.biz.common.BeanUtil;
import com.biz.common.ResultDTO;
import com.biz.component.UserComponent;
import com.biz.constant.*;
import com.biz.domain.SaleMainData;
import com.biz.domain.SaleMainDataWithBLOBs;
import com.biz.service.ISaleMainDataClient;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * sale_main_data表的对应操作
 */
@Controller
@ResponseBody
@RequestMapping("/api/saleMainData/")
public class SaleMainDataController {

    private static final Logger LOG = LoggerFactory.getLogger(SaleMainDataController.class);

    @Autowired
    private UserComponent userComponent;

    @Resource
    private ISaleMainDataClient saleMainDataClient;

    /**
     * /api/saleMainData/insert
     * 新增
     */
    @GetMapping("insert")
    public Object insert(SaleMainDataWithBLOBs saleMainDataWith, HttpServletRequest httpServletRequest){
        saleMainDataWith.setUploads(userComponent.checkUser(httpServletRequest).getId());
        ResultDTO<Boolean> result = saleMainDataClient.insert(saleMainDataWith);

        return result;
    }

    /**
     * /api/saleMainData/deleteById/123456789
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
    public Object updataById(SaleMainDataWithBLOBs saleMainDataWith, HttpServletRequest httpServletRequest){

        saleMainDataWith.setUploads(userComponent.checkUser(httpServletRequest).getId());
        ResultDTO<Boolean> updataResult = saleMainDataClient.updata(saleMainDataWith);


        return updataResult;
    }

    /**
     * /api/saleMainData/findById/{id}
     * 根据id查询单个
     * @param id
     * @return
     */
    @GetMapping("findById/{id}")
    public Object findById(@PathVariable String id){

        ResultDTO<SaleMainData> findResult = saleMainDataClient.findById(id);

        return findResult;
    }

    /**
     * 多条件查询
     * /api/saleMainData/findUserBySelective
     * 参数：SaleMainData对象
     * 返回值：List<SaleMainData>
     */
    @GetMapping("findUserBySelective")
    public Object findUserBySelective(SaleMainData saleMainData){

        ResultDTO<List<SaleMainData>> saleMainDatas = saleMainDataClient.findUserBySelective(saleMainData);
        if (saleMainDatas.getSuccess() && saleMainDatas.getData() != null && saleMainDatas.getData().size() > 0) {
            for (SaleMainData mainData : saleMainDatas.getData()) {
                //将对应value值转成对应的描述
                this.transfer(saleMainData);
            }
        }

        return saleMainDatas;
    }

    /**
     * http://localhost:8088/api/saleMainData/findAll.do?page=1&rows=50
     * /api/saleMainData/findAll/1/10
     * 查询所有
     */
    @GetMapping("findAll")
    public Object findAll(@RequestParam int page, @RequestParam int rows){

        ResultDTO<PageInfo<SaleMainData>> resultDTO = saleMainDataClient.associativeSelectAll(page ,rows);

        if (resultDTO.getSuccess() && resultDTO.getData() != null && resultDTO.getData().getList() != null && resultDTO.getData().getList().size() > 0) {
            for (SaleMainData saleMainData : resultDTO.getData().getList()) {
                //将对应value值转成对应的描述
                this.transfer(saleMainData);
            }
        }
        return resultDTO;
    }

    /**
     * /api/saleMainData/countQuery
     * 统计查询
     */
    @GetMapping("countQuery")
    public Object countQuery(){

        ResultDTO seenPolicymakerResult = saleMainDataClient.countQuery();

        Map<String, Object> map = new HashMap<String, Object>();

        BeanUtil.copyProperties(seenPolicymakerResult, map);

        map.put("wwwwww", "ceshi shuju");

        return map;
    }

    //将对应的枚举类型的value值转成对应的描述
    private void transfer(SaleMainData saleMainData) {
        if (saleMainData.getDonePolicymaker() != null) {
            DonePolicymaker donePolicymaker = DonePolicymaker.getByValue(saleMainData.getDonePolicymaker());
            if (donePolicymaker != null) {
                saleMainData.setDonePolicymaker(donePolicymaker.getDesc());
            }
        }

        if (saleMainData.getDonePolicymakerPosition() != null) {
            DonePolicymakerPosition donePolicymakerPosition = DonePolicymakerPosition.getByValue(saleMainData.getDonePolicymakerPosition());
            if (donePolicymakerPosition != null) {
                saleMainData.setDonePolicymakerPosition(donePolicymakerPosition.getDesc());
            }
        }

        if (saleMainData.getFiveUserUp() != null) {
            FiveUserupResult fiveUserup = FiveUserupResult.getByValue(saleMainData.getFiveUserUp());
            if (fiveUserup != null) {
                saleMainData.setFiveUserUp(fiveUserup.getDesc());
            }
        }

        if (saleMainData.getFiveUserUpComment() != null) {
            FiveUserdownResult fiveUserUpComment = FiveUserdownResult.getByValue(saleMainData.getFiveUserUpComment());
            if (fiveUserUpComment != null) {
                saleMainData.setFiveUserUpComment(fiveUserUpComment.getDesc());
            }
        }

        if (saleMainData.getIsReal() != null) {
            IsReal isReal = IsReal.getByValue(saleMainData.getIsReal());
            if (isReal != null) {
                saleMainData.setIsReal(isReal.getDesc());
            }
        }

        if (saleMainData.getIsRealComment() != null) {
            IsRealComment isRealComment = IsRealComment.getByValue(saleMainData.getIsRealComment());
            if (isRealComment != null) {
                saleMainData.setIsRealComment(isRealComment.getDesc());
            }
        }

        if (saleMainData.getNextPolicymakerAction() != null) {
            NextPolicymakerAction nextPolicymakerAction = NextPolicymakerAction.getByValue(saleMainData.getNextPolicymakerAction());
            if (nextPolicymakerAction != null) {
                saleMainData.setNextPolicymakerAction(nextPolicymakerAction.getDesc());
            }
        }

        if (saleMainData.getNextReqAction() != null) {
            NextReqAction nextReqAction = NextReqAction.getByValue(saleMainData.getNextReqAction());
            if (nextReqAction != null) {
                saleMainData.setNextReqAction(nextReqAction.getDesc());
            }
        }

        if (saleMainData.getPolicymakerPosition() != null) {
            PolicymakerPosition policymakerPosition = PolicymakerPosition.getByValue(saleMainData.getPolicymakerPosition());
            if (policymakerPosition != null) {
                saleMainData.setPolicymakerPosition(policymakerPosition.getDesc());
            }
        }

        if (saleMainData.getStatus() != null) {
            SaleMainStatus saleMainStatus = SaleMainStatus.getByValue(saleMainData.getStatus());
            if (saleMainStatus != null) {
                saleMainData.setStatus(saleMainStatus.getDesc());
            }
        }

        if (saleMainData.getSeenPolicymaker() != null) {
            SeenPolicymaker seenPolicymaker = SeenPolicymaker.getByValue(saleMainData.getSeenPolicymaker());
            if (seenPolicymaker != null) {
                saleMainData.setSeenPolicymaker(seenPolicymaker.getDesc());
            }
        }


    }

    /**
     * /api/saleMainData/resetSaleMainDataStatus/021eda0a82b346e9bda511aac47d2d53/CLOSE
     * 重置(关闭/重新打开)status状态
     */
    @GetMapping("resetSaleMainDataStatus/{id}/{status}")
    public Object resetSaleMainDataStatus(@PathVariable String id, @PathVariable String status) {

        ResultDTO resultDTO = saleMainDataClient.resetStatusById(id, status);

        return resultDTO;
    }
}
