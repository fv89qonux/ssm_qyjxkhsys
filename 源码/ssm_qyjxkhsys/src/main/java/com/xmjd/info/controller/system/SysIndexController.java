package com.xmjd.info.controller.system;

import com.xmjd.info.controller.common.BaseController;
import com.xmjd.info.service.information.InformationService;
import com.xmjd.info.dao.information.ProfitStatistics;
import com.xmjd.info.bean.system.SysUser;
import com.xmjd.spring.web.servlet.RemoteResult;
import com.xmjd.spring.web.servlet.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 平台首页界面，包含各种统计证件人
 * Created by admin on 2021/7/14.
 */
@Controller
public class SysIndexController extends BaseController<SysUser> {

    @Autowired
    private InformationService informationService;

    /**
     * 统计首页
     */
    @RequestMapping("/index")
    public String index(Model model) {
        return "page/system/index";
    }

    /**
     * 利润统计
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/profitStatistics")
    public RemoteResult profitStatistics(Date begin, Date end) {
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());

        if (begin == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, now.get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 0);
            calendar.set(Calendar.HOUR, -12);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            begin = calendar.getTime();
        }
        if (end == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, now.get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, 11);
            calendar.set(Calendar.DAY_OF_MONTH, 30);
            calendar.set(Calendar.HOUR, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            end = calendar.getTime();
        }
        List<ProfitStatistics> profitStatistics = this.informationService.profitStatistics(begin, end);
        return ResultUtils.createDefResult(profitStatistics);
    }
}
