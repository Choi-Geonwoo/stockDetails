package com.springboot.spring.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.spring.dto.DividendDto;
import com.springboot.spring.mapper.DetailsMapper;
import com.springboot.spring.mapper.DividendMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DividendServiceImpl implements DividendService {
    

    @Autowired
    private DividendMapper dividendMapper;

    // 배당 내역 조회
    @Override
    public List<DividendDto> dividendList(DividendDto dividendDto) {
        // 날짜 공백인경우 현재 날짜로 지정
        
        if("".equals(dividendDto.getYearmonth()) || null == dividendDto.getYearmonth()){
            Date nowDate = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
                dividendDto.setYearmonth(simpleDateFormat.format(nowDate));
        }
        if("total".equals(dividendDto.getYearmonth())){
                dividendDto.setYearmonth("");
        }
         
        return dividendMapper.dividendList(dividendDto);
    }
    
}
