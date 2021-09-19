package com.rizalpurnama.dao;

import com.rizalpurnama.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CustomerDao extends PagingAndSortingRepository<Customer, String> {
    Optional<Customer> findByCode(String customerCode);
}
