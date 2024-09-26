package com.xmjd.info.controller.filter;

import com.xmjd.info.controller.constant.SessionConstant;
import com.xmjd.info.service.system.SysResourceService;
import com.xmjd.info.bean.system.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 平台用户登入授权验证
 */
public class SysAuthHandlerInterceptor extends HandlerInterceptorAdapter {

    public static final String LOGIN_URL = "/info/login";

    @Autowired
    private SysResourceService sysResourceService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        String uri = request.getRequestURI().toString();

        if (uri.indexOf("loginLog") != -1 || uri.toLowerCase().indexOf("login") == -1) {
            HttpSession session = request.getSession(true);
            // 从session 里面获取用户名的证件人
            Object obj = session.getAttribute(SessionConstant.LOGINER);
            //判断酒店
            Object sys = session.getAttribute(SessionConstant.HOTEL);

            // 判断如果没有取到用户证件人，就跳转到登陆页面，提示用户进行登陆
            if (obj == null || !(obj instanceof SysUser)) {
                //ajax进行单独处理
                if (isAjax) {
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().print("{\"status\":104,\"message\":\"您的登录已失效，请重登重录\"}");
                    return false;
                } else {
                    response.sendRedirect(LOGIN_URL);
                    return false;
                }
            }
        }

        return super.preHandle(request, response, handler);
    }

}
