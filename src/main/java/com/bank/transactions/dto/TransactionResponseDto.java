package com.bank.transactions.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.bank.transactions.enumdata.TransferType;

import lombok.Data;

@Data
public class TransactionResponseDto {
	
	private BigDecimal balanceAfter;

	private LocalDateTime transactionTime;

	private TransferType type;
	
	private Long id;

}
