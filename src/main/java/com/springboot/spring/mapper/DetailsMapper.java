package com.springboot.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.spring.dto.StockDTO;
import com.springboot.spring.dto.StockportfolioDto;
import com.springboot.spring.vo.StockVO;
import com.springboot.spring.vo.StockportfolioVO;

@Mapper
public interface DetailsMapper {

    // 주식 거래내역
    public List<StockportfolioVO>  stockDetailsList(StockportfolioDto sDto);

    
    // 주식 거래내역 수정
    public int  detailsUpdate(StockportfolioDto stockDTO);

    
    // 주식 거래내역 등록
    public int  detailsInsert(StockportfolioDto stockDTO);

    // 주식 거랙내역 삭제
    public int detailsDelete(int registration_order);
}
