package com.springboot.spring.mapper.byWeekDividend;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

// ��� �ֺ� ����
@Mapper
public interface ByWeekDividendMapper {
    
    // �ֺ� ��� �ŷ�����
    public List<Map> byWeekDividendList(Map<String, Object> map);
}
