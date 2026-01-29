package com.bank.transactions.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class TransferRequestDto {


	@NotNull
	@Positive
	private BigDecimal amount;
}
