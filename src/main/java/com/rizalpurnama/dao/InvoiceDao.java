package com.rizalpurnama.dao;

import com.rizalpurnama.entity.Invoice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InvoiceDao extends PagingAndSortingRepository<Invoice, String> {
}
