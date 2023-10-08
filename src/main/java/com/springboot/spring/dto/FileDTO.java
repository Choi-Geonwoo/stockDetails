package com.springboot.spring.dto;

import java.sql.Blob;

import lombok.Data;

@Data
public class FileDTO {
    private String fNo;        //순번
    private String fName;     //파일명
    private String tNo;       //배당테이블 순번
    private byte[] contents;  //내용
    private String reContents;  //내용
    
}
