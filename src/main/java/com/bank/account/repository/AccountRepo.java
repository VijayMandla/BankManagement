package com.bank.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bank.account.entity.Account;


public interface AccountRepo extends JpaRepository<Account, Long> {

	
	
	
	
	
	
}
