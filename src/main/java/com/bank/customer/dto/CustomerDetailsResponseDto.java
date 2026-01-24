package com.bank.customer.dto;

import java.time.LocalDate;

import com.bank.customer.enums.Gender;
import com.bank.customer.enums.Kyc;

import lombok.Data;

@Data
public class CustomerDetailsResponseDto {
	
	private String fatherName;

	private String motherName;

	private LocalDate dob;


	private Gender gender;

	private Kyc kyc;
	
	private Long id;
}
