package com.smile.azxx.service.videomng;

import com.smile.azxx.dao.videomng.VideoDao;
import com.smile.azxx.entity.VideoInfo;
import com.smile.azxx.service.common.BaseService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smile on 2018/5/3.
 */
@Service
@Transactional
public class VideoService extends BaseService{

    @Autowired
    private VideoDao videoDao;

    public List<VideoInfo> getAllVideo(String userName,String videoType){
        List<VideoInfo> result = new ArrayList<>();
        if(StringUtils.isNotBlank(userName)&&"admin".equals(userName)){
            result = videoDao.findAll();
        }else{
            if(StringUtils.isNotBlank(videoType)&&"1".equals(videoType)){
                //获取私有片库
                result = videoDao.getVideoByUserName(userName);
            }else{
                //获取公共片库
                if(StringUtils.isBlank(videoType))
                    videoType = "0";
                result = videoDao.getPublicVideo(videoType);
            }

        }
        return result;
    }

    public void addVideoes(List<VideoInfo> videoes){
        videoDao.saveIterable(videoes);
    }
    
    public void delVideo(String[] ids){
        if(ArrayUtils.isNotEmpty(ids)){
            for (int i = 0; i < ids.length; i++) {
                videoDao.delete(ids[i]);
            }
        }
    }
}
