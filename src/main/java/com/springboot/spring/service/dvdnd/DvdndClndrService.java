package com.springboot.spring.service.dvdnd;

import java.util.List;
import java.util.Map;

public interface DvdndClndrService {
    
    // 배당 일자별 거래내역
    public List<Map> transactionList(Map<String, Object> map);
}
