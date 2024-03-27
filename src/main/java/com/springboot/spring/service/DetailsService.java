package com.springboot.spring.service;

import java.util.List;
import java.util.Map;

import com.springboot.spring.dto.StockportfolioDto;
import com.springboot.spring.vo.StockportfolioVO;

public interface DetailsService {
    
    // 주식 거래내역
    public List<StockportfolioVO>  stockDetailsList(StockportfolioDto sDto);
    
    // 주식 거래내역 수정
    public int  detailsUpdate(Map<String, Object> map);
    
    // 주식명 조회 셀렉트 박스 사용
    public List<StockportfolioVO>  selectBox();

    // 주식 거래내역 등록
    public int  detailsInsert(Map<String, Object> map);

    // 주식 거랙내역 삭제
    public int detailsDelete(int registration_order);
    
    // 주식 상세 조회
    public Map<String, Object> stockDetailList(Map<String, Object> map);

}
