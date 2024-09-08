package com.springboot.spring.controller.details;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.spring.dto.StockportfolioDto;
import com.springboot.spring.service.com.ComService;
import com.springboot.spring.service.detail.DetailsService;

import lombok.extern.slf4j.Slf4j;

// �ֽ�  �ŷ�����
/* �ڹ� ������ ���� �߻��� : java:Clean Java Language Server Workspace */
@Slf4j
@Controller
public class DetailsController {

    @Autowired
    private DetailsService detailsService;

    @Autowired
    private ComService comService;

@GetMapping("/")
    public String detailsView(Model model){
        StockportfolioDto sDto = new StockportfolioDto();
        log.info("�ֽĳ���");
        model.addAttribute("title", "�ֽĳ���");
        model.addAttribute("selectBox", detailsService.selectBox()); 
        model.addAttribute("sList", detailsService.stockDetailsList(sDto));
        return "view/details/details";
    }
    
// �ֽ� �ŷ� ���� 
 @GetMapping("/details/details")
    public String detailsSearchView(Model model
    ,@RequestParam(value = "stockName" ,required=false) String stockName
    ,@RequestParam(value = "dividendCycle" ,required=false) String dividendCycle
    ){
        StockportfolioDto sDto = new StockportfolioDto();
        sDto.setStockName(stockName);
        sDto.setDividendCycle(dividendCycle);
        model.addAttribute("title", "�ֽĳ���");
        model.addAttribute("reStockName", stockName);
        model.addAttribute("reDividendCycle", dividendCycle);
        model.addAttribute("selectBox", detailsService.selectBox()); 
        model.addAttribute("sList", detailsService.stockDetailsList(sDto));
        return "view/details/details";
    }

    
// �ֽ� �ŷ� ���� 
 @GetMapping("/details/detailsNew")
 public String detailsSearchNewView(Model model
    ,@RequestParam(value = "stockName" ,required=false) String stockName
    ,@RequestParam(value = "dividendCycle" ,required=false) String dividendCycle
 ){
     StockportfolioDto sDto = new StockportfolioDto();
     sDto.setStockName(stockName);
     sDto.setDividendCycle(dividendCycle);
     model.addAttribute("title", "�ֽĳ���");
     model.addAttribute("reStockName", stockName);
     model.addAttribute("reDividendCycle", dividendCycle);
     model.addAttribute("selectBox", detailsService.selectBox01("BANK_001")); 
     model.addAttribute("sList", detailsService.stockDetailsList(sDto));
     model.addAttribute("sumList", detailsService.selectSum01(sDto));
     return "view/details/detailsNew";
 }


    // �ֽ� ���� ���
    @PostMapping("/detailsInsert.do")
    @ResponseBody
    public String detailsInsert(@RequestBody Map<String, Object> map){
        //log.info("����� ������ "+map.toString());
        return String.valueOf(detailsService.detailsInsert(map));
        
    }

    // �ֽ� ���� ����
    @PostMapping("/detailsUpdate.do")
    @ResponseBody
    public String detailsUpdate(@RequestBody Map<String, Object> map){
        return String.valueOf(detailsService.detailsUpdate(map));
        //return "200";
    }

     // �ֽ� ����
    @PostMapping("/detailsDelete.do")
    @ResponseBody
    public String detailsDelete(@RequestBody int registration_order){
        //log.info("registration_order : : : : : : " + registration_order);
        return String.valueOf(detailsService.detailsDelete(registration_order));
    }   
     // �ֽ� �� ��ȸ
     @GetMapping("/stockDetailList.do")
     public ResponseEntity<Map<String, Object>> stockDetailList(@RequestParam String stockName, Model model){
         Map<String, Object> map = new HashMap<>();
         Map<String, Object> mapList = new HashMap();
         map.put("STOCK_NAME", stockName);
         log.info("stockDetailList : :  "+stockName+" : : : : " + map.toString());
         mapList = detailsService.stockDetailList(map);
         return ResponseEntity.ok(mapList);
     }   
    

}
