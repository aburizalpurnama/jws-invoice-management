package com.rizalpurnama.dao;

import com.rizalpurnama.entity.RunningNumber;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.LockModeType;

public interface RunningNumberDao extends CrudRepository<RunningNumber, String> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    RunningNumber findByPrefix(String prefix);
}
