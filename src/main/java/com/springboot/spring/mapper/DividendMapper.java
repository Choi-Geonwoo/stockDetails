package com.springboot.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.spring.dto.DividendDto;

@Mapper
public interface DividendMapper {
        // 주식 거래내역
    public List<DividendDto> dividendList(DividendDto dividendDto);

}
