package com.rizalpurnama.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class HomeController {

    @PreAuthorize("hasAnyAuthority('VIEW_TRANSAKSI','EDIT_TRANSAKSI')")
    @GetMapping("/home")
    public void home(Authentication currentUser){
        log.info("Jenis class authentication : {}", currentUser.getClass().getSimpleName());
        log.info("User yang sedang login : {}", currentUser.getPrincipal());
    }
}
