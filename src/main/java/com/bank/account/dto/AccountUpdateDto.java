package com.bank.account.dto;

import com.bank.account.enumdata.AccountStatus;
import com.bank.account.enumdata.AccountType;


import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountUpdateDto {

	@NotNull
	private AccountStatus status;

	@NotNull

	private AccountType type;
}
