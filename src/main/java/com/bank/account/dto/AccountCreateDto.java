package com.bank.account.dto;

import com.bank.account.enumdata.AccountType;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountCreateDto {
	@NotNull
 private  AccountType type;
}
