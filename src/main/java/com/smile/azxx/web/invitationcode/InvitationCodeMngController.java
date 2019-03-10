package com.smile.azxx.web.invitationcode;

import com.smile.azxx.entity.InvitationCodeInfo;
import com.smile.azxx.entity.sysmng.User;
import com.smile.azxx.service.invitationcode.InvitationCodeService;
import com.smile.azxx.util.NumberValidationUtils;
import com.smile.azxx.web.common.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by smile on 2018/4/26.
 */
@Controller
@RequestMapping(value = "invitationCode")
public class InvitationCodeMngController extends BaseController{

    @Autowired
    private InvitationCodeService invitationCodeService;

    @RequestMapping(value = "createCode")
    public void createCode(HttpServletRequest request, HttpServletResponse response){
        User user = getUser();
        try {
            invitationCodeService.createInvitationCode(15,user.getUsername());
            this.addResultInfo(SUCCESS,"创建成功！",null);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"创建失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "getCodesByStatus")
    public void getCodesByStatus(HttpServletRequest request, HttpServletResponse response){
        String status = request.getParameter("status");
        User user = getUser();
        try {
            List<InvitationCodeInfo> result = invitationCodeService.getCodesByStatus(status,user.getUsername());
            this.addResultInfo(SUCCESS,"查询邀请码成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询邀请码失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "hasUsed")
    public void hasUsed(HttpServletRequest request, HttpServletResponse response){
        String code = request.getParameter("code");
        try {
            boolean result = invitationCodeService.hasUsed(code);
            this.addResultInfo(SUCCESS,"查询邀请码是否使用成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询邀请码是否使用失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "buyInviteCode")
    public void buyInviteCode(HttpServletRequest request, HttpServletResponse response){
        User user = this.getUser();
        String num  = request.getParameter("num");
        String msg = "";
        try {
            if(StringUtils.isBlank(num)||user==null||StringUtils.isBlank(user.getUsername())){
                msg  = "缺少必要的参数！";
            }else if(!NumberValidationUtils.isPositiveInteger(num)){
                msg  = "请输入正确的数量！";
            }else{
                msg = invitationCodeService.buyInviteCode(user.getUsername(),num);
            }
            if(StringUtils.isBlank(msg)){
                this.addResultInfo(SUCCESS,"购买邀请码成功！",null);
                this.responseResult(response,this.getResultJSONStr());
            }else{
                this.addResultInfo(FAILURE,msg,null);
                this.responseResult(response,this.getResultJSONStr());
            }
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询邀请码是否使用失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }
}
