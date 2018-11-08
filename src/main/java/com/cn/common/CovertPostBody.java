package com.cn.common;

import com.cn.entity.pojo.RequestBody;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * User: cuixiaowei
 * Date: 2018/11/1
 * PackageName: com.cn.common
 */
public class CovertPostBody {
    public static RequestBody covert(HttpServletRequest request)
    {
        String postStr = null;
        BufferedReader br = null;
        try {
            br = request.getReader();
            String buffer = null;
            StringBuffer buff = new StringBuffer();
            while ((buffer = br.readLine()) != null) {
                buff.append(buffer + "\n");
            }
            br.close();
            postStr = buff.toString();
            LogHelper.info("接收上游三方平台post发送过来的数据:\n" + postStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        RequestBody requestBody=new RequestBody();
        JSONObject jsonObject= JSONObject.fromObject(postStr);
        requestBody.setOpenid(jsonObject.getString("FromUserName"));
        requestBody.setToUsername(jsonObject.getString("ToUserName"));
        requestBody.setSendTime(CommonHelper.stampToDate(jsonObject.getString("CreateTime")));
        requestBody.setMarchant_appid(jsonObject.getString("marchant_appid"));
        if("text".equals(jsonObject.getString("MsgType")))
        {
            requestBody.setContent(jsonObject.getString("Content"));
        }
        else if("image".equals(jsonObject.getString("MsgType"))
                ||"video".equals(jsonObject.getString("MsgType"))
                ||"voice".equals(jsonObject.getString("MsgType")))
        {
            requestBody.setContent(jsonObject.getString("MediaId"));
        }
        else if("event".equals(jsonObject.getString("MsgType")))
        {
            if("subscribe".equals(jsonObject.getString("Event")))
            {
                if((jsonObject.getString("EventKey")==null?"":jsonObject.getString("EventKey")).contains("qrscene_"))
                {
                    LogHelper.info("扫描带分中心ID的二维码关注该公众号，"+"分中心ID："+jsonObject.getString("EventKey").substring(8));
                    requestBody.setSubcenter_id(jsonObject.getString("EventKey").substring(8));
                    requestBody.setIsChangJingSub("true");
                }
                else
                {
                    LogHelper.info("通过搜索关注该公众号，没有指定分中心ID");
                    requestBody.setIsChangJingSub("true");
                }
            }
            else if("unsubscribe".equals(jsonObject.getString("Event")))
            {
                requestBody.setIsChangJingSub("unsubscribe");
            }
        }
        requestBody.setBodyJson(jsonObject);
        return requestBody;
    }


}
