package com.rizalpurnama.helper;

import com.rizalpurnama.dao.VirtualAccountDao;
import com.rizalpurnama.entity.PaymentProvider;
import com.rizalpurnama.entity.VirtualAccount;
import com.rizalpurnama.exception.VirtualAccountNotFoundException;

import java.util.Optional;

public class VirtualAccountHelper {

    public static VirtualAccount checkIsVaExist(VirtualAccountDao virtualAccountDao, PaymentProvider paymentProvider, String companyId, String accountNumber) throws VirtualAccountNotFoundException {
        Optional<VirtualAccount> optVa =
                virtualAccountDao.findByPaymentProviderAndCompanyIdAndAccountNumber(
                        paymentProvider, companyId, accountNumber);

        if (!optVa.isPresent()){
            throw new VirtualAccountNotFoundException(
                    "VA ["+ companyId +"/"+ accountNumber +"-"+ paymentProvider.getCode()+"] not found");
        }
        VirtualAccount va = optVa.get();
        return va;
    }
}
