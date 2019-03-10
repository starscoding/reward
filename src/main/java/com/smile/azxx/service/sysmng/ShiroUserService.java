package com.smile.azxx.service.sysmng;

import com.smile.azxx.entity.sysmng.User;

import java.util.List;

/**
 * Created by Smile on 2018/6/8.
 */
public interface ShiroUserService {

    User getUser(String id);

    List<String> getRoles(String userName);

    List<String> getResource(List<String> roles);

    User getUserByName(String userName);

    List<User> getAllUsers();
}
