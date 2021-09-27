package com.rizalpurnama.dao;

import com.rizalpurnama.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerDaoTest {

    @Autowired private CustomerDao customerDao;

    @Test
    void testInsert() {
        Customer c = new Customer();
        c.setCode("c-001");
        c.setName("customer test");
        c.setEmail("test@email.com");
        c.setMobilePhone("09991");
        customerDao.save(c);
    }
}
