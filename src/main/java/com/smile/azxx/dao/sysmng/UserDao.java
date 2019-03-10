package com.smile.azxx.dao.sysmng;

import com.smile.azxx.entity.sysmng.User;
import com.smile.azxx.jpa.Dao;

import java.util.List;

/**
 * Created by smile on 2018/4/6.
 */
public interface UserDao extends Dao<User> {

    User get(long id);

    List<String> getRoles(String userId);
    List<String> getRolesByUserName(String userName);

    List<String> getResource(String roleId);

    List<String> getResourceByRoleName(String roleName);

    User getUserByName(String userName);

    List<User> getUsers(String userName);

    void updateContact(String wechat, String qq, String userName);

    void alterPwd(String newPwd, String userName);

    void alterFetchPwd(String newPwd, String userName);

    List<User> getUserByPName(String userName);

    void delUserByNames(String[] userNames);
}
