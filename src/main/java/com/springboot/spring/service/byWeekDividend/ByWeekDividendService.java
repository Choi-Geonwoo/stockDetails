package com.springboot.spring.service.byWeekDividend;

import java.util.List;
import java.util.Map;

import com.springboot.spring.vo.StockportfolioVO;

// 배당 주별 내역
public interface ByWeekDividendService {
    
    // 주별 배당 거래내역
    public List<Map> byWeekDividendList(Map<String, Object> map);
    

}
