package com.codeWithPiyush.SpringBatchApp.repository;

import com.codeWithPiyush.SpringBatchApp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Serializable> {
}
