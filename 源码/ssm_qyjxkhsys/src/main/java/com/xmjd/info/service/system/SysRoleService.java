package com.xmjd.info.service.system;

import com.xmjd.info.commons.exception.CiBizException;
import com.xmjd.info.commons.util.UUIDUtil;
import com.xmjd.info.dao.system.SysRoleDao;
import com.xmjd.info.bean.system.SysResource;
import com.xmjd.info.bean.system.SysRole;
import com.xmjd.info.bean.system.SysUser;
import com.xmjd.spring.data.query.param.PaginParam;
import com.xmjd.spring.data.query.result.Pagination;
import com.xmjd.spring.data.query.util.PaginUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 2021/7/11.
 * 角色业务类
 */
@Service
public class SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysResourceService sysResourceService;

    /**
     * 验证角色名称
     *
     * @param name 名称
     */
    private void validName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new CiBizException("请输入角色名称");
        }
        if (name.length() > 20) {
            throw new CiBizException("角色名称长度不超出20个字符");
        }
    }

    /**
     * 判断角色名称唯一性
     *
     * @param sysRole
     */
    private void validUnique(SysRole sysRole) {
        //判断是否为保留关键字“超级管理员”
        if ("超级管理员".equals(sysRole.getName())) {
            throw new CiBizException("禁止使用'超级管理员'");
        }

        //判断名称唯一性
        List<SysRole> sysRoleList = this.sysRoleDao.findSysRoleByName(sysRole);
        if (CollectionUtils.isNotEmpty(sysRoleList)) {
            throw new CiBizException("角色名称已存在");
        }
    }

    /**
     * 创建平台角色
     *
     * @param sysRole
     */
    @Transactional
    public void createSysRole(SysRole sysRole) {
        this.validName(sysRole.getName());
        this.validUnique(sysRole);
        sysRole.setId(UUIDUtil.genRandomUUID());
        this.sysRoleDao.createSysRole(sysRole);
    }

    /**
     * 修改
     *
     * @param sysRole 平台角色
     */
    @Transactional
    public void updateSysRole(SysRole sysRole) {
        if (StringUtils.isEmpty(sysRole.getId())) {
            throw new CiBizException("请选择需要修改的角色");
        }
        this.validName(sysRole.getName());
        this.validUnique(sysRole);
        this.sysRoleDao.updateSysRole(sysRole);
    }

    /**
     * 删除
     *
     * @param ids 平台角色ID
     */
    @Transactional
    public void deleteSysRole(String[] ids) {
        if (ids.length == 0) {
            throw new CiBizException("请选择需要删除的角色");
        }
        for(String id : ids){
            this.sysRoleDao.deleteRoleResourcesByRoleId(id);
        }
        this.sysRoleDao.deleteSysRole(ids);
    }

    /**
     * 分页查询平台角色
     *
     * @return 结果
     */
    public Pagination findSysRoles(PaginParam paginParam) {

        List<SysRole> sysRoles = this.sysRoleDao.findSysRoles(paginParam);
        for (SysRole role : sysRoles) {
            role.setResources(this.sysRoleDao.getRoleResourcesByRoleId(role.getId()));
        }
        Long total = this.sysRoleDao.findSysRoleCount(paginParam);

        Pagination pagination = PaginUtils.createPagination(paginParam);
        pagination.setData(sysRoles);
        pagination.setTotal(total);

        return pagination;
    }

    /**
     * 获取某个平台下所有的角色
     *
     * @return 结果
     */
    public List<SysRole> getSysRoles() {
        return this.sysRoleDao.getSysRoles();
    }

    /**
     * 获取某个平台用户的权限
     *
     * @param sysUser 用户
     * @return 权限集合，包含按钮和菜单
     */
    public List<SysResource> getResourcesBySysUser(SysUser sysUser) {
        //如果是超级管理员，那么直接获取所有的菜单和按钮
        if (sysUser.getAdmin() == SysUser.ADMINISTRATOR) {
            return this.sysResourceService.getAllSysResources();
        } else {
            //根据角色获取菜单和按钮
            return this.sysRoleDao.getRoleResourcesByRoleId(sysUser.getRoleId());
        }
    }

    /**
     * 保存角色的菜单配置
     *
     * @param roleId      角色ID
     * @param resourceIds 菜单IDS
     */
    @Transactional
    public void saveSysRoleResources(String roleId, String[] resourceIds) {
        this.sysRoleDao.deleteRoleResourcesByRoleId(roleId);
        for (String resourceId : resourceIds) {
            if (StringUtils.isNotEmpty(resourceId))
                this.sysRoleDao.createSysRoleResource(UUIDUtil.genRandomUUID(), roleId, resourceId);
        }
    }

}
