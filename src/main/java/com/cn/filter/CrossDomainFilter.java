package com.cn.filter;

import com.cn.common.LogHelper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 跨域支持过滤器，在HTTP响应中增加一些头信息
 * @author songzhili
 * 2016年7月1日上午9:04:29
 */
public class CrossDomainFilter implements Filter {
	
	    public void init(FilterConfig filterConfig) throws ServletException {
	    }

	    /**
	     * 跨域支持配置
	     * @param servletRequest
	     * @param servletResponse
	     * @param filterChain
	     * @throws IOException
	     * @throws ServletException
	     */
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		String referer=request.getHeader("Referer")==null?"":request.getHeader("Referer");
		LogHelper.info("referer值为："+referer);
		InputStream in = CrossDomainFilter.class.getClassLoader().getResourceAsStream("config.properties");
		Properties p = new Properties();
		p.load(in);
		String allow_header=p.getProperty("allow_header");
		if(allow_header.indexOf(referer)!=-1)
		{
			request.setCharacterEncoding("UTF-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods",
					"GET,POST,PUT,DELETE,OPTIONS");
			response.setHeader("Access-Control-Allow-Headers",
					"Content-Type,TestKey,x-requested-with");
			filterChain.doFilter(request, response);
		}

	}

	public void destroy() {

	}

}
