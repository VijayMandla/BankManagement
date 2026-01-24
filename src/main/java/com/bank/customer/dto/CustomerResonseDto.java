package com.bank.customer.dto;


import java.util.List;

import com.bank.account.dto.AccountResponseDto;
import com.bank.customer.enums.Status;

import lombok.Data;

@Data
public class CustomerResonseDto {

	private Long id;
	
	private String fullName;
	
	private String phone;

	private String email;

	private Status status;
	

	private CustomerDetailsResponseDto customerDetails;
	
	private List<AccountResponseDto> account;
}

