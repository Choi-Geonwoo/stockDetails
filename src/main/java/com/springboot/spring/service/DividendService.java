package com.springboot.spring.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.spring.dto.CombinedDTO;
import com.springboot.spring.dto.StockportfolioDto;
import com.springboot.spring.dto.TransactionDto;
import com.springboot.spring.vo.StockportfolioVO;

public interface DividendService {
    
    //배당 거래내역
    public List<Map>  dividendList(TransactionDto tDto);
    
    // 배당 거래 상세 내역
    public CombinedDTO dividendDtlsInqry(Map<String, Object> map); 

    // 주식명 조회 셀렉트 박스 사용
    public List<StockportfolioVO>  selectBox();
    
    // 주식 거래내역 등록
    public int  transactionInsert(Map<String, Object> map, MultipartFile[] files);

}
