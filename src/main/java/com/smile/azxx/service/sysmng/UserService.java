package com.smile.azxx.service.sysmng;

import com.smile.azxx.dao.sysmng.UserDao;
import com.smile.azxx.dao.sysmng.UserRoleDao;
import com.smile.azxx.entity.sysmng.User;
import com.smile.azxx.entity.sysmng.UserRole;
import com.smile.azxx.entity.sysmng.UserRolePK;
import com.smile.azxx.service.common.BaseService;
import com.smile.azxx.util.ListUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smile on 2018/4/6.
 */
@Service
@Transactional
public class UserService extends BaseService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    public User getUser(String id){
        return  userDao.findOne(id);
    }

    public List<String> getRoles(String userId){
        return userDao.getRoles(userId);
    }

    public List<String> getRolesByUserName(String userName){
        return userDao.getRolesByUserName(userName);
    }
    public List<String> getResource(List<String> roles){
        List<String> result = new ArrayList<>();
        for (String roleId: roles) {
            List<String> temp = userDao.getResource(roleId);
            if(ListUtil.isNotBlank(temp)){
                result.addAll(temp);
            }
        }
        return result;
    }
    public List<String> getResourceByRoleName(List<String> roles){
        List<String> result = new ArrayList<>();
        for (String roleName: roles) {
            List<String> temp = userDao.getResourceByRoleName(roleName);
            if(ListUtil.isNotBlank(temp)){
                result.addAll(temp);
            }
        }
        return result;
    }

    public User getUserByName(String userName){
        return userDao.getUserByName(userName);
    }

    public void save(User user){
        userDao.save(user);
        if(StringUtils.isNotBlank(user.getType())){
            UserRole userRole = new UserRole();
            userRole.setRoleId(user.getType());
            userRole.setUserId(user.getId());
            userRoleDao.save(userRole);
        }

    }

    public List<User> getAllUsers(){
        return userDao.findAll();
    }

    public void delUsers(String[] ids){
        for (int i = 0; i < ids.length; i++) {
            User user = userDao.findOne(ids[i]);
            List<UserRolePK> pks = userRoleDao.getUserRoleByUserId(user.getId());
            if(ListUtil.isNotBlank(pks)){
                for (int j = 0; j < pks.size(); j++) {
                    userRoleDao.delete(pks.get(j));
                }
            }

            userDao.delete(user);
        }
    }

    public void updateUser(User user,String oldRoleId,boolean updateUserRole){
        if(updateUserRole){
            userRoleDao.delete(new UserRolePK(user.getId(),oldRoleId));
            userRoleDao.save(new UserRole(user.getId(),user.getType()));
        }
        userDao.update(user);
    }

    public void updateContact(String wechat,String qq,String userName){
        userDao.updateContact(wechat,qq,userName);
    }

    public String alterPwd(String oldPwd,String newPwd,String userName){
        String msg = "";
        //1、判断旧密码是否正确
        User user = userDao.getUserByName(userName);
        if(!oldPwd.equals(user.getPassword())){
            //1.1 密码错误，返回密码错误
            msg = "密码错误，请确认旧密码！";
        }else{
            //1.2 密码正确，修改密码
            userDao.alterPwd(newPwd,userName);
        }
        return msg;
    }

    public String alterFetchPwd(String oldPwd,String newPwd,String userName){
        String msg = "";
        //1、判断旧密码是否正确
        User user = userDao.getUserByName(userName);
        if(!oldPwd.equals(user.getFetchPwd())){
            //1.1 密码错误，返回密码错误
            msg = "提取密码错误，请确认原提取密码！";
        }else{
            //1.2 密码正确，修改密码
            userDao.alterFetchPwd(newPwd,userName);
        }
        return msg;
    }

    public List<User> getUserByPName(String userName){
        return userDao.getUserByPName(userName);
    }

    public boolean hasUserName(String userName){
        boolean result = false;
        List<User> users = userDao.getUsers(userName);
        if(CollectionUtils.isNotEmpty(users))
            result = true;
        return result;
    }

    public void delUserByNames(String[] names){
        if (ArrayUtils.isNotEmpty(names)) {
            userRoleDao.delUserRoleByUname(names);
            userDao.delUserByNames(names);
        }
    }
}
