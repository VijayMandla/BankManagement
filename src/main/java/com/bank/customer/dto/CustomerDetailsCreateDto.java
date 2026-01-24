package com.bank.customer.dto;

import java.time.LocalDate;

import com.bank.customer.enums.Gender;
import com.bank.customer.enums.Kyc;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerDetailsCreateDto {
	@NotBlank
	private String fatherName;
	@NotBlank
	private String motherName;
	@NotNull
	private LocalDate dob;
	@NotBlank
	@Pattern(regexp = "^[A-Z][0-9]{10}$")
	private String panNumbr;

	@NotBlank
	@Pattern(regexp = "^[0-9]{12}$")
	private String aadharNumber;

	@NotNull
	private Gender gender;

	@NotNull
	private Kyc kyc;
}
