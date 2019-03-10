package com.smile.azxx.dao.videomng;

import com.smile.azxx.entity.VideoInfo;
import com.smile.azxx.jpa.DaoSupport;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by smile on 2018/5/3.
 */
@Component
public class VideoDaoImpl extends DaoSupport<VideoInfo> implements VideoDao{

    @PersistenceContext
    public EntityManager em;

    @Override
    public List<VideoInfo> getVideoByUserName(String userName) {
        String sql = "select v from VideoInfo v where v.publisher=?1";
        Query query = this.getEm().createQuery(sql);
        query.setParameter(1,userName);
        return query.getResultList();
    }
    @Override
    public List<VideoInfo> getPublicVideo(String videoType) {
        String sql = "select v from VideoInfo v where  v.type=?1";
        Query query = this.getEm().createQuery(sql);
        query.setParameter(1,videoType);
        return query.getResultList();
    }

    @Override
    public List<VideoInfo> getVideoByLinkId(String linkId) {
        String sql = "select v from VideoInfo v  where  v.id in (select r.videoId from LinkInfo r where r.id=?1)";
        Query query = this.getEm().createQuery(sql);
        query.setParameter(1,linkId);
        return query.getResultList();
    }
}
