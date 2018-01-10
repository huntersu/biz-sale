package com.biz.service;

import com.biz.common.ResultDTO;
import com.biz.domain.SaleTrack;

import java.util.List;

public interface ITrackClient {


    /**
     * ceshi test
     * @return
     */
    ResultDTO<List<SaleTrack>> findAllTrack();



    ResultDTO<SaleTrack> findById(String id);


}
