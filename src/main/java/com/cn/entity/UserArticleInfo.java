package com.cn.entity;

import java.util.Date;

public class UserArticleInfo {
    private Integer userarticleId;

    private Integer userarticleArticleId;

    private Integer userarticleUserId;

    private Date userarticleTime;

    private String userarticleStatus;

    public UserArticleInfo(Integer userarticleId, Integer userarticleArticleId, Integer userarticleUserId, Date userarticleTime, String userarticleStatus) {
        this.userarticleId = userarticleId;
        this.userarticleArticleId = userarticleArticleId;
        this.userarticleUserId = userarticleUserId;
        this.userarticleTime = userarticleTime;
        this.userarticleStatus = userarticleStatus;
    }

    public UserArticleInfo() {
        super();
    }

    public Integer getUserarticleId() {
        return userarticleId;
    }

    public void setUserarticleId(Integer userarticleId) {
        this.userarticleId = userarticleId;
    }

    public Integer getUserarticleArticleId() {
        return userarticleArticleId;
    }

    public void setUserarticleArticleId(Integer userarticleArticleId) {
        this.userarticleArticleId = userarticleArticleId;
    }

    public Integer getUserarticleUserId() {
        return userarticleUserId;
    }

    public void setUserarticleUserId(Integer userarticleUserId) {
        this.userarticleUserId = userarticleUserId;
    }

    public Date getUserarticleTime() {
        return userarticleTime;
    }

    public void setUserarticleTime(Date userarticleTime) {
        this.userarticleTime = userarticleTime;
    }

    public String getUserarticleStatus() {
        return userarticleStatus;
    }

    public void setUserarticleStatus(String userarticleStatus) {
        this.userarticleStatus = userarticleStatus == null ? null : userarticleStatus.trim();
    }
}