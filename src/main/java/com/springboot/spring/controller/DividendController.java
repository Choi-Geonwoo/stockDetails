package com.springboot.spring.controller;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.spring.dto.CombinedDTO;
import com.springboot.spring.dto.TransactionDto;
import com.springboot.spring.service.DividendService;

import lombok.extern.slf4j.Slf4j;


// 배당 관련 화면
@Slf4j
@Controller
public class DividendController {
    
    @Autowired
    public DividendService dividendService;

    @GetMapping("/dividend/dividendList")
    public String detailsView(Model model 
    ,@RequestParam(value = "stockName" ,required=false) String stockName
    ,@RequestParam(value = "trnscdate" ,required=false) String trnscdate){
        TransactionDto tDto = new TransactionDto();
        tDto.setStockName(stockName); // 항목멱
        tDto.setTrnscdate(trnscdate);     //거래내역
        model.addAttribute("trnscdate", trnscdate); 
        model.addAttribute("stockName", stockName); 
        model.addAttribute("selectBox", dividendService.selectBox()); 
        model.addAttribute("title", "배당내역");
        model.addAttribute("dList", dividendService.dividendList(tDto));
        return "view/dividend/dividendList";
        //E:\VisualStudio\workspace3\stockDetails\src\main\resources\templates\view\dividend\dividendList.html
    }


    
    // 배당 등록
    @PostMapping("/dividendInsert.do")
    @ResponseBody
    //public String detailsList(@RequestBody Map<String, Object> map, Model model){
    public String detailsList(@RequestPart(value = "key") HashMap map
    , @RequestPart(value = "files", required = false) String files){
        //log.info("1.결과 ㅣ ㅣ :ㅣ " + map.toString());
        //log.info("2.결과 ㅣ ㅣ :ㅣ " + files);
        return String.valueOf(dividendService.transactionInsert(map, files));
    }

    // 배당 상세 내역 호출
    @GetMapping("/dividendDtlsInqry.do")
    public ResponseEntity<CombinedDTO> yourEndpoint(
        @RequestParam String stockName,
        @RequestParam String trnscdate,
        @RequestParam String className
    ) {
        // 파라미터를 이용한 작업 수행
        // 예: 데이터베이스에서 데이터를 가져오거나 계산 수행

        // 결과를 클라이언트에 반환
        Map<String, Object> map = new HashMap<>();
        CombinedDTO cDto = new CombinedDTO();
        map.put("stockName", stockName);
        map.put("trnscdate", trnscdate);
        map.put("className", className);
        cDto = dividendService.dividendDtlsInqry(map);
        return ResponseEntity.ok(cDto);
    }



    // 이미지 테스트
    @PostMapping("/imgData.do")
    @ResponseBody
    //public String detailsList(@RequestBody Map<String, Object> map, Model model){
    public String imgData(@RequestPart(value = "files", required = false) String files){
        return String.valueOf(dividendService.imgData(files));
    }
}
