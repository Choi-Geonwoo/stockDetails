package com.springboot.spring.vo;

import lombok.Data;

@Data
public class StockVO {
    private String registration_order = "";    //순번
    private String stock_name = "";    //주식명
    private String shares = "";    //주식수
    private String stock_dividends = "";    //배당 주기
    private String maeipkkeum = "";    //매입금
    private String dividends = "";    //배당금
    private String registration_date = "";    //등록일자
}
