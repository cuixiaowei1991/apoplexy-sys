package com.cn.entity;

import java.util.Date;

public class UserInfo {
    private Integer userinfoId;

    private String userinfoName;

    private String userDes;

    private String userType;

    private String userinfoWechatName;

    private String userinfoWechatAppid;

    private String userinfoWechatOpenid;

    private Integer userinfoSubCenterId;

    private String userinfoSex;

    private String userinfoWechatHeadimage;

    private String userinfoAge;

    private String userinfoRoleTag;

    private Date userinfoCreatetime;

    private String userinfoIsSubscribe;

    public UserInfo(Integer userinfoId, String userinfoName, String userDes, String userType, String userinfoWechatName, String userinfoWechatAppid, String userinfoWechatOpenid, Integer userinfoSubCenterId, String userinfoSex, String userinfoWechatHeadimage, String userinfoAge, String userinfoRoleTag, Date userinfoCreatetime, String userinfoIsSubscribe) {
        this.userinfoId = userinfoId;
        this.userinfoName = userinfoName;
        this.userDes = userDes;
        this.userType = userType;
        this.userinfoWechatName = userinfoWechatName;
        this.userinfoWechatAppid = userinfoWechatAppid;
        this.userinfoWechatOpenid = userinfoWechatOpenid;
        this.userinfoSubCenterId = userinfoSubCenterId;
        this.userinfoSex = userinfoSex;
        this.userinfoWechatHeadimage = userinfoWechatHeadimage;
        this.userinfoAge = userinfoAge;
        this.userinfoRoleTag = userinfoRoleTag;
        this.userinfoCreatetime = userinfoCreatetime;
        this.userinfoIsSubscribe = userinfoIsSubscribe;
    }

    public UserInfo() {
        super();
    }

    public Integer getUserinfoId() {
        return userinfoId;
    }

    public void setUserinfoId(Integer userinfoId) {
        this.userinfoId = userinfoId;
    }

    public String getUserinfoName() {
        return userinfoName;
    }

    public void setUserinfoName(String userinfoName) {
        this.userinfoName = userinfoName == null ? null : userinfoName.trim();
    }

    public String getUserDes() {
        return userDes;
    }

    public void setUserDes(String userDes) {
        this.userDes = userDes == null ? null : userDes.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getUserinfoWechatName() {
        return userinfoWechatName;
    }

    public void setUserinfoWechatName(String userinfoWechatName) {
        this.userinfoWechatName = userinfoWechatName == null ? null : userinfoWechatName.trim();
    }

    public String getUserinfoWechatAppid() {
        return userinfoWechatAppid;
    }

    public void setUserinfoWechatAppid(String userinfoWechatAppid) {
        this.userinfoWechatAppid = userinfoWechatAppid == null ? null : userinfoWechatAppid.trim();
    }

    public String getUserinfoWechatOpenid() {
        return userinfoWechatOpenid;
    }

    public void setUserinfoWechatOpenid(String userinfoWechatOpenid) {
        this.userinfoWechatOpenid = userinfoWechatOpenid == null ? null : userinfoWechatOpenid.trim();
    }

    public Integer getUserinfoSubCenterId() {
        return userinfoSubCenterId;
    }

    public void setUserinfoSubCenterId(Integer userinfoSubCenterId) {
        this.userinfoSubCenterId = userinfoSubCenterId;
    }

    public String getUserinfoSex() {
        return userinfoSex;
    }

    public void setUserinfoSex(String userinfoSex) {
        this.userinfoSex = userinfoSex == null ? null : userinfoSex.trim();
    }

    public String getUserinfoWechatHeadimage() {
        return userinfoWechatHeadimage;
    }

    public void setUserinfoWechatHeadimage(String userinfoWechatHeadimage) {
        this.userinfoWechatHeadimage = userinfoWechatHeadimage == null ? null : userinfoWechatHeadimage.trim();
    }

    public String getUserinfoAge() {
        return userinfoAge;
    }

    public void setUserinfoAge(String userinfoAge) {
        this.userinfoAge = userinfoAge == null ? null : userinfoAge.trim();
    }

    public String getUserinfoRoleTag() {
        return userinfoRoleTag;
    }

    public void setUserinfoRoleTag(String userinfoRoleTag) {
        this.userinfoRoleTag = userinfoRoleTag == null ? null : userinfoRoleTag.trim();
    }

    public Date getUserinfoCreatetime() {
        return userinfoCreatetime;
    }

    public void setUserinfoCreatetime(Date userinfoCreatetime) {
        this.userinfoCreatetime = userinfoCreatetime;
    }

    public String getUserinfoIsSubscribe() {
        return userinfoIsSubscribe;
    }

    public void setUserinfoIsSubscribe(String userinfoIsSubscribe) {
        this.userinfoIsSubscribe = userinfoIsSubscribe == null ? null : userinfoIsSubscribe.trim();
    }
}