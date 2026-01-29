package com.bank.transactions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.common.ApiResponse;
import com.bank.transactions.dto.DepositRequestDto;
import com.bank.transactions.dto.TransactionResponseDto;
import com.bank.transactions.dto.TransferRequestDto;
import com.bank.transactions.dto.WithdrawRequestDto;
import com.bank.transactions.service.TransactionService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/transactions")
@Slf4j
public class TransactionController {
	
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/{accountId}/deposit")
	
	public ResponseEntity<ApiResponse<TransactionResponseDto>> saveDeposite(@PathVariable Long accountId,
	@Valid	@RequestBody	DepositRequestDto dto ){
		
		log.info("PostAPi request received with accountId{}",accountId);
		TransactionResponseDto trd=	transactionService.deposit(accountId, dto);
		
		log.info("Save deposit method eecuted");
		
		return  ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(201,"Amount creadited to account",trd));
	}
	
	@PostMapping("/{accountId}/withdraw")
	
	public ResponseEntity<ApiResponse<TransactionResponseDto>> withdraw(@PathVariable Long accountId,
	@Valid	@RequestBody	WithdrawRequestDto wdto ){
		
		log.info("PostAPi request received with accountId{} for withdraw",accountId);
		TransactionResponseDto trd=	transactionService.withdraw(accountId, wdto);
		
		log.info("Save withdraw method executed");
		
		return  ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(201,"Amount debited to account",trd));
	}
	
	@PostMapping("/{senderId}/{receiverId}/transfer")
	public ResponseEntity<ApiResponse<TransactionResponseDto>> transfer(@PathVariable Long senderId, @PathVariable Long receiverId,
			@Valid	@RequestBody	TransferRequestDto tdto ){
	
		log.info("PostAPi request received with senderId{} and receiver id{} for transfer",senderId,receiverId);
		
		 TransactionResponseDto trd=	transactionService.transfer(senderId, receiverId, tdto);
		 
		 log.info(" transfer method executed");
		 
		return  ResponseEntity.status(HttpStatus.CREATED)
				.body(new ApiResponse<>(201,"Amount transferred to receiver account",trd));
	}

	@GetMapping("/{accountId}/transaction")
	public ResponseEntity<ApiResponse<List<TransactionResponseDto>>> getAllTransactionById(@PathVariable Long accountId){
		
		List<TransactionResponseDto> trans= transactionService.getTransactionsByAccountId(accountId);
		
		return  ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(200,"",trans));
	}
}
