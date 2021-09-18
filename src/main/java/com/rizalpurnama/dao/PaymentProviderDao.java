package com.rizalpurnama.dao;

import com.rizalpurnama.entity.PaymentProvider;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PaymentProviderDao extends PagingAndSortingRepository<PaymentProvider, String> {
}
