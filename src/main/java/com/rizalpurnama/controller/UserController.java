package com.rizalpurnama.controller;

import com.rizalpurnama.dao.ResetPasswordDao;
import com.rizalpurnama.dao.UserDao;
import com.rizalpurnama.dto.RegisterFormDto;
import com.rizalpurnama.entity.User;
import com.rizalpurnama.exception.ResetPasswordInvalidException;
import com.rizalpurnama.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
public class UserController {

    @Autowired private UserService userService;
    @Autowired private ResetPasswordDao resetPasswordDao;
    @Autowired private UserDao userDao;
    @Autowired private PasswordEncoder passwordEncoder;

    @GetMapping("/register/form")
    public ModelMap displayForm(){
        return new ModelMap()
                .addAttribute("registerFormDto",new RegisterFormDto());
    }

    @PostMapping("/register/form")
    public String prossesForm(@ModelAttribute @Valid RegisterFormDto registerFormDto, BindingResult errors, SessionStatus status){

        if(errors.hasErrors()){
            log.debug("Error : {}", errors.toString());
            return "/register/form";
        }

        log.debug("Name : {}", registerFormDto.getName());
        log.debug("Email : {}", registerFormDto.getEmail());
        log.debug("Phone Number : {}", registerFormDto.getPhone());


        String uniqueCode = userService.register(registerFormDto);

        status.setComplete();
        return "redirect:/register/verify/email?code=" + uniqueCode ;
    }

    @GetMapping("/register/success")
    public void registrationSuccess(){

    }

    @GetMapping("/register/verify/email")
    public String verifiyEmail(@RequestParam(name = "code") String uniqueCode) throws ResetPasswordInvalidException {
        userService.verifyEmail(uniqueCode);
        return "redirect:/password/reset?code="+uniqueCode;
    }

    @GetMapping("/password/reset")
    public ModelMap displayFormResetPassword(@RequestParam(name = "code") String uniqueCode){

        ModelMap modelMap = new ModelMap();
        try {
            User user = userService.verifyResetPasswordLink(uniqueCode);
            modelMap.addAttribute("user", user);
        } catch (ResetPasswordInvalidException e) {
            log.warn("Reset password code invalid : " + uniqueCode);

            modelMap.addAttribute("error", "Reset password code invalid");
        }
        log.info(" displayFormResetPassword() : {}", modelMap.getAttribute("user"));
        return modelMap;
    }

    @PostMapping("/password/reset")
    public String proccessFormResetPassword(@RequestParam User user,
                                            @RequestParam String password,
                                            @RequestParam(name = "confirm_password") String confirm){
        log.info("processing form reset password");
        if (user != null
                && StringUtils.hasText(password)
                && StringUtils.hasText(confirm)
                && password.equals(confirm)){
            userService.setNewPassword(user, password);
        } else {
            // Handle validasi, tampilkan error di reset password form
            log.error("proccessFormResetPassword() : validasi password gagal");
        }

        return "redirect:success";
    }

    @GetMapping("/password/success")
    public void successResetPassword(){

    }

    @GetMapping("/password/forgot")
    public void displayForgotPassword(){

    }

    @PostMapping("/password/forgot")
    public String proccessForgotPassword(@RequestParam String email){
        userService.forgotPassword(email);
        return "redirect:sent";
    }

    @GetMapping("/password/sent")
    public void successForgotPassword(){

    }

}
