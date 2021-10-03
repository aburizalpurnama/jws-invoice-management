package com.rizalpurnama.service;

import com.rizalpurnama.dao.UserDao;
import com.rizalpurnama.dao.UserPasswordDao;
import com.rizalpurnama.entity.User;
import com.rizalpurnama.entity.UserPassword;
import com.rizalpurnama.exception.LoginFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired UserDao userDao;
    @Autowired UserPasswordDao userPasswordDao;
    @Autowired private PasswordEncoder passwordEncoder;

    public boolean checkUsernamePassword(String username, String password) throws LoginFailException {
        Optional<User> optUser = userDao.findByUsername(username);
        User user = optUser.orElseThrow(() -> new LoginFailException("Username salah"));
        UserPassword userPassword = userPasswordDao.findByUser(user);
        String hashedPassword = passwordEncoder.encode(password);

        if (userPassword.getPassword() != hashedPassword) {
            throw new LoginFailException("Password salah");
        }

        return true;
    }
}
