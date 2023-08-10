package com.springboot.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.spring.mapper.DetailsMaper;
import com.springboot.spring.vo.StockVO;

@Service
public class DetailServiceImpl implements DetailsService {

    @Autowired
    private DetailsMaper detailsMaper;

    // 주식 거래내역
    @Override
    public List<StockVO> stockDetailsList() {
       return detailsMaper.stockDetailsList();
    }
    
}
