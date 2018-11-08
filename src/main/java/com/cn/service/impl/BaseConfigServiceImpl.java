package com.cn.service.impl;

import com.cn.MsgCode.MsgAndCode;
import com.cn.common.CommonHelper;
import com.cn.common.LogHelper;
import com.cn.dao.*;
import com.cn.entity.*;
import com.cn.entity.pojo.RequestArticle;
import com.cn.entity.pojo.RequestQuestion;
import com.cn.entity.pojo.RequestSubcenter;
import com.cn.entity.pojo.RequsetUserInfo;
import com.cn.service.BaseConfigService;
import com.yunrich.monster.common.paginator.PageList;
import com.yunrich.monster.common.paginator.Paginator;
import net.sf.json.JSONObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.*;

/**
 * User: cuixiaowei
 * Date: 2018/11/4
 * PackageName: com.cn.service.impl
 */
@Service
public class BaseConfigServiceImpl implements BaseConfigService {
    @Autowired(required=false)
    private SubCenterInfoMapper subCenterInfoMapper;
    @Autowired(required=false)
    private ArticleInfoMapper articleInfoMapper;
    @Autowired(required=false)
    private UserArticleInfoMapper userArticleInfoMapper;
    @Autowired(required=false)
    private UserInfoMapper userInfoMapper;

    @Override
    public String addSubCenter(SubCenterInfo subCenterInfo) {
        JSONObject jsonObject=new JSONObject();
        LogHelper.info("新增分中心信息："+CommonHelper.showDetails(subCenterInfo));
        int result=subCenterInfoMapper.insertSelective(subCenterInfo);
        if(1==result)
        {
            jsonObject.put(MsgAndCode.RSP_CODE,MsgAndCode.SUCCESS_CODE);
            jsonObject.put(MsgAndCode.RSP_DESC,MsgAndCode.SUCCESS_MESSAGE);
        }else
        {
            jsonObject.put(MsgAndCode.RSP_CODE,MsgAndCode.FAILE_CODE);
            jsonObject.put(MsgAndCode.RSP_DESC,MsgAndCode.FAILE_MESSAGE);
        }
        return jsonObject.toString();
    }

    @Override
    public String updateSubCenter(SubCenterInfo subCenterInfo) {
        JSONObject jsonObject=new JSONObject();
        subCenterInfo.setSubcenterCreatetime(null);
        LogHelper.info("修改分中心信息："+CommonHelper.showDetails(subCenterInfo));
        int result=subCenterInfoMapper.updateByPrimaryKeySelective(subCenterInfo);
        if(1==result)
        {
            jsonObject.put(MsgAndCode.RSP_CODE,MsgAndCode.SUCCESS_CODE);
            jsonObject.put(MsgAndCode.RSP_DESC,MsgAndCode.SUCCESS_MESSAGE);
        }else
        {
            jsonObject.put(MsgAndCode.RSP_CODE,MsgAndCode.FAILE_CODE);
            jsonObject.put(MsgAndCode.RSP_DESC,MsgAndCode.FAILE_MESSAGE);
        }
        return jsonObject.toString();
    }

