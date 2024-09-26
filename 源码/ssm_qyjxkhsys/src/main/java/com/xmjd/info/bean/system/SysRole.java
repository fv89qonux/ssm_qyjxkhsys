package com.xmjd.info.bean.system;

import com.xmjd.info.bean.common.Role;

import java.util.List;

/**
 * 平台角色
 * Created by admin on 2021/7/11.
 */
public class SysRole extends Role {

    //该角色的菜单，按钮权限
    private List<SysResource> resources;

    public List<SysResource> getResources() {
        return resources;
    }

    public void setResources(List<SysResource> resources) {
        this.resources = resources;
    }
}
