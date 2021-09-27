package com.rizalpurnama.controller;

import com.rizalpurnama.dao.CustomerDao;
import com.rizalpurnama.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerApiController {

    @Autowired CustomerDao customerDao;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody @Valid Customer customer){
        customerDao.save(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable("id") Customer customer){
        return customer;
    }

    @GetMapping("/")
    @ResponseBody
    public Page<Customer> findAllCustomer(Pageable page){
        return customerDao.findAll(page);
    }
}
