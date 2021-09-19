package com.rizalpurnama.controller;

import com.rizalpurnama.dao.CustomerDao;
import com.rizalpurnama.dao.InvoiceTypeDao;
import com.rizalpurnama.dto.CreateInvoiceRequestDto;
import com.rizalpurnama.entity.Customer;
import com.rizalpurnama.entity.InvoiceType;
import com.rizalpurnama.exception.CustomerNotFoundException;
import com.rizalpurnama.exception.InvoiceTypeNotFoundException;
import com.rizalpurnama.service.InvoiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController @RequestMapping("/api/invoice")
public class InvoiceApiController {

    @Autowired CustomerDao customerDao;
    @Autowired InvoiceTypeDao invoiceTypeDao;
    @Autowired InvoiceService invoiceService;

    @PostMapping("/")
    public void createInvoice(@RequestBody @Valid CreateInvoiceRequestDto createInvoiceRequestDto)
            throws CustomerNotFoundException, InvoiceTypeNotFoundException {

        Customer customer = customerDao.findByCode(createInvoiceRequestDto.getCustomerCode())
                .orElseThrow(() -> new CustomerNotFoundException("Customer code '"+ createInvoiceRequestDto.getCustomerCode() +"' not found"));
        log.info("Customer {}", customer.getName());

        InvoiceType invoiceType = invoiceTypeDao.findByCode(createInvoiceRequestDto.getInvoiceTypeCode())
                .orElseThrow(() -> new InvoiceTypeNotFoundException("Invoice type '"+ createInvoiceRequestDto.getInvoiceTypeCode() +"' not found"));
        log.info("Invoice Type {}", invoiceType.getName());

        invoiceService.createInvoice(customer,
                invoiceType,
                createInvoiceRequestDto.getDescription(),
                createInvoiceRequestDto.getAmount());

        log.info("Invoice created");
    }
}
