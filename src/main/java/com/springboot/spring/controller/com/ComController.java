package com.springboot.spring.controller.com;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.spring.service.com.ComService;
import com.springboot.spring.service.detail.DetailsService;


// ���� �ڵ� ���
@Slf4j
@Controller
public class ComController {

    
    @Autowired
    private ComService comService;
    
    // ��з� ��ȸ
    @GetMapping("/com/comonCode")
    public String detailsView(Model model){
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
}
