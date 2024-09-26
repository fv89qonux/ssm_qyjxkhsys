package com.xmjd.info.controller.system;

import com.xmjd.info.service.system.SysResourceService;
import com.xmjd.info.bean.system.SysResource;
import com.xmjd.info.controller.common.BaseController;
import com.xmjd.spring.web.servlet.RemoteResult;
import com.xmjd.spring.web.servlet.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by admin on 2021/7/13.
 * 平台资源的controller
 */
@Controller
@RequestMapping("/sysResource")
public class SysResourceController extends BaseController {

    @Autowired
    private SysResourceService sysResourceService;

    /**
     * 所有的平台系统中的菜单
     *
     * @return 结果
     */
    @ResponseBody
    @RequestMapping("getAllSysResources")
    public RemoteResult getAllSysResources() {
        List<SysResource> resources = this.sysResourceService.getAllSysResources();
        return ResultUtils.createDefResult(resources);
    }


}
