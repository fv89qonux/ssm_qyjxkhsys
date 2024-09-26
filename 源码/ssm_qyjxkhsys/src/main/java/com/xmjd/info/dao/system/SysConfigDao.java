package com.xmjd.info.dao.system;

import com.xmjd.info.bean.system.SysConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 系统配置证件人
 * Created by admin on 2021/7/16.
 */
@Repository
public interface SysConfigDao {

    /**
     * 根据code获取 配置证件人
     * @param code code
     * @return 结果
     */
    SysConfig getConfigByCode(@Param("code") String code);

    /**
     * 保存配置证件人
     * @param sysConfig 系统配置
     */
    void saveSysConfig(SysConfig sysConfig);
}
