package com.springboot.spring.service.com;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.spring.mapper.com.ComMapper;

import lombok.extern.slf4j.Slf4j;

/* 공통코드 등록 */
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

    // 대분류 등록
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

    // 대분류 수정
    @Override
    public Map<String, String> sectionUpdate(Map<String, Object> map) {
        int cnt = 0;
        Map<String, String> retMap = new HashMap<>();
        Map<String, Object> paMap = new HashMap<>();
        try {
                if(map.isEmpty()){
                    throw new UnsupportedOperationException("Unimplemented method 'is   NULL '");
                }
                /******
                paMap.put("NO",         String.valueOf(map.get("value0")));
                paMap.put("SECTION_CD", String.valueOf(map.get("value1")));
                paMap.put("SECTION_NM", null);
                paMap.put("USE_YN",     String.valueOf(map.get("value3")));
                ******/
                paMap.put("NO",         String.valueOf(map.get("value1")));
                paMap.put("SECTION_CD", String.valueOf(map.get("value2")));
                paMap.put("SECTION_NM", null);
                paMap.put("USE_YN",     String.valueOf(map.get("value4")));

                //log.info("paMap + " + paMap.toString());
                
                List<Map<Object, Object>>  liatMap = comMapper.sectionSelect(paMap);

                if((0 != liatMap.size()) && !(String.valueOf(map.get("value1")).equals(liatMap.get(0).get("NO")))){
                    retMap.put("strYn", "N");
                    retMap.put("str", "대분류코드가 중복되었습니다.\n대분류코드 : "+ paMap.get("SECTION_CD")+"");
                    return retMap;

                }
                paMap.put("SECTION_NM", String.valueOf(map.get("value3")));
                cnt = comMapper.sectionUpdate(paMap);

                if(0 == cnt){
                    retMap.put("strYn", "N");
                    retMap.put("str", "실패했습니다.");
                }else{
                    retMap.put("strYn", "Y");
                    retMap.put("str", "성공했습니다.");
                }
            } catch (Exception e) {
                retMap.put("strYn", "F");
                retMap.put("str", "오류가 발생하였습니다.");
                log.error("e", e.toString());
                throw new UnsupportedOperationException("Unimplemented method 'sectionUpdate 01 '");
                }
            return retMap;
    }
    
}
