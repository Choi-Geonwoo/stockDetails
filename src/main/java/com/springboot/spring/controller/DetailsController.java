package com.springboot.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.spring.service.DetailsService;

// 주식 거래내역
@Controller
public class DetailsController {

    @Autowired
    private DetailsService detailsService;

    @GetMapping("/")
    public String detailsView(Model model){
        model.addAttribute("title", "주식내역");
        model.addAttribute("sList", detailsService.stockDetailsList());
        return "view/details/details";
    }

}
