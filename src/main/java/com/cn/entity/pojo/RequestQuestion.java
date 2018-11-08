package com.cn.entity.pojo;

/**
 * User: cuixiaowei
 * Date: 2018/11/2
 * PackageName: com.cn.entity.pojo
 */
public class RequestQuestion {
    private String openid;
    private String appid;
    private int sub_center_id;
    private String topic_type;
    private String advice_isnew;
    private String topic_id;
    private String message_content;
    private String message_type;
    private String label;
    private int pageSize;//每页记录数
    private int pageNum;//当前页
    private Integer messageId;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public int getSub_center_id() {
        return sub_center_id;
    }

    public void setSub_center_id(int sub_center_id) {
        this.sub_center_id = sub_center_id;
    }

    public String getTopic_type() {
        return topic_type;
    }

    public void setTopic_type(String topic_type) {
        this.topic_type = topic_type;
    }

    public String getAdvice_isnew() {
        return advice_isnew;
    }

    public void setAdvice_isnew(String advice_isnew) {
        this.advice_isnew = advice_isnew;
    }

    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }

    public String getMessage_content() {
        return message_content;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }
}
