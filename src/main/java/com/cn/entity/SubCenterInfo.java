package com.cn.entity;

import java.util.Date;

public class SubCenterInfo {
    private Integer subcenterId;

    private String subcenterName;

    private String subcenterImage;

    private String subcenterAddress;

    private String subcenterPersonName;

    private String subcenterPersonMobile;

    private Date subcenterCreatetime;

    private Date subcenterUpdatetime;

    public SubCenterInfo(Integer subcenterId, String subcenterName, String subcenterImage, String subcenterAddress, String subcenterPersonName, String subcenterPersonMobile, Date subcenterCreatetime, Date subcenterUpdatetime) {
        this.subcenterId = subcenterId;
        this.subcenterName = subcenterName;
        this.subcenterImage = subcenterImage;
        this.subcenterAddress = subcenterAddress;
        this.subcenterPersonName = subcenterPersonName;
        this.subcenterPersonMobile = subcenterPersonMobile;
        this.subcenterCreatetime = subcenterCreatetime;
        this.subcenterUpdatetime = subcenterUpdatetime;
    }

    public SubCenterInfo() {
        super();
    }

    public Integer getSubcenterId() {
        return subcenterId;
    }

    public void setSubcenterId(Integer subcenterId) {
        this.subcenterId = subcenterId;
    }

    public String getSubcenterName() {
        return subcenterName;
    }

    public void setSubcenterName(String subcenterName) {
        this.subcenterName = subcenterName == null ? null : subcenterName.trim();
    }

    public String getSubcenterImage() {
        return subcenterImage;
    }

    public void setSubcenterImage(String subcenterImage) {
        this.subcenterImage = subcenterImage == null ? null : subcenterImage.trim();
    }

    public String getSubcenterAddress() {
        return subcenterAddress;
    }

    public void setSubcenterAddress(String subcenterAddress) {
        this.subcenterAddress = subcenterAddress == null ? null : subcenterAddress.trim();
    }

    public String getSubcenterPersonName() {
        return subcenterPersonName;
    }

    public void setSubcenterPersonName(String subcenterPersonName) {
        this.subcenterPersonName = subcenterPersonName == null ? null : subcenterPersonName.trim();
    }

    public String getSubcenterPersonMobile() {
        return subcenterPersonMobile;
    }

    public void setSubcenterPersonMobile(String subcenterPersonMobile) {
        this.subcenterPersonMobile = subcenterPersonMobile == null ? null : subcenterPersonMobile.trim();
    }

    public Date getSubcenterCreatetime() {
        return subcenterCreatetime;
    }

    public void setSubcenterCreatetime(Date subcenterCreatetime) {
        this.subcenterCreatetime = subcenterCreatetime;
    }

    public Date getSubcenterUpdatetime() {
        return subcenterUpdatetime;
    }

    public void setSubcenterUpdatetime(Date subcenterUpdatetime) {
        this.subcenterUpdatetime = subcenterUpdatetime;
    }
}