package com.bank.transactions.service;


import java.util.List;

import com.bank.transactions.dto.DepositRequestDto;
import com.bank.transactions.dto.TransactionResponseDto;
import com.bank.transactions.dto.TransferRequestDto;
import com.bank.transactions.dto.WithdrawRequestDto;

public interface TransactionService {
	
	public TransactionResponseDto deposit(Long accountId, DepositRequestDto ddto);
	
	public TransactionResponseDto withdraw(Long accountId,WithdrawRequestDto wdto);
	
	public TransactionResponseDto transfer(Long senderId, Long receiverId,  TransferRequestDto tdto);
	
	public TransactionResponseDto getTransactionsById(Long trasactionId);
	
	public List<TransactionResponseDto> getAlltransactionOfAccount();

	List<TransactionResponseDto> getTransactionsByAccountId(Long accountId);
}
