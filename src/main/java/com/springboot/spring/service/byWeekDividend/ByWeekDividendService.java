package com.springboot.spring.service.byWeekDividend;

import java.util.List;
import java.util.Map;

import com.springboot.spring.vo.StockportfolioVO;

// ��� �ֺ� ����
public interface ByWeekDividendService {
    
    // �ֺ� ��� �ŷ�����
    public List<Map> byWeekDividendList(Map<String, Object> map);
    

}
