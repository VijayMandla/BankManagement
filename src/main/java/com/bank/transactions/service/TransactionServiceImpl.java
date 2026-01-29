package com.bank.transactions.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.account.dto.AccountResponseDto;
import com.bank.account.entity.Account;
import com.bank.account.enumdata.AccountStatus;
import com.bank.account.enumdata.AccountType;
import com.bank.account.repository.AccountRepo;
import com.bank.customer.exceptions.ResourceNotFoundException;
import com.bank.transactions.dto.DepositRequestDto;
import com.bank.transactions.dto.TransactionResponseDto;
import com.bank.transactions.dto.TransferRequestDto;
import com.bank.transactions.dto.WithdrawRequestDto;
import com.bank.transactions.entity.Transaction;
import com.bank.transactions.enumdata.TransferType;
import com.bank.transactions.repository.TransactionRepo;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private AccountRepo accountRepo;

	@Autowired
	private TransactionRepo transcationRepo;

	@Override
	@Transactional
	public TransactionResponseDto deposit(Long accountId, DepositRequestDto ddto) {

		Account a = accountRepo.findById(accountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found"));

		if (a.getStatus() != AccountStatus.ACTIVE) {
			throw new IllegalStateException("Account is not active");
		}

		BigDecimal afterDepositBalance = a.getBalance().add(ddto.getAmount());
		a.setBalance(afterDepositBalance);

		Transaction t = new Transaction();
		t.setAccount(a);
		t.setBalanceAfter(afterDepositBalance);
		t.setTransactionTime(LocalDateTime.now());
		t.setType(TransferType.CREDIT);
		t.setAmount(ddto.getAmount());

		transcationRepo.save(t);
		return mapToResponseDto(t);
	}

	public TransactionResponseDto withdraw(Long accountId, WithdrawRequestDto wdto) {

		Account a = accountRepo.findById(accountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found"));

		if (a.getStatus() != AccountStatus.ACTIVE) {
			throw new IllegalStateException("Account is not active");

		}

		if (a.getBalance().compareTo(wdto.getWithDrawAmount()) < 0) {
			throw new IllegalStateException("Insufficient balance");
		}

		BigDecimal currentBalance = a.getBalance().subtract(wdto.getWithDrawAmount());
		Transaction t = new Transaction();
		t.setAccount(a);
		a.setBalance(currentBalance);
		t.setAmount(wdto.getWithDrawAmount());
		t.setBalanceAfter(currentBalance);
		t.setTransactionTime(LocalDateTime.now());
		t.setType(TransferType.WITHDRAW);

		transcationRepo.save(t);
		return mapToResponseDto(t);

	}

	public TransactionResponseDto transfer(Long senderId, Long receiverId, TransferRequestDto tdto) {

		Account senderData = accountRepo.findById(senderId).orElseThrow();

		Account receiverData = accountRepo.findById(receiverId).orElseThrow();

		if (senderData.getId().equals(receiverData.getId())) {
			throw new IllegalStateException();
		}

		if (senderData.getStatus() != AccountStatus.ACTIVE || receiverData.getStatus() != AccountStatus.ACTIVE) {

			throw new IllegalStateException("Both sender and receiver must be ACTIVE");
		}

		if (senderData.getBalance().compareTo(tdto.getAmount()) < 0) {
			throw new IllegalStateException("Insufficient balance");
		}

		if (tdto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
		    throw new IllegalArgumentException("Transfer amount must be greater than zero");
		}
		
		BigDecimal senderBalanceAfterTransfer = senderData.getBalance().subtract(tdto.getAmount());

		senderData.setBalance(senderBalanceAfterTransfer);

		BigDecimal receiverBalanceAfterTransfer = receiverData.getBalance().add(tdto.getAmount());

		receiverData.setBalance(receiverBalanceAfterTransfer);

		Transaction senderTransaction = new Transaction();

		senderTransaction.setAccount(senderData);
		senderTransaction.setAmount(tdto.getAmount());
		senderTransaction.setBalanceAfter(senderBalanceAfterTransfer);
		senderTransaction.setTransactionTime(LocalDateTime.now());
		senderTransaction.setType(TransferType.DEBIT);

		transcationRepo.save(senderTransaction);

		Transaction receiverTransaction = new Transaction();

		receiverTransaction.setAccount(receiverData);
		receiverTransaction.setAmount(tdto.getAmount());
		receiverTransaction.setBalanceAfter(receiverBalanceAfterTransfer);
		receiverTransaction.setTransactionTime(LocalDateTime.now());
		receiverTransaction.setType(TransferType.CREDIT);

		transcationRepo.save(receiverTransaction);

		return mapToResponseDto(senderTransaction);
	}

	private TransactionResponseDto mapToResponseDto(Transaction transaction) {

		TransactionResponseDto response = new TransactionResponseDto();
		response.setBalanceAfter(transaction.getBalanceAfter());
		response.setTransactionTime(transaction.getTransactionTime());
		response.setType(transaction.getType());
		response.setId(transaction.getId());
		return response;
	}

	@Override
	public TransactionResponseDto getTransactionsById(Long trasactionId) {
		log.info("GetAPI triggered for getTransactionsById method with transactionId {}",trasactionId);
		
	Transaction t=	transcationRepo.findById(trasactionId)
		          .orElseThrow(()->new ResourceNotFoundException("with this id there is no transactions available"));
		
		log.info("getTransactionsById method executed from transaction service");
		
		
		return mapToResponseDto(t);
	}

	@Override
	public List<TransactionResponseDto>  getTransactionsByAccountId(Long accountId){
		
		log.info("GetAPI triggered for getAlltransactionOfAccount method with accountId  {}",accountId);
		
		List<Transaction> tran=	transcationRepo.findByAccountId (accountId);
		
		log.info("getTransacionById method executed from transaction service");
		
		return tran.stream().map(this::mapToResponseDto).toList();
	}
	/*
	 * @Override public List<TransactionResponseDto> getAlltransactionOfAccount() {
	 * 
	 * log.
	 * info("GetAPI triggered for getAlltransactionOfAccount method with accountId  {}"
	 * );
	 * 
	 * List<Account> account= accountRepo.findAll();
	 * 
	 * 
	 * log.
	 * info("getAlltransactionOfAccount method executed from transaction service");
	 * 
	 * return null; }
	 */

	@Override
	public List<TransactionResponseDto> getAlltransactionOfAccount() {
		// TODO Auto-generated method stub
		return null;
	}

}
