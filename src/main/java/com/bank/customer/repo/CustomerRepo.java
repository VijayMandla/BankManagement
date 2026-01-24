package com.bank.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.customer.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
