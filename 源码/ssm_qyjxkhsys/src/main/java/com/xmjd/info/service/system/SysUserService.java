package com.xmjd.info.service.system;

import com.xmjd.info.commons.exception.CiBizException;
import com.xmjd.info.commons.util.HexUtil;
import com.xmjd.info.commons.util.UUIDUtil;
import com.xmjd.info.dao.information.DeptDao;
import com.xmjd.info.service.log.LoginLogService;
import com.xmjd.info.dao.system.SysUserDao;
import com.xmjd.info.bean.system.SysUser;
import com.xmjd.info.bean.system.SysUserQuery;
import com.xmjd.spring.data.query.result.Pagination;
import com.xmjd.spring.data.query.util.PaginUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 平台员工管理
 */
@Service
public class SysUserService {

    public static final Logger logger = LoggerFactory.getLogger(SysUserService.class);

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private DeptDao deptDao;

    public List<SysUser> findAll() {
        return this.sysUserDao.findAll();
    }

    /**
     * 平台员工
     *
     * @return 结果
     */
    public Pagination findSysUsers(SysUserQuery query) {

        List<SysUser> sysUsers = this.sysUserDao.findSysUsers(query);
        for (SysUser user : sysUsers) {
            user.setDeptName(this.deptDao.findById(user.getDept()).getName());
        }
        Long total = this.sysUserDao.findSysUserCount(query);

        Pagination pagination = PaginUtils.createPagination(query.getOffset(), query.getLimit());
        pagination.setData(sysUsers);
        pagination.setTotal(total);

        return pagination;
    }

    /**
     * 验证平台员工的数据证件人，必填项
     *
     * @param sysUser 平台员工
     */
    private void validSysUser(SysUser sysUser) {
        if (StringUtils.isEmpty(sysUser.getName())) {
            throw new CiBizException("请输入员工姓名");
        }
        if (sysUser.getName().length() > 10) {
            throw new CiBizException("员工姓名不能超出10个字符");
        }
    }

    /**
     * 验证登录账号
     *
     * @param account 账号
     */
    private void validAccount(String account) {
        if (StringUtils.isEmpty(account)) {
            throw new CiBizException("请输入员工登录账号");
        }
        if (account.length() < 4 || account.length() > 18) {
            throw new CiBizException("登录账号长度在4-18个字符之间");
        }
    }

    /**
     * 验证密码
     *
     * @param password 密码
     */
    private void validPassword(String password) {
        if (StringUtils.isEmpty(password)) {
            throw new CiBizException("请输入登录密码");
        }
        if (password.length() < 5 || password.length() > 18) {
            throw new CiBizException("登录密码长度在5-18个字符之间");
        }
    }

    /**
     * 判断姓名，手机号码，账号的唯一性
     *
     * @param sysUser
     */
    private void validUnique(SysUser sysUser) {
        //判断姓名唯一性
        List<SysUser> sysUserList = this.sysUserDao.findSysUserByName(sysUser);
        if (CollectionUtils.isNotEmpty(sysUserList)) {
            throw new CiBizException("员工姓名已存在");
        }
        //判断账号唯一性
        sysUserList = this.sysUserDao.findSysUserByAccount(sysUser);
        if (CollectionUtils.isNotEmpty(sysUserList)) {
            throw new CiBizException("员工账号已存在");
        }
    }

    /**
     * 判断该员工是否为平台超级管理员
     *
     * @param id id
     */
    private void validAdministrator(String id) {
        SysUser user = this.sysUserDao.findSysUserById(id);
        if (user.getAdmin() == SysUser.ADMINISTRATOR) {
            throw new CiBizException("禁止操作超级管理员");
        }
    }

