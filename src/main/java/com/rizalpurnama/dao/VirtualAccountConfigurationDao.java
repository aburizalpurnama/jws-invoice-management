package com.rizalpurnama.dao;

import com.rizalpurnama.entity.VirtualAccountConfiguration;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VirtualAccountConfigurationDao extends PagingAndSortingRepository<VirtualAccountConfiguration, String> {
}
