package com.bank.account.entity;

import java.math.BigDecimal;
import java.util.List;

import com.bank.account.enumdata.AccountStatus;
import com.bank.account.enumdata.AccountType;
import com.bank.customer.entity.Customer;
import com.bank.transactions.entity.Transaction;



import jakarta.persistence.*;

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
	
	private BigDecimal balance;

	@Enumerated(EnumType.STRING)
	private AccountStatus status;

	@Enumerated(EnumType.STRING)

	private AccountType type;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)

	private Customer customer;
	
	@OneToMany(mappedBy = "account")
	private List<Transaction>  transactions;
}
