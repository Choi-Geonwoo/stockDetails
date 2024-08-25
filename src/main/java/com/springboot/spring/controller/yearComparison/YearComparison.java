package com.springboot.spring.controller.yearComparison;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.spring.service.dividend.DividendService;

import lombok.extern.slf4j.Slf4j;

// �⵵ ��� ���� ȭ��
@Slf4j
@Controller
public class YearComparison {

    @Autowired
    public DividendService dividendService;
    
    // �⵵ ���� �ŷ� ����
    @GetMapping("/dividend/yearComparison")
    public String yearComparisonView(Model model 
        ,@RequestParam(value = "trnscdate" ,required=false) String trnscdate)
    {
        
        Map<String, Object> map = new HashMap<>();
        map.put("trnscdate", trnscdate);
        log.info("��系����(�⵵)");
        model.addAttribute("title", "��系����(�⵵)");
        model.addAttribute("dList", dividendService.yearComparison(map));
        return "view/dividend/yearComparison";
        //E:\VisualStudio\workspace3\stockDetails\src\main\resources\templates\view\dividend\dividendList.html
    }


    // �⵵ ���� �ŷ� ����
    @GetMapping("/year/yearComparisonNew")
    public String yearComparisonNewView(Model model 
        ,@RequestParam(value = "trnscdate" ,required=false) String trnscdate)
    {
        
        Map<String, Object> map = new HashMap<>();
        map.put("trnscdate", trnscdate);
        log.info("��系��(�⵵)");
        model.addAttribute("title", "��系��(�⵵)");
        model.addAttribute("dList", dividendService.yearComparison(map));
        return "view/year/yearComparisonNew";
        //E:\VisualStudio\workspace3\stockDetails\src\main\resources\templates\view\dividend\dividendList.html
    }
}
