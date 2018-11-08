package com.cn.common;

import com.cn.cache.AuthorizerInfo;
import com.cn.cache.AuthorizerInfoMap;
import net.sf.json.JSONObject;

/**
 * User: cuixiaowei
 * Date: 2018/10/30
 * PackageName: com.cn.common
 */
public class AuthorizerAccessToken {
    private String access_token;
    private long authorizer_access_tokenExprise;
    public AuthorizerAccessToken(String appid)
    {

        AuthorizerInfo authorizerInfo=AuthorizerInfoMap.get(appid);
        if(authorizerInfo==null)
        {
            authorizerInfo=new AuthorizerInfo();
            initAuthMap(appid,authorizerInfo);
        }else
        {
            if(authorizerInfo.authorizer_access_tokenExprise())
            {
                initAuthMap(appid,authorizerInfo);
            }
            else
            {
                access_token=authorizerInfo.getAuthorizer_access_token();
            }
        }
    }

    public void initAuthMap(String appid,AuthorizerInfo authorizerInfo)
    {
        String accessJson=httpsPostMethod.get(
                "http://www.wumeimart.com/authorization/accessToken?appid="+appid);
        JSONObject jsonObject=JSONObject.fromObject(accessJson);
        access_token= jsonObject.getString("token");
        authorizer_access_tokenExprise=jsonObject.getLong("expires_in");
        authorizerInfo=new AuthorizerInfo(appid,access_token,authorizer_access_tokenExprise);
        AuthorizerInfoMap.put(appid,authorizerInfo);
    }

    public long getAuthorizer_access_tokenExprise() {
        return authorizer_access_tokenExprise;
    }

    public void setAuthorizer_access_tokenExprise(long authorizer_access_tokenExprise) {
        this.authorizer_access_tokenExprise = authorizer_access_tokenExprise;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
