package com.rafael.ribeiro.demoprojetoapirest.repository;

import com.rafael.ribeiro.demoprojetoapirest.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}