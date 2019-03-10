package com.smile.azxx.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by smile on 2018/5/6.
 */
@Entity
@Table(name = "app_link_info", schema = "", catalog = "")
public class LinkInfo {
    private String id;
    private String videoId;
    private String userName;
    private BigDecimal videoPrice;
    private String recordTime;
    private String shortUrl;

    @Id
    @Column(name = "ID", nullable = false, length = 32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "VIDEO_ID", nullable = false, length = 32)
    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Basic
    @Column(name = "USER_NAME", nullable = false, length = 100)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "VIDEO_PRICE", nullable = false, precision = 5)
    public BigDecimal getVideoPrice() {
        return videoPrice;
    }

    public void setVideoPrice(BigDecimal videoPrice) {
        this.videoPrice = videoPrice;
    }

    @Basic
    @Column(name = "RECORD_TIME", nullable = true, length = 25)
    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    @Basic
    @Column(name = "SHORT_URL", nullable = true, length = 100)
    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkInfo that = (LinkInfo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (videoId != null ? !videoId.equals(that.videoId) : that.videoId != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (videoPrice != null ? !videoPrice.equals(that.videoPrice) : that.videoPrice != null) return false;
        if (recordTime != null ? !recordTime.equals(that.recordTime) : that.recordTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (videoId != null ? videoId.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (videoPrice != null ? videoPrice.hashCode() : 0);
        result = 31 * result + (recordTime != null ? recordTime.hashCode() : 0);
        return result;
    }
}
