package com.smile.azxx.web.sysmng;

import com.smile.azxx.entity.sysmng.User;
import com.smile.azxx.service.sysmng.UserService;
import com.smile.azxx.util.RandomGUID;
import com.smile.azxx.web.common.BaseController;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by smile on 2018/4/7.
 */
@Controller
@RequestMapping(value = "/userService")
public class UserController extends BaseController {

    public Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getAllUsers")
    public void getAllUsers(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<User> result = userService.getAllUsers();
            this.addResultInfo(SUCCESS, "查询成功！", result);
            this.responseResult(response, this.getResultJSONStr());
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, "查询成功！", null);
            this.responseResult(response, this.getResultJSONStr());
        }

    }

    @RequestMapping(value = "/addOrUpdateUser")
    public void addOrUpdateUser(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        //验证验证码
        //预防xss攻击
        //保存用户信息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String fetchPwd = request.getParameter("fetchPwd");
        String userType = request.getParameter("type");
        String wechat = request.getParameter("wechat");
        String qq = request.getParameter("qq");
        String createTime = sdf.format(new Date());
        String reqType = request.getParameter("reqType");//请求类型
        String id = request.getParameter("id");
        User cUser = getUser();
        User user = null;
        boolean updateUserRole = false;
        String oldRoleId = "";
        if (StringUtils.isNotBlank(reqType) && "update".equals(reqType)) {
            user = userService.getUser(id);
            if (!user.getType().equals(userType)) {
                updateUserRole = true;
                oldRoleId = user.getType();
            }
            user.setPassword(password);
            user.setUsername(userName);
            user.setWechat(wechat);
            user.setType(userType);
            user.setFetchPwd(fetchPwd);
            user.setQq(qq);
            user.setParentName(cUser.getUsername());
        } else {
            user = new User();
            RandomGUID uuids = new RandomGUID();
            user.setId(uuids.getUUID32());
            user.setPassword(password);
            user.setUsername(userName);
            user.setWechat(wechat);
            user.setType(userType);
            user.setFetchPwd(fetchPwd);
            user.setQq(qq);
            user.setParentName(cUser.getUsername());
            user.setCreateTime(createTime);
        }
        try {
            if (StringUtils.isNotBlank(reqType) && "update".equals(reqType)) {
                userService.updateUser(user, oldRoleId, updateUserRole);
            } else {
                userService.save(user);
            }

            this.addResultInfo(SUCCESS, "添加用户成功！", true);
            this.responseResult(response, this.getResultJSONStr());

        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, "添加用户失败！", null);
            this.responseResult(response, this.getResultJSONStr());
        }

    }

    @RequestMapping(value = "/delUsers")
    public void delUsers(HttpServletRequest request, HttpServletResponse response) {
        String userIds = request.getParameter("userIds");
        String message = "删除用户数据失败！";
        logger.info(userIds);
        try {
            if (StringUtils.isBlank(userIds)) {
                message = "没有要删除的数据！";
            } else {
                String[] ids = userIds.split(",");
                userService.delUsers(ids);
                message = "删除用户数据成功！";
            }
            this.addResultInfo(SUCCESS, message, null);
            this.responseResult(response, this.getResultJSONStr());
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, message, null);
            this.responseResult(response, this.getResultJSONStr());
        }

    }

    @RequestMapping(value = "/delUserByNames")
    public void delUserByNames(HttpServletRequest request, HttpServletResponse response) {
        String[] names = request.getParameterValues("names[]");
        String message = "删除用户数据失败！";
//        logger.info(userIds);
        try {
            if (ArrayUtils.isEmpty(names)) {
                message = "没有要删除的数据！";
            } else {
                userService.delUserByNames(names);
                message = "删除用户数据成功！";
            }
            this.addResultInfo(SUCCESS, message, null);
            this.responseResult(response, this.getResultJSONStr());
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, message, null);
            this.responseResult(response, this.getResultJSONStr());
        }

    }

    @RequestMapping(value = "/updateContact")
    public void updateContact(HttpServletRequest request, HttpServletResponse response) {
        String wechat = request.getParameter("wechat");
        String qq = request.getParameter("qq");
        User user = getUser();
        logger.info(wechat + "," + qq + "," + user.getUsername());
        try {
            userService.updateContact(wechat, qq, user.getUsername());
            this.addResultInfo(SUCCESS, "更新联系方式成功！", null);
            this.responseResult(response, this.getResultJSONStr());
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, "更新联系方式失败！", null);
            this.responseResult(response, this.getResultJSONStr());
        }

    }

    @RequestMapping(value = "/alterPwd")
    public void alterPwd(HttpServletRequest request, HttpServletResponse response) {
        String oldPwd = request.getParameter("oldPwd");
        String newPwd = request.getParameter("newPwd");
        User user = getUser();
        String message = "";
        logger.info(oldPwd + "," + newPwd + "," + user.getUsername());
        try {
            message = userService.alterPwd(oldPwd, newPwd, user.getUsername());
            if (StringUtils.isBlank(message)) {
                this.addResultInfo(SUCCESS, "修改密码成功！", null);
                this.responseResult(response, this.getResultJSONStr());
            } else {
                this.addResultInfo(FAILURE, message, null);
                this.responseResult(response, this.getResultJSONStr());
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, "修改密码失败！", null);
            this.responseResult(response, this.getResultJSONStr());
        }
    }
    @RequestMapping(value = "/alterFetchPwd")
    public void alterFetchPwd(HttpServletRequest request, HttpServletResponse response) {
        String oldPwd = request.getParameter("oldPwd");
        String newPwd = request.getParameter("newPwd");
        User user = getUser();
        String message = "";
        logger.info(oldPwd + "," + newPwd + "," + user.getUsername());
        try {
            message = userService.alterFetchPwd(oldPwd, newPwd, user.getUsername());
            if (StringUtils.isBlank(message)) {
                this.addResultInfo(SUCCESS, "修改提取密码成功！", null);
                this.responseResult(response, this.getResultJSONStr());
            } else {
                this.addResultInfo(FAILURE, message, null);
                this.responseResult(response, this.getResultJSONStr());
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, "修改提取密码失败！", null);
            this.responseResult(response, this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "/getUserInfo")
    public void getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        User user = getUser();
        logger.info(user.getUsername());
        try {
            user = userService.getUserByName(user.getUsername());
            this.addResultInfo(SUCCESS, "获取用户信息成功！", user);
            this.responseResult(response, this.getResultJSONStr());
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, "获取用户信息失败！", null);
            this.responseResult(response, this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "/getUserByPName")
    public void getUserByPName(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        logger.info(userName);
        try {
            List<User> result = userService.getUserByPName(userName);
            this.addResultInfo(SUCCESS, "获取用户信息成功！", result);
            this.responseResult(response, this.getResultJSONStr());
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, "获取用户信息失败！", null);
            this.responseResult(response, this.getResultJSONStr());
        }
    }
}
