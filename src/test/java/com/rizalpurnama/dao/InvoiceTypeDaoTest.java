package com.rizalpurnama.dao;

import com.rizalpurnama.entity.InvoiceType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.Assert;

@SpringBootTest
@Sql(scripts = {"/sql/delete-invoice-type.sql", "/sql/insert-inactive-invoice-type.sql"})
public class InvoiceTypeDaoTest {

    @Autowired
    InvoiceTypeDao invoiceTypeDao;

    @Test
    public void testInsertInvoiceType() throws InterruptedException {
        InvoiceType it = new InvoiceType();
        it.setCode("IT-001");
        it.setName("Invoice Type Test");

        Assertions.assertNull(it.getId());
        invoiceTypeDao.save(it);

        System.out.println("Id : " + it.getId());
        System.out.println("Created : " + it.getCreated());
        System.out.println("Created by : " + it.getCreatedBy());
        System.out.println("Updated : " + it.getUpdated());
        System.out.println("Updated By : " + it.getUpdatedBy());
        System.out.println("Status Record : " + it.getStatusRecord());
        System.out.println("Code : " + it.getCode());
        System.out.println("Name : " + it.getName());

        Assertions.assertNotNull(it.getId());
        Assertions.assertNotNull(it.getCreated());
        Assertions.assertNotNull(it.getCreatedBy());
        Assertions.assertNotNull(it.getUpdated());
        Assertions.assertNotNull(it.getUpdatedBy());
        Assertions.assertNotNull(it.getStatusRecord());
        Assertions.assertEquals("IT-001", it.getCode());
        Assertions.assertEquals("Invoice Type Test", it.getName());
        Assertions.assertEquals(it.getCreated(), it.getUpdated());

        Thread.sleep(1000);
        it.setName("Test Update");
        invoiceTypeDao.save(it);

        InvoiceType invoiceType = invoiceTypeDao.findById(it.getId()).orElse(null);
        System.out.println("Created : " + invoiceType.getCreated());
        System.out.println("Updated : " + invoiceType.getUpdated());

        Assertions.assertNotEquals(invoiceType.getCreated(), invoiceType.getUpdated());

        Thread.sleep(3000);
    }

    @Test
    public void testQuerySoftDelete() {
        long jumlahRecord = invoiceTypeDao.count();
        System.out.println("Jumlah Recoord : " + jumlahRecord);
        Assertions.assertEquals(2, jumlahRecord);
    }

    @Test
    public void testSoftDelete() {
        InvoiceType it = invoiceTypeDao.findById("test003").get();
        invoiceTypeDao.delete(it);

        long jumlahRecord = invoiceTypeDao.count();
        System.out.println("Jumlah Recoord : " + jumlahRecord);
        Assertions.assertEquals(1, jumlahRecord);
    }
}