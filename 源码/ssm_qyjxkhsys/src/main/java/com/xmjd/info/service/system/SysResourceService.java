package com.xmjd.info.service.system;

import com.xmjd.info.dao.system.SysResourceDao;
import com.xmjd.info.bean.system.SysResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2021/7/11.
 * 菜单、按钮资源业务类
 */
@Service
public class SysResourceService {

    @Autowired
    private SysResourceDao sysResourceDao;

    /**
     * 获取所有的平台系统的菜单、按钮资源
     *
     * @return 结果
     */
    public List<SysResource> getAllSysResources() {
        return this.sysResourceDao.getAllSysResources();
    }

}
