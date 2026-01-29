package com.bank.transactions.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.transactions.entity.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Long>{

	List<Transaction> findByAccountId(Long accountId);
}
