package com.springboot.spring.service.com;

import java.util.List;
import java.util.Map;

import com.springboot.spring.vo.StockportfolioVO;

/* �����ڵ� ��� */
public interface ComService {
    
    // ��з� ��ȸ
    public List<Map<Object, Object>>  sectionSelect(Map<String, Object> map);
    
    // ��з� ���
    public String  sectionInsert(Map<String, Object> map);
    
    // ��з� ����
    public Map<String, String>  sectionUpdate(Map<String, Object> map);
    
    // �ߺз� ���
    public Map<String, String>  comCodeClsfcInster(List<Map<String, Object>> map);

    // �ߺз� ��ȸ
    public List<Map> comCodeClsfcSelect(Map<String, Object> formMap);
    
    // �ֽĸ� ��ȸ ����Ʈ �ڽ� ���
    public List<Map>  selectBox(Map<String, Object> map);
}
