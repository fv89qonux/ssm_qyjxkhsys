package com.xmjd.info.controller.system;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.xmjd.info.service.system.SysConfigService;
import com.xmjd.info.bean.system.SysConfig;
import com.xmjd.info.controller.common.BaseController;
import com.xmjd.spring.web.servlet.RemoteResult;
import com.xmjd.spring.web.servlet.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 系统配置
 * Created by admin on 2021/7/13.
 * 平台资源的controller
 */
@Controller
@RequestMapping("/sysConfig")
public class SysConfigController extends BaseController {

    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 所有的平台系统中的菜单
     *
     * @return 结果
     */
    @RequestMapping("toIndex")
    public String getAllSysResources(Model model) {
        model.addAttribute("CHIDAO", this.sysConfigService.getConfigByCode(SysConfig.CHIDAO));
        model.addAttribute("ZAOTUI", this.sysConfigService.getConfigByCode(SysConfig.ZAOTUI));
        model.addAttribute("YIGEYUE", this.sysConfigService.getConfigByCode(SysConfig.YIGEYUE));
        model.addAttribute("SHIJIA", this.sysConfigService.getConfigByCode(SysConfig.SHIJIA));
        model.addAttribute("BINGJIA", this.sysConfigService.getConfigByCode(SysConfig.BINGJIA));
        model.addAttribute("LINSHIQIANTUI", this.sysConfigService.getConfigByCode(SysConfig.LINSHIQIANTUI));
        model.addAttribute("YEWUPILOU", this.sysConfigService.getConfigByCode(SysConfig.YEWUPILOU));
        return "page/system/sysConfig";
    }

    /**
     * 保存系统配置
     *
     * @return 结果
     */
    @ResponseBody
    @RequestMapping("saveSysConfig")
    public RemoteResult saveSysConfig(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<List<SysConfig>>() {
        }.getType();
        List<SysConfig> configs = gson.fromJson(json, type);
        this.sysConfigService.saveSysConfig(configs);
        return ResultUtils.createNullResult();
    }
}
