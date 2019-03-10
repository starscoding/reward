package com.smile.azxx.shiro;

import com.smile.azxx.dao.sysmng.UserDao;
import com.smile.azxx.entity.sysmng.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SysUserFilter extends PathMatchingFilter {

    @Inject
    private UserDao userDao;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userDao.getUserByName(username);
        request.setAttribute(Constants.CURRENT_USER, user);
        return true;
    }
}