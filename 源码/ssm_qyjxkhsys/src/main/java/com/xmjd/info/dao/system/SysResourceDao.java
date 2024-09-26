package com.xmjd.info.dao.system;

import com.xmjd.info.bean.system.SysResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2021/7/11.
 * 平台系统的菜单
 */
@Repository
public interface SysResourceDao {


    /**
     * 获取所有的平台系统的菜单
     *
     * @return
     */
    List<SysResource> getAllSysResources();
}
