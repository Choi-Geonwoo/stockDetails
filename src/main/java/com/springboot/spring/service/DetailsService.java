package com.springboot.spring.service;

import java.util.List;

import com.springboot.spring.vo.StockVO;

public interface DetailsService {
    
    // 주식 거래내역
    public List<StockVO>  stockDetailsList();

}
