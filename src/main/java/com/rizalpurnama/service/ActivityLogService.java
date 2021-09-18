package com.rizalpurnama.service;

import com.rizalpurnama.dao.ActivityLogDao;
import com.rizalpurnama.entity.ActivityLog;
import com.rizalpurnama.entity.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivityLogService {

    @Autowired ActivityLogDao activityLogDao;

    @Transactional (propagation = Propagation.REQUIRES_NEW)
    public void log(String logMessage){

        ActivityLog log = new ActivityLog();
        log.setFeature(Feature.PAYMENT);
        log.setMessage(logMessage);
        activityLogDao.save(log);
    }
}
