package com.springboot.spring.mapper.com;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ComMapper {
    
    // 대분류코드 조회
    public List<Map<Object, Object>> sectionSelect(Map<String, Object> map);

    // 대분류코드 등록
    public int  sectionInsert(Map<String, Object> map);

    // 대분류코드 수정
    public int  sectionUpdate(Map<String, Object> map);

    public int comCodeClsfcInster(Map<String, Object> mapData);
    
    // 중분류코드 조회
    public List<Map> comCodeClsfcSelect(Map<String, Object> map);

    
    // 중분류코드 조회
    public List<Map> comCodeClsfcSelect001(Map<String, Object> map);

    // 중분류코드 수정
    public int  comCodeClsfcUpdate(Map<String, Object> map);
}
