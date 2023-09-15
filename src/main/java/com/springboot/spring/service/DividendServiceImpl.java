package com.springboot.spring.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.spring.dto.DividendDto;
import com.springboot.spring.dto.StockportfolioDto;
import com.springboot.spring.dto.TransactionDto;
import com.springboot.spring.mapper.DetailsMapper;
import com.springboot.spring.mapper.DividendMapper;
import com.springboot.spring.vo.StockportfolioVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DividendServiceImpl implements DividendService {
    

    @Autowired
    private DividendMapper dividendMapper;
    
    @Autowired
    private DetailsMapper detailsMaper;

    // 배당 내역 조회
    @Override
    public List<Map> dividendList(TransactionDto tDto) { 
        // 날짜 공백인경우 현재 날짜로 지정
        if("".equals(tDto.getTrnscdate()) || null == tDto.getTrnscdate()){
            Date nowDate = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            tDto.setTrnscdate(simpleDateFormat.format(nowDate));
        }
        if("total".equals(tDto.getTrnscdate())){
            tDto.setTrnscdate("");
        }
        // tDto
        //log.info("toString : : : : : " + tDto.toString());
        return dividendMapper.dividendList(tDto);
    }

    // 주식명 조회 셀렉트 박스 사용
    @Override
    public List<StockportfolioVO> selectBox() {
        StockportfolioDto sDto = null;
        return detailsMaper.stockDetailsList(sDto);
    }

    // 배당 거래 내역
    @Override
    public int dividendInsert(Map<String, Object> map) {
        int cnt = -1;
        ObjectMapper oMapper = new ObjectMapper();
        try {
            //TransactionDto tDTO = oMapper.convertValue(map, TransactionDto.class);
            TransactionDto tDTO = oMapper.convertValue(map, TransactionDto.class);
            log.info("tDTO " + tDTO.toString());
            log.info("tDTO " + tDTO.toString());
            log.info("tDTO " + tDTO.toString());
            log.info("tDTO " + tDTO.toString());
            //log.info("어떤식으로 오나");
            //log.info("toString : : : : " + tDTO.toString());
            // 거래일자 등록
            cnt = dividendMapper.transactionInsert(tDTO);
            // 거래역 등록
            //cnt = dividendMapper.dividendInsert(dDto);
        } catch (Exception e) {
            log.error("오류 : " + e.toString());
            cnt = -1;
        }
        return cnt;
    }
    
}
