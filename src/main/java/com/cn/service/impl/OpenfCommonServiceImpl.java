package com.cn.service.impl;

import com.cn.common.CommonHelper;
import com.cn.common.LogHelper;
import com.cn.common.httpsPostMethod;
import com.cn.dao.UserInfoMapper;
import com.cn.entity.UserInfo;
import com.cn.entity.pojo.RequestBody;
import com.cn.service.OpenfCommonService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * User: cuixiaowei
 * Date: 2018/11/1
 * PackageName: com.cn.service.impl
 */
@Service
public class OpenfCommonServiceImpl implements OpenfCommonService {
    @Autowired(required=false)
    private UserInfoMapper userInfoMapper;
    @Override
    public void handleMessage(RequestBody requestBody) {
        LogHelper.info("封装的上游回传参数requestBody："+ CommonHelper.showDetails(requestBody));
        if("true".equals(requestBody.getIsChangJingSub()))
        {
            String userinfo=getUserInfo(requestBody.getOpenid(),requestBody.getMarchant_appid());
            JSONObject respCode_json=JSONObject.fromObject(userinfo);
            if("000".equals(respCode_json.getString("rspCode")))
            {
                JSONObject user_json=respCode_json.getJSONObject("userinfo");
                UserInfo userInfo =new UserInfo();
                userInfo.setUserinfoSex(user_json.getString("sex")==null?"1":user_json.getString("sex"));
                if(null==requestBody.getSubcenter_id() || "".equals(requestBody.getSubcenter_id()))
                {
                    userInfo.setUserinfoSubCenterId(0);
                }
                else {
                    userInfo.setUserinfoSubCenterId(Integer.valueOf(requestBody.getSubcenter_id()));
                }
                userInfo.setUserinfoWechatName(user_json.getString("nickname"));
                userInfo.setUserinfoWechatHeadimage(user_json.getString("headimgurl")==null?"":user_json.getString("headimgurl"));
                userInfo.setUserinfoCreatetime(new Date());
                userInfo.setUserinfoWechatAppid(requestBody.getMarchant_appid());
                userInfo.setUserinfoWechatOpenid(requestBody.getOpenid());
                userInfo.setUserinfoIsSubscribe("1");
                UserInfo uf=new UserInfo();
                uf.setUserinfoWechatOpenid(requestBody.getOpenid());
                uf.setUserinfoWechatAppid(requestBody.getMarchant_appid());
                UserInfo userInfo1= userInfoMapper.selectByUserInfo(uf);
                if(null==userInfo1)
                {
                    int aa= userInfoMapper.insertSelective(userInfo);
                    System.out.println(aa);
                }else
                {
                    if("2".equals(userInfo1.getUserinfoIsSubscribe()))
                    {
                        userInfo1.setUserinfoIsSubscribe("1");
                    }
                    if(!userInfo.getUserinfoSubCenterId().equals(userInfo1.getUserinfoSubCenterId()))
                    {
                        userInfo1.setUserinfoSubCenterId(userInfo.getUserinfoSubCenterId());
                    }
                    userInfoMapper.updateByPrimaryKeySelective(userInfo1);
                }


            }
        }
        else if("unsubscribe".equals(requestBody.getIsChangJingSub()))
        {
            UserInfo uf=new UserInfo();
            uf.setUserinfoWechatOpenid(requestBody.getOpenid());
            uf.setUserinfoWechatAppid(requestBody.getMarchant_appid());
            UserInfo userInfo1= userInfoMapper.selectByUserInfo(uf);
            if(null!=userInfo1)
            {
                userInfo1.setUserinfoIsSubscribe("2");//置为取消关注
                userInfoMapper.updateByPrimaryKeySelective(userInfo1);
            }
        }
    }

    public String getUserInfo(String openid ,String appid)
    {
        JSONObject  source=new JSONObject();
        source.put("app_id",appid);
        source.put("openid",openid);
        String userInfo= httpsPostMethod.httpRequest("http://www.wumeimart.com/authorization/getUserInfo" ,
                source.toString(), "POST");

        System.out.println("获取的用户信息为："+userInfo);
        return userInfo;
    }

    public static void main(String[] args) {
        JSONObject  source=new JSONObject();
        source.put("app_id","wx3fc913b1cac94b23");
        source.put("openid","oHHOn1G_Z8JKBCEHmXIerZF8kksc");
        String userInfo= httpsPostMethod.httpRequest("http://www.wumeimart.com/authorization/getUserInfo" ,
                source.toString(), "POST");

        System.out.println("获取的用户信息为："+userInfo);
    }
}
