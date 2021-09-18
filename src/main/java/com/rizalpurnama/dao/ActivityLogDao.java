package com.rizalpurnama.dao;

import com.rizalpurnama.entity.ActivityLog;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ActivityLogDao extends PagingAndSortingRepository<ActivityLog, String> {
}
