package com.smile.azxx.web.sysmng;

import com.smile.azxx.entity.sysmng.Resource;
import com.smile.azxx.entity.sysmng.User;
import com.smile.azxx.service.invitationcode.InvitationCodeService;
import com.smile.azxx.service.sysmng.ResourceService;
import com.smile.azxx.service.sysmng.UserService;
import com.smile.azxx.shiro.Constants;
import com.smile.azxx.util.RandomGUID;
import com.smile.azxx.util.ValidateCodeUtil;
import com.smile.azxx.web.common.BaseController;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by smile on 2018/4/7.
 */
@Controller
@RequestMapping
public class LoginController extends BaseController{
    public Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private InvitationCodeService invitationCodeService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "/pages/login.jsp";
    }
    //用户登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpSession session,
                        HttpServletRequest request,
                        HttpServletResponse response,
                        @RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String username,
                        @RequestParam(FormAuthenticationFilter.DEFAULT_PASSWORD_PARAM) String password,
                        Model model) {

        String authCode = request.getParameter("verifyCode");
        String verifyCode = (String)request.getSession().getAttribute(Constants.SIMPLE_CAPCHA_SESSION_KEY);

        if(StringUtils.isNotBlank(verifyCode)){
            String[] codes = verifyCode.split("&&");
            String code = codes[0];
            Long expiredTime = Long.valueOf(codes[1]);
            Long nowTime = Calendar.getInstance().getTimeInMillis();
            if(code.toLowerCase().equals(authCode.toLowerCase())){
                if(expiredTime<nowTime){
                    model.addAttribute("message","验证码已失效，请重新输入！");
                    model.addAttribute("verifyImageFlagCode", "1");
                    return "login";
                }
            }else {
                model.addAttribute("message", "验证码输入错误！");
                model.addAttribute("verifyImageFlagCode", "1");
                return "login";
            }
        }

        Subject subject = SecurityUtils.getSubject();
        logger.info(username+","+password);
        if(StringUtils.isNotBlank(password)){
//            password = CommonUtil.encodePassword(password);
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try{
            subject.login(token);//会跳到我们自定义的realm中
            return "redirect:/pages/main.jsp";
        }catch(Exception e){
            if (e instanceof UnknownAccountException) {
                model.addAttribute("message", "账户不存在,请重试");
            }else if (e instanceof IncorrectCredentialsException) {
                model.addAttribute("message", "身份验证错误");
            }else if (e instanceof LockedAccountException) {
                model.addAttribute("message", e.getMessage());
            } else if (e instanceof DisabledAccountException) {
                model.addAttribute("message", "您的账户已被停用,请联系管理员");
            } else if (e instanceof AuthenticationException) {
                model.addAttribute("message", "登录失败,请重试");
            } else {
//                e.printStackTrace();
            }
            request.setAttribute("error", "用户名或密码错误！");
            return "login";
        }
    }

    @RequestMapping(value = "/register")
    public String register(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                         Model model){

        String errorpage = "register";
        String succPage = "login";

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fetchPwd = request.getParameter("fetchPwd");
        //验证码验证
        String authCode = request.getParameter("verifyCode");
        String verifyCode = (String)request.getSession().getAttribute(Constants.SIMPLE_CAPCHA_SESSION_KEY);

        if(StringUtils.isNotBlank(verifyCode)){
            String[] codes = verifyCode.split("&&");
            String code = codes[0];
            Long expiredTime = Long.valueOf(codes[1]);
            Long nowTime = Calendar.getInstance().getTimeInMillis();
            if(code.toLowerCase().equals(authCode.toLowerCase())){
                if(expiredTime<nowTime){
                    model.addAttribute("message","验证码已失效，请重新输入！");
                    model.addAttribute("verifyImageFlagCode", "1");
                    return errorpage;
                }
            }else {
                model.addAttribute("message", "验证码输入错误！");
                model.addAttribute("verifyImageFlagCode", "1");
                return errorpage;
            }
        }
        if(StringUtils.isBlank(username)){
            model.addAttribute("message", "请输入用户名！");
            model.addAttribute("verifyImageFlagCode", "1");
            return errorpage;
        }else if(hasSpecialChar(username)){
            model.addAttribute("message", "用户名中不能有特殊字符！");
            model.addAttribute("verifyImageFlagCode", "1");
            return errorpage;
        }
        if(userService.hasUserName(username)){
            model.addAttribute("message", "用户名已存在！");
            model.addAttribute("verifyImageFlagCode", "1");
            return errorpage;
        }
        if(StringUtils.isBlank(password)){
            model.addAttribute("message", "密码不能为空！");
            model.addAttribute("verifyImageFlagCode", "1");
            return errorpage;
        }
        if(StringUtils.isBlank(fetchPwd)){
            model.addAttribute("message", "提现密码不能为空！");
            model.addAttribute("verifyImageFlagCode", "1");
            return errorpage;
        }

        //邀请码验证
        String invitionCode = request.getParameter("invitionCode");
        boolean codeHasUsed = invitationCodeService.hasUsed(invitionCode);
        if(codeHasUsed){
            model.addAttribute("message", "邀请码已使用或不存在！");
            model.addAttribute("verifyImageFlagCode", "1");
            return errorpage;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String userName = request.getParameter("username");
//        String invitionCode = request.getParameter("invitionCode");

        String createTime = sdf.format(new Date());
        User user = new User();
        RandomGUID uuids = new RandomGUID();
        user.setId(uuids.getUUID32());
//        user.setPassword(CommonUtil.encodePassword(password));
        user.setPassword(password);
        user.setUsername(userName);
        user.setInvitationCode(invitionCode);
        user.setType("4BE44AC464FFCE79E15B1DF31F9DAB27");
        user.setFetchPwd(fetchPwd);
        user.setCreateTime(createTime);

        try {
            //更新邀请码信息
            invitationCodeService.updateInvitationCodeInfo(user.getUsername(),"1",invitionCode);
            //新增用户信息
            userService.save(user);
            model.addAttribute("message", "注册成功，请登录！");
            model.addAttribute("verifyImageFlagCode", "1");
            return succPage;
//            this.addResultInfo(SUCCESS,"注册成功！",null);
//            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("message", "注册失败！");
            model.addAttribute("verifyImageFlagCode", "1");
            return errorpage;
//            this.addResultInfo(FAILURE,"注册成功！",null);
//            this.responseResult(response,this.getResultJSONStr());
        }

    }

    /**
     * 获取验证码
     */
    @RequestMapping(value="/getVerifyImage",method = RequestMethod.GET)
    public void getCode(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        ValidateCodeUtil.getRandcode(request, response);
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "index";
    }

    @RequestMapping("getMenu")
    public void getMenu(HttpServletRequest request,
                            HttpServletResponse responset){
        String userName = request.getParameter("userName");
        List<Resource> result = new ArrayList<>();
        try {
            result = resourceService.getResourcesByUser(userName);
            this.addResultInfo(SUCCESS,"查询成功！",result);
            this.responseResult(responset,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询失败！",null);
            this.responseResult(responset,this.getResultJSONStr());
        }

    }

    public boolean hasSpecialChar(String str){
        boolean result = false;
        if(StringUtils.isNotBlank(str)){
            String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(str);
            result= m.find();
        }
        return result;
    }
}
