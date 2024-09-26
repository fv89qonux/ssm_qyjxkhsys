package com.xmjd.info.service.system;

import com.xmjd.info.dao.system.SysConfigDao;
import com.xmjd.info.bean.system.SysConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 系统配置证件人的业务逻辑层
 * Created by admin on 2021/7/16.
 */
@Service
public class SysConfigService {

    @Autowired
    private SysConfigDao sysConfigDao;


    /**
     * 根据code获取配置证件人
     *
     * @param code code
     * @return
     */
    public SysConfig getConfigByCode(String code) {
        return this.sysConfigDao.getConfigByCode(code);
    }

    /**
     * 保存配置证件人
     *
     * @param sysConfig 系统配置
     */
    @Transactional
    public void saveSysConfig(List<SysConfig> configList) {
        for (SysConfig config : configList) {
            this.sysConfigDao.saveSysConfig(config);
        }
    }

}
