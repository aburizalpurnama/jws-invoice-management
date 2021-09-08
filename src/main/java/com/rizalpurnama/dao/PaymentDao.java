package com.rizalpurnama.dao;

import com.rizalpurnama.entity.Payment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentDao extends PagingAndSortingRepository<Payment, String> {
}
