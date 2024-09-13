package com.springboot.spring.controller.dvdnd;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.spring.service.dvdnd.DvdndClndrService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DvdndClndrController {
    @Autowired
    public DvdndClndrService dvdndClndrService;
    
    @GetMapping("/clndr/dvdndClndr")
    public String detailsView(Model model){
    model.addAttribute("title", "배당 일정관리");
        return "view/clndr/dvdndClndr";
    }

    @PostMapping("/transactionList.do")
    @ResponseBody
    public List<Map> transactionList(Model model, @RequestBody Map<String, Object> map){
        return dvdndClndrService.transactionList(map);
    }

    
}
