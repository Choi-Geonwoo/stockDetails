package com.springboot.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import lombok.extern.slf4j.Slf4j;

// 공통 코드 등록
@Slf4j
@Controller
public class ComController {
    
    @GetMapping("/com/comonCode")
    public String detailsView(Model model){
        log.info("공통코드");
        model.addAttribute("title", "공통코드 관리");
        return "view/com/comonCode";
    }
}
