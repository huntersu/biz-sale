package com.biz.controller;

import com.biz.service.ITrackClient;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
@ResponseBody
@RequestMapping("/api/saleTrack/")
public class SaleTrackController {


    @Resource
    private ITrackClient trackClient;

    @GetMapping("findAll")
    public Object findAll() {

        return trackClient.findAllTrack();

    }

    @GetMapping("findById")
    public Object findAll(@RequestParam String id) {

        return trackClient.findById(id);

    }

}
