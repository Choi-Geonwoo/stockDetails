package com.springboot.spring.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.spring.dto.TransactionDto;

@Mapper
public interface DvdndClndrMapper {
    
    // ��� ���ں� �ŷ�����
    public List<TransactionDto> transactionList(Map<String, Object> map);
}
