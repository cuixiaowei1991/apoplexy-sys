package com.cn.service;

import com.cn.entity.pojo.RequestQuestion;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * User: cuixiaowei
 * Date: 2018/11/2
 * PackageName: com.cn.service
 */
public interface OfflineOperateService {
    public String submmitMessage(RequestQuestion requestQuestion);

    public JSONObject queryMessageLists(RequestQuestion requestQuestion);

    public String deleteMessage(Map<String, Object> source);

    public String submmitTopicQuestion(RequestQuestion requestQuestion);

    public JSONObject queryTopicLists(RequestQuestion requestQuestion);

    public String deleteTopic(Map<String, Object> source);
}
