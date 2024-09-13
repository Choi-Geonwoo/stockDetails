package com.springboot.spring.service.com;

import java.util.List;
import java.util.Map;

import com.springboot.spring.vo.StockportfolioVO;

/* 공통코드 등록 */
public interface ComService {
    
    // 대분류 조회
    public List<Map<Object, Object>>  sectionSelect(Map<String, Object> map);
    
    // 대분류 등록
    public String  sectionInsert(Map<String, Object> map);
    
    // 대분류 수정
    public Map<String, String>  sectionUpdate(Map<String, Object> map);
    
    // 중분류 등록
    public Map<String, String>  comCodeClsfcInster(List<Map<String, Object>> map);

    // 중분류 조회
    public List<Map> comCodeClsfcSelect(Map<String, Object> formMap);
    
    // 주식명 조회 셀렉트 박스 사용
    public List<Map>  selectBox(Map<String, Object> map);
}
