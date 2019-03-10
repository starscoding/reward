package com.smile.azxx.web.domainMng;

import com.smile.azxx.entity.DomainInfo;
import com.smile.azxx.service.domainMng.DomainMngService;
import com.smile.azxx.web.common.BaseController;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by smile on 2018/5/14.
 */
@Controller
@RequestMapping(value = "domainMng")
public class DomainMngController extends BaseController{

    @Autowired
    private DomainMngService domainMngService;

    @RequestMapping(value = "getAllDomain")
    public void getAllDomain(HttpServletRequest request, HttpServletResponse response){
        try {
            List<DomainInfo> result = domainMngService.getAllDomain();
            this.addResultInfo(SUCCESS,"获取成功！",result);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"获取失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "updateDomain")
    public void updateDomain(HttpServletRequest request, HttpServletResponse response){
        String domain = request.getParameter("domain");
        String status = request.getParameter("status");
        log.info("domain : "+domain+",status:"+status);
        try {
            String msg = "";
            if(StringUtils.isNotBlank(domain)&&StringUtils.isNotBlank(status)){
                domainMngService.updateDomain(new DomainInfo(domain,status));
            }else{
                msg = "缺少必要的参数！";
            }
            if(StringUtils.isBlank(msg)){
                this.addResultInfo(SUCCESS,"获取成功！",null);
                this.responseResult(response,this.getResultJSONStr());
            }else{
                this.addResultInfo(FAILURE,msg,null);
                this.responseResult(response,this.getResultJSONStr());
            }
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"获取失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "addDomain")
    public void addDomain(HttpServletRequest request, HttpServletResponse response){
        String domain = request.getParameter("domain");
        String status = request.getParameter("status");
        log.info("domain : "+domain+",status:"+status);
        try {
            String msg = "";
            if(StringUtils.isNotBlank(domain)&&StringUtils.isNotBlank(status)){
                domainMngService.addDomain(new DomainInfo(domain,status));
            }else{
                msg = "缺少必要的参数！";
            }
            if(StringUtils.isBlank(msg)){
                this.addResultInfo(SUCCESS,"新增成功！",null);
                this.responseResult(response,this.getResultJSONStr());
            }else{
                this.addResultInfo(FAILURE,msg,null);
                this.responseResult(response,this.getResultJSONStr());
            }
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"获取失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "addOrUpdateDomain")
    public void addOrUpdateDomain(HttpServletRequest request, HttpServletResponse response){
        String domain = request.getParameter("domain");
        String status = request.getParameter("status");
        String type = request.getParameter("type");
        String oldDomain = request.getParameter("oldDomain");
        log.info("domain : "+domain+",status:"+status+",type:"+type);
        try {
            String msg = "";
            if(StringUtils.isNotBlank(domain)&&StringUtils.isNotBlank(status)){
                if("edit".equals(type))
                    domainMngService.updateDomain(domain,oldDomain);
                else
                    domainMngService.addDomain(new DomainInfo(domain,status));
            }else{
                msg = "缺少必要的参数！";
            }
            if(StringUtils.isBlank(msg)){
                if("edit".equals(type)){
                    msg = "修改成功！";
                }else{
                    msg = "新增成功！";
                }
                this.addResultInfo(SUCCESS,msg,null);
                this.responseResult(response,this.getResultJSONStr());
            }else{
                this.addResultInfo(FAILURE,msg,null);
                this.responseResult(response,this.getResultJSONStr());
            }
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"新增/更新失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "deleteDomain")
    public void deleteDomain(HttpServletRequest request, HttpServletResponse response){
        String[] ids = request.getParameterValues("ids[]");
        log.info("ids : "+ids);
        try {
            String msg = "";
            if(ArrayUtils.isNotEmpty(ids)){
                domainMngService.deleteDomain(ids);
            }else{
                msg = "参数缺失！";
            }
            if(StringUtils.isBlank(msg)){
                this.addResultInfo(SUCCESS,"删除成功！",null);
                this.responseResult(response,this.getResultJSONStr());
            }else{
                this.addResultInfo(FAILURE,msg,null);
                this.responseResult(response,this.getResultJSONStr());
            }
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"获取失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "setDomainUse")
    public void setDomainUse(HttpServletRequest request,HttpServletResponse response){
        String domain = request.getParameter("domain");
        try {
            domainMngService.setDomainUse(domain);
            this.addResultInfo(SUCCESS,"设置启用成功！",null);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"设置启用失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }
    @RequestMapping(value = "banDomain")
    public void banDomain(HttpServletRequest request,HttpServletResponse response){
        String[] ids = request.getParameterValues("ids[]");
        try {
            domainMngService.banDomains(ids);
            this.addResultInfo(SUCCESS,"设置禁用成功！",null);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"设置禁用失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

}