    @Override
    public String querySubCenterList(RequestSubcenter requestSubcenter) {
        JSONObject wechatJos=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        PageList pageList= messagePageList(requestSubcenter);
        if(pageList != null && pageList.size()>0)
        {
            List<SubCenterInfo> wcpList= (List<SubCenterInfo>) pageList.get(0);
            for(SubCenterInfo subCenterInfo:wcpList)
            {
                JSONObject message=new JSONObject();
                message.put("subcenter_id", CommonHelper.nullOrEmptyToString(subCenterInfo.getSubcenterId()));
                message.put("subcenter_name",CommonHelper.nullOrEmptyToString(subCenterInfo.getSubcenterName()));
                message.put("subcenter_image",CommonHelper.nullOrEmptyToString(subCenterInfo.getSubcenterImage()));
                message.put("subcenter_address",CommonHelper.nullOrEmptyToString(subCenterInfo.getSubcenterAddress()));
                message.put("subcenter_createtime",CommonHelper.formatTime(subCenterInfo.getSubcenterCreatetime(),"yyyy-MM-dd HH:mm:ss"));
                message.put("subcenter_person_name",CommonHelper.nullOrEmptyToString(subCenterInfo.getSubcenterPersonName()));
                message.put("subcenter_person_mobile",CommonHelper.nullOrEmptyToString(subCenterInfo.getSubcenterPersonMobile()));
                message.put("subcenter_updatetime",CommonHelper.formatTime(subCenterInfo.getSubcenterUpdatetime(),"yyyy-MM-dd HH:mm:ss"));
                jsonArray.put(message);
            }
            wechatJos.put("lists",jsonArray);
            wechatJos.put("pageNum",requestSubcenter.getPageNum());
            wechatJos.put("pageSize",requestSubcenter.getPageSize());
            wechatJos.put("total",pageList.getPaginator().getItemsPerPage());
            if(jsonArray!=null && jsonArray.length()>0)
            {
                wechatJos.put(MsgAndCode.RSP_CODE,MsgAndCode.SUCCESS_CODE);
                wechatJos.put(MsgAndCode.RSP_DESC,MsgAndCode.SUCCESS_MESSAGE);
            }
            else
            {
                wechatJos.put(MsgAndCode.RSP_CODE,MsgAndCode.FAILE_CODE);
                wechatJos.put(MsgAndCode.RSP_DESC,MsgAndCode.FAILE_MESSAGE);
            }
        }
        LogHelper.info("获取回复信息列表："+wechatJos);
        return wechatJos.toString();
    }

    @Override
    public String addArticle(ArticleInfo articleInfo) {
        JSONObject jsonObject=new JSONObject();
        LogHelper.info("发布文章信息："+CommonHelper.showDetails(articleInfo));
        int result=articleInfoMapper.insertSelective(articleInfo);
        if(1==result)
        {
            jsonObject.put(MsgAndCode.RSP_CODE,MsgAndCode.SUCCESS_CODE);
            jsonObject.put(MsgAndCode.RSP_DESC,MsgAndCode.SUCCESS_MESSAGE);
        }else
        {
            jsonObject.put(MsgAndCode.RSP_CODE,MsgAndCode.FAILE_CODE);
            jsonObject.put(MsgAndCode.RSP_DESC,MsgAndCode.FAILE_MESSAGE);
        }
        return jsonObject.toString();
    }

    @Override
    public String updateArticle(ArticleInfo articleInfo) {
        JSONObject jsonObject=new JSONObject();
        LogHelper.info("修改文章信息："+CommonHelper.showDetails(articleInfo));
        int result=articleInfoMapper.updateByPrimaryKeySelective(articleInfo);
        if(1==result)
        {
            jsonObject.put(MsgAndCode.RSP_CODE,MsgAndCode.SUCCESS_CODE);
            jsonObject.put(MsgAndCode.RSP_DESC,MsgAndCode.SUCCESS_MESSAGE);
        }else
        {
            jsonObject.put(MsgAndCode.RSP_CODE,MsgAndCode.FAILE_CODE);
            jsonObject.put(MsgAndCode.RSP_DESC,MsgAndCode.FAILE_MESSAGE);
        }
        return jsonObject.toString();
    }

