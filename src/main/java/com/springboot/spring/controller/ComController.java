package com.springboot.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import lombok.extern.slf4j.Slf4j;

// ���� �ڵ� ���
@Slf4j
@Controller
public class ComController {
    
    @GetMapping("/com/comonCode")
    public String detailsView(Model model){
        log.info("�����ڵ�");
        model.addAttribute("title", "�����ڵ� ����");
        return "view/com/comonCode";
    }
}
