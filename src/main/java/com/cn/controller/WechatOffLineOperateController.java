package com.cn.controller;

import com.cn.common.CommonHelper;
import com.cn.common.LogHelper;
import com.cn.entity.pojo.RequestQuestion;
import com.cn.service.OfflineOperateService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * User: cuixiaowei
 * Date: 2018/11/2
 * PackageName: com.cn.controller
 */
@Controller
@RequestMapping("/offlineOpe")
public class WechatOffLineOperateController {

    @Autowired
    private OfflineOperateService offlineOperateService;

    @RequestMapping(value="/submmitMessage",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String submmitMessage(@RequestBody Map<String, Object> source)
    {
        LogHelper.info("提交回复信息："+ CommonHelper.showDetails(source));
        RequestQuestion requestQuestion=covert(source);
        String result= offlineOperateService.submmitMessage(requestQuestion);
        return result;
    }
    @RequestMapping(value="/deleteMessage",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String deleteMessage(@RequestBody Map<String, Object> source)
    {
        LogHelper.info("删除回复信息："+ CommonHelper.showDetails(source));

        String result= offlineOperateService.deleteMessage(source);
        return result;
    }
    @RequestMapping(value="/submmitTopic",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String submmitTopic(@RequestBody Map<String, Object> source)
    {
        LogHelper.info("提交咨询信息："+ CommonHelper.showDetails(source));
        RequestQuestion requestQuestion=covert(source);
        String result= offlineOperateService.submmitTopicQuestion(requestQuestion);
        return result;
    }

    @RequestMapping(value="/getTopicList",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getTopicList(@RequestBody Map<String, Object> source)
    {
        LogHelper.info("获取讨论主题列表："+ CommonHelper.showDetails(source));
        RequestQuestion requestQuestion=covert(source);
        JSONObject topicsJson= offlineOperateService.queryTopicLists(requestQuestion);
        return topicsJson.toString();
    }
    @RequestMapping(value="/deleteTopic",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String deleteTopic(@RequestBody Map<String, Object> source)
    {
        LogHelper.info("删除主题信息："+ CommonHelper.showDetails(source));

        String result= offlineOperateService.deleteTopic(source);
        return result;
    }

    @RequestMapping(value="/getMessageList",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getMessageList(@RequestBody Map<String, Object> source)
    {
        LogHelper.info("获取回复消息列表："+ CommonHelper.showDetails(source));
        RequestQuestion requestQuestion=covert(source);
        JSONObject topicsJson= offlineOperateService.queryMessageLists(requestQuestion);
        return topicsJson.toString();
    }

    private RequestQuestion covert(Map<String, Object> source)
    {
        RequestQuestion requestQuestion=new RequestQuestion();
        requestQuestion.setAppid(CommonHelper.nullToString(source.get("appid")));
        requestQuestion.setMessage_content(CommonHelper.nullToString(source.get("messageContent")));
        requestQuestion.setMessage_type(CommonHelper.nullToString(source.get("messageType")));
        requestQuestion.setOpenid(CommonHelper.nullToString(source.get("openid")));

        if(!"".equals(CommonHelper.nullToString(source.get("message_id"))))
        {
            requestQuestion.setMessageId(Integer.valueOf(source.get("message_id").toString()));
        }

        if(!"".equals(CommonHelper.nullToString(source.get("topic_id"))))
        {
            requestQuestion.setTopic_id(CommonHelper.nullToString(source.get("topic_id")));
        }
        if(!"".equals(CommonHelper.nullToString(source.get("sub_centerid"))))
        {
            requestQuestion.setSub_center_id(Integer.valueOf(CommonHelper.nullToString(source.get("sub_centerid"))));
        }
        requestQuestion.setLabel(CommonHelper.nullToString(source.get("label")));
        if(!"".equals(CommonHelper.nullToString(source.get("pageSize"))))
        {
            requestQuestion.setPageSize(Integer.valueOf(CommonHelper.nullToString(source.get("pageSize"))));
        }
        if(!"".equals(CommonHelper.nullToString(source.get("pageNum"))))
        {
            requestQuestion.setPageNum(Integer.valueOf(CommonHelper.nullToString(source.get("pageNum"))));
        }

        return requestQuestion;
    }
}
