package com.smile.azxx.dao.DomainMng;

import com.smile.azxx.entity.DomainInfo;
import com.smile.azxx.jpa.Dao;

import java.util.List;

/**
 * Created by smile on 2018/5/14.
 */
public interface DomainDao extends Dao<DomainInfo>{

    List<DomainInfo> getUsingDomain();
}
