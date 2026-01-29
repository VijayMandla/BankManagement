package com.bank.transactions.entity;

import java.math.BigDecimal;

import java.time.LocalDateTime;

import com.bank.account.entity.Account;
import com.bank.transactions.enumdata.TransferType;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(nullable = false)
    private BigDecimal amount;
    
	private BigDecimal balanceAfter;

	private LocalDateTime transactionTime;

	@Enumerated(EnumType.STRING)
	private TransferType type;

	@ManyToOne
	@JoinColumn(name = "account_Id", nullable = false)
	private Account account;

}
