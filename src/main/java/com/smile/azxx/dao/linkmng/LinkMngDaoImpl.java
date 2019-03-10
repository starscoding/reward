package com.smile.azxx.dao.linkmng;

import com.smile.azxx.entity.LinkInfo;
import com.smile.azxx.jpa.DaoSupport;
import com.smile.azxx.model.VideoAndLinkInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by smile on 2018/5/6.
 */
@Component
public class LinkMngDaoImpl extends DaoSupport<LinkInfo> implements LinkMngDao {

    @PersistenceContext
    public EntityManager em;

    @Override
    public List<LinkInfo> getLinkInfo(String userName) {
        String sql = "select l from LinkInfo l where l.userName=?1";
        Query query = getEm().createQuery(sql);
        query.setParameter(1,userName);
        return query.getResultList();
    }

    @Override
    public List<VideoAndLinkInfo> getVideoAndLinkInfo(String userName) {
        List<VideoAndLinkInfo> result = new ArrayList<>();
        StringBuffer sql = new StringBuffer("select link.VIDEO_ID,link.VIDEO_PRICE,video.TITLE_,video.URL_,link.SHORT_URL,num.amount,video.type_,link.id,video.IMG_NAME from app_link_info link");
        sql.append(" left join app_video_info video on link.VIDEO_ID =video.ID_");
        sql.append(" left join (select LINK_ID,sum(AMOUNT) AMOUNT from app_reward_info");
        sql.append(" where NAME_=? group by LINK_ID) num on link.ID=num.LINK_ID");
        sql.append(" where link.USER_NAME=?");
        log.info(sql.toString());
        Query query = this.getEm().createNativeQuery(sql.toString());
        query.setParameter(1,userName);
        query.setParameter(2,userName);
        List list = query.getResultList();
        if(CollectionUtils.isNotEmpty(list)){
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                VideoAndLinkInfo temp = new VideoAndLinkInfo();
                temp.setVideoId(obj[0]==null?"":obj[0].toString());
                temp.setPrice(obj[1]==null?"":obj[1].toString());
                temp.setVideoTitle(obj[2]==null?"":obj[2].toString());
                temp.setVideoUrl(obj[3]==null?"":obj[3].toString());
                temp.setShortUrl(obj[4]==null?"":obj[4].toString());
                temp.setIncome(obj[5]==null?"":obj[5].toString());
                temp.setVideoType(obj[6]==null?"":obj[6].toString());
                temp.setLinkId(obj[7]==null?"":obj[7].toString());
                temp.setImgName(obj[8]==null?"":obj[8].toString());
                result.add(temp);
            }
        }
        return result;
    }

    @Override
    public void updatePrice(String[] ids, String price) {
        String sql = "update LinkInfo l set l.videoPrice=?1 where l.id in ?2";
        List<String> list = Arrays.asList(ids);
        Query query = this.getEm().createQuery(sql);
        query.setParameter(1,new BigDecimal(price));
        query.setParameter(2,list);
        query.executeUpdate();
    }
}
