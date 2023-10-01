package com.springboot.spring.service;

import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.spring.com.DateRltd;
import com.springboot.spring.dto.CombinedDTO;
import com.springboot.spring.dto.DividendDto;
import com.springboot.spring.dto.FileDTO;
import com.springboot.spring.dto.StockportfolioDto;
import com.springboot.spring.dto.TransactionDto;
import com.springboot.spring.mapper.DetailsMapper;
import com.springboot.spring.mapper.DividendMapper;
import com.springboot.spring.mapper.FileMapper;
import com.springboot.spring.vo.StockportfolioVO;

import lombok.extern.slf4j.Slf4j;

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
    public List<Map> dividendList(TransactionDto tDto) { 
        // 날짜 공백인경우 현재 날짜로 지정
        if("".equals(tDto.getTrnscdate()) || null == tDto.getTrnscdate()){
            Date nowDate = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            tDto.setTrnscdate(simpleDateFormat.format(nowDate));
        }
        if("total".equals(tDto.getTrnscdate())){
            tDto.setTrnscdate("");
        }
        // tDto
        //log.info("toString : : : : : " + tDto.toString());
        return dividendMapper.dividendList(tDto);
    }

    // 주식명 조회 셀렉트 박스 사용
    @Override
    public List<StockportfolioVO> selectBox() {
        StockportfolioDto sDto = null;
        return detailsMaper.stockDetailsList(sDto);
    }

    // 배당 거래 내역 등록
    @Override
    public int transactionInsert(Map<String, Object> map, String files) {
        int cnt = -1;
        String tNo = "";
        try {
            TransactionDto tDTO = new TransactionDto();
            FileDTO fDto = new FileDTO();
            tDTO.setStockName(String.valueOf(map.get("stockName")));
            tDTO.setTrnscdate(String.valueOf(map.get("trnscdate")));
            tDTO.setAmount(String.valueOf(map.get("amount")));
            tNo = dividendMapper.tNoString(tDTO);
            tDTO.setNo(Integer.valueOf(tNo));
            fDto.setTNo(tNo);
            fDto.setFName(String.valueOf(map.get("fName")));
            // 배당 거래 등록
            cnt = dividendMapper.transactionInsert(tDTO);
            fDto.setContents(files.getBytes());
            // 이미지 파일 등록
            fileMapper.fileInsert(fDto);
            
        } catch (Exception e) {
            log.error("오류 : " + e.toString());
            cnt = -1;
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
                //fDto.setReContents(fDto.getContents().toString());
                String base64ToString = new String(fDto.getContents());
                fDto.setReContents(base64ToString);
                log.info("결과 :ㅣ : " + fDto.toString());
                log.info("결과 :ㅣ : " + fDto.toString());
                log.info("결과 :ㅣ : " + fDto.toString());
                log.info("결과 :ㅣ : " + fDto.toString());
                log.info("결과 :ㅣ : " + fDto.toString());
                cDto.setFileDTO(fDto);

            }
            cDto.setTransactionDto(tList);
        } catch (Exception e) {
            log.error(e.toString());
            cDto = null;
        }
        return cDto;
    }


    @Override
    public String imgData(String files) {
        FileDTO fDto = new FileDTO();
        String tNo = "";
            tNo = "999";
            fDto.setTNo(tNo);
            fDto.setFName("1111");

            fDto.setContents(files.getBytes());
            fileMapper.fileInsert(fDto);
        return "";
    }
    
}
