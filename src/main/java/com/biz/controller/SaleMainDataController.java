package com.biz.controller;

import com.biz.common.ResultDTO;
import com.biz.domain.SaleMainDataWithBLOBs;
import com.biz.service.ISaleMainDataClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Object insert(SaleMainDataWithBLOBs saleMainDataWithBLOBs){

        ResultDTO result = saleMainDataClient.insert(saleMainDataWithBLOBs);

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
    public Object updataById(SaleMainDataWithBLOBs saleMainDataWithBLOBs){

        ResultDTO updataResult = saleMainDataClient.updata(saleMainDataWithBLOBs);

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
     * /api/saleMainData/findAll
     * 查询所有
     */
    @GetMapping("findAll")
    public Object findAll(){

        ResultDTO resultDTO = saleMainDataClient.findAll();

        return resultDTO;
    }

}
