package com.cn.service;



import net.sf.json.JSONObject;

import java.util.Map;

/**
 * User: cuixiaowei
 * Date: 2018/11/1
 * PackageName: com.cn.service
 */
public interface TicketCodeService {
    public JSONObject createCode(Map<String, Object> source);
}
