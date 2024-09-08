package com.springboot.spring.service.yearAlctn;

import java.util.List;
import java.util.Map;

public interface YearAlctnService {
    
    //배당 거래내역(년도)
    public List<Map>  yearComparison(Map<String, Object> map);
}
