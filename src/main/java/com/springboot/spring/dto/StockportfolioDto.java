package com.springboot.spring.dto;

import lombok.Data;

@Data
public class StockportfolioDto {
    private String no = "";    //순번
    private String stockName = "";    //주식명
    private String stockQuantity = "";    //주식보유수
    private String dividendCycle = "";    //배당 주기
    private String purchasePrice = "";    //매입금
    private String dividendAmount = "";    //배당 금액
    private String clscd = "";    //상태구분코드
    private String rgstdate = "";    //등록일자
}
