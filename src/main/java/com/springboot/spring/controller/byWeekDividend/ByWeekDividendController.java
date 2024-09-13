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

// �ֺ� ��� ����
@Slf4j
@Controller
public class ByWeekDividendController {

    
    @Autowired
    private ByWeekDividendService byWeekDividendService; // �ֺ� ��� ����

    @Autowired
    private ComService comService;
    
    // ����¡ ó��
    PaginationService paginationService = new PaginationService();
    
    // �ֺ� ���� �ŷ� ����
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
        
        /* ### ����¡ ó�� ### */
        int currentPage = page; // ���� ������
        int totalCount = 0; // �� �Խù� ����
        if(!list.isEmpty()){
            totalCount = Integer.parseInt(String.valueOf(list.get(0).get("TOTALPAGES"))); // �� �Խù� ����
        }
        // Pagination ������ ����մϴ�.
        // PaginationService ��ü�� �����մϴ�.
        Map<String, Object> paginationMap = paginationService.calculatePagination(totalCount, currentPage);

        /* ### ����¡ ó�� ### */
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("SECTION_CD", "BANK_001");
        model.addAttribute("selectBox", comService.selectBox(mapData)); 
        model.addAttribute("startYmd", startYmd);
        model.addAttribute("endYmd", endYmd);
        model.addAttribute("title", "�ֺ� ��系��");
        model.addAttribute("byWeekList", list);
        model.addAttribute("page", page);
        model.addAttribute("reStockName", stockName);
        model.addAttribute("pageVo", paginationMap);
        
        return "view/byWeekDividend/byWeekDividendListNew";
    }
}
