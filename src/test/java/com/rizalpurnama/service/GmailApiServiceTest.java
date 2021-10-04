package com.rizalpurnama.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GmailApiServiceTest {
    @Autowired private GmailApiService gmailApiService;

    @Test
    void testKirimEmail() {
        String to = "detination@yopmail.com";
        String from = "invoice.management@yopmail.com";
        String subject = "Percobaan kirim email menggunakan Gmail API";
        String content = "<h1>Halo User</h1> <b>Selamat sore</b> <i> test user </i>";

        gmailApiService.kirimEmail(from, to, subject, content);
    }
}
