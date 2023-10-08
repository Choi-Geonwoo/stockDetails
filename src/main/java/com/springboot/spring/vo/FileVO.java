package com.springboot.spring.vo;

import lombok.Data;

@Data
public class FileVO {
    private String fNo;        //순번
    private String fName;     //파일명
    private String tNo;       //배당테이블 순번
    private byte[] contents;  //내용
    
}
