package com.bank.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.customer.dto.CustomerCreateDto;
import com.bank.customer.dto.CustomerDetailsCreateDto;
import com.bank.customer.dto.CustomerDetailsResponseDto;
import com.bank.customer.dto.CustomerDetailsUpdateDto;
import com.bank.customer.dto.CustomerResonseDto;
import com.bank.customer.dto.CustomerUpdateDto;
import com.bank.customer.entity.Customer;
import com.bank.customer.entity.CustomerDetails;
import com.bank.customer.enums.Kyc;
import com.bank.customer.enums.Status;
import com.bank.customer.exceptions.ResourceNotFoundException;
import com.bank.customer.repo.CustomerDetailsRepo;
import com.bank.customer.repo.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CustomerDetailsRepo customerDetailsRepo;

	@Override
	public CustomerResonseDto saveCustomer(CustomerCreateDto customerCreateDto) {

		Customer dto = new Customer();

		dto.setFullName(customerCreateDto.getFullName());
		dto.setPhone(customerCreateDto.getPhone());
		dto.setEmail(customerCreateDto.getEmail());
		dto.setStatus(Status.ACTIVE);
		Customer c=customerRepo.save(dto);
		return maptoRespons(c);
	}

	@Override
	public List<CustomerResonseDto> saveAllCustomers(List<CustomerCreateDto> customerCreateDto) {

		List<Customer> list = new ArrayList();

		for (CustomerCreateDto cust : customerCreateDto) {
			Customer dto = new Customer();
			dto.setFullName(cust.getFullName());
			dto.setPhone(cust.getPhone());
			dto.setEmail(cust.getEmail());
			dto.setStatus(Status.ACTIVE);

			list.add(dto);

		}
		List<Customer> c = customerRepo.saveAll(list);

		return c.stream().map(this::maptoRespons).toList();

	}

	@Override
	public CustomerResonseDto getCustomer(Long id) {
		Customer c = customerRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(" ith this id there is no records available"));

		return maptoRespons(c);
	}

	@Override
	public List<CustomerResonseDto> getAllCustomers() {
		List<Customer> c = customerRepo.findAll();

		return c.stream().map(this::maptoRespons).toList();
	}

	@Override
	public CustomerResonseDto saveCustomerAndCustomerDetails(CustomerCreateDto customerCreateDto,
			CustomerDetailsCreateDto customerDetailsCreateDto) {
		

		Customer dto = new Customer();
		
		CustomerDetails cd=new CustomerDetails();
		
		cd.setFatherName(customerDetailsCreateDto.getFatherName());
        cd.setMotherName(customerDetailsCreateDto.getMotherName());
		
		cd.setAadharNumber(customerDetailsCreateDto.getAadharNumber());
		cd.setPanNumbr(customerDetailsCreateDto.getPanNumbr());
		cd.setDob(customerDetailsCreateDto.getDob());
		cd.setGender(customerDetailsCreateDto.getGender());
		
		cd.setKyc(Kyc.VERIFIED);
		cd.setCustomer(dto);
		

		dto.setFullName(customerCreateDto.getFullName());
		dto.setPhone(customerCreateDto.getPhone());
		dto.setEmail(customerCreateDto.getEmail());
		//dto.setStatus(customerCreateDto.getStatus());
		dto.setCustomerDetails(cd);
		
		Customer c=customerRepo.save(dto);
		
		return maptoRespons(c);

	
		}

	@Override
	public CustomerResonseDto updateCustomer(Long id, CustomerUpdateDto updateDto) {

		Customer dto = customerRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(" ith this id there is no records available"));

		dto.setFullName(updateDto.getFullName());

		dto.setPhone(updateDto.getPhone());

		dto.setEmail(updateDto.getEmail());

	

		customerRepo.save(dto);

		return maptoRespons(dto);
	}

	private CustomerResonseDto maptoRespons(Customer customer) {

		CustomerResonseDto dto = new CustomerResonseDto();

		dto.setFullName(customer.getFullName());
		dto.setId(customer.getId());
		dto.setPhone(customer.getPhone());
		dto.setEmail(customer.getEmail());
		dto.setStatus(customer.getStatus());

		return dto;

	}

	@Override
	public CustomerDetailsResponseDto updateCustomerDetails(Long customerId, CustomerDetailsUpdateDto updateDto) {

		CustomerDetails cd = customerDetailsRepo.findByCustomerId(customerId)
				.orElseThrow(() -> new ResourceNotFoundException(" ith this id there is no records available"));

		cd.setFatherName(updateDto.getFatherName());
		cd.setMotherName(updateDto.getMotherName());
		cd.setGender(updateDto.getGender());
		cd.setDob(updateDto.getDob());
		cd.setKyc(updateDto.getKyc());

		customerDetailsRepo.save(cd);
		return mapToRespons(cd);
	}

	private CustomerDetailsResponseDto mapToRespons(CustomerDetails updateDto) {
		CustomerDetailsResponseDto cd = new CustomerDetailsResponseDto();

		cd.setFatherName(updateDto.getFatherName());
		cd.setMotherName(updateDto.getMotherName());
		cd.setGender(updateDto.getGender());
	
		cd.setKyc(updateDto.getKyc());
	    cd.setId(updateDto.getId());

		return cd;
	
	}

}
