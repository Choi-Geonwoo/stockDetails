package com.springboot.spring.vo;

import lombok.Data;

@Data
public class TransactionVO {
    private String no = "";    //순번
    private String trnscdate = "";    //거래일자
    private String stockName = "";    //주식명
    private String amount = "";    //거래금액
    private String rgstdate = "";    //등록일자
}
