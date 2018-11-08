package com.cn.service;

import com.cn.entity.ArticleInfo;
import com.cn.entity.SubCenterInfo;
import com.cn.entity.UserInfo;
import com.cn.entity.pojo.RequestArticle;
import com.cn.entity.pojo.RequestSubcenter;
import com.cn.entity.pojo.RequsetUserInfo;

import java.util.Map;

/**
 * User: cuixiaowei
 * Date: 2018/11/4
 * PackageName: com.cn.service
 */
public interface BaseConfigService {
    /**
     * 新增分中心
     * @param subCenterInfo
     * @return
     */
    public String addSubCenter(SubCenterInfo subCenterInfo);

    /**
     * 更新分中心信息
     * @param subCenterInfo
     * @return
     */
    public String updateSubCenter(SubCenterInfo subCenterInfo);

    /**
     * 查询分中心列表
     * @param requestSubcenter
     * @return
     */
    public String querySubCenterList(RequestSubcenter requestSubcenter);

    /**
     *发布文章
     * @param articleInfo
     * @return
     */
    public String addArticle(ArticleInfo articleInfo);

    /**
     * 更新文章信息(包括删除文章)
     * @param articleInfo
     * @return
     */
    public String updateArticle(ArticleInfo articleInfo);

    /**
     * 获取文章列表
     * @param source
     * @return
     */
    public String getArticleLists(Map<String, Object> source);

    /**
     * 收藏文章
     * @param requestArticle
     * @return
     */
    public String collectionArticle(RequestArticle requestArticle);

    /**
     * 获取收藏列表
     * @param requestArticle
     * @return
     */
    public String getCollectionArticleList(RequestArticle requestArticle);

    /**
     * 更新收藏文章，主要是取消收藏功能
     * @param requestArticle
     * @return
     */
    public String updateCollectionArticle(RequestArticle requestArticle);

    /**
     * 更新完善用户信息
     * @param userInfo
     * @return
     */
    public String updateUserInfo(UserInfo userInfo);


    /**
     * 获取用户信息列表
     * @param requsetUserInfo
     * @return
     */
    public String getUserLists(RequsetUserInfo requsetUserInfo);
}
