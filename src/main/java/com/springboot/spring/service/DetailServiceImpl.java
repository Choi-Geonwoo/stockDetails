package com.springboot.spring.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.spring.dto.StockportfolioDto;
import com.springboot.spring.mapper.DetailsMapper;
import com.springboot.spring.vo.StockportfolioVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DetailServiceImpl implements DetailsService {

    @Autowired
    private DetailsMapper detailsMaper;

    // 주식 거래내역
    @Override
    public List<StockportfolioVO> stockDetailsList(StockportfolioDto sDto) {
        // 전체 클릭한 경우 값 null 셋팅
        if("allStockName".equals(sDto.getStockName())){
            sDto.setStockName(null);
        }
       return detailsMaper.stockDetailsList(sDto);
    }

    // 주식 내역 등록
    @Override
    public int detailsInsert(Map<String, Object> map) {
        int cnt = -1;
        ObjectMapper oMapper = new ObjectMapper();
        try {
            StockportfolioDto stockDTO = oMapper.convertValue(map, StockportfolioDto.class);
            //log.info("어떤식으로 오나");
            //log.info("toString : : : : " + stockDTO.toString());
            cnt = detailsMaper.detailsInsert(stockDTO);
        } catch (Exception e) {
            log.error("오류 : " + e.toString());
            cnt = -1;
        }
        return cnt;
    }

    // 주식 내역 수정
    @Override
    public int detailsUpdate(Map<String, Object> map) {
        int cnt = -1;
        
        ObjectMapper oMapper = new ObjectMapper();
        try {
            StockportfolioDto stockDTO = oMapper.convertValue(map, StockportfolioDto.class);
            cnt = detailsMaper.detailsUpdate(stockDTO);
        } catch (Exception e) {
            log.error("오류 " + e.toString());
            cnt = -1;
        }
        return cnt;
    }

    // 주식 거래내역 삭제
    @Override
    public int detailsDelete(int registration_order) {
        int cnt = -1;
        try {
            cnt = detailsMaper.detailsDelete(registration_order);
        } catch (Exception e) {
            log.error("오류 " + e.toString());
        }
        return cnt;
    }

    // 주식명 조회 셀렉트 박스 사용
    @Override
    public List<StockportfolioVO> selectBox() {
        StockportfolioDto sDto = null;
        return detailsMaper.stockDetailsList(sDto);
    }
    
}
