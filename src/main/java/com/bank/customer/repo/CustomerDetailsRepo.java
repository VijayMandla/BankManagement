package com.bank.customer.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bank.customer.entity.CustomerDetails;

public interface CustomerDetailsRepo extends JpaRepository<CustomerDetails, Long> {
	Optional<CustomerDetails> findByCustomerId(Long customerId);

}
