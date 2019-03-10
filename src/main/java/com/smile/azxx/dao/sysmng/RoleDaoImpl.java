package com.smile.azxx.dao.sysmng;

import com.smile.azxx.entity.sysmng.Role;
import com.smile.azxx.jpa.DaoSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by smile on 2018/4/12.
 */
@Component
public class RoleDaoImpl extends DaoSupport<Role> implements RoleDao{

    @PersistenceContext
    public EntityManager em;
}
