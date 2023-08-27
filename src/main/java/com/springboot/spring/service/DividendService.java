package com.springboot.spring.service;

import java.util.List;
import java.util.Map;

import com.springboot.spring.dto.DividendDto;
import com.springboot.spring.dto.TransactionDto;
import com.springboot.spring.vo.StockVO;
import com.springboot.spring.vo.StockportfolioVO;

public interface DividendService {
    
    //배당 거래내역
    public List<Map>  dividendList(TransactionDto tDto);

    // 주식명 조회 셀렉트 박스 사용
    public List<StockportfolioVO>  selectBox();
    
    // 주식 거래내역 등록
    public int  dividendInsert(Map<String, Object> map);

}
