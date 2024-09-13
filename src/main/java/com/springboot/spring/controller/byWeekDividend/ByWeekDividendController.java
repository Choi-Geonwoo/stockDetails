package com.springboot.spring.controller.byWeekDividend;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.spring.com.PaginationService;
import com.springboot.spring.service.byWeekDividend.ByWeekDividendService;
import com.springboot.spring.service.com.ComService;

import lombok.extern.slf4j.Slf4j;

// 주별 배당 내역
@Slf4j
@Controller
public class ByWeekDividendController {

    
    @Autowired
    private ByWeekDividendService byWeekDividendService; // 주별 배당 내역

    @Autowired
    private ComService comService;
    
    // 페이징 처리
    PaginationService paginationService = new PaginationService();
    
    // 주별 베당 거래 내역
    @GetMapping("/byWeekDividend/byWeekDividendListNew")
    public String byWeekDividendNewView(Model model
     ,@RequestParam(value = "startYmd" ,required=false) String startYmd 
     ,@RequestParam(value = "endYmd" ,required=false) String endYmd 
     ,@RequestParam(value = "stockName" ,required=false) String stockName 
     ,@RequestParam(value = "page", defaultValue = "1") final int page
     )
    {

        Map<String, Object> map = new HashMap<>();
        map.put("startYmd", startYmd);
        map.put("endYmd", endYmd);
        map.put("stockName", stockName);
        map.put("page", page);
        map.put("rowCount", 10);
        List<Map> list = byWeekDividendService.byWeekDividendList(map);
        
        /* ### 페이징 처리 ### */
        int currentPage = page; // 현재 페이지
        int totalCount = 0; // 총 게시물 개수
        if(!list.isEmpty()){
            totalCount = Integer.parseInt(String.valueOf(list.get(0).get("TOTALPAGES"))); // 총 게시물 개수
        }
        // Pagination 정보를 계산합니다.
        // PaginationService 객체를 생성합니다.
        Map<String, Object> paginationMap = paginationService.calculatePagination(totalCount, currentPage);

        /* ### 페이징 처리 ### */
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("SECTION_CD", "BANK_001");
        model.addAttribute("selectBox", comService.selectBox(mapData)); 
        model.addAttribute("startYmd", startYmd);
        model.addAttribute("endYmd", endYmd);
        model.addAttribute("title", "주별 배당내역");
        model.addAttribute("byWeekList", list);
        model.addAttribute("page", page);
        model.addAttribute("reStockName", stockName);
        model.addAttribute("pageVo", paginationMap);
        
        return "view/byWeekDividend/byWeekDividendListNew";
    }
}
