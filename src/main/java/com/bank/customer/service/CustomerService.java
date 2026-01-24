package com.bank.customer.service;

import java.util.List;

import com.bank.customer.dto.CustomerCreateDto;
import com.bank.customer.dto.CustomerDetailsCreateDto;
import com.bank.customer.dto.CustomerDetailsResponseDto;
import com.bank.customer.dto.CustomerDetailsUpdateDto;
import com.bank.customer.dto.CustomerResonseDto;
import com.bank.customer.dto.CustomerUpdateDto;


public interface CustomerService {

	public CustomerResonseDto saveCustomer(CustomerCreateDto customerCreateDto);
	
	public List<CustomerResonseDto> saveAllCustomers(List<CustomerCreateDto> customerCreateDto);
	
	public CustomerResonseDto getCustomer(Long id);
	
	public List<CustomerResonseDto> getAllCustomers();
	
	
	public CustomerResonseDto saveCustomerAndCustomerDetails(CustomerCreateDto customerCreateDto, CustomerDetailsCreateDto customerDetailsCreateDto);
	
	public CustomerResonseDto updateCustomer(Long id, CustomerUpdateDto updateDto);
	
	public CustomerDetailsResponseDto updateCustomerDetails(Long customerId, CustomerDetailsUpdateDto updateDto);
	
	
}
