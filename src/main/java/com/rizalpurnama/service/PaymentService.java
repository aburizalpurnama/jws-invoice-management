package com.rizalpurnama.service;

import com.rizalpurnama.dao.InvoiceDao;
import com.rizalpurnama.dao.VirtualAccountDao;
import com.rizalpurnama.entity.*;
import com.rizalpurnama.exception.PaymentExceedInvoiceAmountException;
import com.rizalpurnama.exception.VirtualAccountAlreadyPaidException;
import com.rizalpurnama.exception.VirtualAccountNotFoundException;
import com.rizalpurnama.helper.VirtualAccountHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class PaymentService {

    @Autowired VirtualAccountDao virtualAccountDao;
    @Autowired
    ActivityLogService auditLogService;
    @Autowired InvoiceDao invoiceDao;

    @Transactional(
            rollbackOn = {
                    VirtualAccountNotFoundException.class,
                    VirtualAccountAlreadyPaidException.class,
                    PaymentExceedInvoiceAmountException.class
            })
    public void pay(PaymentProvider paymentProvider,
                    String companyId,
                    String accountNumber,
                    BigDecimal amount,
                    String reference)
            throws VirtualAccountNotFoundException, VirtualAccountAlreadyPaidException, PaymentExceedInvoiceAmountException {

        auditLogService.log("Start payment VA " + accountNumber);
        // 1. cek apakah VA ada ?
        VirtualAccount va = VirtualAccountHelper.checkIsVaExist(virtualAccountDao, paymentProvider, companyId, accountNumber);

        // 2. cek apakah VA sudah lunas ?
        checkIsVaAlreadyPaid(paymentProvider, companyId, accountNumber, va);

        // 3. cek apakah amount pembayaran > nilai tagihan ?
        checkIsPaymentAmountExceedInvoice(amount, va);

        // 4. update status VA menjadi lunas.
        va.setStatusRecord(StatusRecord.INACTIVE);

        // 5. update status invoice menjadi lunas
        Invoice invoice = va.getInvoice();
        invoice.setTotalPayment(invoice.getTotalPayment().add(amount));
        invoice.setPaid(true);
        invoice.setPaymentStatus(PaymentStatus.FULL);
        invoiceDao.save(invoice);

        // 6. insert ke tabel payment
        // 7. notifikasi (next phase)

//        throw new VirtualAccountNotFoundException();
    }

    private void checkIsPaymentAmountExceedInvoice(BigDecimal amount, VirtualAccount va) throws PaymentExceedInvoiceAmountException {
        if ( va.getInvoice().getAmount().compareTo(amount) == -1){
            throw new PaymentExceedInvoiceAmountException("Jumlah bayar melebihi jumlah tagihan !");
        }
    }

    private void checkIsVaAlreadyPaid(PaymentProvider paymentProvider, String companyId, String accountNumber, VirtualAccount va) throws VirtualAccountAlreadyPaidException {
        if (va.getInvoice().isPaid()){
            throw new VirtualAccountAlreadyPaidException(
                    "VA ["+ companyId +"/"+ accountNumber +"-"+ paymentProvider.getCode()+"] already paid");

        }
    }
}
