package com.springboot.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.spring.com.CnvrsData;
import com.springboot.spring.com.PaginationService;
import com.springboot.spring.dto.CombinedDTO;
import com.springboot.spring.dto.PaginationVo;
import com.springboot.spring.service.DividendService;

import lombok.extern.slf4j.Slf4j;


// 배당 관련 화면
@Slf4j
@Controller
public class DividendController {
   
    @Autowired
    public DividendService dividendService;
    
    PaginationService paginationService = new PaginationService();

    // 베당 거래 내역
    @GetMapping("/dividend/dividendList")
    public String detailsView(Model model 
    ,@RequestParam(value = "stockName" ,required=false) String stockName
    ,@RequestParam(value = "trnscdate" ,required=false) String trnscdate
    ,@RequestParam(value = "monthSelect" ,required=false) String monthSelect)
    {
        
        Map<String, Object> map = new HashMap<>();
        map.put("stockName", stockName);
        map.put("trnscdate", trnscdate);
        map.put("monthSelect", monthSelect);
        log.info("배당내역");

        //tDto.setStockName(stockName); // 항목멱
        //tDto.setTrnscdate(trnscdate);     //거래내역
        model.addAttribute("trnscdate", trnscdate); 
        model.addAttribute("monthSelect", monthSelect); 
        model.addAttribute("stockName", stockName); 
        model.addAttribute("selectBox", dividendService.selectBox()); 
        model.addAttribute("title", "배당내역");
        model.addAttribute("dList", dividendService.dividendList(map));
        return "view/dividend/dividendList";
        //E:\VisualStudio\workspace3\stockDetails\src\main\resources\templates\view\dividend\dividendList.html
    }


    
    // 배당 등록
    @PostMapping("/dividendInsert.do")
    @ResponseBody
    //public String detailsList(@RequestBody Map<String, Object> map, Model model){
    public ResponseEntity<Map> detailsList(@RequestPart(value = "key") HashMap map
    , @RequestPart(value = "files", required = false) String files){
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("retNo", dividendService.transactionInsert(map, files));
        reMap.put("msg", "등록");
        return ResponseEntity.ok(reMap);
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

    // 배당 거래내역 수정
    @PostMapping("/dividendUpdate.do")
    @ResponseBody
    public ResponseEntity<Map> dividendUpdate(@RequestPart(value = "key") HashMap map
                                , @RequestPart(value = "files", required = false) String files
    ){
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("retNo", dividendService.transactionUpdate(map,files));
        reMap.put("msg", "수정");
        //return String.valueOf();
        return ResponseEntity.ok(reMap);
    }

    // 배당 거래내역 수정
    @PostMapping("/dividendDelete.do")
    @ResponseBody
    public ResponseEntity<Map> dividendDelete(@RequestPart(value = "key") HashMap map ) throws Exception
    {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("msg", "삭제");
        reMap.put("retNo", dividendService.transactionDelete(String.valueOf(map.get("no"))));
        //return String.valueOf();
        return ResponseEntity.ok(reMap);
    }


    
    // 주별 베당 거래 내역
    @GetMapping("/dividend/byWeekDividendList")
    public String byWeekDividendView(Model model
     ,@RequestParam(value = "startYmd" ,required=false) String startYmd 
     ,@RequestParam(value = "endYmd" ,required=false) String endYmd 
     ,@RequestParam(value = "page", defaultValue = "1") final int page
     )
    {


        Map<String, Object> map = new HashMap<>();
        map.put("startYmd", startYmd);
        map.put("endYmd", endYmd);
        map.put("page", page);
        map.put("rowCount", 10);
        List<Map> list = dividendService.byWeekDividendList(map);
        
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

        model.addAttribute("selectBox", dividendService.selectBox()); 
        model.addAttribute("startYmd", startYmd);
        model.addAttribute("endYmd", endYmd);
        model.addAttribute("title", "주별 배당내역");
        model.addAttribute("byWeekList", list);
        model.addAttribute("page", page);
        model.addAttribute("pageVo", paginationMap);
        
        return "view/dividend/byWeekDividendList";
    }
}
