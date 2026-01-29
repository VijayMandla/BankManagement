package com.bank.transactions.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DepositRequestDto {
	
	
	@NotNull(message = "Deposit amount  ot null ")
	@Positive(message="Deposit amount must be greater than zero")
	private BigDecimal amount;
}
