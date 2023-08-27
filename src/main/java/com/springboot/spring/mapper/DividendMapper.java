package com.springboot.spring.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.spring.dto.DividendDto;
import com.springboot.spring.dto.StockportfolioDto;
import com.springboot.spring.dto.TransactionDto;
import com.springboot.spring.vo.StockportfolioVO;

@Mapper
public interface DividendMapper {
    // 배당 거래내역
    public List<Map> dividendList(TransactionDto tDto);

    
    // 주식 거래내역 등록
    public int  transactionInsert(TransactionDto tDTO);
    
    // 주식 거래내역 등록
    public int  dividendInsert(DividendDto tDTO);

}
