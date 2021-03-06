package com.rizalpurnama.dao;

import com.rizalpurnama.entity.ResetPassword;
import com.rizalpurnama.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ResetPasswordDao extends CrudRepository<ResetPassword, String> {

    Optional<ResetPassword>findByUniqueCode(String uniqueCode);

    void deleteByUser(User user);
}
