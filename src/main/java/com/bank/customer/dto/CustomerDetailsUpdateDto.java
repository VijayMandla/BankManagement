package com.bank.customer.dto;

import java.time.LocalDate;

import com.bank.customer.enums.Gender;
import com.bank.customer.enums.Kyc;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerDetailsUpdateDto {
	@NotBlank
	private String fatherName;
	@NotBlank
	private String motherName;
	@NotNull
	private LocalDate dob;
	@NotNull
	private Gender gender;

	@NotNull
	private Kyc kyc;
}
