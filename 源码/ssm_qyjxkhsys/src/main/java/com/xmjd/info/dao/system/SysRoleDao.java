package com.xmjd.info.dao.system;

import com.xmjd.info.bean.system.SysResource;
import com.xmjd.info.bean.system.SysRole;
import com.xmjd.spring.data.query.param.PaginParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2021/7/11.
 * 平台角色
 */
@Repository
public interface SysRoleDao {
    /**
     * 创建系统角色
     *
     * @param sysRole 平台角色数据
     */

    void createSysRole(SysRole sysRole);

    /**
     * 修改角色
     *
     * @param sysRole 平台角色数据
     */
    void updateSysRole(SysRole sysRole);

    /**
     * 删除角色
     *
     * @param ids 角色id
     */
    void deleteSysRole(@Param("ids") String[] ids);

    /**
     * 根据名称、id查看，主要是判断名称唯一性的时候使用的
     *
     * @param sysRole 主键ID，名称
     * @return 结果
     */
    List<SysRole> findSysRoleByName(SysRole sysRole);

    /**
     * 获取系统角色列表，分页
     *
     * @return 结果
     */
    List<SysRole> findSysRoles(PaginParam paginParam);

    /**
     * 获取系统角色总数
     *
     * @return 结果
     */
    Long findSysRoleCount(PaginParam paginParam);

    /**
     * 获取所有的系统角色
     *
     * @return
     */
    List<SysRole> getSysRoles();

    /**
     * 获取角色的菜单配置证件人
     * @param roleId 角色ID
     * @return 菜单证件人
     */
    List<SysResource> getRoleResourcesByRoleId(@Param("roleId") String roleId);

    /**
     * 删除该角色的所有菜单证件人
     * @param roleId 角色ID
     */
    void deleteRoleResourcesByRoleId(@Param("roleId") String roleId);

    /**
     * 创建角色-菜单资源的关联关系
     * @param roleId 角色ID
     * @param resourceId 菜单资源ID
     */
    void createSysRoleResource(@Param("id") String id, @Param("roleId") String roleId, @Param("resourceId") String resourceId);

}
