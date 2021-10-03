package com.rizalpurnama.dao;

import com.rizalpurnama.entity.User;
import com.rizalpurnama.entity.UserPassword;
import org.springframework.data.repository.CrudRepository;

public interface UserPasswordDao extends CrudRepository<UserPassword, String> {

    UserPassword findByUser(User user);
}
