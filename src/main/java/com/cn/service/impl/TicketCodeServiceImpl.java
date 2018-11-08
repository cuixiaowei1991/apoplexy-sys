package com.cn.service.impl;

import com.cn.MsgCode.MsgAndCode;
import com.cn.common.AuthorizerAccessToken;
import com.cn.common.CommonHelper;
import com.cn.common.LogHelper;
import com.cn.common.httpsPostMethod;
import com.cn.service.TicketCodeService;
import net.sf.json.JSONObject;
import org.json.JSONException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * User: cuixiaowei
 * Date: 2018/11/1
 * PackageName: com.cn.service.impl
 */
@Service
public class TicketCodeServiceImpl implements TicketCodeService {
    @Value("${ticketCode_url}")
    private String ticketCode_url;
    @Override
    public JSONObject createCode(Map<String, Object> source) {
        LogHelper.info("获取带参数的二维码，上传参数："+source.toString());
        JSONObject url_json=new JSONObject();
        if(CommonHelper.isNullOrEmpty(source))
        {
            url_json.put(MsgAndCode.RSP_CODE,MsgAndCode.NULL_CODE);
            url_json.put(MsgAndCode.RSP_DESC,MsgAndCode.NULL_MESSAGE);
            return url_json;
        }else
        {
            String fenzhongx_id=String.valueOf(source.get("subcenter_id"));
            String appid=String.valueOf(source.get("appid"));
            String url="";
            try {
                url="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+
                        URLEncoder.encode(getTicket(fenzhongx_id,appid),"utf-8");
                url_json.put("ticketUrl",url);
                url_json.put(MsgAndCode.RSP_CODE,MsgAndCode.SUCCESS_CODE);
                url_json.put(MsgAndCode.RSP_DESC,MsgAndCode.SUCCESS_MESSAGE);
                LogHelper.info("生成的分中心二维码："+url+"，分中心为："+fenzhongx_id);
                return url_json;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return url_json;
    }
    /**
     * 获取ticket
     *
     * @param subcenter_id 推广分中心ID
     * @param appid 商户微信appid
     */
    public String getTicket(String subcenter_id,String appid)
    {
        LogHelper.info("获取ticket用来生成永久二维码，推广分中心ID：------》"+subcenter_id+"商户appid--------------》"+appid);
        AuthorizerAccessToken accessToken=new AuthorizerAccessToken(appid);
        String ticketinfo = httpsPostMethod.sendHttpsPost(
                ticketCode_url+"?access_token=" + accessToken.getAccess_token()
                , ticketParams(subcenter_id),
                "获取ticket");
        String ticket="";
        try {
            JSONObject json_tick =JSONObject.fromObject(ticketinfo);
            ticket=json_tick.getString("ticket");

        } catch (JSONException e) {
            e.printStackTrace();

        }

        return ticket;
    }
    public String ticketParams(String subcenter_id)
    {
        String param=
                "{\"action_name\":\""+"QR_LIMIT_SCENE"+"\",\"action_info\":{\"scene\":{\"scene_id\":"+subcenter_id+"}}}";
        return param;
    }

    public  void main(String[] args) {
        Map<String, Object> source =new HashMap<>();
        source.put("subcenter_id","sub_01");
        source.put("appid","wx3fc913b1cac94b23");
        this.createCode(source);
    }
}
