package com.cn.entity.pojo;

import net.sf.json.JSONObject;

import java.util.Date;

/**
 * User: cuixiaowei
 * Date: 2018/11/1
 * PackageName: com.cn.entity.pojo
 */
public class RequestBody {
    private JSONObject bodyJson;
    private String openid;
    private String sendTime;
    private String msgtype;
    private String content;
    private String toUsername;
    private String mideaId;
    private String subcenter_id;
    private String isChangJingSub="false";
    private String marchant_appid;

    public String getMarchant_appid() {
        return marchant_appid;
    }

    public void setMarchant_appid(String marchant_appid) {
        this.marchant_appid = marchant_appid;
    }

    public String getIsChangJingSub() {
        return isChangJingSub;
    }

    public void setIsChangJingSub(String isChangJingSub) {
        this.isChangJingSub = isChangJingSub;
    }

    public String getSubcenter_id() {
        return subcenter_id;
    }

    public void setSubcenter_id(String subcenter_id) {
        this.subcenter_id = subcenter_id;
    }

    public String getMideaId() {
        return mideaId;
    }

    public void setMideaId(String mideaId) {
        this.mideaId = mideaId;
    }

    public JSONObject getBodyJson() {
        return bodyJson;
    }

    public void setBodyJson(JSONObject bodyJson) {
        this.bodyJson = bodyJson;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }
}
