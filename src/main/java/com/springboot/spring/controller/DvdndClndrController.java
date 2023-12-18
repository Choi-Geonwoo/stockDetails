package com.springboot.spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.spring.service.DvdndClndrService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DvdndClndrController {
    @Autowired
    public DvdndClndrService dvdndClndrService;
    
    @GetMapping("/clndr/dvdndClndr")
    public String detailsView(Model model){
    log.info("硅寸 老沥包府");
    model.addAttribute("title", "硅寸 老沥包府");
        return "view/clndr/dvdndClndr";
    }

    @GetMapping("/transactionList.do")
    @ResponseBody
    public List<Map> transactionList(Model model){
        return dvdndClndrService.transactionList();
    }
}
