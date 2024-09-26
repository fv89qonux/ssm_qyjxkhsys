package com.xmjd.info.controller.system;

import com.xmjd.info.commons.exception.CiBizException;
import com.xmjd.info.commons.util.NetworkUtil;
import com.xmjd.info.controller.constant.SessionConstant;
import com.xmjd.info.service.system.SysRoleService;
import com.xmjd.info.service.system.SysUserService;
import com.xmjd.info.bean.system.SysUser;
import com.xmjd.spring.web.servlet.RemoteResult;
import com.xmjd.spring.web.servlet.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 平台用户登录入口等
 * Created by admin on 2021/7/13.
 */
@Controller
@RequestMapping("/")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 平台用户的登录入口
     *
     * @return 返回界面
     */
    @RequestMapping(method = RequestMethod.GET, value = "login")
    public String toLogin() {
        return "page/common/login";
    }

    /**
     * 平台用户登录验证
     *
     * @param name       账号证件人
     * @param password   密码
     * @param randomCode 验证码
     * @param session    session
     * @return 结果
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "toLogin")
    public RemoteResult login(String name, String password, String randomCode, HttpServletRequest request, HttpSession session) {
        try {
//            //先验证randomCode
//            String currentRandomCode = (String) session.getAttribute(SessionConstant.RANDOM_CODE);
//            if (!currentRandomCode.toUpperCase().equals(randomCode.toUpperCase())) {
//                throw new CiBizException("验证码错误");
//            }

            //获取IP地址
            String ip = NetworkUtil.getIpAddress(request);

            SysUser sysUser = this.sysUserService.sysUserLogin(name, password, ip);
            session.setAttribute(SessionConstant.LOGINER, sysUser);
            session.setAttribute(SessionConstant.LOGINER_ID, sysUser.getId());
            session.setAttribute(SessionConstant.LOGINER_NAME, sysUser.getName());


        } catch (CiBizException e) {
            session.removeAttribute(SessionConstant.RANDOM_CODE);
            throw e;
        }

        return ResultUtils.createNullResult();
    }

    /**
     * 修改密码
     *
     * @param passwordOld     旧密码
     * @param password        新密码
     * @param passwordConfirm 确认密码
     * @param session         session
     * @return
     */
    @ResponseBody
    @RequestMapping("changePassword")
    public RemoteResult changePassword(String passwordOld, String password, String passwordConfirm, HttpSession session) {
        SysUser loginer = (SysUser) session.getAttribute(SessionConstant.LOGINER);
        if (loginer == null) {
            return ResultUtils.createErrorResult("登录已失效，请刷新界面");
        }
        this.sysUserService.changePassword(loginer.getAccount(), passwordOld, password, passwordConfirm);

        return ResultUtils.createNullResult();
    }

    /**
     * 登出
     *
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:login";
    }
}
