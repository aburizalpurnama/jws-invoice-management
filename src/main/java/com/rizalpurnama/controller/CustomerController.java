package com.rizalpurnama.controller;

import com.rizalpurnama.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @RequestMapping("/customer")
public class CustomerController {

    @Autowired private CustomerDao customerDao;

    @GetMapping("/list")
    public ModelMap dataCustomer(Pageable pageable){
        return new ModelMap()
                .addAttribute("dataCustomer", customerDao.findAll(pageable));
    }
}
