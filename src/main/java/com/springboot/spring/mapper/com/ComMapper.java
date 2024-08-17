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

    public int comCodeClsfcInster(Map<String, Object> mapData);
    
    // �ߺз��ڵ� ��ȸ
    public List<Map> comCodeClsfcSelect(Map<String, Object> map);

    
    // �ߺз��ڵ� ��ȸ
    public List<Map> comCodeClsfcSelect001(Map<String, Object> map);

    // �ߺз��ڵ� ����
    public int  comCodeClsfcUpdate(Map<String, Object> map);
}
