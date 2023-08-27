package com.springboot.spring.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private String no = "";    //순번
    private String trnscdate = "";    //거래일자
    private String stockName = "";    //주식명
    private String amount = "";    //거래금액
    private String rgstdate = "";    //등록일자
}
