package com.bank.customer.dto;



import com.bank.customer.enums.Status;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data

public class CustomerCreateDto {
	
	@NotBlank(message="Name can not be blank ")
	private String fullName;
	
	@Pattern(regexp = "^[0-9]{10}$")
	@NotBlank(message="phone number must be ten digits")
	private String phone;
	
	@Email(message="Emial is invalid")
	@NotBlank(message="email can not be blank")
	private String email;
	
	
}
