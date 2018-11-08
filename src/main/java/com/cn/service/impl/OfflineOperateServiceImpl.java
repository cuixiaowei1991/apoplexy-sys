package com.cn.service.impl;

import com.cn.MsgCode.MsgAndCode;
import com.cn.common.CommonHelper;
import com.cn.common.LogHelper;
import com.cn.dao.MessageHCountMapper;
import com.cn.dao.MessageInfoMapper;
import com.cn.dao.TopicInfoMapper;
import com.cn.dao.UserInfoMapper;
import com.cn.entity.MessageHCount;
import com.cn.entity.MessageInfo;
import com.cn.entity.TopicInfo;
import com.cn.entity.UserInfo;
import com.cn.entity.pojo.RequestQuestion;
import com.cn.service.OfflineOperateService;
import com.yunrich.monster.common.paginator.PageList;
import com.yunrich.monster.common.paginator.Paginator;
import net.sf.json.JSONObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * User: cuixiaowei
 * Date: 2018/11/2
 * PackageName: com.cn.service.impl
 */
@Service
public class OfflineOperateServiceImpl implements OfflineOperateService {
    @Autowired(required=false)
    private TopicInfoMapper topicInfoMapper;
    @Autowired(required=false)
    private MessageInfoMapper messageInfoMapper;
    @Autowired(required=false)
    private UserInfoMapper userInfoMapper;
    @Autowired(required=false)
    private MessageHCountMapper messageHCountMapper;
    @Override
    public String submmitMessage(RequestQuestion requestQuestion) {
        JSONObject jsonObject=new JSONObject();
        MessageInfo messageInfo=covertMessageInfo(requestQuestion);
        int result= messageInfoMapper.insertSelective(messageInfo);
        if(1==result)
        {
            MessageHCount queryParam=new MessageHCount();
            queryParam.setHcountTopicId(Integer.valueOf(messageInfo.getTopicId()));
            MessageHCount messageHCount=messageHCountMapper.selectByEntity(queryParam);
            if(null==messageHCount)
            {
                messageHCount=new MessageHCount();
                messageHCount.setHcountTopicId(Integer.valueOf(messageInfo.getTopicId()));
                messageHCount.setHcountTmsCount(1);
                messageHCount.setHcountUpdatetime(new Date());
                messageHCountMapper.insertSelective(messageHCount);
            }
            else
            {
                int historyCount=messageHCount.getHcountTmsCount();
                historyCount=++historyCount;
                messageHCount.setHcountTmsCount(historyCount);
                messageHCount.setHcountUpdatetime(new Date());
                messageHCountMapper.updateByPrimaryKeySelective(messageHCount);
            }
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
    public JSONObject queryMessageLists(RequestQuestion requestQuestion) {
        JSONObject wechatJos=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        PageList pageList= messagePageList(requestQuestion);
        if(pageList != null && pageList.size()>0)
        {
            List<MessageInfo> wcpList= (List<MessageInfo>) pageList.get(0);
            for(MessageInfo messageInfo :wcpList)
            {
                UserInfo userInfo=new UserInfo();
                userInfo.setUserinfoWechatOpenid(messageInfo.getMessageOpenid());
                userInfo.setUserinfoWechatAppid(messageInfo.getMessageAppid());
                UserInfo userInfo1=userInfoMapper.selectByUserInfo(userInfo);
                JSONObject message=new JSONObject();
                message.put("openid", CommonHelper.nullOrEmptyToString(messageInfo.getMessageOpenid()));
                message.put("message_id",CommonHelper.nullOrEmptyToString(messageInfo.getMessageId()));
                message.put("message_content",CommonHelper.nullOrEmptyToString(messageInfo.getMessageContent()));
                message.put("topic_id",CommonHelper.nullOrEmptyToString(messageInfo.getTopicId()));
                message.put("message_createtime",CommonHelper.formatTime(messageInfo.getMessageCreatetime(),"yyyy-MM-dd HH:mm:ss"));
                message.put("message_type",CommonHelper.nullOrEmptyToString(messageInfo.getMessageType()));
                message.put("message_sub_centerid",CommonHelper.nullOrEmptyToString(messageInfo.getUserCenterId()));
                message.put("message_userinfo",CommonHelper.nullOrEmptyToString(messageInfo.getMessageUserDes()));
                message.put("message_appid",CommonHelper.nullOrEmptyToString(messageInfo.getMessageAppid()));
                message.put("message_replyTime",CommonHelper.formatTime(messageInfo.getAdviceReplyTime(),"yyyy-MM-dd HH:mm:ss"));
                if(userInfo1!=null)
                {
                    message.put("user_id",CommonHelper.nullOrEmptyToString(userInfo1.getUserinfoId()));
                    message.put("user_wechat_name",CommonHelper.nullOrEmptyToString(userInfo1.getUserinfoWechatName()));
                    message.put("user_wechat_image",CommonHelper.nullOrEmptyToString(userInfo1.getUserinfoWechatHeadimage()));
                    message.put("user_des",CommonHelper.nullOrEmptyToString(userInfo1.getUserDes()));
                    message.put("user_type",CommonHelper.nullOrEmptyToString(userInfo1.getUserType()));
                }else
                {
                    message.put("user_id","");
                    message.put("user_wechat_name","");
                    message.put("user_wechat_image","");
                    message.put("user_des","");
                    message.put("user_type","");
                }
                jsonArray.put(message);
            }
            wechatJos.put("lists",jsonArray);
            wechatJos.put("pageNum",requestQuestion.getPageNum());
            wechatJos.put("pageSize",requestQuestion.getPageSize());
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
        return wechatJos;
    }

    @Override
    public String deleteMessage(Map<String, Object> source) {

        JSONObject jsonObject=new JSONObject();
        if(CommonHelper.isNullOrEmpty(source.get("message_id")))
        {
            jsonObject.put(MsgAndCode.RSP_CODE,MsgAndCode.NULL_CODE);
            jsonObject.put(MsgAndCode.RSP_DESC,MsgAndCode.NULL_MESSAGE);
            return jsonObject.toString();
        }
        int res=messageInfoMapper.deleteByPrimaryKey(Integer.valueOf(source.get("message_id").toString()));
        if(1==res)
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
    public String submmitTopicQuestion(RequestQuestion requestQuestion) {
        LogHelper.info("提交的讨论主题topic："+CommonHelper.showDetails(requestQuestion));
        JSONObject jsonObject=new JSONObject();
        TopicInfo topicInfo=coverTopic(requestQuestion);
        int result= topicInfoMapper.insertSelective(topicInfo);
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
    public JSONObject queryTopicLists(RequestQuestion requestQuestion) {
        JSONObject wechatJos=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        PageList pageList= topicPageList(requestQuestion);
        if(pageList != null && pageList.size()>0)
        {
            List<TopicInfo> topicList= (List<TopicInfo>) pageList.get(0);
            for(TopicInfo topicInfo :topicList)
            {
                JSONObject topic=new JSONObject();
                topic.put("openid", CommonHelper.nullOrEmptyToString(topicInfo.getTopicOpenid()));
                topic.put("topic_appid",CommonHelper.nullOrEmptyToString(topicInfo.getTopicAppid()));
                topic.put("topic_content",CommonHelper.nullOrEmptyToString(topicInfo.getTopicContent()));
                topic.put("topic_id",CommonHelper.nullOrEmptyToString(topicInfo.getTopicId()));
                topic.put("topic_createTime",CommonHelper.formatTime(topicInfo.getTopicCreatetime(),"yyyy-MM-dd HH:mm:ss"));
                topic.put("topic_type",CommonHelper.nullOrEmptyToString(topicInfo.getTopicType()));
                topic.put("topic_label",CommonHelper.nullOrEmptyToString(topicInfo.getTopicLabel()));
                topic.put("topic_sub_centerid",CommonHelper.nullOrEmptyToString(topicInfo.getTopicSubCenterId()));
                Map newTopicCountMap=new HashMap();
                newTopicCountMap.put("topicId",topicInfo.getTopicId());
                int newTopicMessageCount=messageInfoMapper.queryPageCount(newTopicCountMap);
                int oldTopicMessageCount=messageHCountMapper.queryPageCount(newTopicCountMap);
                if(newTopicMessageCount > oldTopicMessageCount)
                {
                    topic.put("topic_isnew","true");
                }else
                {
                    topic.put("topic_isnew","false");
                }
                jsonArray.put(topic);
            }
            wechatJos.put("lists",jsonArray);
            wechatJos.put("pageNum",requestQuestion.getPageNum());
            wechatJos.put("pageSize",requestQuestion.getPageSize());
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
        LogHelper.info("获取讨论主题列表："+wechatJos);
        return wechatJos;
    }

    @Override
    public String deleteTopic(Map<String, Object> source) {
        JSONObject jsonObject=new JSONObject();
        if(CommonHelper.isNullOrEmpty(source.get("topicId")))
        {
            jsonObject.put(MsgAndCode.RSP_CODE,MsgAndCode.NULL_CODE);
            jsonObject.put(MsgAndCode.RSP_DESC,MsgAndCode.NULL_MESSAGE);
            return jsonObject.toString();
        }
        int res=topicInfoMapper.deleteByPrimaryKey(Integer.valueOf(source.get("topicId").toString()));

        if(1==res)
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


    public PageList messagePageList(RequestQuestion requestQuestion){
        PageList pageList = new PageList();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("topicId",requestQuestion.getTopic_id());
        map.put("messageOpenid",requestQuestion.getOpenid());
        List<MessageInfo> newUsrMsgLogDos = new ArrayList<MessageInfo>();
        Paginator paginator = new Paginator();
        int count=messageInfoMapper.queryPageCount(map);
        if (0 == requestQuestion.getPageSize()) {
            paginator.setItemsPerPage(count);
        }else {
            paginator.setItemsPerPage(requestQuestion.getPageSize());
        }
        paginator.setPage(requestQuestion.getPageNum());
        paginator.setItems(count);
        pageList.setPaginator(paginator);
        if(paginator.getPage() == requestQuestion.getPageNum()) {
            if (paginator.getBeginIndex() <= paginator.getItems()) {
                map.put("endRow", paginator.getEndIndex()+"");
                map.put("startRow", paginator.getBeginIndex()+"");
                List<MessageInfo> messageInfos = messageInfoMapper.queryPageByConditons(map);
                if (null == messageInfos || messageInfos.size() == 0) {
                    LogHelper.info("未查询到满足条件的咨询回复消息列表！");
                }
                newUsrMsgLogDos.addAll(messageInfos);
                pageList.add(newUsrMsgLogDos);
                LogHelper.info("查询满足条件的咨询回复消息数：【count="+paginator.getItems()+"】");
            }
        } else {
            pageList.add(newUsrMsgLogDos);
        }
        return pageList;
    }

    public PageList topicPageList(RequestQuestion requestQuestion)
    {
        PageList pageList = new PageList();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("subCenterId",requestQuestion.getSub_center_id());
        List<TopicInfo> newUsrMsgLogDos = new ArrayList<TopicInfo>();
        Paginator paginator = new Paginator();
        int count=topicInfoMapper.queryPageCount(map);
        if (0 == requestQuestion.getPageSize()) {
            paginator.setItemsPerPage(count);
        }else {
            paginator.setItemsPerPage(requestQuestion.getPageSize());
        }
        paginator.setPage(requestQuestion.getPageNum());
        paginator.setItems(count);
        pageList.setPaginator(paginator);
        if(paginator.getPage() == requestQuestion.getPageNum()) {
            if (paginator.getBeginIndex() <= paginator.getItems()) {
                map.put("endRow", paginator.getEndIndex()+"");
                map.put("startRow", paginator.getBeginIndex()+"");
                List<TopicInfo> topicInfos = topicInfoMapper.queryPageByConditons(map);
                if (null == topicInfos || topicInfos.size() == 0) {
                    LogHelper.info("未查询到满足条件的咨询主题列表！");
                }
                newUsrMsgLogDos.addAll(topicInfos);
                pageList.add(newUsrMsgLogDos);
                LogHelper.info("查询满足条件的咨询主题消息数：【count="+paginator.getItems()+"】");
            }
        } else {
            pageList.add(newUsrMsgLogDos);
        }
        return pageList;
    }
    public MessageInfo covertMessageInfo(RequestQuestion requestQuestion)
    {
        MessageInfo messageInfo=new MessageInfo();
        messageInfo.setMessageContent(requestQuestion.getMessage_content());
        messageInfo.setMessageOpenid(requestQuestion.getOpenid());
        messageInfo.setMessageType(requestQuestion.getMessage_type());
        messageInfo.setTopicId(requestQuestion.getTopic_id());
        messageInfo.setMessageCreatetime(new Date());
        messageInfo.setAdviceReplyTime(new Date());
        messageInfo.setMessageAppid(requestQuestion.getAppid());
        UserInfo userQuery=new UserInfo();
        userQuery.setUserinfoWechatAppid(requestQuestion.getAppid());
        userQuery.setUserinfoWechatOpenid(requestQuestion.getOpenid());
        UserInfo userInfo=userInfoMapper.selectByUserInfo(userQuery);
        if(userInfo!=null)
        {
            messageInfo.setUserCenterId(userInfo.getUserinfoSubCenterId()==null?0:userInfo.getUserinfoSubCenterId());
            messageInfo.setMessageUserDes(userInfo.getUserDes()==null?"":userInfo.getUserDes());
        }
        return messageInfo;

    }

    public TopicInfo coverTopic(RequestQuestion requestQuestion)
    {
         TopicInfo topicInfo=new TopicInfo();
        topicInfo.setTopicAppid(requestQuestion.getAppid());
        topicInfo.setTopicContent(requestQuestion.getMessage_content());
        topicInfo.setTopicCreatetime(new Date());
        topicInfo.setTopicLabel(requestQuestion.getLabel());
        topicInfo.setTopicOpenid(requestQuestion.getOpenid());
        topicInfo.setTopicType(requestQuestion.getTopic_type());
        UserInfo userQuery=new UserInfo();
        userQuery.setUserinfoWechatAppid(requestQuestion.getAppid());
        userQuery.setUserinfoWechatOpenid(requestQuestion.getOpenid());
        UserInfo userInfo=userInfoMapper.selectByUserInfo(userQuery);
        if(userInfo!=null)
        {
            topicInfo.setTopicSubCenterId(userInfo.getUserinfoSubCenterId()==null?0:userInfo.getUserinfoSubCenterId());
            //topicInfo.setMessageUserDes(userInfo.getUserDes()==null?"":userInfo.getUserDes());
        }
        //topicInfo.setTopicSubCenterId(requestQuestion.getTopic_type());
         return topicInfo;
    }
}
