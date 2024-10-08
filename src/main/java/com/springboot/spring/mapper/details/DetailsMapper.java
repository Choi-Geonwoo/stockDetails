package com.springboot.spring.mapper.details;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.spring.dto.StockportfolioDto;
import com.springboot.spring.vo.StockportfolioVO;

@Mapper
public interface DetailsMapper {

    // 주식 거래내역
    public List<StockportfolioVO>  stockDetailsList(StockportfolioDto sDto);
    
    // 주식 종목 조회
    public List<StockportfolioVO>  selectBox(StockportfolioDto sDto);

    // 주식 거래내역 수정
    public int  detailsUpdate(StockportfolioDto stockDTO);

    
    // 주식 거래내역 등록
    public int  detailsInsert(StockportfolioDto stockDTO);

    // 주식 거랙내역 삭제
    public int detailsDelete(int registration_order);
    
    // 주식 상세 조회
    public Map stockDetailList(Map<String, Object> map);

    // 자산관리 총 자산, 누적 배당금
    public List<Map> selectSum01(StockportfolioDto sDto);
    
    // 자산관리 개별 배당금
    public List<Map> selectSum02(StockportfolioDto sDto);
    
    // 자산관리 배당금 추이
    public List<Map> selectSum03(StockportfolioDto sDto);
    
    // 자산관리 월별 배당금 추이
    public List<Map> selectSum04(StockportfolioDto sDto);
}
