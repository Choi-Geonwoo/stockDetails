package com.springboot.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.spring.vo.StockVO;

@Mapper
public interface DetailsMaper {

    // 주식 거래내역
    public List<StockVO>  stockDetailsList();
}
