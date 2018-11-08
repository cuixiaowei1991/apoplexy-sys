package com.cn.aopAnnotation;

import com.cn.MsgCode.MsgAndCode;
import com.cn.common.CommonHelper;
import com.cn.common.LogHelper;
import com.cn.common.WeChatCommon.MD5;
import net.sf.json.JSONObject;
import org.apache.poi.util.StringUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * User: cuixiaowei
 * Date: 2018/11/5
 * PackageName: com.cn.aopAnnotation
 */
@Component
@Aspect
public class HttpHeaderValidateAspect {

    @Value("${allow_header}")
    private String allow_header;
    @Value("${token}")
    private String token;
    //缓存从配置文件或环境变量读取的值
    private static Map<String,String> headerCache = new HashMap<String,String>();
    //切点
    @Pointcut("within(@org.springframework.stereotype.Controller *))")
    //@Pointcut("@annotation(com.cn.aopAnnotation.HttpHeaderValidate))")
    public void pointCut(){
        System.out.println("我是一个切入点");
    }
    @Around("pointCut()")//&& @annotation(httpHeaderValidate), HttpHeaderValidate httpHeaderValidate
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable
    {
        LogHelper.info("=============进入了切面，校验接口权限=================");
        ProceedingJoinPoint pjp = (ProceedingJoinPoint)joinPoint;
        try
        {
            System.out.println("aspect aroundAdvice!\n"+joinPoint);
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();  //获取request
            String referer=request.getHeader("Referer");
            String appid=request.getParameter("marchant_appid");
            String sign=request.getParameter("sign");
            String daiqianming=appid+"&"+token;
            if(!CommonHelper.isNullOrEmpty(referer))
            {
                if(allow_header.indexOf(referer)!=-1 && sign.equals(MD5.md5(daiqianming,"UTF-8")))
                {
                    Object obj= pjp.proceed();
                    return obj;
                }
                else
                {
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put(MsgAndCode.RSP_CODE,MsgAndCode.NOAUTH_CODE);
                    jsonObject.put(MsgAndCode.RSP_DESC,MsgAndCode.NOAUTH_MESSAGE);
                    return jsonObject;
                }
            }
            else
            {
                if(sign.equals(MD5.md5(daiqianming,"UTF-8")))
                {
                    Object obj= pjp.proceed();
                    return obj;
                }
                else
                {
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put(MsgAndCode.RSP_CODE,MsgAndCode.NOAUTH_CODE);
                    jsonObject.put(MsgAndCode.RSP_DESC,MsgAndCode.NOAUTH_MESSAGE);
                    return jsonObject;
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        String aa="POSTdauidhauiwdhauiwda";
        System.out.println(aa.substring(4));
    }
}
