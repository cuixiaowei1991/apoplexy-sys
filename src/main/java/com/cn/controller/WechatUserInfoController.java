package com.cn.controller;

import com.cn.aopAnnotation.HttpHeaderValidate;
import com.cn.cache.AuthorizerInfoMap;
import com.cn.common.AuthorizerAccessToken;
import com.cn.common.CommonHelper;
import com.cn.common.LogHelper;
import com.cn.common.httpsPostMethod;
import com.cn.entity.UserInfo;
import com.cn.entity.pojo.RequsetUserInfo;
import com.cn.service.BaseConfigService;
import com.cn.service.TicketCodeService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * User: cuixiaowei
 * Date: 2018/11/1
 * PackageName: com.cn.controller
 */
@Controller
@RequestMapping("/user")
@HttpHeaderValidate(header="你在访问aop方法")
public class WechatUserInfoController {

    @Autowired
    private TicketCodeService ticketCodeService;
    @Autowired
    private BaseConfigService baseConfigService;

    @RequestMapping(value="/updateUser",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String updateUser(@RequestBody Map<String, Object> source)
    {
        LogHelper.info("修改完善用户信息："+ CommonHelper.showDetails(source));
        UserInfo userInfo=covertUserInfo(source);
        String result= baseConfigService.updateUserInfo(userInfo);
        return result;
    }

    @RequestMapping(value="/getUserLists",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String getUserLists(@RequestBody Map<String, Object> source)
    {
        LogHelper.info("获取用户列表信息："+ CommonHelper.showDetails(source));
        RequsetUserInfo requsetUserInfo= covertRequestUser(source);
        String result= baseConfigService.getUserLists(requsetUserInfo);
        return result;
    }

    @RequestMapping(value="/createTicketCode",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @ResponseBody
    public String createTicketCode()//@RequestBody Map<String, Object> source
    {
        Map<String, Object> source=new HashMap<>();
        source.put("subcenter_id","56");
        source.put("appid","wx3fc913b1cac94b23");
       // JSONObject code_json=ticketCodeService.createCode(source);
        JSONObject code_json=new JSONObject();
        code_json.put("aa","99999999999999999999999");
        return code_json.toString();
    }

    private RequsetUserInfo covertRequestUser(Map<String, Object> source)
    {
        RequsetUserInfo requsetUserInfo=new RequsetUserInfo();
        requsetUserInfo.setSubcenterid(CommonHelper.nullOrEmptyToString(source.get("user_subcenterId")));
        requsetUserInfo.setUserage(CommonHelper.nullOrEmptyToString(source.get("user_age")));
        requsetUserInfo.setUsername(CommonHelper.nullOrEmptyToString(source.get("user_name")));
        requsetUserInfo.setUsersex(CommonHelper.nullOrEmptyToString(source.get("user_sex")));
        requsetUserInfo.setPageNum(Integer.valueOf(source.get("pageNum").toString()));
        requsetUserInfo.setPageSize(Integer.valueOf(source.get("pageSize").toString()));
        return requsetUserInfo;
    }

    private UserInfo covertUserInfo(Map<String, Object> source)
    {
        UserInfo userInfo=new UserInfo();
        userInfo.setUserinfoId(Integer.valueOf(source.get("user_id").toString()));
        userInfo.setUserinfoSex(CommonHelper.nullOrEmptyToString(source.get("user_sex")));
        userInfo.setUserinfoAge(CommonHelper.nullOrEmptyToString(source.get("user_age")));
        userInfo.setUserDes(CommonHelper.nullOrEmptyToString(source.get("user_des")));
        userInfo.setUserinfoName(CommonHelper.nullOrEmptyToString(source.get("user_name")));
        userInfo.setUserType(CommonHelper.nullOrEmptyToString(source.get("user_type")));
        return userInfo;
    }
    public static void main(String[] args) {
        String openid_url="https://api.weixin.qq.com/cgi-bin/user/info";
        String openid="oHHOn1G_Z8JKBCEHmXIerZF8kksc";
        String access_token=new AuthorizerAccessToken("wx3fc913b1cac94b23").getAccess_token();
        /*String getuserinfo = httpsPostMethod.sendHttpsPost(
                openid_url+"?access_token="+ access_token+"&openid="+openid
                        +""
                , "&lang=zh_CN",
                "获取获取openid");*/

        String kefu_response_2= httpsPostMethod.sendHttpsPost("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + access_token,
                "{\"touser\":\"" + openid + "\",\"msgtype\":\"" + "text" + "\",\"text\":{\"content\":\"" + "哈哈楼青年"+"_from_api" + "\"}}", "");

        System.out.println(kefu_response_2);
    }

}
