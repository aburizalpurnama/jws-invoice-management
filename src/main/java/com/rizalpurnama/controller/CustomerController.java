package com.rizalpurnama.controller;

import com.rizalpurnama.dao.CustomerDao;
import com.rizalpurnama.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller @RequestMapping("/customer")
public class CustomerController {

    @Autowired private CustomerDao customerDao;

    @GetMapping("/list")
    public ModelMap dataCustomer(Pageable pageable){
        return new ModelMap()
                .addAttribute("dataCustomer", customerDao.findAll(pageable));
    }

    @GetMapping("/form")
    public ModelMap displayForm(@RequestParam("id") Customer customer){
        if (customer == null){
            customer = new Customer();
        }
        return new ModelMap()
                .addAttribute("customer", customer);
    }

    @PostMapping("/form")
    public String proccessForm(@ModelAttribute @Valid Customer customer, BindingResult errors){

        if (errors.hasErrors()){
            return "customer/form";
        }
        customerDao.save(customer);
        return "redirect:list";
    }
}
