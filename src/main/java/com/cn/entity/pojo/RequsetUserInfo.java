package com.cn.entity.pojo;

/**
 * User: cuixiaowei
 * Date: 2018/11/5
 * PackageName: com.cn.entity.pojo
 */
public class RequsetUserInfo {
    private String username;
    private String usersex;
    private String userage;
    private String subcenterid;
    private int pageSize;//每页记录数
    private int pageNum;//当前页

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsersex() {
        return usersex;
    }

    public void setUsersex(String usersex) {
        this.usersex = usersex;
    }

    public String getUserage() {
        return userage;
    }

    public void setUserage(String userage) {
        this.userage = userage;
    }

    public String getSubcenterid() {
        return subcenterid;
    }

    public void setSubcenterid(String subcenterid) {
        this.subcenterid = subcenterid;
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
}
