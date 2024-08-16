package com.springboot.spring.controller.com;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.spring.service.com.ComService;


// 공통 코드 등록
@Slf4j
@Controller
public class ComController {

    @Autowired
    private ComService comService;
    
    // 대분류 조회
    @GetMapping("/com/comonCode")
    public String comonCodeView(Model model){
        log.info("공통코드");
        model.addAttribute("title", "공통코드 관리");
        model.addAttribute("cList", comService.sectionSelect(null));
        return "view/com/comonCode";
    }


    // 대분류 조회
    @GetMapping("/com/detailsSearch.do")
    public String detailsSearch(Model model, @RequestParam Map<String, Object> map){
        log.info("공통코드 검색 : : : " + map.toString());
        model.addAttribute("title", "공통코드 관리");
        model.addAttribute("cList", comService.sectionSelect(map));
        model.addAttribute("SECTION_NM", String.valueOf(map.get("SECTION_NM")));
        model.addAttribute("SECTION_CD", String.valueOf(map.get("SECTION_CD")));
        return "view/com/comonCode";
    }

    // 대분류 등록
    @PostMapping("/com/comonCodeInster.do")
    //@ResponseBody
    public String comonCodeInster(@RequestParam Map<String, Object> formMap, Model model){
        log.info("formMap + " + formMap.toString());
        model.addAttribute("title", "공통코드 관리");
        model.addAttribute("cStr", String.valueOf(comService.sectionInsert(formMap)));
        return "view/com/comonCode";
    }

    // 대분류 수정
    @PostMapping("/com/comonCodeUpdate.do")
    public ResponseEntity<Map> comonCodeUpdate(@RequestBody  Map<String, Object> formMap, Model model){
            
        model.addAttribute("title", "공통코드 관리");
        //model.addAttribute("cStr", String.valueOf(comService.sectionInsert(formMap)));
        Map<String, String> reMap = comService.sectionUpdate(formMap);
        return ResponseEntity.ok(reMap);
    }



    // 대분류 조회
    @GetMapping("/com/comonCodeNew")
    public String comonCodeNewView(Model model){
        log.info("공통코드");
        model.addAttribute("title", "공통코드 관리");
        model.addAttribute("cList", comService.sectionSelect(null));
        return "view/com/comonCodeNew";
    }

    // 대분류 조회
    @GetMapping("/com/comonCodeNewSearch.do")
    public String comonCodeNewSearch(Model model, @RequestParam Map<String, Object> map){
        log.info("공통코드 검색 : : : " + map.toString());
        model.addAttribute("title", "공통코드 관리");
        model.addAttribute("cList", comService.sectionSelect(map));
        model.addAttribute("SECTION_NM", String.valueOf(map.get("SECTION_NM")));
        model.addAttribute("SECTION_CD", String.valueOf(map.get("SECTION_CD")));
        return "view/com/comonCodeNew";
    }


    // 대분류 등록
    @PostMapping("/com/comonCodeNewInster.do")
    //@ResponseBody
    public String comonCodeNewInster(@RequestParam Map<String, Object> formMap, Model model){
        log.info("formMap + " + formMap.toString());
        model.addAttribute("title", "공통코드 관리");
        model.addAttribute("cStr", String.valueOf(comService.sectionInsert(formMap)));
        return "view/com/comonCodeNew";
    }


    // 대분류 수정
    @PostMapping("/com/comonCodeNewUpdate.do")
    public ResponseEntity<Map> comonCodeNewUpdate(@RequestBody  Map<String, Object> formMap, Model model){
            
        model.addAttribute("title", "공통코드 관리");
        //model.addAttribute("cStr", String.valueOf(comService.sectionInsert(formMap)));
        Map<String, String> reMap = comService.sectionUpdate(formMap);
        return ResponseEntity.ok(reMap);
    }


    // 중분류 등록
    @PostMapping("/com/comCodeClsfcInster.do")
    //@ResponseBody
    public ResponseEntity<Map> comCodeClsfcInster(@RequestBody List<Map<String, Object>> formMap, Model model){
        log.info("formMap + " + formMap.toString());
        //model.addAttribute("title", "공통코드 관리");
        //model.addAttribute("cStr", String.valueOf(comService.comonCodeClsfcInster(formMap)));
        Map<String, String> reMap = comService.comCodeClsfcInster(formMap);
        return ResponseEntity.ok(reMap);
    }

    
    // 중분류 조회
    @GetMapping("/com/comCodeClsfcSelect.do")
    //@ResponseBody
    //public ResponseEntity<List<Map>> comCodeClsfcSelect(@RequestBody Map<String, Object> formMap, Model model){
    public ResponseEntity<List<Map>> comCodeClsfcSelect(Model model, @RequestParam("SECTION_CD") String sectionCd){
        Map<String, Object> formMap = new HashMap<>();
        formMap.put("SECTION_CD", sectionCd);
        log.info("formMap + " + formMap.toString());
        //model.addAttribute("title", "공통코드 관리");
        //model.addAttribute("cStr", String.valueOf(comService.comonCodeClsfcInster(formMap)));
        List<Map> reMap = comService.comCodeClsfcSelect(formMap);
        return ResponseEntity.ok(reMap);
    }

    
    // 중분류 조회
    @GetMapping("/com/comCodeClsfcSelect01.do")
    //@ResponseBody
    //public ResponseEntity<List<Map>> comCodeClsfcSelect(@RequestBody Map<String, Object> formMap, Model model){
    public String comCodeClsfcSelect01(Model model,  @RequestParam(value = "SECTION_CD", required = false) String sectionCd){
        Map<String, Object> formMap = new HashMap<>();
        formMap.put("SECTION_CD", sectionCd);
        log.info("formMap + " + formMap.toString());
        model.addAttribute("title", "공통코드 관리");
        //model.addAttribute("cStr", String.valueOf(comService.comonCodeClsfcInster(formMap)));
        //model.addAttribute("cList", comService.comCodeClsfcSelect(formMap));
        model.addAttribute("cList", comService.sectionSelect(null));
        model.addAttribute("cList01", comService.comCodeClsfcSelect(formMap));
        return  "view/com/comonCodeNew";
    }

}
