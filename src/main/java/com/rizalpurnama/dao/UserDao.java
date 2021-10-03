package com.rizalpurnama.dao;

import com.rizalpurnama.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserDao extends PagingAndSortingRepository<User, String> {
    Optional<User> findByUsername(String username);
}
