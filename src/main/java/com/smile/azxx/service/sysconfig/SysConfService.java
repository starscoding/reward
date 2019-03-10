package com.smile.azxx.service.sysconfig;

import com.smile.azxx.dao.sysconfig.SysConfDao;
import com.smile.azxx.entity.ConfInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by smile on 2018/4/21.
 */
@Service
@Transactional
public class SysConfService {

    @Autowired
    private SysConfDao sysConfDao;

    public void deleteByGroupName(String groupName) {
        sysConfDao.deleteByGroupName(groupName);
    }

    public void updateAgencyConf(List<ConfInfo> confInfos, String groupName) {
        this.deleteByGroupName(groupName);
        sysConfDao.saveIterable(confInfos);
    }

    public Map<String, String> getConfByGroupName(String groupName) {
        Map<String, String> result = new HashMap<>();
        List<ConfInfo> confs = sysConfDao.getConfByGroupName(groupName);
        if (CollectionUtils.isNotEmpty(confs)) {
            for (ConfInfo conf : confs) {
                result.put(conf.getName(), conf.getValue());
            }
        }
        return result;
    }

    public ConfInfo getConfByName(String name) {
        return sysConfDao.getConfByName(name);
    }

    public String getLDDomain(String status) {
        return sysConfDao.getLDDomain(status);
    }

}
