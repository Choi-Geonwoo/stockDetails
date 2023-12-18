package com.springboot.spring.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.spring.dto.FileDTO;

@Mapper
public interface FileMapper {
    
    // 이미지 파일 등록
    public int  fileInsert(FileDTO stockDTO);

    // 이미지 파일 수정
    public int  fileUpdate(FileDTO stockDTO);

    // 주식 거래내역 등록
    public FileDTO  imgFileList(String tNo);
}
