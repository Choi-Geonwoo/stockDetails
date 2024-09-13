package com.springboot.spring.service.byWeekDividend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.spring.dto.StockportfolioDto;
import com.springboot.spring.mapper.byWeekDividend.ByWeekDividendMapper;
import com.springboot.spring.mapper.dividend.DividendMapper;
import com.springboot.spring.vo.StockportfolioVO;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

// 배당 주별 내역
@Slf4j
@Service
public class ByWeekDividendServiceImpl implements ByWeekDividendService {

    @Autowired
    private ByWeekDividendMapper byWeekDividendMapper;

    // 주별 배당 내역 조회
    @Override
    public List<Map> byWeekDividendList(Map<String, Object> map) { 
        map.put("startYmd", ("null".equals(map.get("startYmd")) ? null :  map.get("startYmd")));
        map.put("endYmd", ("null".equals(map.get("endYmd")) ? null :  map.get("endYmd")));
        return byWeekDividendMapper.byWeekDividendList(map);
    }


}
