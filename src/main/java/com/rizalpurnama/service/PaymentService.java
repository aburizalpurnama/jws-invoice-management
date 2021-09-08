package com.rizalpurnama.service;

import com.rizalpurnama.entity.PaymentProvider;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service @Transactional
public class PaymentService {

    public void pay(PaymentProvider paymentProvider,
                    String companyId,
                    String accountNumber,
                    BigDecimal amount,
                    String reference){
        // 1. cek apakah VA ada ?
        // 2. cek apakah VA sudah lunas ?
        // 3. cek apakah amount pembayaran > nilai tagihan ?
        // 4. update status VA menjadi lunas.
        // 5. update status invoice menjadi lunas
        // 6. insert ke tabel payment
        // 7. notifikasi (next phase)
    }
}
