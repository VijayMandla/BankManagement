package com.bank.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account.dto.AccountCreateDto;
import com.bank.account.dto.AccountResponseDto;
import com.bank.account.dto.AccountUpdateDto;
import com.bank.account.service.AccountService;
import com.bank.common.ApiResponse;
import com.bank.common.CustomerAccount;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/accounts")

@Slf4j
public class AccountController {

	@Autowired
	private AccountService acountServ;

	@PostMapping("/{customerId}")
	public ResponseEntity<ApiResponse<AccountResponseDto>> saveAccount(@PathVariable Long customerId,
			@Valid @RequestBody AccountCreateDto accountCreateDto) {

		log.info("Received PostMapping Api request with id {} ", customerId);
		AccountResponseDto response = acountServ.saveAccount(customerId, accountCreateDto);

		log.info("PostMapping of saveAccount method executed ");

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(201, "Account saved successfully", response));

	}

	@PutMapping("/{customerId}/{accountId}")
	public ResponseEntity<ApiResponse<AccountResponseDto>> updateccount(@PathVariable Long customerId,
			@PathVariable Long accountId,@Valid @RequestBody AccountUpdateDto accountupdateDto) {
		
		log.info("Received PUT request with customerId {} and accountId {}", customerId, accountId);

		
		AccountResponseDto response = acountServ.updateAccount(customerId, accountId, accountupdateDto);

		log.info("putmapping of updateccount method executed ");
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse<>(200, "Account updated successfully", response));

	}
  @PutMapping("/{customerId}/{accountId}/deactivate")
  public ResponseEntity<ApiResponse<AccountResponseDto>> deactivateAccount(@PathVariable Long customerId,
		  @PathVariable Long accountId){
	  
		log.info("Received PUT request for deactivate with  customerId {} and accountId {}", customerId, accountId);

		AccountResponseDto response = acountServ.deactivateAccount(customerId, accountId);
		
		log.info("putmapping of deactivateAccount method executed ");
		
	  return ResponseEntity.status(HttpStatus.OK)
				.body(new ApiResponse<>(200, "Account deactivated", response));
  }
  
  @PostMapping("/save-account-with-customer")

  
  public ResponseEntity<ApiResponse<AccountResponseDto>> saveAccountWithCutomer( @Valid
		                                    @RequestBody CustomerAccount customerAndAccount) {

		log.info("Received PostMapping Api request");
		AccountResponseDto response = acountServ.saveCustomerAndAccount
				    (customerAndAccount.getCustomer(),customerAndAccount.getAccount());

		log.info("PostMapping of saveAccountWithCutomer method executed ");

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(201, "saveAccountWithCutomer saved successfully", response));

	}

}
