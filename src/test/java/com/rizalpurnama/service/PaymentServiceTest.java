package com.rizalpurnama.service;

import com.rizalpurnama.dao.VirtualAccountConfigurationDao;
import com.rizalpurnama.dao.VirtualAccountDao;
import com.rizalpurnama.entity.Payment;
import com.rizalpurnama.entity.PaymentProvider;
import com.rizalpurnama.entity.VirtualAccount;
import com.rizalpurnama.entity.VirtualAccountConfiguration;
import com.rizalpurnama.exception.PaymentExceedInvoiceAmountException;
import com.rizalpurnama.exception.VirtualAccountAlreadyPaidException;
import com.rizalpurnama.exception.VirtualAccountNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class PaymentServiceTest {

    @Autowired PaymentService paymentService;
    @Autowired VirtualAccountConfigurationDao vaConfigDao;
    @Autowired VirtualAccountDao virtualAccountDao;


    @Test
    void testCreatePayment() throws VirtualAccountAlreadyPaidException, PaymentExceedInvoiceAmountException, VirtualAccountNotFoundException {
        VirtualAccountConfiguration config = vaConfigDao.findById("va-bni").get();

        paymentService.pay(config.getPaymentProvider(),
                config.getCompany_prefix(),
                "23509899929919",
                new BigDecimal(123000.98),
                "Test Make a Payment");
    }
}
