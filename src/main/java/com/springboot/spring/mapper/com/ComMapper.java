package com.springboot.spring.mapper.com;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ComMapper {
    
    // ��з��ڵ� ��ȸ
    public List<Map<Object, Object>> sectionSelect(Map<String, Object> map);

    // ��з��ڵ� ���
    public int  sectionInsert(Map<String, Object> map);

    
    // ��з��ڵ� ����
    public int  sectionUpdate(Map<String, Object> map);
}
