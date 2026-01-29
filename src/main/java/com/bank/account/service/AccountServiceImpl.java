package com.bank.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.account.dto.AccountCreateDto;
import com.bank.account.dto.AccountResponseDto;
import com.bank.account.dto.AccountUpdateDto;
import com.bank.account.entity.Account;
import com.bank.account.enumdata.AccountStatus;
import com.bank.account.enumdata.AccountType;
import com.bank.account.repository.AccountRepo;
import com.bank.customer.dto.CustomerCreateDto;
import com.bank.customer.entity.Customer;
import com.bank.customer.enums.Status;
import com.bank.customer.exceptions.ResourceNotFoundException;
import com.bank.customer.repo.CustomerRepo;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepo accountRepo;

	@Autowired
	private CustomerRepo custRepo;

	@Override // saving account
	public AccountResponseDto saveAccount(Long customerId, AccountCreateDto accountDto) {

		log.info("PostMapping Api triggered for saveAccount method");
		Customer c = custRepo.findById(customerId)
				.orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

		Account account = new Account();

		account.setType(AccountType.SAVINGS);
		account.setStatus(AccountStatus.ACTIVE);
		account.setAcountNumber(generateAccountNumber());
		account.setCustomer(c);
		accountRepo.save(account);
		log.info("saveAccount method excuted ");
		return mapToResponseDto(account);
	}

	@Override //updateAccount
	public AccountResponseDto updateAccount(Long customerId, Long accountId, AccountUpdateDto accountDto) {

		log.info("put Api triggered for updateAccount method");

		Account account = accountRepo.findById(accountId)
				             .orElseThrow(() -> new RuntimeException("Account not found"));

		if (!account.getCustomer().getId().equals(customerId)) {
			throw new RuntimeException("Account does not belong to this customer");
		}
		account.setType(accountDto.getType());
		account.setStatus(accountDto.getStatus());

		accountRepo.save(account);

		log.info(" updateAccount method excuted from serice");

		return mapToResponseDto(account);
	}
	
	@Override //deactivateAccount
	public AccountResponseDto deactivateAccount(Long customerId, Long accountId) {

		Account a = accountRepo.findById(accountId)  
				                   .orElseThrow(() -> new RuntimeException("Account not found"));

		if (!a.getCustomer().getId().equals(customerId)) {
			throw new ResourceNotFoundException("Account does not belong to this customer");
		}
		
		if(a.getStatus()==AccountStatus.INACTIVE) {
			throw new ResourceNotFoundException("Already acount inactive");
		}
		a.setStatus(AccountStatus.INACTIVE);
		accountRepo.save(a);

		return mapToResponseDto(a);
	}

	@Override
	public AccountResponseDto saveCustomerAndAccount(CustomerCreateDto customerDto, AccountCreateDto accountDto) {

		Customer customer = new Customer();
		customer.setFullName(customerDto.getFullName());
		customer.setEmail(customerDto.getEmail());
		customer.setPhone(customerDto.getPhone());
		customer.setStatus(Status.ACTIVE);

		Customer savedCustomer = custRepo.save(customer);

		Account account = new Account();
		account.setType(accountDto.getType());
		account.setStatus(AccountStatus.ACTIVE);
		account.setAcountNumber(generateAccountNumber());
		account.setCustomer(savedCustomer);

		Account savedAccount = accountRepo.save(account);

		return mapToResponseDto(savedAccount);
	}

	private String generateAccountNumber() {
		return "ACC" + System.currentTimeMillis() + (int) (Math.random() * 100);
	}

	public AccountResponseDto mapToResponseDto(Account account) {

		AccountResponseDto response = new AccountResponseDto();
		response.setId(account.getId());
		response.setStatus(account.getStatus());
		response.setType(account.getType());
		response.setAccountNumber(account.getAcountNumber());
		return response;
	}

	

}
