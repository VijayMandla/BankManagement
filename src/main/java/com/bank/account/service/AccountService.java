package com.bank.account.service;

import com.bank.account.dto.AccountCreateDto;
import com.bank.account.dto.AccountResponseDto;
import com.bank.account.dto.AccountUpdateDto;
import com.bank.customer.dto.CustomerCreateDto;

public interface AccountService {

	

	    AccountResponseDto saveAccount(
	            Long customerId,
	            AccountCreateDto accountDto
	    );

	    AccountResponseDto saveCustomerAndAccount(
	            CustomerCreateDto customerDto,
	            AccountCreateDto accountDto
	    );

	    AccountResponseDto updateAccount(
	            Long customerId,
	            Long accountId,
	            AccountUpdateDto accountDto
	    );

	    AccountResponseDto deactivateAccount(
	            Long customerId,
	            Long accountId
	    );
	}


