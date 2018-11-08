package com.cn.controller;

import com.cn.aopAnnotation.HttpHeaderValidate;
import com.cn.common.CommonHelper;
import com.cn.common.LogHelper;
import com.cn.entity.ArticleInfo;
import com.cn.entity.SubCenterInfo;
import com.cn.entity.pojo.RequestArticle;
import com.cn.entity.pojo.RequestSubcenter;
import com.cn.service.BaseConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

/**
 * User: cuixiaowei
 * Date: 2018/11/4
 * PackageName: com.cn.controller
 */
@Controller
@RequestMapping("/baseConfig")
public class BaseConfigController {
    @Autowired
    private BaseConfigService baseConfigService;
    @RequestMapping(value="/addSubConcenter",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String addSubConcenter(@RequestBody Map<String, Object> source)
    {
        LogHelper.info("新增分中心信息："+ CommonHelper.showDetails(source));
        SubCenterInfo subCenterInfo=covertSubCenterInfo(source);
        String result= baseConfigService.addSubCenter(subCenterInfo);
        return result;
    }

    @RequestMapping(value="/updateSubConcenter",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String updateSubConcenter(@RequestBody Map<String, Object> source)
    {
        LogHelper.info("修改分中心信息："+ CommonHelper.showDetails(source));
        SubCenterInfo subCenterInfo=covertSubCenterInfo(source);
        String result= baseConfigService.updateSubCenter(subCenterInfo);
        return "";
    }
    @RequestMapping(value="/querySubCenterList",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String querySubCenterList(@RequestBody Map<String, Object> source)
    {
        LogHelper.info("获取分中心信息列表："+ CommonHelper.showDetails(source));
        RequestSubcenter requestSubcenter=covertRequestSubcenter(source);
        String result= baseConfigService.querySubCenterList(requestSubcenter);
        return result;
    }
    @RequestMapping(value="/addArticle",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String addArticle(@RequestBody Map<String, Object> source)
    {
        LogHelper.info("发布文章信息："+ CommonHelper.showDetails(source));
        ArticleInfo articleInfo=covertArticleInfo(source);
        String result= baseConfigService.addArticle(articleInfo);
        return result;
    }
    @RequestMapping(value="/updatearticle",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String updatearticle(@RequestBody Map<String, Object> source)
    {
        LogHelper.info("修改文章信息："+ CommonHelper.showDetails(source));
        ArticleInfo articleInfo=covertArticleInfo(source);
        String result= baseConfigService.updateArticle(articleInfo);
        return result;
    }
    @RequestMapping(value="/getArticleList",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getArticleList(@RequestBody Map<String, Object> source)
    {
        LogHelper.info("获取文章列表："+ CommonHelper.showDetails(source));
        String result= baseConfigService.getArticleLists(source);
        return result;
    }

    @RequestMapping(value="/collectionArticle",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String collectionArticle(@RequestBody Map<String, Object> source)
    {
        LogHelper.info("收藏文章信息："+ CommonHelper.showDetails(source));

        RequestArticle requestArticle=covertRequestArticle(source);
        String result=baseConfigService.collectionArticle(requestArticle);
        return result;
    }
    @RequestMapping(value="/updateCollectionArticle",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String updateCollectionArticle(@RequestBody Map<String, Object> source)
    {
        LogHelper.info("更新收藏文章信息："+ CommonHelper.showDetails(source));

        RequestArticle requestArticle=covertRequestArticle(source);
        String result=baseConfigService.updateCollectionArticle(requestArticle);
        return result;
    }

    @RequestMapping(value="/getCollectionList",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getCollectionList(@RequestBody Map<String, Object> source)
    {
        LogHelper.info("获取收藏文章列表："+ CommonHelper.showDetails(source));
        RequestArticle requestArticle=covertRequestArticle(source);
        String result= baseConfigService.getCollectionArticleList(requestArticle);
        return result;
    }

    private ArticleInfo covertArticleInfo(Map<String, Object> source)
    {
        ArticleInfo articleInfo=new ArticleInfo();
        if(null!=source.get("article_id") && !"".equals(source.get("article_id")))
        {
            articleInfo.setArticleId(Integer.valueOf(source.get("article_id").toString()));
        }
        articleInfo.setArticleTitle(CommonHelper.nullOrEmptyToString(source.get("article_title")));
        articleInfo.setArticleContent(CommonHelper.nullOrEmptyToString(source.get("article_content")));
        if(!CommonHelper.isNullOrEmpty(source.get("article_upuserId")))
        {
            articleInfo.setArticleUpUserid(Integer.valueOf(source.get("article_upuserId").toString()));
        }
        if(!CommonHelper.isNullOrEmpty(source.get("article_sucenter_Id")))
        {
            articleInfo.setArticleSubCenterid(Integer.valueOf(source.get("article_sucenter_Id").toString()));
        }
        articleInfo.setArticleIsDel(CommonHelper.nullOrEmptyToString(source.get("article_is_del")));
        //articleInfo
        articleInfo.setArticleUpTime(new Date());
        return articleInfo;
    }
    private RequestSubcenter covertRequestSubcenter(Map<String, Object> source)
    {
        RequestSubcenter requestSubcenter=new RequestSubcenter();
        if(!CommonHelper.isNullOrEmpty(source.get("pageNum")))
        {
            requestSubcenter.setPageNum(Integer.valueOf(source.get("pageNum").toString()));//当前页
        }
        if (!CommonHelper.isNullOrEmpty(source.get("pageSize"))) {
            requestSubcenter.setPageSize(Integer.valueOf(source.get("pageSize").toString()));//每页记录数
        }
        requestSubcenter.setSub_center_name(CommonHelper.nullOrEmptyToString(source.get("sub_center_name")));
        requestSubcenter.setSubcenterId(CommonHelper.nullOrEmptyToString(source.get("sub_center_id")));
        return requestSubcenter;
    }
    private SubCenterInfo covertSubCenterInfo(Map<String, Object> source)
    {
        SubCenterInfo subCenterInfo=new SubCenterInfo();
        if(null!=source.get("sub_id")||!"".equals(source.get("sub_id")))
        {
            subCenterInfo.setSubcenterId(Integer.valueOf(source.get("sub_id").toString()));
        }
        subCenterInfo.setSubcenterAddress(CommonHelper.nullOrEmptyToString(source.get("sub_address")));
        subCenterInfo.setSubcenterImage(CommonHelper.nullOrEmptyToString(source.get("sub_image")));
        subCenterInfo.setSubcenterName(CommonHelper.nullOrEmptyToString(source.get("sub_center_name")));
        subCenterInfo.setSubcenterPersonName(CommonHelper.nullOrEmptyToString(source.get("sub_person_name")));
        subCenterInfo.setSubcenterPersonMobile(CommonHelper.nullOrEmptyToString(source.get("sub_person_mobile")));
        subCenterInfo.setSubcenterCreatetime(new Date());
        subCenterInfo.setSubcenterUpdatetime(new Date());
        return subCenterInfo;
    }
    private RequestArticle covertRequestArticle(Map<String, Object> source)
    {
        RequestArticle requestArticle=new RequestArticle();
        if(!CommonHelper.isNullOrEmpty(source.get("userarticleId")))
        {
            requestArticle.setUserarticleId(Integer.valueOf(source.get("userarticleId").toString()));
        }
        if(!CommonHelper.isNullOrEmpty(source.get("articleId")))
        {
            requestArticle.setArticleId(Integer.valueOf(source.get("articleId").toString()));
        }
        if(!CommonHelper.isNullOrEmpty(source.get("userId")))
        {
            requestArticle.setUserId(Integer.valueOf(source.get("userId").toString()));
        }
        if(!CommonHelper.isNullOrEmpty(source.get("pageSize")))
        {
            requestArticle.setPageSize(Integer.valueOf(source.get("pageSize").toString()));
        }
        if(!CommonHelper.isNullOrEmpty(source.get("pageNum")))
        {
            requestArticle.setPageNum(Integer.valueOf(source.get("pageNum").toString()));
        }

        requestArticle.setStatus(CommonHelper.nullOrEmptyToString(source.get("status").toString()));
        return requestArticle;
    }


}
