package com.cn.entity;

import java.util.Date;

public class ArticleInfo {
    private Integer articleId;

    private String articleTitle;

    private String articleContent;

    private Integer articleUpUserid;

    private Integer articleSubCenterid;

    private Date articleUpTime;

    private String articleIsDel;

    public ArticleInfo(Integer articleId, String articleTitle, String articleContent, Integer articleUpUserid, Integer articleSubCenterid, Date articleUpTime,String articleIsDel) {
        this.articleId = articleId;
        this.articleTitle = articleTitle;
        this.articleContent = articleContent;
        this.articleUpUserid = articleUpUserid;
        this.articleSubCenterid = articleSubCenterid;
        this.articleUpTime = articleUpTime;
        this.articleIsDel = articleIsDel;
    }

    public ArticleInfo() {
        super();
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
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

    public Date getArticleUpTime() {
        return articleUpTime;
    }

    public void setArticleUpTime(Date articleUpTime) {
        this.articleUpTime = articleUpTime;
    }

    public String getArticleIsDel() {
        return articleIsDel;
    }

    public void setArticleIsDel(String articleIsDel) {
        this.articleIsDel = articleIsDel == null ? null : articleIsDel.trim();
    }
}
