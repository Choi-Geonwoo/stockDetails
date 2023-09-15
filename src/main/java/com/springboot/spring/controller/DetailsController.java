package com.springboot.spring.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.spring.dto.StockportfolioDto;
import com.springboot.spring.service.DetailsService;
import com.springboot.spring.vo.StockportfolioVO;

import lombok.extern.slf4j.Slf4j;

// 주식 거래내역
@Slf4j
@Controller
public class DetailsController {

    @Autowired
    private DetailsService detailsService;

    @GetMapping("/")
    public String detailsView(Model model){
        StockportfolioDto sDto = new StockportfolioDto();
        model.addAttribute("title", "주식내역");
        model.addAttribute("selectBox", detailsService.selectBox()); 
        model.addAttribute("sList", detailsService.stockDetailsList(sDto));
        return "view/details/details";
    }

 @GetMapping("/details/details")
    public String detailsSearchView(Model model
    ,@RequestParam(value = "stockName" ,required=false) String stockName){
        StockportfolioDto sDto = new StockportfolioDto();
        sDto.setStockName(stockName);
        model.addAttribute("title", "주식내역");
        model.addAttribute("reStockName", stockName);
        model.addAttribute("selectBox", detailsService.selectBox()); 
        model.addAttribute("sList", detailsService.stockDetailsList(sDto));
        return "view/details/details";
    }
    

    // 주식 내역 등록
    @PostMapping("/detailsInsert.do")
    @ResponseBody
    public String detailsInsert(@RequestBody Map<String, Object> map){
        //log.info("어떤값이 나오나 "+map.toString());
        return String.valueOf(detailsService.detailsInsert(map));
        
    }

    // 주식 내역 수정
    @PostMapping("/detailsUpdate.do")
    @ResponseBody
    public String detailsUpdate(@RequestBody Map<String, Object> map){
        return String.valueOf(detailsService.detailsUpdate(map));
        //return "200";
    }

     // 주식 삭제
    @PostMapping("/detailsDelete.do")
    @ResponseBody
    public String detailsDelete(@RequestBody int registration_order){
        //log.info("registration_order : : : : : : " + registration_order);
        return String.valueOf(detailsService.detailsDelete(registration_order));
    }   

}
