package com.bank.common;

import com.bank.customer.dto.CustomerCreateDto;
import com.bank.customer.dto.CustomerDetailsCreateDto;


import lombok.Data;

@Data
public class CustomerAndCustomerDetails {
	
	private CustomerCreateDto customerDto;
	
	private CustomerDetailsCreateDto customerDetailsDto;
	

}
