package com.springboot.spring.service.com;

import java.util.List;
import java.util.Map;

/* �����ڵ� ��� */
public interface ComService {
    
    // ��з� ��ȸ
    public List<Map<Object, Object>>  sectionSelect(Map<String, Object> map);
    
    // ��з� ���
    public String  sectionInsert(Map<String, Object> map);
}
