package com.smile.azxx.service.sysmng;

import com.smile.azxx.dao.sysmng.ResourceDao;
import com.smile.azxx.entity.sysmng.Resource;
import com.smile.azxx.entity.sysmng.RoleResource;
import com.smile.azxx.model.TreeResource;
import com.smile.azxx.service.common.BaseService;
import com.smile.azxx.util.ListUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smile on 2018/4/10.
 */
@Service
@Transactional
public class ResourceService extends BaseService{

    @Autowired
    private ResourceDao resourceDao;

    @Autowired
    private RoleResourceService roleResourceService;

    public List<Resource> getResourcesByUser(String userName){
        return resourceDao.getResourcesByUser(userName);
    }

    public void addResource(Resource resource){
        resourceDao.save(resource);
    }

    public void updateResource(Resource resource){
        resourceDao.update(resource);
    }

    public Resource getResource(String id){
        return resourceDao.findOne(id);
    }

    public List<Resource> getAllResource(){
       return resourceDao.findAll();
    }

    public void delResources(String[] ids){
        for (int i = 0; i < ids.length; i++) {

            List<RoleResource> roleResources = roleResourceService.getRoleResourceByResourceId(ids[i]);
            if(CollectionUtils.isNotEmpty(roleResources)){
                roleResourceService.delete(roleResources);
            }
            resourceDao.delete(ids[i]);
        }
    }

    public List<TreeResource> getTreeResource(){
        List<TreeResource> result = new ArrayList<>();
        List<Resource> resources = resourceDao.findAll();
        if(ListUtil.isNotBlank(resources)){
            result = this.getTreeResource(resources);
        }
        return  result;
    }

    public List<TreeResource> getTreeNodeResource(){
        List<TreeResource> result = new ArrayList<>();
        List<Resource> resources = resourceDao.findAll();
        if(ListUtil.isNotBlank(resources)){
            result = this.getTreeResource(resources);
        }
        return  result;
    }

    public List<TreeResource> getTreeResource(List<Resource> resources){
        List<TreeResource> result = new ArrayList<>();
        for (int i = 0; i < resources.size(); i++) {
            if(StringUtils.isBlank(resources.get(i).getParentId())){
                TreeResource temp = resourceToTreesource(resources.get(i));
                this.getTreeResource(resources, temp);
                result.add(temp);
            }
        }
        return result;
    }

    public void getTreeResource(List<Resource> resources,TreeResource result){
        boolean hasChildren = false;
        for (int i = 0; i < resources.size(); i++) {
            if(result.getId().equals(resources.get(i).getParentId())){
                hasChildren = true;
                if(result.getChildren()==null){
                    result.setChildren(new ArrayList<>()) ;
                }
                if(result.getNodes()==null){
                    result.setNodes(new ArrayList<>()); ;
                }
                TreeResource temp = resourceToTreesource(resources.get(i));
                getTreeResource(resources,temp);
                result.getChildren().add(temp);
                result.getNodes().add(temp);
            }
        }
        if(!hasChildren){
            result.setChildren(null);
            result.setNodes(null);
        }
    }

    public TreeResource resourceToTreesource(Resource resource){
        TreeResource result = new TreeResource();
        result.setId(resource.getId());
        result.setName(resource.getName());
        result.setDescription(resource.getDescription());
        result.setParentId(resource.getParentId());
        result.setValue(resource.getValue());
        result.setCls(resource.getCls());
        result.setOrder(resource.getOrder());
        result.setType(resource.getType());
        result.setUrl(resource.getUrl());
        result.setText(resource.getName());
        return result;
    }

    public List<Resource> getResourceByRoleId(String roleId){
        return resourceDao.getResourceByRoleId(roleId);
    }

}
