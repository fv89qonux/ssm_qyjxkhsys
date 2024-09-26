package com.xmjd.info.controller.system;

import com.xmjd.info.commons.exception.CiBizException;
import com.xmjd.info.controller.common.BaseController;
import com.xmjd.info.service.system.SysRoleService;
import com.xmjd.info.bean.system.SysRole;
import com.xmjd.info.bean.system.SysRoleQuery;
import com.xmjd.spring.data.query.result.Pagination;
import com.xmjd.spring.web.servlet.RemoteResult;
import com.xmjd.spring.web.servlet.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2021/7/11.
 * 平台角色的controller
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController {


    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 系统管理=角色管理主界面
     *
     * @return
     */
    @RequestMapping("/toList")
    public String toSysRoleList() {
        return "page/system/sysRoleList";
    }

    /**
     * 数据列表
     *
     * @param query 分页数据
     * @return 结果
     */
    @ResponseBody
    @RequestMapping("findSysRoles")
    public RemoteResult findSysRole(SysRoleQuery query) {
        Pagination pagination = this.sysRoleService.findSysRoles(query);
        return ResultUtils.createDefResult(pagination);
    }


    /**
     * 添加平台角色
     *
     * @param sysRole 平台角色
     * @return
     */
    @ResponseBody
    @RequestMapping("createSysRole")
    public RemoteResult createUser(SysRole sysRole) {
        this.sysRoleService.createSysRole(sysRole);
        return ResultUtils.createNullResult();
    }

    /**
     * 修改平台角色
     *
     * @param sysRole 平台角色
     * @return
     */
    @ResponseBody
    @RequestMapping("updateSysRole")
    public RemoteResult updateSysRole(SysRole sysRole) {
        if (sysRole.getId() == null) {
            throw new CiBizException("请选择需要修改的平台角色");
        }
        this.sysRoleService.updateSysRole(sysRole);
        return ResultUtils.createNullResult();
    }

    /**
     * 删除平台角色
     *
     * @param ids 平台角色ID
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteSysRole")
    public RemoteResult deleteSysRole(@RequestParam(value = "ids[]") String[] ids) {
        this.sysRoleService.deleteSysRole(ids);
        return ResultUtils.createNullResult();
    }

    /**
     * 设置角色权限
     *
     * @param id          角色ID
     * @param resourceIds 权限ID集合，由，拼接而成
     * @return 结果
     */
    @ResponseBody
    @RequestMapping("saveSysRoleResources")
    public RemoteResult saveSysRoleResources(String id, String resourceIds) {
        if (StringUtils.isEmpty(resourceIds)) {
            String[] empty = new String[0];
            this.sysRoleService.saveSysRoleResources(id, empty);
        } else {
            this.sysRoleService.saveSysRoleResources(id, resourceIds.split(","));
        }
        return ResultUtils.createNullResult();
    }

}
