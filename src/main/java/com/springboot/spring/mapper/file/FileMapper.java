package com.springboot.spring.mapper.file;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.spring.dto.FileDTO;

@Mapper
public interface FileMapper {
    
    // �̹��� ���� ���
    public int  fileInsert(FileDTO stockDTO);

    // �̹��� ���� ����
    public int  fileUpdate(FileDTO stockDTO);

    // �ֽ� �ŷ����� ���
    public FileDTO  imgFileList(String tNo);
}
