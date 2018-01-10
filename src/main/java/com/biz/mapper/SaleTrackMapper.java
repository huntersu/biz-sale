package com.biz.mapper;

import com.biz.domain.SaleTrack;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SaleTrackMapper {

    /**
     * 查询用户表中的所有数据
     * @return
     */
    List<SaleTrack> selectAll();



    /**
     * 查询用户表中的所有数据
     * @return
     */
    SaleTrack selectById(String id);

}
