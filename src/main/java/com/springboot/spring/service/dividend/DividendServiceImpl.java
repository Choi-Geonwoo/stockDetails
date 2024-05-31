package com.springboot.spring.service.dividend;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.spring.com.CnvrsData;
import com.springboot.spring.com.DateRltd;
import com.springboot.spring.com.IsNullCheck;
import com.springboot.spring.dto.CombinedDTO;
import com.springboot.spring.dto.FileDTO;
import com.springboot.spring.dto.StockportfolioDto;
import com.springboot.spring.dto.TransactionDto;
import com.springboot.spring.mapper.DetailsMapper;
import com.springboot.spring.mapper.DividendMapper;
import com.springboot.spring.mapper.FileMapper;
import com.springboot.spring.vo.StockportfolioVO;

import lombok.extern.slf4j.Slf4j;

// 주식 배당 거래내역 관련 함수
@Slf4j
@Service
public class DividendServiceImpl implements DividendService {
    

    @Autowired
    private DividendMapper dividendMapper;
    
    @Autowired
    private DetailsMapper detailsMaper;

    @Autowired
    private FileMapper fileMapper;

    // 배당 내역 조회
    @Override
    public List<Map> dividendList(Map<String, Object> map) { 
        TransactionDto tDto = new TransactionDto();
        if(IsNullCheck.isNull(map.get("stockName"))){
            tDto.setStockName("");
        }else{
            tDto.setStockName(String.valueOf(map.get("stockName")));
        }
        log.info("전체 : ", map);
        //년도 공백 인경우
        
        if(IsNullCheck.isNull(map.get("trnscdate")) && IsNullCheck.isNull(map.get("monthSelect"))){
            tDto.setTrnscdate("");
        }else if(!IsNullCheck.isNull(map.get("trnscdate")) && IsNullCheck.isNull(map.get("monthSelect"))){
            tDto.setTrnscdate(String.valueOf(map.get("trnscdate")+"-"));
        }else if(IsNullCheck.isNull(map.get("trnscdate")) && !IsNullCheck.isNull(map.get("monthSelect")) ){
            tDto.setTrnscdate(String.valueOf("-"+map.get("monthSelect")));
        }else if(!IsNullCheck.isNull(map.get("trnscdate")) && !IsNullCheck.isNull(map.get("monthSelect")) ){
            tDto.setTrnscdate(String.valueOf(map.get("trnscdate")+"-"+map.get("monthSelect")));
        }
        
        //tDto.setTrnscdate(String.valueOf(map.get("trnscdate")+"-"+map.get("monthSelect")));
        //tDto.setTrnscdate(String.valueOf(CnvrsData.toMonth(map.get("trnscdate"))));
        // tDto
        //log.info("toString : : : : : " + tDto.toString());
        return dividendMapper.dividendList(tDto);
    }

    // 주식명 조회 셀렉트 박스 사용
    @Override
    public List<StockportfolioVO> selectBox() {
        StockportfolioDto sDto = null;
        return detailsMaper.selectBox(sDto);
    }

 // 배당 거래 내역 등록
    @Override
    public int transactionInsert(Map<String, Object> map, String files) {
        int cnt = -1;
        String tNo = "";
        try {
            log.info("========== transactionInsert START ===========");
            TransactionDto tDTO = new TransactionDto();
            FileDTO fDto = new FileDTO();
            tDTO.setStockName(String.valueOf(map.get("stockName")));
            tDTO.setTrnscdate(String.valueOf(map.get("trnscdate")));
            tDTO.setAmount(String.valueOf(map.get("amount")));
            tDTO.setDividend(String.valueOf(map.get("dividend")));
            tNo = dividendMapper.tNoString(tDTO);
            //tNo ="999";
            tDTO.setNo(Integer.valueOf(tNo));
            fDto.setTNo(tNo);
            fDto.setFName(String.valueOf(map.get("fName")));
            // ë°°ëš ęą°ë ?ąëĄ?
            cnt = dividendMapper.transactionInsert(tDTO);
            if(!IsNullCheck.isNull(files)){
                log.info("1. ?ě˛? ę°? : : : " + fDto.getFName() + " | " + fDto.getFNo() + " | " + fDto.getTNo() + " | " + files.length());
                fDto.setContents(files.getBytes());
                // ?´ëŻ¸ě?? ??ź ?ąëĄ?
                fileMapper.fileInsert(fDto);
            }
            log.info("========== transactionInsert END ===========");
            
        } catch (Exception e) {
            cnt = -1;
            handleException("transactionInsert",e);
        }
        return cnt;
    }

