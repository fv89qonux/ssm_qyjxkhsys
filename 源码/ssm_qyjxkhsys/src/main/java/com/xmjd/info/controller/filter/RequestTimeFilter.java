package com.xmjd.info.controller.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.xmjd.info.commons.util.ProcessContextManagerUtil;
import com.xmjd.spring.web.servlet.RequestDelegate;

/**
 * Servlet Filter implementation class RequestTimeFilter
 */
@WebFilter(description = "请求时间记录", urlPatterns = { "/*" })
public class RequestTimeFilter implements Filter {
    public static final Logger logger = LoggerFactory.getLogger(RequestTimeFilter.class);

    /**
     * Default constructor.
     */
    public RequestTimeFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */

    protected final Gson gson = new Gson();

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long starttime = System.currentTimeMillis();
        
        
        HttpServletRequest req = (HttpServletRequest) request;
        
        
        ProcessContextManagerUtil.init();
        ProcessContextManagerUtil.putAttribute("_CURRENT_REQ", new RequestDelegate(req));//运行时当前运行时上下文管理  
        
        Enumeration<String> headerNames = req.getHeaderNames();
        
        logger.debug("*******************START:[" + starttime + "]****************************");
        logger.debug("RemoteAddr>>>>>" + req.getRemoteAddr());
        logger.debug("RequestURL>>>>>" + req.getRequestURL().toString());
        logger.debug("HTTP-HEADS:");
        
        while (headerNames.hasMoreElements()) {
        	
            String headname = headerNames.nextElement().toLowerCase();
            String header = req.getHeader(headname);
            if(StringUtils.equals("request-id", headname)){
            	ProcessContextManagerUtil.putAttribute(headname, header);
            }
            logger.debug(">>>>>[" + headname + "]:" + header);
        }
        Map<String, String[]> parameterMap = req.getParameterMap();
        String parameters = gson.toJson(parameterMap);
        logger.debug("REQUEST PARAMETERS:");
        logger.debug(">>>" + parameters);
        logger.debug("PROCESSING>>>>>>>>>>>>");
        chain.doFilter(request, response);
        long endtime = System.currentTimeMillis();

        logger.debug("RequestUri->" + req.getRequestURI() + ",QueryString-->" + req.getQueryString() + ",Method->"
                + req.getMethod() + ",Cost-->" + (endtime - starttime) + "ms");
        logger.debug("********************END:[" + endtime + "]****************************");

    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
