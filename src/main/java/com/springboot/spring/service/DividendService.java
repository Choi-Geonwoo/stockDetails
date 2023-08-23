package com.springboot.spring.service;

import java.util.List;
import java.util.Map;

import com.springboot.spring.dto.DividendDto;
import com.springboot.spring.vo.StockVO;

public interface DividendService {
    
    // 주식 거래내역
    public List<DividendDto>  dividendList(DividendDto dividendDto);

}
