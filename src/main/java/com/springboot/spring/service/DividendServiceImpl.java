package com.springboot.spring.service;

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

    // 배당 거래 내역
    @Override
    public int transactionInsert(Map<String, Object> map, MultipartFile[] files) {
        int cnt = -1;
        ObjectMapper oMapper = new ObjectMapper();
        String tNo = "";
        try {
            //TransactionDto tDTO = oMapper.convertValue(map, TransactionDto.class);
            TransactionDto tDTO = new TransactionDto();
            FileDTO fDto = new FileDTO();
            tDTO.setStockName(String.valueOf(map.get("stockName")));
            tDTO.setTrnscdate(String.valueOf(map.get("trnscdate")));
            tDTO.setAmount(String.valueOf(map.get("amount")));
            String base64 = String.valueOf(files);
            tNo = dividendMapper.tNoString(tDTO);
            log.info("#####################################");
            log.info("#####################################");
            log.info("#####################################");
            log.info(map.toString());
            log.info("#####################################");
            log.info("#####################################");
            tDTO.setNo(Integer.valueOf(tNo));
            fDto.setTNo(tNo);
            fDto.setFName(String.valueOf(map.get("fName")));
            // 거래일자 등록
            cnt = dividendMapper.transactionInsert(tDTO);
            
            fDto.setContents(base64.getBytes());
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
            switch (String.valueOf(map.get("className"))) {
                case "JANUARY":
                    tDto.setTrnscdate(map.get("trnscdate")+"-01-01");
                    break;
                case "FEBRUARY":
                    tDto.setTrnscdate(map.get("trnscdate")+"-02-01");
                    break;
                case "MARCH":
                    tDto.setTrnscdate(map.get("trnscdate")+"-03-01");
                    break;
                case "APRIL":
                    tDto.setTrnscdate(map.get("trnscdate")+"-04-01");
                    break;
                case "MAY":
                    tDto.setTrnscdate(map.get("trnscdate")+"-05-01");
                    break;
                case "JUNE":
                    tDto.setTrnscdate(map.get("trnscdate")+"-06-01");
                    break;
                case "JULY":
                    tDto.setTrnscdate(map.get("trnscdate")+"-07-01");
                    break;
                case "AUGUST":
                    tDto.setTrnscdate(map.get("trnscdate")+"-08-01");
                    break;
                case "SEPTEMBER":
                    tDto.setTrnscdate(map.get("trnscdate")+"-09-01");
                    break;
                case "OCTOBER":
                    tDto.setTrnscdate(map.get("trnscdate")+"-10-01");
                    break;
                case "NOVEMBER":
                    tDto.setTrnscdate(map.get("trnscdate")+"-11-01");
                    break;
                case "DECEMBER":
                    tDto.setTrnscdate(map.get("trnscdate")+"-12-01");
                    break;
            }

            tList = dividendMapper.dividendDtlsInqry(tDto);
            fDto = fileMapper.imgFileList(String.valueOf(tList.getNo()));
            byte[] imageContent = (byte[]) fDto.getContents();
            log.info("################################");
            log.info("################################");
            log.info(""+fDto.getContents());
            log.info(""+imageContent);
            log.info("################################");
            log.info("################################");
            log.info("################################");
            fDto.setReContents(fDto.getContents().toString());
            cDto.setTransactionDto(tList);
            cDto.setFileDTO(fDto);
        } catch (Exception e) {
            log.error(e.toString());
            cDto = null;
        }
        return cDto;
    }
    
}