    @Override
    public String getArticleLists(Map<String, Object> source) {
        RequestArticle article=new RequestArticle();
        JSONObject wechatJos=new JSONObject();
        if(!CommonHelper.isNullOrEmpty(source.get("sub_centerId")))
        {
            article.setArticleSubCenterid(Integer.valueOf(source.get("sub_centerId").toString()));
        }
        article.setArticleTitle(CommonHelper.nullOrEmptyToString(source.get("article_title")));
        if(!CommonHelper.isNullOrEmpty(source.get("article_upuserId")))
        {
            article.setArticleUpUserid(Integer.valueOf(source.get("article_upuserId").toString()));
        }
        if(CommonHelper.isNullOrEmpty(source.get("pageNum"))||CommonHelper.isNullOrEmpty(source.get("pageSize")))
        {
            wechatJos.put(MsgAndCode.RSP_CODE,MsgAndCode.NULL_CODE);
            wechatJos.put(MsgAndCode.RSP_DESC,MsgAndCode.NULL_CODE);
            return wechatJos.toString();
        }
        article.setPageNum(Integer.valueOf(source.get("pageNum").toString()));
        article.setPageSize(Integer.valueOf(source.get("pageSize").toString()));

        JSONArray jsonArray=new JSONArray();
        PageList pageList= articlePageList(article);
        if(pageList != null && pageList.size()>0)
        {
            List<ArticleInfo> wcpList = (List<ArticleInfo>) pageList.get(0);
            for(ArticleInfo articleInfo:wcpList)
            {
                JSONObject message=new JSONObject();
                message.put("article_id", CommonHelper.nullOrEmptyToString(articleInfo.getArticleId()));
                message.put("article_title",CommonHelper.nullOrEmptyToString(articleInfo.getArticleTitle()));
                message.put("article_content",CommonHelper.nullOrEmptyToString(articleInfo.getArticleContent()));
                message.put("article_upuserId",CommonHelper.nullOrEmptyToString(articleInfo.getArticleUpUserid()));
                message.put("article_uptime",CommonHelper.formatTime(articleInfo.getArticleSubCenterid(),"yyyy-MM-dd HH:mm:ss"));
                message.put("article_subcenterId",CommonHelper.nullOrEmptyToString(articleInfo.getArticleSubCenterid()));
                jsonArray.put(message);
            }
        }
        wechatJos.put("lists",jsonArray);
        wechatJos.put("pageNum",article.getPageNum());
        wechatJos.put("pageSize",article.getPageSize());
        wechatJos.put("total",pageList.getPaginator().getItemsPerPage());
        if(jsonArray!=null && jsonArray.length()>0)
        {
            wechatJos.put(MsgAndCode.RSP_CODE,MsgAndCode.SUCCESS_CODE);
            wechatJos.put(MsgAndCode.RSP_DESC,MsgAndCode.SUCCESS_MESSAGE);
        }
        else
        {
            wechatJos.put(MsgAndCode.RSP_CODE,MsgAndCode.FAILE_CODE);
            wechatJos.put(MsgAndCode.RSP_DESC,MsgAndCode.FAILE_MESSAGE);
        }
        LogHelper.info("获取文章列表："+wechatJos);
        return wechatJos.toString();
    }

    @Override
    public String collectionArticle(RequestArticle requestArticle) {
        JSONObject jsonObject=new JSONObject();
        if(CommonHelper.isNullOrEmpty(requestArticle.getArticleId())
                || CommonHelper.isNullOrEmpty(requestArticle.getUserId())
                || CommonHelper.isNullOrEmpty(requestArticle.getStatus()))
        {
            jsonObject.put(MsgAndCode.RSP_CODE,MsgAndCode.NULL_CODE);
            jsonObject.put(MsgAndCode.RSP_DESC,MsgAndCode.NULL_MESSAGE);
            return jsonObject.toString();
        }
        UserArticleInfo userArticleInfo=new UserArticleInfo();
        userArticleInfo.setUserarticleArticleId(requestArticle.getArticleId());
        userArticleInfo.setUserarticleStatus(requestArticle.getStatus());
        userArticleInfo.setUserarticleTime(new Date());
        userArticleInfo.setUserarticleUserId(requestArticle.getUserId());
        int result=userArticleInfoMapper.insertSelective(userArticleInfo);
        if(1==result)
        {
            jsonObject.put(MsgAndCode.RSP_CODE,MsgAndCode.SUCCESS_CODE);
            jsonObject.put(MsgAndCode.RSP_DESC,MsgAndCode.SUCCESS_MESSAGE);
        }else
        {
            jsonObject.put(MsgAndCode.RSP_CODE,MsgAndCode.FAILE_CODE);
            jsonObject.put(MsgAndCode.RSP_DESC,MsgAndCode.FAILE_MESSAGE);
        }
        return jsonObject.toString();
    }

