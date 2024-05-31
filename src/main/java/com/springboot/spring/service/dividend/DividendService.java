package com.springboot.spring.service.dividend;

import java.util.List;
import java.util.Map;

import com.springboot.spring.dto.CombinedDTO;
import com.springboot.spring.vo.StockportfolioVO;

/* ��� �ŷ����� ���� �Լ� */
public interface DividendService {
    //��� �ŷ�����
    public List<Map>  dividendList(Map<String, Object> map);
    
    //�ֺ� ��� �ŷ�����
    public List<Map>  byWeekDividendList(Map<String, Object> map);
    
    // ��� �ŷ� �� ����
    public CombinedDTO dividendDtlsInqry(Map<String, Object> map); 

    // �ֽĸ� ��ȸ ����Ʈ �ڽ� ���
    public List<StockportfolioVO>  selectBox();
    
    // �ֽ� �ŷ����� ���
    public int  transactionInsert(Map<String, Object> map, String files);
    
    // �ֽ� �ŷ����� ����
    public int  transactionUpdate(Map<String, Object> map, String files);
    
    // �ֽ� ��� �ŷ����� ����
    public int  transactionDelete(String no)  throws Exception;
    

    //��� �ŷ�����(�⵵)
    public List<Map>  yearComparison(Map<String, Object> map);
}
