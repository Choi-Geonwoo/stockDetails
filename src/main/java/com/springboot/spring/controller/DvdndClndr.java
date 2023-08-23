package com.springboot.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DvdndClndr {
    
    @GetMapping("/clndr/dvdndClndr")
    public String detailsView(Model model){
        model.addAttribute("title", "배당 일정관리");
        return "view/clndr/dvdndClndr";
    }
}