    @Override
    public String getCollectionArticleList(RequestArticle requestArticle) {
        JSONObject wechatJos=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        PageList pageList= collectionActicleList(requestArticle);
        if(pageList != null && pageList.size()>0)
        {
            List<UserArticleInfo> wcpList= (List<UserArticleInfo>) pageList.get(0);
            for(UserArticleInfo userArticleInfo:wcpList)
            {
                JSONObject message=new JSONObject();
                ArticleInfo articleInfo=articleInfoMapper.selectByPrimaryKey(userArticleInfo.getUserarticleArticleId());
                if(null==articleInfo)
                {
                    LogHelper.info("该文章id："+userArticleInfo.getUserarticleArticleId()+"不存在ArticleInfo表的对应信息！");
                    continue;
                }
                message.put("userarticleId",userArticleInfo.getUserarticleId());
                message.put("article_id", CommonHelper.nullOrEmptyToString(articleInfo.getArticleId()));
                message.put("article_title",CommonHelper.nullOrEmptyToString(articleInfo.getArticleTitle()));
                message.put("article_content",CommonHelper.nullOrEmptyToString(articleInfo.getArticleContent()));
                message.put("article_upuserId",CommonHelper.nullOrEmptyToString(articleInfo.getArticleUpUserid()));
                message.put("article_uptime",CommonHelper.formatTime(articleInfo.getArticleSubCenterid(),"yyyy-MM-dd HH:mm:ss"));
                message.put("article_subcenterId",CommonHelper.nullOrEmptyToString(articleInfo.getArticleSubCenterid()));
                jsonArray.put(message);
            }
        }
        wechatJos.put("lists",jsonArray);

        wechatJos.put("pageNum",requestArticle.getPageNum());
        wechatJos.put("pageSize",requestArticle.getPageSize());
        wechatJos.put("total",pageList.getPaginator().getItemsPerPage());
        if(jsonArray!=null && jsonArray.length()>0)
        {
            wechatJos.put(MsgAndCode.RSP_CODE,MsgAndCode.SUCCESS_CODE);
            wechatJos.put(MsgAndCode.RSP_DESC,MsgAndCode.SUCCESS_MESSAGE);
        }
        else
        {
            wechatJos.put(MsgAndCode.RSP_CODE,MsgAndCode.FAILE_CODE);
            wechatJos.put(MsgAndCode.RSP_DESC,MsgAndCode.FAILE_MESSAGE);
        }
        LogHelper.info("获取收藏文章列表："+wechatJos);
        return wechatJos.toString();
    }

    @Override
    public String updateCollectionArticle(RequestArticle requestArticle) {
        JSONObject jsonObject=new JSONObject();
        UserArticleInfo userArticleInfo=new UserArticleInfo();
        userArticleInfo.setUserarticleStatus(requestArticle.getStatus());
        userArticleInfo.setUserarticleUserId(requestArticle.getUserId());
        userArticleInfo.setUserarticleArticleId(requestArticle.getArticleId());
        userArticleInfo.setUserarticleId(requestArticle.getUserarticleId());
        int result=userArticleInfoMapper.updateByPrimaryKeySelective(userArticleInfo);
        if(1==result)
        {
            jsonObject.put(MsgAndCode.RSP_CODE,MsgAndCode.SUCCESS_CODE);
            jsonObject.put(MsgAndCode.RSP_DESC,MsgAndCode.SUCCESS_MESSAGE);
        }else
        {
            jsonObject.put(MsgAndCode.RSP_CODE,MsgAndCode.FAILE_CODE);
            jsonObject.put(MsgAndCode.RSP_DESC,MsgAndCode.FAILE_MESSAGE);
        }
        return jsonObject.toString();
    }

    @Override
    public String updateUserInfo(UserInfo userInfo) {
        JSONObject jsonObject=new JSONObject();
        int result= userInfoMapper.updateByPrimaryKeySelective(userInfo);
        if(1==result)
        {
            jsonObject.put(MsgAndCode.RSP_CODE,MsgAndCode.SUCCESS_CODE);
            jsonObject.put(MsgAndCode.RSP_DESC,MsgAndCode.SUCCESS_MESSAGE);
        }else
        {
            jsonObject.put(MsgAndCode.RSP_CODE,MsgAndCode.FAILE_CODE);
            jsonObject.put(MsgAndCode.RSP_DESC,MsgAndCode.FAILE_MESSAGE);
        }
        return jsonObject.toString();
    }

