package com.springboot.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.spring.dto.StockDTO;
import com.springboot.spring.vo.StockVO;

@Mapper
public interface DetailsMapper {

    // 주식 거래내역
    public List<StockVO>  stockDetailsList();

    
    // 주식 거래내역 수정
    public int  detailsUpdate(StockDTO stockDTO);

    
    // 주식 거래내역 등록
    public int  detailsInsert(StockDTO stockDTO);

    // 주식 거랙내역 삭제
    public int detailsDelete(int registration_order);
}
