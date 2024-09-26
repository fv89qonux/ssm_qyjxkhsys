package com.xmjd.info.dao.system;

import com.xmjd.info.bean.system.SysUser;
import com.xmjd.info.bean.system.SysUserQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 用户相关的操作
 * <p>
 * Created by Administrator on 2016/5/25.
 */
@Repository
public interface SysUserDao {

    List<SysUser> findAll();

    /**
     * 创建
     *
     * @param sysUser 用户数据
     */
    void createSysUser(SysUser sysUser);

    /**
     * 修改用户
     *
     * @param sysUser 用户数据
     */
    void updateSysUser(SysUser sysUser);

    /**
     * 删除用户
     *
     * @param ids 用户id
     */
    void deleteSysUser(@Param("ids") String[] ids);


    /**
     * 获取用户列表，分页
     *
     * @return 结果
     */
    List<SysUser> findSysUsers(SysUserQuery query);

    /**
     * 获取用户的总人数
     *
     * @return 结果
     */
    Long findSysUserCount(SysUserQuery query);

    /**
     * 重置密码
     *
     * @param sysUser 用户ID和密码
     */
    void resetPassword(SysUser sysUser);

    /**
     * 根据ID查询用户
     *
     * @param id 主键ID
     * @return 结果
     */
    SysUser findSysUserById(@Param("id") String id);

    /**
     * 根据名称、id查看，主要是判断名称唯一性的时候使用的
     *
     * @param sysUser 主键ID，名称
     * @return 结果
     */
    List<SysUser> findSysUserByName(SysUser sysUser);

    /**
     * 根据账号、id查看，主要是判断账号唯一性的时候使用的
     *
     * @param sysUser 主键ID，账号
     * @return 结果
     */
    List<SysUser> findSysUserByAccount(SysUser sysUser);
}
