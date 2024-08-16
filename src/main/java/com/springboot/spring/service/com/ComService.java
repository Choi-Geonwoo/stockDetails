package com.springboot.spring.service.com;

import java.util.List;
import java.util.Map;

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
}
