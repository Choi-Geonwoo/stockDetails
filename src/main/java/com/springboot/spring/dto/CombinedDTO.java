package com.springboot.spring.dto;

import lombok.Data;

@Data
public class CombinedDTO  {
    private FileDTO fileDTO;
    private TransactionDto transactionDto;

    public CombinedDTO() {
        // 기본 생성자
    }

    public CombinedDTO(FileDTO fileDTO, TransactionDto transactionDto) {
        this.fileDTO = fileDTO;
        this.transactionDto = transactionDto;
    }

    public FileDTO getFileDTO() {
        return fileDTO;
    }

    public void setFileDTO(FileDTO fileDTO) {
        this.fileDTO = fileDTO;
    }

    public TransactionDto getTransactionDto() {
        return transactionDto;
    }

    public void setTransactionDto(TransactionDto transactionDto) {
        this.transactionDto = transactionDto;
    }
}
