package com.smile.azxx.service.domainMng;

import com.smile.azxx.dao.DomainMng.DomainDao;
import com.smile.azxx.entity.DomainInfo;
import com.smile.azxx.service.common.BaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by smile on 2018/5/14.
 */
@Service
@Transactional
public class DomainMngService extends BaseService{

    @Autowired
    private DomainDao domainDao;

    public List<DomainInfo> getAllDomain(){
        return domainDao.findAll();
    }

    public void updateDomain(DomainInfo domainInfo){
        domainDao.update(domainInfo);
    }
    public void updateDomain(String  newDomain,String oldDomain){
        domainDao.delete(oldDomain);
        domainDao.save(new DomainInfo(newDomain,"1"));
    }

    public void addDomain(DomainInfo domainInfo){
        domainDao.save(domainInfo);
    }

    public void deleteDomain(String[] ids){
        if(ArrayUtils.isNotEmpty(ids)){
            for (int i = 0; i < ids.length; i++) {
                domainDao.delete(ids[i]);
            }
        }
    }

    public void setDomainUse(String domain){
        List<DomainInfo> using = domainDao.getUsingDomain();
        if(CollectionUtils.isNotEmpty(using)){
            for (int i = 0; i < using.size(); i++) {
                using.get(i).setStatus("2");
            }
        }
        DomainInfo domainInfo = domainDao.findOne(domain);
        domainInfo.setStatus("0");
        domainDao.update(domainInfo);
    }

    public void banDomains(String[] ids){
        if(ArrayUtils.isNotEmpty(ids)){
            for (int i = 0; i < ids.length; i++) {
                DomainInfo temp = new DomainInfo(ids[i],"2");
                domainDao.update(temp);
            }
        }
    }
}
