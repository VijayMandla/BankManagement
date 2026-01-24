package com.bank.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.common.ApiResponse;
import com.bank.common.CustomerAndCustomerDetails;
import com.bank.customer.dto.CustomerCreateDto;
import com.bank.customer.dto.CustomerDetailsResponseDto;
import com.bank.customer.dto.CustomerDetailsUpdateDto;
import com.bank.customer.dto.CustomerResonseDto;
import com.bank.customer.dto.CustomerUpdateDto;
import com.bank.customer.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class Customerontoller {

	@Autowired
	private CustomerService customerServ;

	@PostMapping
	public ResponseEntity<ApiResponse<CustomerResonseDto>> saveCustomer(@Valid @RequestBody CustomerCreateDto custDto) {

		CustomerResonseDto d = customerServ.saveCustomer(custDto);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(201, "Customer Data saved successfully", d));

	}

	@PostMapping("/bulk")
	public ResponseEntity<ApiResponse<List<CustomerResonseDto>>> saveAllCustomer(
			@Valid @RequestBody List<CustomerCreateDto> custDto) {

		List<CustomerResonseDto> d = customerServ.saveAllCustomers(custDto);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(201, "Customer Data saved successfully", d));

	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<CustomerResonseDto>> getCustomer(@PathVariable Long id) {

		CustomerResonseDto d = customerServ.getCustomer(id);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse<>(200, "Customer details retrived successfully", d));

	}
	@GetMapping()
	public ResponseEntity<ApiResponse<List<CustomerResonseDto>>> getAllCustomers() {

		List<CustomerResonseDto> d = customerServ.getAllCustomers();

		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse<>(200, "Customer details retrived successfully", d));

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<CustomerResonseDto>> updateCustomer( @PathVariable Long id,@Valid @RequestBody CustomerUpdateDto custDto) {
	CustomerResonseDto d = customerServ.updateCustomer(id,custDto);

	return ResponseEntity.status(HttpStatus.OK)
			.body(new ApiResponse<>(200, "Customer Data updated successfully", d));

}
	
	@PutMapping("/{id}/details")
	public ResponseEntity<ApiResponse<CustomerDetailsResponseDto>> updatCustomerDetails( @PathVariable Long customerId ,@Valid @RequestBody CustomerDetailsUpdateDto custDto) {
	CustomerDetailsResponseDto d = customerServ.updateCustomerDetails(customerId,custDto);

	return ResponseEntity.status(HttpStatus.OK)
			.body(new ApiResponse<>(200, "Customer Data updated successfully", d));

}
	@PostMapping("/SavingBoth")
	public ResponseEntity<ApiResponse<CustomerResonseDto>> saveCustomerAndDetails(@Valid @RequestBody CustomerAndCustomerDetails custDto) {

		CustomerResonseDto d = customerServ.saveCustomerAndCustomerDetails(custDto.getCustomerDto(),custDto.getCustomerDetailsDto());

	
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(201, "Customer Data saved successfully", d));

	}
	
	
	

}
