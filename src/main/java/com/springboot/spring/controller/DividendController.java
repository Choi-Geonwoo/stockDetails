package com.springboot.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.spring.dto.DividendDto;
import com.springboot.spring.service.DividendService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DividendController {
    
    @Autowired
    public DividendService dividendService;
    
    @GetMapping("/dividend/dividendList")
    public String detailsView(Model model
    ,@RequestParam(value = "category" ,required=false) String category
    ,@RequestParam(value = "yearmonth" ,required=false) String yearmonth){
        log.info("category " + category);
        log.info("yearmonth " + yearmonth);
        DividendDto dividendDto = new DividendDto();
        dividendDto.setYearmonth(yearmonth);
        dividendDto.setCategory(category);
        model.addAttribute("yearmonth", yearmonth);
        model.addAttribute("category", category);
        model.addAttribute("title", "배당내역");
        model.addAttribute("dList", dividendService.dividendList(dividendDto));
        return "view/dividend/dividendList";
        //E:\VisualStudio\workspace3\stockDetails\src\main\resources\templates\view\dividend\dividendList.html
    }
    
    @GetMapping("/dividend/dividendList.do")
    public String detailsList(Model model){
        Map<String, Object> javaMap = new HashMap<String, Object>();
        return "";
    }
}
