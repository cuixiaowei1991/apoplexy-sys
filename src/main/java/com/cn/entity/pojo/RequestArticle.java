package com.cn.entity.pojo;

/**
 * User: cuixiaowei
 * Date: 2018/11/5
 * PackageName: com.cn.entity.pojo
 */
public class RequestArticle {
    private String articleTitle;
    private Integer articleUpUserid;
    private Integer articleSubCenterid;
    private int pageSize;//每页记录数
    private int pageNum;//当前页
    private Integer articleId;//收藏的文章id
    private Integer userId;//收藏者的id
    private String status;//收藏状态
    private Integer userarticleId;

    public Integer getUserarticleId() {
        return userarticleId;
    }

    public void setUserarticleId(Integer userarticleId) {
        this.userarticleId = userarticleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Integer getArticleUpUserid() {
        return articleUpUserid;
    }

    public void setArticleUpUserid(Integer articleUpUserid) {
        this.articleUpUserid = articleUpUserid;
    }

    public Integer getArticleSubCenterid() {
        return articleSubCenterid;
    }

    public void setArticleSubCenterid(Integer articleSubCenterid) {
        this.articleSubCenterid = articleSubCenterid;
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
