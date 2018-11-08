package com.cn.entity;

import java.util.Date;

public class TopicInfo {
    private Integer topicId;

    private String topicOpenid;

    private String topicAppid;

    private Integer topicSubCenterId;

    private String topicType;

    private String topicLabel;

    private String topicContent;

    private Date topicCreatetime;

    private String topicIsnew;

    public TopicInfo(Integer topicId, String topicOpenid, String topicAppid, Integer topicSubCenterId, String topicType, String topicLabel, String topicContent, Date topicCreatetime, String topicIsnew) {
        this.topicId = topicId;
        this.topicOpenid = topicOpenid;
        this.topicAppid = topicAppid;
        this.topicSubCenterId = topicSubCenterId;
        this.topicType = topicType;
        this.topicLabel = topicLabel;
        this.topicContent = topicContent;
        this.topicCreatetime = topicCreatetime;
        this.topicIsnew = topicIsnew;
    }

    public TopicInfo() {
        super();
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicOpenid() {
        return topicOpenid;
    }

    public void setTopicOpenid(String topicOpenid) {
        this.topicOpenid = topicOpenid == null ? null : topicOpenid.trim();
    }

    public String getTopicAppid() {
        return topicAppid;
    }

    public void setTopicAppid(String topicAppid) {
        this.topicAppid = topicAppid == null ? null : topicAppid.trim();
    }

    public Integer getTopicSubCenterId() {
        return topicSubCenterId;
    }

    public void setTopicSubCenterId(Integer topicSubCenterId) {
        this.topicSubCenterId = topicSubCenterId;
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType == null ? null : topicType.trim();
    }

    public String getTopicLabel() {
        return topicLabel;
    }

    public void setTopicLabel(String topicLabel) {
        this.topicLabel = topicLabel == null ? null : topicLabel.trim();
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent == null ? null : topicContent.trim();
    }

    public Date getTopicCreatetime() {
        return topicCreatetime;
    }

    public void setTopicCreatetime(Date topicCreatetime) {
        this.topicCreatetime = topicCreatetime;
    }

    public String getTopicIsnew() {
        return topicIsnew;
    }

    public void setTopicIsnew(String topicIsnew) {
        this.topicIsnew = topicIsnew == null ? null : topicIsnew.trim();
    }
}