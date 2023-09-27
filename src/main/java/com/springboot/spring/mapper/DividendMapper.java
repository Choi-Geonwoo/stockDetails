package com.springboot.spring.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.spring.dto.DividendDto;
import com.springboot.spring.dto.TransactionDto;

@Mapper
public interface DividendMapper {
    // 배당 거래내역
    public List<Map> dividendList(TransactionDto tDto);

    // 배당 거래 상세 내역
    public TransactionDto dividendDtlsInqry(TransactionDto tDto); 

    // 주식 거래내역 등록
    public int  transactionInsert(TransactionDto tDTO);
    
    // 주식 거래내역 등록
    public int  dividendInsert(DividendDto tDTO);

    public String tNoString(TransactionDto tDTO);

}
