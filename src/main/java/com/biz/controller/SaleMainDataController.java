package com.biz.controller;

import com.biz.common.ResultDTO;
import com.biz.common.ResultDTOBuilder;
import com.biz.domain.SaleMainData;
import com.biz.service.ISaleMainDataClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

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

        ResultDTO result = saleMainDataClient.insert(saleMainData);

        return result;
    }

    /**
     * /api/saleMainData/deleteById
     * 根据id删除数据
     */
    @GetMapping("deleteById/{id}")
    public Object deleteById(@PathVariable String id){

        ResultDTO deleteResult = saleMainDataClient.deleteById(id);

        return deleteResult;
    }

    /**
     * /api/saleMainData/updataById
     * 根据id修改数据
     */
    @GetMapping("updataById")
    public Object updataById(SaleMainData saleMainData){

        ResultDTO updataResult = saleMainDataClient.updata(saleMainData);

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

        ResultDTO findResult = saleMainDataClient.findById(id);

        return findResult;
    }

    /**
     * /api/saleMainData/findAll/1/10
     * 查询所有
     */
    @GetMapping("findAll/{page}/{rows}")
    public Object findAll(@PathVariable int page, @PathVariable int rows){

        ResultDTO resultDTO = saleMainDataClient.findAll(page ,rows);

        return resultDTO;
    }

    /**
     * /api/saleMainData/countQuery
     * 统计查询
     */
    @GetMapping("countQuery")
    public Object countQuery(){

        Map<String, Integer> finalMap = new LinkedHashMap<String, Integer>();

        ResultDTO seenPolicymakerResult = saleMainDataClient.countHaveSeenPolicymaker();

        if (seenPolicymakerResult.getSuccess()) {
            finalMap.put("seenPolicymakerNum", (Integer) seenPolicymakerResult.getData());
        }

        ResultDTO isRealResult = saleMainDataClient.countIsReal();

        if (isRealResult.getSuccess()) {
            finalMap.put("isRealNum", (Integer) isRealResult.getData());
        }

        ResultDTO statusIsNotCloseResult = saleMainDataClient.countStatusIsNotClose();

        if (statusIsNotCloseResult.getSuccess()) {
            finalMap.put("statusIsNotCloseNum", (Integer) statusIsNotCloseResult.getData());
        }

        ResultDTO fiveUserupResult = saleMainDataClient.countFiveUserupResult();

        if (fiveUserupResult.getSuccess()) {
            finalMap.put("fiveUserupNum", (Integer) fiveUserupResult.getData());
        }

        return ResultDTOBuilder.success(finalMap);
    }

}