    @Override
    public String getUserLists(RequsetUserInfo requsetUserInfo) {
        LogHelper.info("获取用户信息列表："+CommonHelper.showDetails(requsetUserInfo));
        JSONObject wechatJos=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        PageList pageList= userPageList(requsetUserInfo);
        if(pageList != null && pageList.size()>0)
        {
            List<UserInfo> wcpList= (List<UserInfo>) pageList.get(0);
            for(UserInfo userInfo:wcpList)
            {
                JSONObject message=new JSONObject();
                message.put("user_id", CommonHelper.nullOrEmptyToString(userInfo.getUserinfoId()));
                message.put("user_name",CommonHelper.nullOrEmptyToString(userInfo.getUserinfoName()));
                message.put("user_des",CommonHelper.nullOrEmptyToString(userInfo.getUserDes()));
                message.put("user_type",CommonHelper.nullOrEmptyToString(userInfo.getUserType()));
                message.put("user_wechat_name",CommonHelper.nullOrEmptyToString(userInfo.getUserinfoWechatName()));
                message.put("user_wechat_appid",CommonHelper.nullOrEmptyToString(userInfo.getUserinfoWechatAppid()));
                message.put("user_wechat_openid",CommonHelper.nullOrEmptyToString(userInfo.getUserinfoWechatOpenid()));
                message.put("user_subcenterId",CommonHelper.nullOrEmptyToString(userInfo.getUserinfoSubCenterId()));
                message.put("user_sex",CommonHelper.nullOrEmptyToString(userInfo.getUserinfoSex()));
                message.put("user_wechat_headimage",CommonHelper.nullOrEmptyToString(userInfo.getUserinfoWechatHeadimage()));
                message.put("user_age",CommonHelper.nullOrEmptyToString(userInfo.getUserinfoAge()));
                message.put("user_createtime",CommonHelper.formatTime(userInfo.getUserinfoCreatetime(),"yyyy-MM-dd HH:mm:ss"));
                message.put("user_issubscribe",CommonHelper.nullOrEmptyToString(userInfo.getUserinfoIsSubscribe()));
                jsonArray.put(message);
            }
        }
        wechatJos.put("lists",jsonArray);
        wechatJos.put("pageNum",requsetUserInfo.getPageNum());
        wechatJos.put("pageSize",requsetUserInfo.getPageSize());
        wechatJos.put("total",pageList.getPaginator().getItemsPerPage());
        if(jsonArray!=null && jsonArray.length()>0)
        {
            wechatJos.put(MsgAndCode.RSP_CODE,MsgAndCode.SUCCESS_CODE);
            wechatJos.put(MsgAndCode.RSP_DESC,MsgAndCode.SUCCESS_MESSAGE);
        }
        else
        {
            wechatJos.put(MsgAndCode.RSP_CODE,MsgAndCode.FAILE_CODE);
            wechatJos.put(MsgAndCode.RSP_DESC,MsgAndCode.FAILE_MESSAGE);
        }
        LogHelper.info("获取用户列表："+wechatJos);
        return wechatJos.toString();

    }


    public PageList messagePageList(RequestSubcenter requestSubcenter){
        PageList pageList = new PageList();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("subcenterId",requestSubcenter.getSubcenterId());
        map.put("subcenterName",requestSubcenter.getSub_center_name());
        List<SubCenterInfo> newUsrMsgLogDos = new ArrayList<SubCenterInfo>();
        Paginator paginator = new Paginator();
        int count=subCenterInfoMapper.queryPageCount(map);
        if (0 == requestSubcenter.getPageSize()) {
            paginator.setItemsPerPage(count);
        }else {
            paginator.setItemsPerPage(requestSubcenter.getPageSize());
        }
        paginator.setPage(requestSubcenter.getPageNum());
        paginator.setItems(count);
        pageList.setPaginator(paginator);
        if(paginator.getPage() == requestSubcenter.getPageNum()) {
            if (paginator.getBeginIndex() <= paginator.getItems()) {
                map.put("endRow", paginator.getEndIndex()+"");
                map.put("startRow", paginator.getBeginIndex()+"");
                List<SubCenterInfo> messageInfos = subCenterInfoMapper.queryPageByConditons(map);
                if (null == messageInfos || messageInfos.size() == 0) {
                    LogHelper.info("未查询到满足条件的分中心列表！");
                }
                newUsrMsgLogDos.addAll(messageInfos);
                pageList.add(newUsrMsgLogDos);
                LogHelper.info("查询满足条件的分中心列表：【count="+paginator.getItems()+"】");
            }
        } else {
            pageList.add(newUsrMsgLogDos);
        }
        return pageList;
    }

