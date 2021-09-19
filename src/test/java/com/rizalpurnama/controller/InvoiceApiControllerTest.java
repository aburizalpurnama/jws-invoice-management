package com.rizalpurnama.controller;

import com.rizalpurnama.dto.CreateInvoiceRequestDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.*;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = {
        "classpath:/sql/delete-all-data.sql",
        "classpath:/sql/insert-sample-data-invoice.sql"
})
public class InvoiceApiControllerTest {
    @LocalServerPort
    private Integer serverPort;

    @BeforeEach
    public void setup() {
        baseURI = "http://localhost";
        port = serverPort;

        System.out.println("Post : " + serverPort);
    }

    @Test
    void testCreateInvoice() {
        CreateInvoiceRequestDto invoiceRequest = new CreateInvoiceRequestDto();
        invoiceRequest.setCustomerCode("CUST-001");
        invoiceRequest.setInvoiceTypeCode("REG-001");
        invoiceRequest.setAmount(new BigDecimal(123000.98));
        invoiceRequest.setDescription("Pembayaran jadi presiden");

        with().body(invoiceRequest).contentType(ContentType.JSON)
                .when().post("/api/invoice/")
                .then()
                .statusCode(200);
    }
}
