package com.smile.azxx.dao.videomng;

import com.smile.azxx.entity.VideoInfo;
import com.smile.azxx.jpa.Dao;

import java.util.List;

/**
 * Created by smile on 2018/5/3.
 */
public interface VideoDao extends Dao<VideoInfo> {

    public List<VideoInfo> getVideoByUserName(String userName);

    public List<VideoInfo> getPublicVideo(String videoType);

    public List<VideoInfo> getVideoByLinkId(String linkId);
}