    /**
     * 添加平台员工
     *
     * @param sysUser
     */
    @Transactional
    public void createSysUser(SysUser sysUser) {
        this.validSysUser(sysUser);
        this.validAccount(sysUser.getAccount());
        this.validPassword(sysUser.getPassword());
        this.validUnique(sysUser);
        //生成主键
        sysUser.setId(UUIDUtil.genRandomUUID());
        //密码加密
        sysUser.setPassword(HexUtil.getEncryptedPwd(sysUser.getPassword()));
        this.sysUserDao.createSysUser(sysUser);
    }

    /**
     * 创建平台超级管理员
     * sysUser 设置 sysId
     *
     * @param sysUser
     */
    @Transactional
    public void createSysAdministrator(SysUser sysUser) {
        //验证是否为超级管理员
        if (sysUser.getAdmin() != SysUser.ADMINISTRATOR) {
            sysUser.setAdmin(SysUser.ADMINISTRATOR);
        }
        this.createSysUser(sysUser);
    }

    /**
     * 修改平台员工
     *
     * @param sysUser 平台员工
     */
    @Transactional
    public void updateSysUser(SysUser sysUser) {
        if (StringUtils.isEmpty(sysUser.getId())) {
            throw new CiBizException("请选择需要修改的员工");
        }
        //判断是否为超级管理员
        this.validAdministrator(sysUser.getId());
        this.validSysUser(sysUser);
        this.validAccount(sysUser.getAccount());
        this.validUnique(sysUser);
        this.sysUserDao.updateSysUser(sysUser);
    }

    /**
     * 重置平台员工密码
     *
     * @param sysUser 平台员工ID和密码
     */
    @Transactional
    public void resetPassword(SysUser sysUser) {
        this.validPassword(sysUser.getPassword());
        //密码加密
        sysUser.setPassword(HexUtil.getEncryptedPwd(sysUser.getPassword()));
        this.sysUserDao.resetPassword(sysUser);
    }

    /**
     * 删除平台员工
     *
     * @param ids 平台员工ID
     */
    @Transactional
    public void deleteSysUser(String[] ids) {
        if (ids.length == 0) {
            throw new CiBizException("请选择需要删除的员工");
        }
        for (String id : ids) {
            this.validAdministrator(id);
        }
        this.sysUserDao.deleteSysUser(ids);
    }

    /**
     * 平台员工登录
     *
     * @param account  平台员工登录账户
     * @param password 密码
     */
    @Transactional
    public SysUser sysUserLogin(String account, String password, String ip) {
        this.validAccount(account);
        this.validPassword(password);

        SysUser sysUser = new SysUser();
        sysUser.setAccount(account);

        List<SysUser> sysUserList = this.sysUserDao.findSysUserByAccount(sysUser);
        if (CollectionUtils.isEmpty(sysUserList)) {
            throw new CiBizException("员工名或密码错误");
        }

        SysUser loginer = sysUserList.get(0);

        if (!HexUtil.valid(password, loginer.getPassword())) {
            throw new CiBizException("员工名或密码错误");
        }

        return loginer;
    }

    /**
     * 修改密码
     *
     * @param account         员工
     * @param passwordOld     旧密码
     * @param password        新密码
     * @param passwordConfirm 确认密码
     */
    @Transactional
    public void changePassword(String account, String passwordOld, String password, String passwordConfirm) {
        this.validPassword(passwordOld);
        this.validPassword(password);
        this.validPassword(passwordConfirm);

        if (!password.equals(passwordConfirm)) {
            throw new CiBizException("两次密码不相同");
        }

        if (password.equals(passwordOld)) {
            throw new CiBizException("新密码不能与旧密码相同");
        }

        SysUser sysUser = new SysUser();
        sysUser.setAccount(account);

        List<SysUser> sysUserList = this.sysUserDao.findSysUserByAccount(sysUser);

        //判断原密码是否错误
        SysUser loginer = sysUserList.get(0);
        if (!HexUtil.validPassword(passwordOld, loginer.getPassword())) {
            throw new CiBizException("原密码错误");
        }

        loginer.setPassword(password);

        this.resetPassword(loginer);

    }

}
