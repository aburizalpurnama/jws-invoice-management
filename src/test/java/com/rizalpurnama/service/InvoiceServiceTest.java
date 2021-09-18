package com.rizalpurnama.service;

import com.rizalpurnama.dao.*;
import com.rizalpurnama.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootTest
public class InvoiceServiceTest {

    @Autowired CustomerDao customerDao;
    @Autowired InvoiceTypeDao invoiceTypeDao;
    @Autowired VirtualAccountDao virtualAccountDao;
    @Autowired InvoiceService invoiceService;
    @Autowired VirtualAccountConfigurationDao vaConfigDao;
    @Autowired PaymentProviderDao paymentProviderDao;

    @Test
    @Sql(scripts = {
            "classpath:/sql/delete-sample-data-invoice.sql",
            "classpath:/sql/insert-sample-data-invoice.sql"
    })
    public void createInvoice(){
        Customer customer = customerDao.findById("c001").get();
        InvoiceType registrasi = invoiceTypeDao.findById("registrasi").get();
        BigDecimal amount = new BigDecimal(123000.98);
        String description = "Tagihan Registrasi";

        Invoice invoice = invoiceService.createInvoice(customer, registrasi, description, amount);

        VirtualAccountConfiguration config = vaConfigDao.findById("va-bni").get();

        PaymentProvider paymentProvider = paymentProviderDao.findById("bankbni").get();

        VirtualAccount va = new VirtualAccount();
        va.setInvoice(invoice);
        va.setAccountNumber("12345");
        va.setVirtualAccountConfiguration(config);
        va.setCreated(LocalDateTime.now());
        va.setCreatedBy("user-test");
        va.setPaymentProvider(paymentProvider);
        va.setCompanyId(config.getCompany_prefix());
        virtualAccountDao.save(va);

        Assertions.assertNotNull(invoice);
        Assertions.assertNotNull(invoice.getInvoiceNumber());

        System.out.println("Invoice Number : " + invoice.getInvoiceNumber());

    }
}
