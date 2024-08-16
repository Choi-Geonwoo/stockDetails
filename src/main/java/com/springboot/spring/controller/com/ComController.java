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


// ���� �ڵ� ���
@Slf4j
@Controller
public class ComController {

    @Autowired
    private ComService comService;
    
    // ��з� ��ȸ
    @GetMapping("/com/comonCode")
    public String comonCodeView(Model model){
        log.info("�����ڵ�");
        model.addAttribute("title", "�����ڵ� ����");
        model.addAttribute("cList", comService.sectionSelect(null));
        return "view/com/comonCode";
    }


    // ��з� ��ȸ
    @GetMapping("/com/detailsSearch.do")
    public String detailsSearch(Model model, @RequestParam Map<String, Object> map){
        log.info("�����ڵ� �˻� : : : " + map.toString());
        model.addAttribute("title", "�����ڵ� ����");
        model.addAttribute("cList", comService.sectionSelect(map));
        model.addAttribute("SECTION_NM", String.valueOf(map.get("SECTION_NM")));
        model.addAttribute("SECTION_CD", String.valueOf(map.get("SECTION_CD")));
        return "view/com/comonCode";
    }

    // ��з� ���
    @PostMapping("/com/comonCodeInster.do")
    //@ResponseBody
    public String comonCodeInster(@RequestParam Map<String, Object> formMap, Model model){
        log.info("formMap + " + formMap.toString());
        model.addAttribute("title", "�����ڵ� ����");
        model.addAttribute("cStr", String.valueOf(comService.sectionInsert(formMap)));
        return "view/com/comonCode";
    }

    // ��з� ����
    @PostMapping("/com/comonCodeUpdate.do")
    public ResponseEntity<Map> comonCodeUpdate(@RequestBody  Map<String, Object> formMap, Model model){
            
        model.addAttribute("title", "�����ڵ� ����");
        //model.addAttribute("cStr", String.valueOf(comService.sectionInsert(formMap)));
        Map<String, String> reMap = comService.sectionUpdate(formMap);
        return ResponseEntity.ok(reMap);
    }



    // ��з� ��ȸ
    @GetMapping("/com/comonCodeNew")
    public String comonCodeNewView(Model model){
        log.info("�����ڵ�");
        model.addAttribute("title", "�����ڵ� ����");
        model.addAttribute("cList", comService.sectionSelect(null));
        return "view/com/comonCodeNew";
    }

    // ��з� ��ȸ
    @GetMapping("/com/comonCodeNewSearch.do")
    public String comonCodeNewSearch(Model model, @RequestParam Map<String, Object> map){
        log.info("�����ڵ� �˻� : : : " + map.toString());
        model.addAttribute("title", "�����ڵ� ����");
        model.addAttribute("cList", comService.sectionSelect(map));
        model.addAttribute("SECTION_NM", String.valueOf(map.get("SECTION_NM")));
        model.addAttribute("SECTION_CD", String.valueOf(map.get("SECTION_CD")));
        return "view/com/comonCodeNew";
    }


    // ��з� ���
    @PostMapping("/com/comonCodeNewInster.do")
    //@ResponseBody
    public String comonCodeNewInster(@RequestParam Map<String, Object> formMap, Model model){
        log.info("formMap + " + formMap.toString());
        model.addAttribute("title", "�����ڵ� ����");
        model.addAttribute("cStr", String.valueOf(comService.sectionInsert(formMap)));
        return "view/com/comonCodeNew";
    }


    // ��з� ����
    @PostMapping("/com/comonCodeNewUpdate.do")
    public ResponseEntity<Map> comonCodeNewUpdate(@RequestBody  Map<String, Object> formMap, Model model){
            
        model.addAttribute("title", "�����ڵ� ����");
        //model.addAttribute("cStr", String.valueOf(comService.sectionInsert(formMap)));
        Map<String, String> reMap = comService.sectionUpdate(formMap);
        return ResponseEntity.ok(reMap);
    }


    // �ߺз� ���
    @PostMapping("/com/comCodeClsfcInster.do")
    //@ResponseBody
    public ResponseEntity<Map> comCodeClsfcInster(@RequestBody List<Map<String, Object>> formMap, Model model){
        log.info("formMap + " + formMap.toString());
        //model.addAttribute("title", "�����ڵ� ����");
        //model.addAttribute("cStr", String.valueOf(comService.comonCodeClsfcInster(formMap)));
        Map<String, String> reMap = comService.comCodeClsfcInster(formMap);
        return ResponseEntity.ok(reMap);
    }

    
    // �ߺз� ��ȸ
    @GetMapping("/com/comCodeClsfcSelect.do")
    //@ResponseBody
    //public ResponseEntity<List<Map>> comCodeClsfcSelect(@RequestBody Map<String, Object> formMap, Model model){
    public ResponseEntity<List<Map>> comCodeClsfcSelect(Model model, @RequestParam("SECTION_CD") String sectionCd){
        Map<String, Object> formMap = new HashMap<>();
        formMap.put("SECTION_CD", sectionCd);
        log.info("formMap + " + formMap.toString());
        //model.addAttribute("title", "�����ڵ� ����");
        //model.addAttribute("cStr", String.valueOf(comService.comonCodeClsfcInster(formMap)));
        List<Map> reMap = comService.comCodeClsfcSelect(formMap);
        return ResponseEntity.ok(reMap);
    }

    
    // �ߺз� ��ȸ
    @GetMapping("/com/comCodeClsfcSelect01.do")
    //@ResponseBody
    //public ResponseEntity<List<Map>> comCodeClsfcSelect(@RequestBody Map<String, Object> formMap, Model model){
    public String comCodeClsfcSelect01(Model model,  @RequestParam(value = "SECTION_CD", required = false) String sectionCd){
        Map<String, Object> formMap = new HashMap<>();
        formMap.put("SECTION_CD", sectionCd);
        log.info("formMap + " + formMap.toString());
        model.addAttribute("title", "�����ڵ� ����");
        //model.addAttribute("cStr", String.valueOf(comService.comonCodeClsfcInster(formMap)));
        //model.addAttribute("cList", comService.comCodeClsfcSelect(formMap));
        model.addAttribute("cList", comService.sectionSelect(null));
        model.addAttribute("cList01", comService.comCodeClsfcSelect(formMap));
        return  "view/com/comonCodeNew";
    }

}
