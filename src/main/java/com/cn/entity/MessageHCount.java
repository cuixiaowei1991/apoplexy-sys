package com.cn.entity;

import java.util.Date;

public class MessageHCount {
    private Integer hcountId;

    private Integer hcountTopicId;

    private Integer hcountTmsCount;

    private Date hcountUpdatetime;

    public MessageHCount(Integer hcountId, Integer hcountTopicId, Integer hcountTmsCount, Date hcountUpdatetime) {
        this.hcountId = hcountId;
        this.hcountTopicId = hcountTopicId;
        this.hcountTmsCount = hcountTmsCount;
        this.hcountUpdatetime = hcountUpdatetime;
    }

    public MessageHCount() {
        super();
    }

    public Integer getHcountId() {
        return hcountId;
    }

    public void setHcountId(Integer hcountId) {
        this.hcountId = hcountId;
    }

    public Integer getHcountTopicId() {
        return hcountTopicId;
    }

    public void setHcountTopicId(Integer hcountTopicId) {
        this.hcountTopicId = hcountTopicId;
    }

    public Integer getHcountTmsCount() {
        return hcountTmsCount;
    }

    public void setHcountTmsCount(Integer hcountTmsCount) {
        this.hcountTmsCount = hcountTmsCount;
    }

    public Date getHcountUpdatetime() {
        return hcountUpdatetime;
    }

    public void setHcountUpdatetime(Date hcountUpdatetime) {
        this.hcountUpdatetime = hcountUpdatetime;
    }
}