package com.springboot.spring.mapper.byWeekDividend;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

// 배당 주별 내역
@Mapper
public interface ByWeekDividendMapper {
    
    // 주별 배당 거래내역
    public List<Map> byWeekDividendList(Map<String, Object> map);
}
