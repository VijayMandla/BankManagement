package com.bank.transactions.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class WithdrawRequestDto {

	@NotNull(message="withDraw amount is not null")
	@Positive(message="withdrawamount can not be less than zero ")
	private BigDecimal withDrawAmount;
	
	
}
