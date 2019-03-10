package com.smile.azxx.dao.sysmng;

import com.smile.azxx.entity.sysmng.UserRole;
import com.smile.azxx.entity.sysmng.UserRolePK;
import com.smile.azxx.jpa.DaoSupport;
import com.smile.azxx.util.ListUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by smile on 2018/4/14.
 */
@Component
public class UserRoleDaoImpl extends DaoSupport<UserRole> implements UserRoleDao{

    @PersistenceContext
    public EntityManager em;

    @Override
    public List<UserRolePK> getUserRoleByUserId(String userId) {
        List<UserRolePK> result = new ArrayList<>();
        String sql = "select * from sys_user_role where user_id=?";
        Query query = this.getEm().createNativeQuery(sql);
        query.setParameter(1,userId);
        List list = query.getResultList();
        if(ListUtil.isNotBlank(list)){
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                UserRolePK temp = new UserRolePK();
                temp.setUserId(StringUtils.isBlank(obj[0].toString())?"":obj[0].toString());
                temp.setRoleId(StringUtils.isBlank(obj[1].toString())?"":obj[1].toString());
                result.add(temp);
            }
        }
        return result;
    }

    @Override
    public List<UserRolePK> getUserRoleByRoleId(String roleId) {
        List<UserRolePK> result = new ArrayList<>();
        String sql = "select * from sys_user_role where role_id=?";
        Query query = this.getEm().createNativeQuery(sql);
        query.setParameter(1,roleId);
        List list = query.getResultList();
        if(ListUtil.isNotBlank(list)){
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                UserRolePK temp = new UserRolePK();
                temp.setUserId(StringUtils.isBlank(obj[0].toString())?"":obj[0].toString());
                temp.setRoleId(StringUtils.isBlank(obj[1].toString())?"":obj[1].toString());
                result.add(temp);
            }
        }
        return result;
    }

    @Override
    public void delUserRoleByUname(String[] names) {
        String sql = "delete from UserRole ur where ur.userId in(select  u.id from User u where u.username in ?1)";
        Query query = this.getEm().createQuery(sql);
        query.setParameter(1,Arrays.asList(names));
        query.executeUpdate();
    }


}
