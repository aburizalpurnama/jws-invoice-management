package com.rizalpurnama.controller;

import com.rizalpurnama.dto.LoginFormDto;
import com.rizalpurnama.dto.RegisterFormDto;
import com.rizalpurnama.entity.User;
import com.rizalpurnama.exception.LoginFailException;
import com.rizalpurnama.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import javax.print.attribute.standard.PresentationDirection;
import javax.validation.Valid;

@Slf4j
@Controller
public class LoginController {

    @Autowired private LoginService loginService;

    @GetMapping("/login")
    public ModelMap displayFormLogin(){
        return new ModelMap()
                .addAttribute("loginFormDto", new LoginFormDto());
    }

    @PostMapping("/login")
    public void posesLogin(@ModelAttribute @Valid LoginFormDto loginFormDto, BindingResult errors, SessionStatus status){

        String username = loginFormDto.getUsername();
        String password = loginFormDto.getUsername();
        try {
            loginService.checkUsernamePassword(username, password);
        } catch (LoginFailException e) {
            e.printStackTrace();
        }

        status.setComplete();
    }
}
