package com.rizalpurnama.service;

import com.rizalpurnama.dao.InvoiceDao;
import com.rizalpurnama.dao.PaymentDao;
import com.rizalpurnama.entity.Customer;
import com.rizalpurnama.entity.Invoice;
import com.rizalpurnama.entity.InvoiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service @Transactional
public class InvoiceService {

    @Autowired
    InvoiceDao invoiceDao;

    public Invoice createInvoice(Customer customer, InvoiceType registrasi, String description, BigDecimal amount) {
        Invoice invoice = new Invoice();
        invoice.setAmount(amount);
        invoice.setInvoiceType(registrasi);
        invoice.setCustomer(customer);
        invoice.setDescription(description);
        invoiceDao.save(invoice);
        return invoice;
    }
}
