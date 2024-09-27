package com.springboot.spring.controller.dividend;

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

import com.springboot.spring.com.PaginationService;
import com.springboot.spring.dto.CombinedDTO;
import com.springboot.spring.service.com.ComService;
import com.springboot.spring.service.dividend.DividendService;

import lombok.extern.slf4j.Slf4j;


// ��� ���� ȭ��
@Slf4j
@Controller
public class DividendController {
   
    @Autowired
    public DividendService dividendService;
    
    @Autowired
    public ComService comService;
    
    PaginationService paginationService = new PaginationService();

    // ���� �ŷ� ����
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
        log.info("��系��");

        //tDto.setStockName(stockName); // �׸��
        //tDto.setTrnscdate(trnscdate);     //�ŷ�����
        model.addAttribute("trnscdate", trnscdate); 
        model.addAttribute("monthSelect", monthSelect); 
        model.addAttribute("stockName", stockName); 
        model.addAttribute("selectBox", dividendService.selectBox()); 
        model.addAttribute("title", "��系��");
        model.addAttribute("dList", dividendService.dividendList(map));
        return "view/dividend/dividendList";
        //E:\VisualStudio\workspace3\stockDetails\src\main\resources\templates\view\dividend\dividendList.html
    }


    // ���� �ŷ� ����
    @GetMapping("/dividend/dividendListNew")
    public String detailsNewView(Model model 
    ,@RequestParam(value = "stockName" ,required=false) String stockName
    ,@RequestParam(value = "trnscdate" ,required=false) String trnscdate
    ,@RequestParam(value = "monthSelect" ,required=false) String monthSelect)
    {
        
        Map<String, Object> map = new HashMap<>();
        map.put("stockName", stockName);
        map.put("trnscdate", trnscdate);
        map.put("monthSelect", monthSelect);
        log.info("��系��");

        //tDto.setStockName(stockName); // �׸��
        //tDto.setTrnscdate(trnscdate);     //�ŷ�����
        model.addAttribute("trnscdate", trnscdate); 
        model.addAttribute("monthSelect", monthSelect); 
        model.addAttribute("stockName", stockName); 
        model.addAttribute("selectBox", comService.selectBox(map)); 
        model.addAttribute("title", "��系��");
        model.addAttribute("dList", dividendService.dividendList(map));
        return "view/dividend/dividendListNew";
        //E:\VisualStudio\workspace3\stockDetails\src\main\resources\templates\view\dividend\dividendList.html
    }

    
    // ��� ���
    @PostMapping("/dividendInsert.do")
    @ResponseBody
    //public String detailsList(@RequestBody Map<String, Object> map, Model model){
    public ResponseEntity<Map> detailsList(@RequestPart(value = "key") HashMap map
    , @RequestPart(value = "files", required = false) String files){
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("retNo", dividendService.transactionInsert(map, files));
        reMap.put("msg", "���");
        return ResponseEntity.ok(reMap);
    }

    // ��� �� ���� ȣ��
    @GetMapping("/dividendDtlsInqry.do")
    public ResponseEntity<CombinedDTO> yourEndpoint(
        @RequestParam String stockName,
        @RequestParam String trnscdate,
        @RequestParam String className
    ) {
        // �Ķ���͸� �̿��� �۾� ����
        // ��: �����ͺ��̽����� �����͸� �������ų� ��� ����

        // ����� Ŭ���̾�Ʈ�� ��ȯ
        Map<String, Object> map = new HashMap<>();
        CombinedDTO cDto = new CombinedDTO();
        map.put("stockName", stockName);
        map.put("trnscdate", trnscdate);
        map.put("className", className);
        cDto = dividendService.dividendDtlsInqry(map);
        return ResponseEntity.ok(cDto);
    }

    // ��� �ŷ����� ����
    @PostMapping("/dividendUpdate.do")
    @ResponseBody
    public ResponseEntity<Map> dividendUpdate(@RequestPart(value = "key") HashMap map
                                , @RequestPart(value = "files", required = false) String files
    ){
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("retNo", dividendService.transactionUpdate(map,files));
        reMap.put("msg", "����");
        //return String.valueOf();
        return ResponseEntity.ok(reMap);
    }

    // ��� �ŷ����� ����
    @PostMapping("/dividendDelete.do")
    @ResponseBody
    public ResponseEntity<Map> dividendDelete(@RequestPart(value = "key") HashMap map ) throws Exception
    {
        Map<String, Object> reMap = new HashMap<>();
        reMap.put("msg", "����");
        reMap.put("retNo", dividendService.transactionDelete(String.valueOf(map.get("no"))));
        //return String.valueOf();
        return ResponseEntity.ok(reMap);
    }


    
    // �ֺ� ���� �ŷ� ����
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

        model.addAttribute("selectBox", dividendService.selectBox()); 
        model.addAttribute("startYmd", startYmd);
        model.addAttribute("endYmd", endYmd);
        model.addAttribute("title", "�ֺ� ��系��");
        model.addAttribute("byWeekList", list);
        model.addAttribute("page", page);
        model.addAttribute("pageVo", paginationMap);
        
        return "view/dividend/byWeekDividendList";
    }

    //yearComparison

    

}
