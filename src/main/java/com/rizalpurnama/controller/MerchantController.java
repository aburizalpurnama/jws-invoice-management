package com.rizalpurnama.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller @RequestMapping("/merchant")
public class MerchantController {

    @GetMapping("/info/view")
    public void displayMerchatInfo(){

    }

    @GetMapping("/info/form")
    public void editMerchatInfo(){

    }

    @PostMapping("/info/form")
    public String proccessEditMerchantInfo(){
        return "redirect:view";
    }
}
