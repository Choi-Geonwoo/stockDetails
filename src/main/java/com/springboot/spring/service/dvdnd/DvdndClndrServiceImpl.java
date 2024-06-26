package com.springboot.spring.service.dvdnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.spring.dto.TransactionDto;
import com.springboot.spring.mapper.dvdnd.DvdndClndrMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DvdndClndrServiceImpl implements DvdndClndrService {

    @Autowired
    private DvdndClndrMapper dvdndClndrMapper;
    @Override
    public List<Map> transactionList(Map<String, Object> map) {
        List<TransactionDto> tList =  dvdndClndrMapper.transactionList(map);
        List<Map> listAll = null;
        log.info("################################");
        listAll = new ArrayList<Map>();
        for(TransactionDto string : tList){
            HashMap<String, Object> hash = new HashMap<>();
            
            hash.put("title", string.getStockName());
            hash.put("start", string.getTrnscdate());
            hash.put("amount", string.getAmount());
            
            listAll.add(hash);
        }   
        log.info("list " + listAll.toString());
        log.info("################################");
        return listAll;
    }
    
}
