package com.cn.entity;

import java.util.Date;

public class MessageInfo {
    private Integer messageId;

    private String messageOpenid;

    private String messageType;

    private String messageContent;

    private Integer userCenterId;

    private String messageUserDes;

    private Date adviceReplyTime;

    private String topicId;

    private Date messageCreatetime;

    private String messageAppid;

    public MessageInfo(Integer messageId, String messageOpenid, String messageType, String messageContent, Integer userCenterId, String messageUserDes, Date adviceReplyTime,Date messageCreatetime, String topicId,String messageAppid) {
        this.messageId = messageId;
        this.messageOpenid = messageOpenid;
        this.messageType = messageType;
        this.messageContent = messageContent;
        this.userCenterId = userCenterId;
        this.messageUserDes = messageUserDes;
        this.adviceReplyTime = adviceReplyTime;
        this.messageCreatetime=messageCreatetime;
        this.topicId = topicId;
        this.messageAppid=messageAppid;
    }

    public MessageInfo() {
        super();
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessageOpenid() {
        return messageOpenid;
    }

    public void setMessageOpenid(String messageOpenid) {
        this.messageOpenid = messageOpenid == null ? null : messageOpenid.trim();
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType == null ? null : messageType.trim();
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }

    public Integer getUserCenterId() {
        return userCenterId;
    }

    public void setUserCenterId(Integer userCenterId) {
        this.userCenterId = userCenterId;
    }

    public String getMessageUserDes() {
        return messageUserDes;
    }

    public void setMessageUserDes(String messageUserDes) {
        this.messageUserDes = messageUserDes == null ? null : messageUserDes.trim();
    }

    public Date getAdviceReplyTime() {
        return adviceReplyTime;
    }

    public void setAdviceReplyTime(Date adviceReplyTime) {
        this.adviceReplyTime = adviceReplyTime;
    }

    public Date getMessageCreatetime() {
        return messageCreatetime;
    }

    public void setMessageCreatetime(Date messageCreatetime) {
        this.messageCreatetime = messageCreatetime;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getMessageAppid() {
        return messageAppid;
    }

    public void setMessageAppid(String messageAppid) {
        this.messageAppid =messageAppid == null ? null : messageAppid.trim();
    }
}