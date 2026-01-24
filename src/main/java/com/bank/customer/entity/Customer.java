package com.bank.customer.entity;



import java.util.List;

import com.bank.account.entity.Account;
import com.bank.customer.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String fullName;
	@Column(nullable = false, unique=true)
	private String phone;
	@Column(nullable = false, unique=true)
	private String email;
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private CustomerDetails customerDetails;
	
	@OneToMany(mappedBy = "customer" , cascade =  CascadeType.ALL,fetch = FetchType.LAZY)
	private List< Account> accounts;
}

