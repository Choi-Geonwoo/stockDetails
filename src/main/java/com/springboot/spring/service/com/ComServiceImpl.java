package com.springboot.spring.service.com;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.spring.com.IsNullCheck;
import com.springboot.spring.dto.StockportfolioDto;
import com.springboot.spring.mapper.com.ComMapper;
import com.springboot.spring.vo.StockportfolioVO;

import lombok.extern.slf4j.Slf4j;
import oracle.sql.TIMESTAMP;

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
                paMap.put("NO",         String.valueOf(map.get("value0")));
                paMap.put("SECTION_CD", String.valueOf(map.get("value1")));
                paMap.put("SECTION_NM", null);
                paMap.put("USE_YN",     String.valueOf(map.get("value3")));
                log.info("paMap + " + paMap.toString());
                
                List<Map<Object, Object>>  liatMap = comMapper.sectionSelect(paMap);

                if((0 != liatMap.size()) && !(String.valueOf(map.get("value0")).equals(liatMap.get(0).get("NO")))){
                    retMap.put("strYn", "N");
                    retMap.put("str", "대분류코드가 중복되었습니다.\n대분류코드 : "+ paMap.get("SECTION_CD")+"");
                    return retMap;

                }
                paMap.put("SECTION_NM", String.valueOf(map.get("value2")));
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




    // 중분류 등록
    @Override
    public Map<String, String> comCodeClsfcInster(List<Map<String, Object>> listMap) {
        int cnt = 0;
        String str = "N";
        Map<String, String> retMap = new HashMap<>();
        try {
            for (Map<String,Object> mapData : listMap) {
                List<Map>  listMap01 = comMapper.comCodeClsfcSelect(mapData);
                
                    /*********
                     * 케이스
                     * 1. 신규 등록
                        ㄴ cnt += comMapper.comCodeClsfcInster(mapData);
                     * 2. 대분류코드, 중분류코드, 중분류코드명, 사용여부 중복 등록
                     * 3. 대분류코드, 중분류코드 미변경, (중분류코드명 or 사용여부) 변경
                     *  ㄴ cnt += comMapper.comCodeClsfcUpdate(mapData);
                     *********/
                    // 1. 신규 등록
                    boolean bo01 = (listMap01.size() == 0) && (IsNullCheck.isNull(mapData.get("NO")));
                    boolean bo02 = false ;
                    boolean bo03 = false ;
                    for (Map<String,Object> map01 : listMap01) {
                        bo02 = (
                            (map01.get("SECTION_CD").equals(mapData.get("SECTION_CD"))) &&
                            (map01.get("CLSFC_CD").equals(mapData.get("CLSFC_CD"))) &&
                            (map01.get("CLSFC_NM").equals(mapData.get("CLSFC_NM"))) &&
                            (map01.get("USE_YN").equals(mapData.get("USE_YN"))) 
                        );
                        bo03 = (
                            (map01.get("SECTION_CD").equals(mapData.get("SECTION_CD"))) &&
                            (map01.get("CLSFC_CD").equals(mapData.get("CLSFC_CD"))) &&
                            (!(map01.get("CLSFC_NM").equals(mapData.get("CLSFC_NM"))) ||
                             !(map01.get("USE_YN").equals(mapData.get("USE_YN")))
                            )
                            );
                    }
                    log.info("/*****************************");
                    log.info(" * bo01 : " + bo01 + " | bo02 : " + bo02 + " | bo03 : " + bo03);
                    log.info("*****************************/");
                    if(bo01){
                        // 1. 신규 등록
                        cnt += comMapper.comCodeClsfcInster(mapData);
                    }else if(bo02){
                        // 2. 대분류코드, 중분류코드, 중분류코드명, 사용여부 중복 등록
                        retMap.put("strYn", "N");
                        retMap.put("str", "중복되었습니다.");
                        return retMap;
                    }else if(bo03){
                        // 3. 대분류코드, 중분류코드 미변경, (중분류코드명 or 사용여부) 변경
                        cnt += comMapper.comCodeClsfcUpdate(mapData);
                    }

                }
            if(listMap.size() == cnt) {
                retMap.put("strYn", "Y");
                retMap.put("str", "성공했습니다.");
            }else{
                retMap.put("strYn", "N");
                retMap.put("str", "실패했습니다.");
            }

        } catch (Exception e) {
            str="N";
            retMap.put("strYn", "F");
            retMap.put("str", "오류가 발생하였습니다.");
            throw new UnsupportedOperationException("Unimplemented method 'comCodeClsfcInster'");
        }
        return retMap;
    }

    // 중분류 코드 조회
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
                // 데이터베이스에서 oracle.sql.TIMESTAMP 값을 가져왔다고 가정
                TIMESTAMP oracleTimestamp = new TIMESTAMP(String.valueOf(listMap.get(i).get("REG_YMD")));
                // TIMESTAMP를 java.sql.Timestamp로 변환
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

        // 주식명 조회 셀렉트 박스 사용
    @Override
    public List<Map> selectBox(Map<String, Object> map) {
        //Map<String, Object> mapDate = map;
        return comMapper.selectBox(map);
    }
}

