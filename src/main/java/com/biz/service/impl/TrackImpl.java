package com.biz.service.impl;


import com.biz.common.ResultDTO;
import com.biz.domain.SaleTrack;
import com.biz.mapper.SaleTrackMapper;
import com.biz.service.ITrackClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrackImpl implements ITrackClient {

    @Resource
    private SaleTrackMapper saleTrackMapper;

    public ResultDTO<List<SaleTrack>> findAllTrack() {



        List<SaleTrack> resultDataList = new ArrayList<SaleTrack>();
        ResultDTO result = new ResultDTO();

        result.setSuccess(true);
        result.setErrCode("");
        result.setErrMsg("");
        result.setData(saleTrackMapper.selectAll());

        return result;


    }

    public ResultDTO<SaleTrack> findById(String id){
        List<SaleTrack> resultDataList = new ArrayList<SaleTrack>();
        ResultDTO result = new ResultDTO();

        result.setSuccess(true);
        result.setErrCode("");
        result.setErrMsg("");
        result.setData(saleTrackMapper.selectById(id));

        return result;
    }
}
