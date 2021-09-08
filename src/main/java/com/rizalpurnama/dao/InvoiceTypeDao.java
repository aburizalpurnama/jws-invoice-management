package com.rizalpurnama.dao;

import com.rizalpurnama.entity.InvoiceType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvoiceTypeDao extends PagingAndSortingRepository<InvoiceType, String> {
}
