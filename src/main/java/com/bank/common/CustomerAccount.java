package com.bank.common;


import com.bank.account.dto.AccountCreateDto;
import com.bank.customer.dto.CustomerCreateDto;

import lombok.Data;

@Data
public class CustomerAccount {
	
private CustomerCreateDto customer;

private AccountCreateDto account;
}
