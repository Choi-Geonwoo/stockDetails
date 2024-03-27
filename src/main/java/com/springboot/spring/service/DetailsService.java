package com.springboot.spring.service;

import java.util.List;
import java.util.Map;

import com.springboot.spring.dto.StockportfolioDto;
import com.springboot.spring.vo.StockportfolioVO;

public interface DetailsService {
    
    // �ֽ� �ŷ�����
    public List<StockportfolioVO>  stockDetailsList(StockportfolioDto sDto);
    
    // �ֽ� �ŷ����� ����
    public int  detailsUpdate(Map<String, Object> map);
    
    // �ֽĸ� ��ȸ ����Ʈ �ڽ� ���
    public List<StockportfolioVO>  selectBox();

    // �ֽ� �ŷ����� ���
    public int  detailsInsert(Map<String, Object> map);

    // �ֽ� �ŷ����� ����
    public int detailsDelete(int registration_order);
    
    // �ֽ� �� ��ȸ
    public Map<String, Object> stockDetailList(Map<String, Object> map);

}
