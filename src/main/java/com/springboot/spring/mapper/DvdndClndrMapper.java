package com.springboot.spring.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.spring.dto.TransactionDto;

@Mapper
public interface DvdndClndrMapper {
    
    // 배당 일자별 거래내역
    public List<TransactionDto> transactionList();
}
