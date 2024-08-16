package com.springboot.spring.service.com;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.spring.mapper.com.ComMapper;

import lombok.extern.slf4j.Slf4j;
import oracle.sql.TIMESTAMP;

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
        Map<String, Object> paMap = new HashMap<>();
        try {
            paMap.put("SECTION_NM", null);
            paMap.put("SECTION_NM", map.get("SECTION_CD"));
            cnt = comMapper.sectionSelect(paMap).size();
            paMap.putAll(map);
            if(cnt == 0){
                cnt = comMapper.sectionInsert(paMap);
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
        Map<String, Object> paMap = new HashMap<>();
        try {
                if(map.isEmpty()){
                    throw new UnsupportedOperationException("Unimplemented method 'is   NULL '");
                }
                paMap.put("NO",         String.valueOf(map.get("value0")));
                paMap.put("SECTION_CD", String.valueOf(map.get("value1")));
                paMap.put("SECTION_NM", null);
                paMap.put("USE_YN",     String.valueOf(map.get("value3")));
                log.info("paMap + " + paMap.toString());
                
                List<Map<Object, Object>>  liatMap = comMapper.sectionSelect(paMap);

                if((0 != liatMap.size()) && !(String.valueOf(map.get("value0")).equals(liatMap.get(0).get("NO")))){
                    retMap.put("strYn", "N");
                    retMap.put("str", "��з��ڵ尡 �ߺ��Ǿ����ϴ�.\n��з��ڵ� : "+ paMap.get("SECTION_CD")+"");
                    return retMap;

                }
                paMap.put("SECTION_NM", String.valueOf(map.get("value2")));
                cnt = comMapper.sectionUpdate(paMap);

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
                throw new UnsupportedOperationException("Unimplemented method 'sectionUpdate 01 '");
                }
            return retMap;
    }




    // �ߺз� ���
    @Override
    public Map<String, String> comCodeClsfcInster(List<Map<String, Object>> listMap) {
        int cnt = 0;
        String str = "N";
        Map<String, String> retMap = new HashMap<>();
        try {
            for (Map<String,Object> mapData : listMap) {
                List<Map>  liatMap01 = comMapper.comCodeClsfcSelect(mapData);
                    if(0 != liatMap01.size() ){
                        if("Y".equals(liatMap01.get(0).get("CHEK"))
                            && String.valueOf(mapData.get("CLSFC_NM")).equals(liatMap01.get(0).get("CLSFC_NM"))){
                            retMap.put("strYn", "F");
                            retMap.put("str", "�ߺ��Ǿ����ϴ�.");
                            return retMap;
                        }else{                            
                            cnt += comMapper.comCodeClsfcUpdate(mapData);
                        }
                    }else{
                        cnt += comMapper.comCodeClsfcInster(mapData);
                    }
            }
            if(listMap.size() == cnt) {
                retMap.put("strYn", "Y");
                retMap.put("str", "�����߽��ϴ�.");
            }else{
                retMap.put("strYn", "N");
                retMap.put("str", "�����߽��ϴ�.");
            }

        } catch (Exception e) {
            str="N";
            retMap.put("strYn", "F");
            retMap.put("str", "������ �߻��Ͽ����ϴ�.");
            throw new UnsupportedOperationException("Unimplemented method 'comCodeClsfcInster'");
        }
        return retMap;
    }

    // �ߺз� �ڵ� ��ȸ
    @Override
    public List<Map> comCodeClsfcSelect(Map<String, Object> formMap) {
        List<Map> listMap = new ArrayList<>();
        List<Map> retListMap = new ArrayList<>();
        Map retMap = new HashMap<>();
        Boolean useBl = false;
        try {
            listMap = comMapper.comCodeClsfcSelect(formMap);
            for (int i = 0; i < listMap.size(); i++) {
                retMap = new HashMap<>();
                retMap.putAll(listMap.get(i));
                // �����ͺ��̽����� oracle.sql.TIMESTAMP ���� �����Դٰ� ����
                TIMESTAMP oracleTimestamp = new TIMESTAMP(String.valueOf(listMap.get(i).get("REG_YMD")));
                // TIMESTAMP�� java.sql.Timestamp�� ��ȯ
                Timestamp timestamp = oracleTimestamp.timestampValue();
                retMap.put("REG_YMD", timestamp);
                useBl = "Y".equals(retMap.get("USE_YN"));
                retMap.put("USE_YN", useBl);
                retListMap.add(retMap);
            }
            log.info("ret : : : : " + retListMap.toString());
        } catch (Exception e) {
            retListMap = null;
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'comCodeClsfcSelect'");
        }
        return retListMap;
    }

    
}

