package com.springboot.spring.service;

import java.util.List;
import java.util.Map;

import com.springboot.spring.vo.StockVO;
import com.springboot.spring.vo.StockportfolioVO;

public interface DetailsService {
    
    // 주식 거래내역
    public List<StockportfolioVO>  stockDetailsList();
    
    // 주식 거래내역 수정
    public int  detailsUpdate(Map<String, Object> map);

    
    // 주식 거래내역 등록
    public int  detailsInsert(Map<String, Object> map);

    // 주식 거랙내역 삭제
    public int detailsDelete(int registration_order);

}
