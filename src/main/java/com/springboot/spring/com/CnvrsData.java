package com.springboot.spring.com;

import java.time.LocalDate;



public class CnvrsData {
    
    // 년도 구하기
    public static Object toYear(Object year){
        // 현재 날짜 구하기 (시스템 시계, 시스템 타임존)        
        LocalDate now = LocalDate.now();
        Object dataYear = null;
        System.out.println("결과 년도 : : : " + year + " | "+ IsNullCheck.isNull(year));
        if(IsNullCheck.isNull(year)){
            dataYear = String.valueOf(now.getYear());;
        }
        return dataYear;
    }

    // 월 구하기
    public static Object toMonth(Object month){
        // 현재 날짜 구하기 (시스템 시계, 시스템 타임존)        
        LocalDate now = LocalDate.now();
        Object dataMonth = null;
        System.out.println("결과  월 : : : "+ month+ " | " + IsNullCheck.isNull(month));
        if(IsNullCheck.isNull(month)){
            dataMonth = String.valueOf(now.getMonthValue());;
        }
        return dataMonth;
    }
}
