package com.springboot.spring.service.dividend;

import java.util.List;
import java.util.Map;

import com.springboot.spring.dto.CombinedDTO;
import com.springboot.spring.vo.StockportfolioVO;

/* 배당 거래내역 관련 함수 */
public interface DividendService {
    //배당 거래내역
    public List<Map>  dividendList(Map<String, Object> map);
    
    //주별 배당 거래내역
    public List<Map>  byWeekDividendList(Map<String, Object> map);
    
    // 배당 거래 상세 내역
    public CombinedDTO dividendDtlsInqry(Map<String, Object> map); 

    // 주식명 조회 셀렉트 박스 사용
    public List<StockportfolioVO>  selectBox();
    
    // 주식 거래내역 등록
    public int  transactionInsert(Map<String, Object> map, String files);
    
    // 주식 거래내역 수정
    public int  transactionUpdate(Map<String, Object> map, String files);
    
    // 주식 배당 거래내역 삭제
    public int  transactionDelete(String no)  throws Exception;
    

    //배당 거래내역(년도)
    public List<Map>  yearComparison(Map<String, Object> map);
}
