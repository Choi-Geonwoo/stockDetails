package com.springboot.spring.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.spring.dto.StockportfolioDto;
import com.springboot.spring.vo.StockportfolioVO;

@Mapper
public interface DetailsMapper {

    // �ֽ� �ŷ�����
    public List<StockportfolioVO>  stockDetailsList(StockportfolioDto sDto);
    
    // �ֽ� ���� ��ȸ
    public List<StockportfolioVO>  selectBox(StockportfolioDto sDto);

    
    // �ֽ� �ŷ����� ����
    public int  detailsUpdate(StockportfolioDto stockDTO);

    
    // �ֽ� �ŷ����� ���
    public int  detailsInsert(StockportfolioDto stockDTO);

    // �ֽ� �ŷ����� ����
    public int detailsDelete(int registration_order);
    
    // �ֽ� �� ��ȸ
    public Map stockDetailList(Map<String, Object> map);
}
