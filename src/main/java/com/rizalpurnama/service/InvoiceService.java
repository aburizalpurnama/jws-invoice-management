package com.rizalpurnama.service;

import com.rizalpurnama.entity.Invoice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service @Transactional
public class InvoiceService {

    public Invoice createInvoice(){
        Invoice invoice = new Invoice();

        return invoice;
    }
}
