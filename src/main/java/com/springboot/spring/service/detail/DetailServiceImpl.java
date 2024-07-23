package com.springboot.spring.service.detail;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.spring.dto.StockportfolioDto;
import com.springboot.spring.mapper.details.DetailsMapper;
import com.springboot.spring.vo.StockportfolioVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DetailServiceImpl implements DetailsService {

    @Autowired
    private DetailsMapper detailsMapper;
    

    // �ֽ� �ŷ�����
    @Override
    public List<StockportfolioVO> stockDetailsList(StockportfolioDto sDto) {
        // ��ü Ŭ���� ��� �� null ����
        if("allStockName".equals(sDto.getStockName())){
            sDto.setStockName(null);
        }
        // ��ü Ŭ���� ��� �� null ����
        if("��ü".equals(sDto.getDividendCycle())){
            sDto.setDividendCycle(null);
        }
       return detailsMapper.stockDetailsList(sDto);
    }

    // �ֽ� ���� ���
    @Override
    public int detailsInsert(Map<String, Object> map) {
        int cnt = -1;
        ObjectMapper oMapper = new ObjectMapper();
        try {
            StockportfolioDto stockDTO = oMapper.convertValue(map, StockportfolioDto.class);
            //FileDTO fileDTO = new FileDTO();
            //String base64 = String.valueOf(map.get("contents"));
            //fileDTO.setContents(base64.getBytes());
            
            //log.info("������� ����");
            //log.info("toString : : : : " + stockDTO.toString());
            cnt = detailsMapper.detailsInsert(stockDTO);
        } catch (Exception e) {
            log.error("���� : " + e.toString());
            cnt = -1;
        }
        return cnt;
    }

    // �ֽ� ���� ����
    @Override
    public int detailsUpdate(Map<String, Object> map) {
        int cnt = -1;
        
        ObjectMapper oMapper = new ObjectMapper();
        try {
            StockportfolioDto stockDTO = oMapper.convertValue(map, StockportfolioDto.class);
            cnt = detailsMapper.detailsUpdate(stockDTO);
        } catch (Exception e) {
            log.error("���� " + e.toString());
            cnt = -1;
        }
        return cnt;
    }

    // �ֽ� �ŷ����� ����
    @Override
    public int detailsDelete(int registration_order) {
        int cnt = -1;
        try {
            cnt = detailsMapper.detailsDelete(registration_order);
        } catch (Exception e) {
            log.error("���� " + e.toString());
        }
        return cnt;
    }

    // �ֽĸ� ��ȸ ����Ʈ �ڽ� ���
    @Override
    public List<StockportfolioVO> selectBox() {
        StockportfolioDto sDto = null;
        return detailsMapper.selectBox(sDto);
    }

    // �ֽ� �� ��ȸ
    @Override
    public Map stockDetailList(Map<String, Object> map) {
        log.info("DetailServiceImpl.stockDetailList");
        try {
            return detailsMapper.stockDetailList(map);
        } catch (Exception e) {
            log.error("error ", e.toString());
            throw new UnsupportedOperationException("Unimplemented method 'stockDetailList'");
        }
        
    }

    @Override
    public String selectSum01(StockportfolioDto sDto) {
        // �ֽ� ���� �ݾ� �հ� ��ȸ
        return detailsMapper.selectSum01(sDto);
    }
    
}
