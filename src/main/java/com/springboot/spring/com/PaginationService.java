package com.springboot.spring.com;

import java.util.HashMap;
import java.util.Map;

public class PaginationService {
    
    private int rowCount = 10; // �� ������ �� ������ �Խù� ����
    private int pageCount = 5; // �� ���� �� ���� ������ ����

    public Map<String, Object> calculatePagination(int totalCount, int currentPage) {
        
                System.out.println("################# 1.  totalCount :  " + totalCount + " currentPage : " + currentPage);
                Map<String, Object> paginationMap = new HashMap<>();
                paginationMap.put("totalCount", totalCount);
                paginationMap.put("page", currentPage);

                // �� ������ ���� ���
                int totalPageCount = (int) Math.ceil((double) totalCount / rowCount);
                paginationMap.put("totalPageCount", totalPageCount);

                // �� ���� ���� ������ ���
                int startPage = ((currentPage - 1) / pageCount) * pageCount + 1;
                paginationMap.put("startPage", startPage);

                // �� ���� �� ������ ���
                int endPage = Math.min(startPage + pageCount - 1, totalPageCount);
                paginationMap.put("endPage", endPage);

                // ���� ��� ��ư ���� ���� �Ǻ�
                boolean isPrev = startPage > 1;
                paginationMap.put("isPrev", isPrev);

                // ���� ��� ��ư ���� ���� �Ǻ�
                boolean isNext = endPage < totalPageCount;
                paginationMap.put("isNext", isNext);

                // offset ���
                int offset = (currentPage - 1) * rowCount;
                paginationMap.put("offset", offset);

                return paginationMap;
    }
}