    public PageList articlePageList(RequestArticle article)
    {
        PageList pageList = new PageList();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("articleSubCenterid",article.getArticleSubCenterid());
        map.put("articleUpUserid",article.getArticleUpUserid());
        map.put("articleTitle",article.getArticleTitle());
        List<ArticleInfo> newUsrMsgLogDos = new ArrayList<ArticleInfo>();
        Paginator paginator = new Paginator();
        int count=articleInfoMapper.queryPageCount(map);
        if (0 == article.getPageSize()) {
            paginator.setItemsPerPage(count);
        }else {
            paginator.setItemsPerPage(article.getPageSize());
        }
        paginator.setPage(article.getPageNum());
        paginator.setItems(count);
        pageList.setPaginator(paginator);
        if(paginator.getPage() == article.getPageNum()) {
            if (paginator.getBeginIndex() <= paginator.getItems()) {
                map.put("endRow", paginator.getEndIndex()+"");
                map.put("startRow", paginator.getBeginIndex()+"");
                List<ArticleInfo> messageInfos = articleInfoMapper.queryPageByConditons(map);
                if (null == messageInfos || messageInfos.size() == 0) {
                    LogHelper.info("未查询到满足条件的文章信息列表！");
                }
                newUsrMsgLogDos.addAll(messageInfos);
                pageList.add(newUsrMsgLogDos);
                LogHelper.info("查询满足条件的文章信息列表：【count="+paginator.getItems()+"】");
            }
        } else {
            pageList.add(newUsrMsgLogDos);
        }
        return pageList;
    }

    public PageList collectionActicleList(RequestArticle requestArticle)
    {
        PageList pageList = new PageList();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userarticleUserId",requestArticle.getUserId());
        //map.put("subcenterName",requestArticle.getSub_center_name());
        List<UserArticleInfo> newUsrMsgLogDos = new ArrayList<UserArticleInfo>();
        Paginator paginator = new Paginator();
        int count=userArticleInfoMapper.queryPageCount(map);
        if (0 == requestArticle.getPageSize()) {
            paginator.setItemsPerPage(count);
        }else {
            paginator.setItemsPerPage(requestArticle.getPageSize());
        }
        paginator.setPage(requestArticle.getPageNum());
        paginator.setItems(count);
        pageList.setPaginator(paginator);
        if(paginator.getPage() == requestArticle.getPageNum()) {
            if (paginator.getBeginIndex() <= paginator.getItems()) {
                map.put("endRow", paginator.getEndIndex()+"");
                map.put("startRow", paginator.getBeginIndex()+"");
                List<UserArticleInfo> messageInfos = userArticleInfoMapper.queryPageByConditons(map);
                if (null == messageInfos || messageInfos.size() == 0) {
                    LogHelper.info("未查询到满足条件的收藏文章列表！");
                }
                newUsrMsgLogDos.addAll(messageInfos);
                pageList.add(newUsrMsgLogDos);
                LogHelper.info("查询满足条件的收藏文章列表：【count="+paginator.getItems()+"】");
            }
        } else {
            pageList.add(newUsrMsgLogDos);
        }
        return pageList;
    }

    public PageList userPageList(RequsetUserInfo requsetUserInfo)
    {
        PageList pageList = new PageList();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userinfoSubCenterId",requsetUserInfo.getSubcenterid());
        map.put("userinfoName",requsetUserInfo.getUsername());
        map.put("userinfoAge",requsetUserInfo.getUserage());
        map.put("userinfoSex",requsetUserInfo.getUsersex());
        List<UserInfo> newUsrMsgLogDos = new ArrayList<UserInfo>();
        Paginator paginator = new Paginator();
        int count=userInfoMapper.queryPageCount(map);
        if (0 == requsetUserInfo.getPageSize()) {
            paginator.setItemsPerPage(count);
        }else {
            paginator.setItemsPerPage(requsetUserInfo.getPageSize());
        }
        paginator.setPage(requsetUserInfo.getPageNum());
        paginator.setItems(count);
        pageList.setPaginator(paginator);
        if(paginator.getPage() == requsetUserInfo.getPageNum()) {
            if (paginator.getBeginIndex() <= paginator.getItems()) {
                map.put("endRow", paginator.getEndIndex()+"");
                map.put("startRow", paginator.getBeginIndex()+"");
                List<UserInfo> messageInfos = userInfoMapper.queryPageByConditons(map);
                if (null == messageInfos || messageInfos.size() == 0) {
                    LogHelper.info("未查询到满足条件的用户信息列表！");
                }
                newUsrMsgLogDos.addAll(messageInfos);
                pageList.add(newUsrMsgLogDos);
                LogHelper.info("查询满足条件的用户信息列表：【count="+paginator.getItems()+"】");
            }
        } else {
            pageList.add(newUsrMsgLogDos);
        }
        return pageList;
    }
}
