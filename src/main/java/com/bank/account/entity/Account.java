package com.bank.account.entity;

import com.bank.account.enumdata.AccountStatus;
import com.bank.account.enumdata.AccountType;
import com.bank.customer.entity.Customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String acountNumber;

	@Enumerated(EnumType.STRING)
	private AccountStatus status;

	@Enumerated(EnumType.STRING)

	private AccountType type;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)

	private Customer customer;
}
