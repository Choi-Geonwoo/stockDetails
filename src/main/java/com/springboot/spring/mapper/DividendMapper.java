package com.springboot.spring.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.spring.dto.DividendDto;
import com.springboot.spring.dto.TransactionDto;

// ��� �ŷ����� ���� �Լ�
@Mapper
public interface DividendMapper {

    // ��� �ŷ�����
    public List<Map> dividendList(TransactionDto tDto);

    // ��� �ŷ� �� ����
    public TransactionDto dividendDtlsInqry(TransactionDto tDto); 

    // �ֽ� ��� �ŷ����� ���
    public int  transactionInsert(TransactionDto tDTO);
    
    // �ֽ� ��� �ŷ����� ����
    public int  transactionUpdate(TransactionDto tDTO);
    
    // �ֽ� ��� �ŷ����� ����
    public int  transactionDelete(String no);
    
    // �ֽ� �ŷ����� ���
    public int  dividendInsert(DividendDto tDTO);

    // �ֽ� ��� �ŷ����� ���� ��ȸ
    public String tNoString(TransactionDto tDTO);

}
