package com.springboot.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.spring.dto.TransactionDto;

@Mapper
public interface DvdndClndrMapper {
    
    // ��� ���ں� �ŷ�����
    public List<TransactionDto> transactionList();
}
