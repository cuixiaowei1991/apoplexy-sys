package com.cn.controller;

import com.cn.common.CommonHelper;
import com.cn.common.CovertPostBody;
import com.cn.common.LogHelper;
import com.cn.entity.pojo.RequestBody;
import com.cn.service.OpenfCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * User: cuixiaowei
 * Date: 2018/11/1
 * PackageName: com.cn.controller
 */
@Controller
@RequestMapping("/openf")
public class OpenPlatformController {

    @Autowired
    private OpenfCommonService openfCommonService;
    @RequestMapping(value="/receive",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    public void openfCommonReceive(HttpServletResponse response, HttpServletRequest request)
    {
        RequestBody requestBody= CovertPostBody.covert(request);
        openfCommonService.handleMessage(requestBody);

    }
}
