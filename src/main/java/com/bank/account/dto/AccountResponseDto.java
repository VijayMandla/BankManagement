package com.bank.account.dto;

import com.bank.account.enumdata.AccountStatus;
import com.bank.account.enumdata.AccountType;

import lombok.Data;

@Data
public class AccountResponseDto {

	private Long id;
	private AccountStatus status;
	private AccountType type;
	private String accountNumber;

}
