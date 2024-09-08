package com.springboot.spring.service.yearAlctn;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.spring.mapper.dividend.DividendMapper;
import com.springboot.spring.mapper.yearAlctn.YearAlctnMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class YearAlctnServiceImpl implements YearAlctnService {
    
    @Autowired
    private DividendMapper dividendMapper;

    
    @Autowired
    private YearAlctnMapper yearAlctnMapper;

    //년도 배당금
    @Override
    public List<Map> yearComparison(Map<String, Object> map) {
        try {
            return yearAlctnMapper.yearComparison(map);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'yearComparison'");
        }
    }
}
