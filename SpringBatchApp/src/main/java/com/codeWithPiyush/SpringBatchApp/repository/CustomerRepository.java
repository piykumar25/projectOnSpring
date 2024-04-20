package com.codeWithPiyush.SpringBatchApp.repository;

import com.codeWithPiyush.SpringBatchApp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface CustomerRepository extends JpaRepository<Customer, Serializable> {
}
