package com.xmjd.info.controller.system;

import com.xmjd.info.dao.information.DeptDao;
import com.xmjd.info.service.system.SysUserService;
import com.xmjd.info.dao.system.SysUserDao;
import com.xmjd.info.bean.system.SysUser;
import com.xmjd.info.bean.system.SysUserQuery;
import com.xmjd.info.controller.common.BaseController;
import com.xmjd.spring.data.query.result.Pagination;
import com.xmjd.spring.web.servlet.RemoteResult;
import com.xmjd.spring.web.servlet.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 平台用户管理的controller
 * 平台用户登录修改密码等，查看SysUserController
 * Created by admin on 2021/7/13
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserAdminController extends BaseController<SysUser> {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private DeptDao deptDao;

    /**
     * 平台用户管理界面
     *
     * @return
     */
    @RequestMapping("/toList")
    public String toSysUserList(Model model) {
        model.addAttribute("depts",this.deptDao.getDepts());
        return "page/system/sysUserList";
    }

    /**
     * 数据列表
     *
     * @param query 分页数据
     * @return 结果
     */
    @ResponseBody
    @RequestMapping("findSysUsers")
    public RemoteResult findSysUsers(SysUserQuery query) {
        Pagination pagination = this.sysUserService.findSysUsers(query);
        return ResultUtils.createDefResult(pagination);
    }

    /**
     * 删除平台用户管理
     *
     * @param ids 平台用户管理ID
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteSysUser")
    public RemoteResult deleteSysUser(@RequestParam(value = "ids[]") String[] ids) {
        this.sysUserService.deleteSysUser(ids);
        return ResultUtils.createNullResult();
    }

    /**
     * 添加平台用户管理证件人
     *
     * @param sysUser 平台用户管理
     * @return
     */
    @ResponseBody
    @RequestMapping("createSysUser")
    public RemoteResult createSysUser(SysUser sysUser) {
        this.sysUserService.createSysUser(sysUser);
        return ResultUtils.createNullResult();
    }

    /**
     * 修改平台用户管理证件人
     *
     * @param sysUser 平台用户管理
     * @return
     */
    @ResponseBody
    @RequestMapping("updateSysUser")
    public RemoteResult updateSysUser(SysUser sysUser) {
        this.sysUserService.updateSysUser(sysUser);
        return ResultUtils.createNullResult();
    }

    /**
     * 重置平台用户管理密码
     *
     * @param sysUser 平台用户管理ID,PWD
     * @return 结果
     */
    @ResponseBody
    @RequestMapping("resetPassword")
    public RemoteResult resetPassword(SysUser sysUser) {
        this.sysUserService.resetPassword(sysUser);
        return ResultUtils.createNullResult();
    }

    @RequestMapping("/print")
    public String print(Model model, String id) {
        model.addAttribute("user", this.sysUserDao.findSysUserById(id));
        return "page/system/sysUserPrint";
    }

}
