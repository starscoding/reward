package com.smile.azxx.web.withdrawMng;

import com.smile.azxx.entity.WithdrawInfo;
import com.smile.azxx.entity.sysmng.User;
import com.smile.azxx.service.sysconfig.SysConfService;
import com.smile.azxx.service.withdrawmng.WithdrawService;
import com.smile.azxx.util.NumberValidationUtils;
import com.smile.azxx.util.RandomGUID;
import com.smile.azxx.web.common.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by smile on 2018/5/5.
 */
@Controller
@RequestMapping(value = "withdrawmng")
public class WithdrawController extends BaseController{

    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private SysConfService sysConfService;

    @RequestMapping(value = "applyForWithdraw")
    public void applyForWithdraw(@RequestParam MultipartFile file,
                                 HttpServletRequest request, HttpServletResponse response){
        User user = this.getUser();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String alipayAccount = request.getParameter("alipayAccount");
        String name = request.getParameter("name");
        String amount = request.getParameter("amount");
        String fethpwd = request.getParameter("fethpwd");
        String id = new RandomGUID().getUUID32();
        String msg = "";
        String fileName = null;
        String filePath = null;
        DecimalFormat df = new DecimalFormat("#.##");
        fileName = file.getOriginalFilename();
        BigDecimal fileSize =new BigDecimal(file.getSize()).divide(new BigDecimal(1024));
        log.info("文件名："+file.getOriginalFilename()+"文件大小:"+df.format(fileSize)+"文件类型："+file.getContentType());

        try {
            String balance = withdrawService.countBalance(user.getUsername());
            String pattern = "^[0-9]*[1-9][0-9]*$";
            boolean isMatch = Pattern.matches(pattern,amount);
            if(StringUtils.isBlank(amount)){
                msg = "请输入提现金额！";
            }else if(!NumberValidationUtils.isPositiveInteger(amount)){
                msg = "提现金额请输入正整数！";
            }else if(Integer.parseInt(amount)<100){
                msg = "提现金额不得少于100！";
            }else if(!fethpwd.equals(user.getFetchPwd())){
                msg = "提现密码错误！";
            }else if(new BigDecimal(balance).compareTo(new BigDecimal(amount))==-1){
                msg = "余额不足！";
            }else if(StringUtils.isBlank(fileName)){
                msg = "请上传收款二维码图片！";
            }else if(StringUtils.isBlank(fileName)){
                msg = "请上传收款二维码图片！";
            }else if(fileSize.compareTo(new BigDecimal("500"))>-1){
                msg = "图片大小不能超过500KB!";
            }else{
//                ConfInfo conf = sysConfService.getConfByName("codeImgPath");
                this.saveFile(file.getInputStream(), request.getSession().getServletContext().getRealPath("")+"/data/codeImg/", id);
                WithdrawInfo withdrawInfo = new WithdrawInfo();
                withdrawInfo.setId(id);
                withdrawInfo.setName(user.getUsername());
                withdrawInfo.setAlipayaccount(alipayAccount);
                withdrawInfo.setFullName(name);
                withdrawInfo.setTime(sdf.format(new Date()));
                withdrawInfo.setOpStatus("0");
                withdrawInfo.setAmount(new BigDecimal(amount));
                withdrawInfo.setUrl("/data/codeImg/"+id);
                withdrawService.add(withdrawInfo);
            }
            if(StringUtils.isNotBlank(msg)){
                this.addResultInfo(FAILURE,msg,null);
                this.responseResult(response,this.getResultJSONStr());
            }else{
                this.addResultInfo(SUCCESS,"提现申请成功",null);
                this.responseResult(response,this.getResultJSONStr());
            }

        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"提现申请失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "getWithdrawInfo")
    public void getWithdrawInfo(HttpServletRequest request,HttpServletResponse response){
        User user = this.getUser();
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        try {
            List<WithdrawInfo> result = withdrawService.getWithdrawInfo(user.getUsername(),startTime,endTime);
            this.addResultInfo(SUCCESS,"查询成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }
    @RequestMapping(value = "getWithdrawByName")
    public void getWithdrawByName(HttpServletRequest request,HttpServletResponse response){
        String userName = request.getParameter("userName");
        try {
            List<WithdrawInfo> result = withdrawService.getWithdrawByName(userName);
            this.addResultInfo(SUCCESS,"查询成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }
    @RequestMapping(value = "getWithdrawByPName")
    public void getWithdrawByPName(HttpServletRequest request,HttpServletResponse response){
        String userName = request.getParameter("userName");
        try {
            List<WithdrawInfo> result = withdrawService.getWithdrawByPName(userName);
            this.addResultInfo(SUCCESS,"查询成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }
    @RequestMapping(value = "countBalance")
    public void countBalance(HttpServletRequest request,HttpServletResponse response){
        User user = this.getUser();
        try {
            String result = withdrawService.countBalance(user.getUsername());
            this.addResultInfo(SUCCESS,"查询成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }
    @RequestMapping(value = "updateWithdrawStatus")
    public void updateWithdrawStatus(HttpServletRequest request,HttpServletResponse response){
        User user = this.getUser();
        String[] ids = request.getParameterValues("ids[]");
        String status = request.getParameter("status");
        try {
            withdrawService.updateWithdrawStatus(ids,status);
            this.addResultInfo(SUCCESS,"查询成功！",null);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"查询失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "countWithdrawAmount")
    public void countWithdrawAmount(HttpServletRequest request,HttpServletResponse response){
        String date = request.getParameter("date");
        String isSuccess = request.getParameter("isSuccess");
        try {
            String result = "0";
            if("0".equals(isSuccess)){
                result = withdrawService.countWithdrawAmount(date,true);
            }else{
                result = withdrawService.countWithdrawAmount(date,false);
            }
            this.addResultInfo(SUCCESS,"获取成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"获取失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

}
