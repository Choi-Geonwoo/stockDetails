package com.springboot.spring.mapper.yearAlctn;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface YearAlctnMapper {
    
    // ��� �ŷ�����(�⵵)
    public List<Map> yearComparison(Map<String, Object> map);
}