    // 배당 거래 상세 내역
    @Override
    public CombinedDTO dividendDtlsInqry(Map<String, Object> map) {
        TransactionDto tList = new TransactionDto();
        TransactionDto tDto = new TransactionDto();
        FileDTO fDto = new FileDTO();
        CombinedDTO cDto = new CombinedDTO();
        try {
            tDto.setStockName(String.valueOf(map.get("stockName")));
            String rStr = DateRltd.dateCnvrs(map);
            tDto.setTrnscdate(rStr);
            tList = dividendMapper.dividendDtlsInqry(tDto);
            fDto = fileMapper.imgFileList(String.valueOf(tList.getNo()));
            if(null != fDto){
                 fDto.setReContents(fDto.getContents().toString());
                 String base64ToString = new String(fDto.getContents());
                 fDto.setReContents(base64ToString);
                 cDto.setFileDTO(fDto);

             }
            cDto.setTransactionDto(tList);
        } catch (Exception e) {
            cDto = null;
            handleException("dividendDtlsInqry",e);
        }
        return cDto;
    }

    // 주식 배당 거래내역 함수
    @Override
    public int transactionUpdate(Map<String, Object> map, String files) {
        int cnt = -1;
    
        try {
            log.info("========== transactionUpdate START ===========");
            //log.info("FILE " + files);
            //log.info("mapmapmapmapmapmap " + map.toString());
            TransactionDto tDTO = new TransactionDto();
            tDTO.setStockName(String.valueOf(map.get("stockName")));
            tDTO.setTrnscdate(String.valueOf(map.get("trnscdate")));
            tDTO.setAmount(String.valueOf(map.get("amount")));
            tDTO.setDividend(String.valueOf(map.get("dividend")));
            tDTO.setNo(Integer.valueOf(String.valueOf(map.get("no"))));
            cnt = dividendMapper.transactionUpdate(tDTO);
    
            if (!IsNullCheck.isNull(files)) {
                FileDTO fDTO = new FileDTO();
                fDTO.setFNo(String.valueOf(map.get("fNo")));
                fDTO.setFName(String.valueOf(map.get("fName")));
                fDTO.setContents(files.getBytes());
                fDTO.setTNo(String.valueOf(map.get("no")));
    
                if (IsNullCheck.isNull(fDTO.getFNo())) {
                    cnt += fileMapper.fileInsert(fDTO);
                } else {
                    cnt += fileMapper.fileUpdate(fDTO);
                }
            }
    
            log.info("succ : " + cnt);
            log.info("========== transactionUpdate END ===========");
        } catch (Exception e) {
            handleException("transactionUpdate",e);
            cnt = -1;
        }
    
        return cnt;
    }

    @Override
    public int transactionDelete(String no) throws Exception {
        int cnt = -1;
        try {            
            cnt = dividendMapper.transactionDelete(no);
        } catch (Exception e) {
            log.error("============== transactionDelete ERROR START =====================");
            log.error(e.toString());
            log.error("============== transactionDelete ERROR END =====================");
            throw new UnsupportedOperationException("Unimplemented method 'transactionDelete'");
        }
        return cnt;
    }

    // 주별 배당 내역 조회
    @Override
    public List<Map> byWeekDividendList(Map<String, Object> map) { 
        map.put("startYmd", ("null".equals(map.get("startYmd")) ? null :  map.get("startYmd")));
        map.put("endYmd", ("null".equals(map.get("endYmd")) ? null :  map.get("endYmd")));
        return dividendMapper.byWeekDividendList(map);
    }


    //년도 배당금
    @Override
    public List<Map> yearComparison(Map<String, Object> map) {
        try {
            return dividendMapper.yearComparison(map);
        } catch (Exception e) {
            throw new UnsupportedOperationException("Unimplemented method 'yearComparison'");
        }
    }

    
    private void handleException(String str,Exception e) {
        log.error(" === "+str+" ERROR START === ");
        log.error(e.toString());
        log.error(" === "+str+" ERROR END === ");
    }



}
