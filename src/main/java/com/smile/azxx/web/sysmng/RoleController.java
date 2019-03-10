package com.smile.azxx.web.sysmng;

import com.smile.azxx.entity.sysmng.Role;
import com.smile.azxx.service.sysmng.RoleService;
import com.smile.azxx.util.RandomGUID;
import com.smile.azxx.web.common.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by smile on 2018/4/12.
 */
@Controller
@RequestMapping(value = "/roleService")
public class RoleController extends BaseController{

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/addOrUpdateRole")
    public void addOrUpdateRole(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String value = request.getParameter("value");
        String desc = request.getParameter("desc");
        String reqType = request.getParameter("reqType");
        String id = request.getParameter("id");
        RandomGUID guid = new RandomGUID();
        Role role = null;
        if(StringUtils.isNotBlank(reqType)&&"update".equals(reqType)){
            role = roleService.getRole(id);
            role.setValue(value);
            role.setDescription(desc);
            role.setName(name);
        }else{
            role = new Role();
            role.setId(guid.getUUID32().replace("-",""));
            role.setName(name);
            role.setDescription(desc);
            role.setValue(value);
        }

        try {
            if(StringUtils.isNotBlank(reqType)&&"update".equals(reqType)){
                roleService.updateRole(role);
            }else{
                roleService.addRole(role);
            }
            this.addResultInfo(SUCCESS,"增加/更新角色成功！",null);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"增加/更新角色失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "/delRoles")
    public void delRoles(HttpServletRequest request, HttpServletResponse response) {
        String roleIds = request.getParameter("roleIds");
        String message = "删除角色数据失败！";
        try {
            if (StringUtils.isBlank(roleIds)) {
                message = "没有要删除的数据！";
            } else {
                String[] ids = roleIds.split(",");
                roleService.deleteRoles(ids);
                message = "删除角色数据成功！";
            }
            this.addResultInfo(SUCCESS, message, null);
            this.responseResult(response, this.getResultJSONStr());
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, message, null);
            this.responseResult(response, this.getResultJSONStr());
        }

    }

    @RequestMapping(value = "/getAllRoles")
    public void getAllRoles(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Role> result = roleService.getAllRoles();
            this.addResultInfo(SUCCESS, "查询成功！", result);
            this.responseResult(response, this.getResultJSONStr());
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, "查询成功！", null);
            this.responseResult(response, this.getResultJSONStr());
        }

    }

    @RequestMapping(value = "roleAccredit")
    public void roleAccredit(HttpServletRequest request,HttpServletResponse response){
        String[] delIds = request.getParameterValues("delResourceIds[]");
        String[] addIds = request.getParameterValues("addResourceIds[]");
        String roleId = request.getParameter("roleId");
        log.info(delIds+","+addIds+","+roleId);
        try {
            roleService.roleAccredit(delIds,addIds,roleId);
            this.addResultInfo(SUCCESS, "授权成功！", null);
            this.responseResult(response, this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE, "授权失败！", null);
            this.responseResult(response, this.getResultJSONStr());
        }
    }
}

