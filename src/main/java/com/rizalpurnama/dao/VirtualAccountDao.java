package com.rizalpurnama.dao;

import com.rizalpurnama.entity.PaymentProvider;
import com.rizalpurnama.entity.VirtualAccount;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface VirtualAccountDao extends PagingAndSortingRepository<VirtualAccount, String> {
    Optional<VirtualAccount> findByPaymentProviderAndCompanyIdAndAccountNumber(PaymentProvider paymentProvider, String companyId, String accountNumber);
}
