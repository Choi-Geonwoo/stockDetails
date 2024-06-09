package com.springboot.spring.service.com;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.spring.mapper.ComMapper;

import lombok.extern.slf4j.Slf4j;

/* �����ڵ� ��� */
@Slf4j
@Service
public class ComServiceImpl implements ComService {
    
    @Autowired
    private ComMapper comMapper;
    
    @Override
    public List<Map<Object, Object>>  sectionSelect(Map<String, Object> map) {
        //log.debug("return " + map.toString());
        return comMapper.sectionSelect(map);
    }

    // ��з� ���
    @Override
    public String sectionInsert(Map<String, Object> map) {
        int cnt = 0;
        String str = "N";
        try {
            map.put("SECTION_NM", null);
            cnt = comMapper.sectionSelect(map).size();
            if(cnt == 0){
                cnt = comMapper.sectionInsert(map);
            }
            if(cnt != 0){
                str="Y";
            }
        } catch (Exception e) {
            str="N";
            throw new UnsupportedOperationException("Unimplemented method 'sectionInsert'");
        }
        return str;
    }

    // ��з� ����
    @Override
    public Map<String, String> sectionUpdate(Map<String, Object> map) {
        int cnt = 0;
        Map<String, String> retMap = new HashMap<>();
        try {
            cnt = comMapper.sectionUpdate(map);
            if(0 == cnt){
                retMap.put("strYn", "N");
                retMap.put("str", "�����߽��ϴ�.");
            }else{
                retMap.put("strYn", "Y");
                retMap.put("str", "�����߽��ϴ�.");
            }
            } catch (Exception e) {
                retMap.put("strYn", "F");
                retMap.put("str", "������ �߻��Ͽ����ϴ�.");
                log.error("e", e.toString());
                throw new UnsupportedOperationException("Unimplemented method 'sectionUpdate'");
                }
            return retMap;
    }
    
}
