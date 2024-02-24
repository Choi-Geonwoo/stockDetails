package com.springboot.spring.com;

import java.util.HashMap;
import java.util.Map;

public class PaginationService {
    
    private int rowCount = 10; // 한 페이지 당 보여줄 게시물 개수
    private int pageCount = 5; // 한 블럭에 몇 개의 페이지 개수

    public Map<String, Object> calculatePagination(int totalCount, int currentPage) {
        
                System.out.println("################# 1.  totalCount :  " + totalCount + " currentPage : " + currentPage);
                Map<String, Object> paginationMap = new HashMap<>();
                paginationMap.put("totalCount", totalCount);
                paginationMap.put("page", currentPage);

                // 총 페이지 개수 계산
                int totalPageCount = (int) Math.ceil((double) totalCount / rowCount);
                paginationMap.put("totalPageCount", totalPageCount);

                // 한 블럭의 시작 페이지 계산
                int startPage = ((currentPage - 1) / pageCount) * pageCount + 1;
                paginationMap.put("startPage", startPage);

                // 한 블럭의 끝 페이지 계산
                int endPage = Math.min(startPage + pageCount - 1, totalPageCount);
                paginationMap.put("endPage", endPage);

                // 이전 블록 버튼 생성 유무 판별
                boolean isPrev = startPage > 1;
                paginationMap.put("isPrev", isPrev);

                // 다음 블록 버튼 생성 유무 판별
                boolean isNext = endPage < totalPageCount;
                paginationMap.put("isNext", isNext);

                // offset 계산
                int offset = (currentPage - 1) * rowCount;
                paginationMap.put("offset", offset);

                return paginationMap;
    }
}
