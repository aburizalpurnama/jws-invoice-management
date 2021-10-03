package com.rizalpurnama.service;

import com.rizalpurnama.dao.ResetPasswordDao;
import com.rizalpurnama.dao.UserDao;
import com.rizalpurnama.dao.UserPasswordDao;
import com.rizalpurnama.dto.RegisterFormDto;
import com.rizalpurnama.entity.ResetPassword;
import com.rizalpurnama.entity.Role;
import com.rizalpurnama.entity.User;
import com.rizalpurnama.entity.UserPassword;
import com.rizalpurnama.exception.ResetPasswordInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service @Transactional
public class UserService {

    private final static String ROLE_ID_NEW_USER = "role_new_user";

    @Autowired private ResetPasswordDao resetPasswordDao;
    @Autowired private UserDao userDao;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserPasswordDao userPasswordDao;

    public String register(RegisterFormDto data){
        // insert ke tabel user dengan role staff (nanti diganti)
        User newUser = new User();
        newUser.setUsername(data.getEmail());
        newUser.setActive(false);

        Role newUserRole = new Role();
        newUserRole.setId(ROLE_ID_NEW_USER);

        newUser.setRole(newUserRole);
        userDao.save(newUser);


        // insert ke tabel reset password
        ResetPassword rp = new ResetPassword();
        rp.setUser(newUser);
        rp.setUniqueCode(UUID.randomUUID().toString());
        resetPasswordDao.save(rp);
        return rp.getUniqueCode();
        // Kirim email ke user baru dengan link untuk reset password
    }

    public void setPassword(String newPassword){

    }

    public void verifyEmail(String uniqueCode) throws ResetPasswordInvalidException {
        ResetPassword rp = resetPasswordDao.findByUniqueCode(uniqueCode)
                .orElseThrow(() -> new ResetPasswordInvalidException("UniqueCode tidak ada"));

        User user = rp.getUser();
        user.setActive(true);
        userDao.save(user);
    }

    public User verifyResetPasswordLink(String uniqueCode) throws ResetPasswordInvalidException{
        Optional<ResetPassword> orp = resetPasswordDao.findByUniqueCode(uniqueCode);
        ResetPassword rp = orp.orElseThrow(() -> new ResetPasswordInvalidException("UniqueCode tidak ada"));
        if (LocalDateTime.now().isAfter(rp.getExpired())){
            throw new ResetPasswordInvalidException("Unique code " + uniqueCode + "sudah expired");
        }
        log.info("verifiyResetPasswordLink() : id user {}", rp.getUser().getId());
        return rp.getUser();
    }

    public void setNewPassword(User user, String password) {
        log.info("setNewPassword() started");
        UserPassword userPassword = userPasswordDao.findByUser(user);
        log.info("complete findByUser(user)");
        if (userPassword == null){
            log.info("user == null");
            userPassword = new UserPassword();
            userPassword.setUser(user);
            log.info("'inside if' user:{}", userPassword.getUser());
        }

        log.info("'outside if' user:{}", userPassword.getUser());
        String newPassword = passwordEncoder.encode(password);
        userPassword.setPassword(newPassword);
        userPasswordDao.save(userPassword);
        log.info("setNewPassword() : berhasil menyimpan user password ["+user.getId()+","+newPassword+"]");

    }
}
