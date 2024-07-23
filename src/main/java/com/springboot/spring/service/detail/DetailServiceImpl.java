package com.springboot.spring.service.detail;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.spring.dto.StockportfolioDto;
import com.springboot.spring.mapper.details.DetailsMapper;
import com.springboot.spring.vo.StockportfolioVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DetailServiceImpl implements DetailsService {

    @Autowired
    private DetailsMapper detailsMapper;
    

    // 주식 거래내역
    @Override
    public List<StockportfolioVO> stockDetailsList(StockportfolioDto sDto) {
        // 전체 클릭한 경우 값 null 셋팅
        if("allStockName".equals(sDto.getStockName())){
            sDto.setStockName(null);
        }
        // 전체 클릭한 경우 값 null 셋팅
        if("전체".equals(sDto.getDividendCycle())){
            sDto.setDividendCycle(null);
        }
       return detailsMapper.stockDetailsList(sDto);
    }

    // 주식 내역 등록
    @Override
    public int detailsInsert(Map<String, Object> map) {
        int cnt = -1;
        ObjectMapper oMapper = new ObjectMapper();
        try {
            StockportfolioDto stockDTO = oMapper.convertValue(map, StockportfolioDto.class);
            //FileDTO fileDTO = new FileDTO();
            //String base64 = String.valueOf(map.get("contents"));
            //fileDTO.setContents(base64.getBytes());
            
            //log.info("어떤식으로 오나");
            //log.info("toString : : : : " + stockDTO.toString());
            cnt = detailsMapper.detailsInsert(stockDTO);
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
            cnt = detailsMapper.detailsUpdate(stockDTO);
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
            cnt = detailsMapper.detailsDelete(registration_order);
        } catch (Exception e) {
            log.error("오류 " + e.toString());
        }
        return cnt;
    }

    // 주식명 조회 셀렉트 박스 사용
    @Override
    public List<StockportfolioVO> selectBox() {
        StockportfolioDto sDto = null;
        return detailsMapper.selectBox(sDto);
    }

    // 주식 상세 조회
    @Override
    public Map stockDetailList(Map<String, Object> map) {
        log.info("DetailServiceImpl.stockDetailList");
        try {
            return detailsMapper.stockDetailList(map);
        } catch (Exception e) {
            log.error("error ", e.toString());
            throw new UnsupportedOperationException("Unimplemented method 'stockDetailList'");
        }
        
    }

    @Override
    public String selectSum01(StockportfolioDto sDto) {
        // 주식 투자 금액 합계 조회
        return detailsMapper.selectSum01(sDto);
    }
    
}
