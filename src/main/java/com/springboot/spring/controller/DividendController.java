package com.springboot.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.spring.dto.DividendDto;
import com.springboot.spring.dto.TransactionDto;
import com.springboot.spring.service.DividendService;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
// 배당 관련 화면
@Slf4j
@Controller
public class DividendController {
    
    @Autowired
    public DividendService dividendService;

    @GetMapping("/dividend/dividendList")
    public String detailsView(Model model 
    ,@RequestParam(value = "stockName" ,required=false) String stockName
    ,@RequestParam(value = "trnscdate" ,required=false) String trnscdate){
        TransactionDto tDto = new TransactionDto();
        tDto.setStockName(stockName); // 항목멱
        tDto.setTrnscdate(trnscdate);     //거래내역
        model.addAttribute("trnscdate", trnscdate); 
        model.addAttribute("stockName", stockName); 
        model.addAttribute("selectBox", dividendService.selectBox()); 
        model.addAttribute("title", "배당내역");
        model.addAttribute("dList", dividendService.dividendList(tDto));
        return "view/dividend/dividendList";
        //E:\VisualStudio\workspace3\stockDetails\src\main\resources\templates\view\dividend\dividendList.html
    }
    
    // 배당 등록
    @PostMapping("/dividendInsert.do")
    @ResponseBody
    public String detailsList(@RequestBody Map<String, Object> map, Model model){
        log.info("결과 ㅣ ㅣ :ㅣ " + map.toString());
        return String.valueOf(dividendService.dividendInsert(map));
    }

    
}
