package com.rizalpurnama.dao;

import com.rizalpurnama.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerDao extends PagingAndSortingRepository<Customer, String> {
}
