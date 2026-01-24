package com.bank.customer.entity;

import java.time.LocalDate;
import com.bank.customer.enums.Gender;
import com.bank.customer.enums.Kyc;
import com.bank.customer.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class CustomerDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fatherName;
	
	private String motherName;
	
	private LocalDate dob;

	private String panNumbr;
	
	private String aadharNumber;
	
	@Enumerated(EnumType.STRING)
	
	private Gender gender;
	
	@Enumerated(EnumType.STRING)
	private Kyc kyc;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="customerId")
	private Customer customer;


}
