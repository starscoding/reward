package com.smile.azxx.web.sysmng;

import com.smile.azxx.entity.sysmng.Resource;
import com.smile.azxx.model.TreeResource;
import com.smile.azxx.service.sysmng.ResourceService;
import com.smile.azxx.util.RandomGUID;
import com.smile.azxx.web.common.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by smile on 2018/4/13.
 */
@Controller
@RequestMapping(value = "resourceService")
public class ResourceController extends BaseController{

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "/addOrUpdateResource")
    public void addOrUpdateResource(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        String value = request.getParameter("value");
        String url = request.getParameter("url");
        String type = request.getParameter("type");
        String cls = request.getParameter("cls");
        String description = request.getParameter("description");
        String reqType = request.getParameter("reqType");
        String id = request.getParameter("id");
        String parentId = request.getParameter("parentId");
        RandomGUID guid = new RandomGUID();
        Resource resource = null;
        if(StringUtils.isNotBlank(reqType)&&"update".equals(reqType)){
            resource = resourceService.getResource(id);
            resource.setValue(value);
            resource.setDescription(description);
            resource.setName(name);
            resource.setUrl(url);
            resource.setType(type);
            resource.setCls(cls);
//            resource.setParentId(parentId);
        }else{
            resource = new Resource();
            resource.setId(guid.getUUID32().replace("-",""));
            resource.setValue(value);
            resource.setDescription(description);
            resource.setName(name);
            resource.setUrl(url);
            resource.setType(type);
            resource.setCls(cls);
            resource.setParentId(parentId);

        }
        try {
            if(StringUtils.isNotBlank(reqType)&&"update".equals(reqType)){
                resourceService.updateResource(resource);
            }else{
                resourceService.addResource(resource);
            }
            this.addResultInfo(SUCCESS,"增加/更新资源成功！",null);
            this.responseResult(response,this.getResultJSONStr());
        }catch (Exception e){
            e.printStackTrace();
            this.addResultInfo(FAILURE,"增加/更新资源失败！",null);
            this.responseResult(response,this.getResultJSONStr());
        }
    }

    @RequestMapping(value = "/delResources")
    public void delResources(HttpServletRequest request, HttpServletResponse response) {
        String resourceIds = request.getParameter("resourceIds");
        String message = "删除角色数据失败！";
        try {
            if (StringUtils.isBlank(resourceIds)) {
                message = "没有要删除的数据！";
            } else {
                String[] ids = resourceIds.split(",");
                resourceService.delResources(ids);
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

    @RequestMapping(value = "/getAllResources")
    public void getAllResources(HttpServletRequest request, HttpServletResponse response) {
        try {
//            List<Resource> result = resourceService.getAllResource();
            List<TreeResource> result = resourceService.getTreeResource();
            this.addResultInfo(SUCCESS, "查询成功！", result);
//            System.out.println(this.getResultJSONStr().replaceAll("\\[\\]", ""));
            this.responseResult(response, this.getResultJSONStr().replaceAll("\\[\\]", "null"));
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, "查询失败！", null);
            this.responseResult(response, this.getResultJSONStr());
        }

    }

    @RequestMapping(value = "/getResourceByRoleId")
    public void getResourceByRoleId(HttpServletRequest request, HttpServletResponse response) {
        String roleId = request.getParameter("roleId");
        try {
            List<Resource> result = resourceService.getResourceByRoleId(roleId);
            this.addResultInfo(SUCCESS, "查询成功！", result);
            System.out.println(this.getResultJSONStr().replaceAll("\\[\\]", ""));
            this.responseResult(response, this.getResultJSONStr().replaceAll("\\[\\]", "null"));
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, "查询失败！", null);
            this.responseResult(response, this.getResultJSONStr());
        }

    }

    @RequestMapping(value = "/getAllResource",method = RequestMethod.GET)
    public void getAllResource(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Resource> result = resourceService.getAllResource();
            this.addResultInfo(SUCCESS, "查询成功！", result);
            this.responseResult(response, this.getResultJSONStr());
        } catch (Exception e) {
            e.printStackTrace();
            this.addResultInfo(FAILURE, "查询失败！", null);
            this.responseResult(response, this.getResultJSONStr());
        }

    }
}
