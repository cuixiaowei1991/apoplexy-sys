package com.cn.entity.pojo;

/**
 * User: cuixiaowei
 * Date: 2018/11/4
 * PackageName: com.cn.entity.pojo
 */
public class RequestSubcenter {
    private int pageSize;//每页记录数
    private int pageNum;//当前页
    private String sub_center_name;
    private String subcenterId;

    public String getSubcenterId() {
        return subcenterId;
    }

    public void setSubcenterId(String subcenterId) {
        this.subcenterId = subcenterId;
    }

    public String getSub_center_name() {
        return sub_center_name;
    }

    public void setSub_center_name(String sub_center_name) {
        this.sub_center_name = sub_center_name;
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
