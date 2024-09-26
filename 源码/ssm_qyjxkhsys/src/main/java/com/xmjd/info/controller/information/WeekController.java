package com.xmjd.info.controller.information;

import com.xmjd.info.bean.information.Week;
import com.xmjd.info.bean.information.WeekQuery;
import com.xmjd.info.bean.system.SysUser;
import com.xmjd.info.commons.exception.CiBizException;
import com.xmjd.info.controller.common.BaseController;
import com.xmjd.info.service.information.WeekService;
import com.xmjd.spring.data.query.result.Pagination;
import com.xmjd.spring.web.servlet.RemoteResult;
import com.xmjd.spring.web.servlet.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2018/4/11.
 * 周报的controller
 */
@Controller
@RequestMapping("/week")
public class WeekController extends BaseController {


    @Autowired
    private WeekService weekService;

    /**
     * 系统管理=公告管理主界面
     *
     * @return
     */
    @RequestMapping("/toList")
    public String toWeekList() {
        return "page/jx/weekList";
    }

    /**
     * 数据列表
     *
     * @param query 分页数据
     * @return 结果
     */
    @ResponseBody
    @RequestMapping("findWeeks")
    public RemoteResult findWeek(WeekQuery query) {
        SysUser user = super.getLoginer();
        if (user.getAdmin() != SysUser.ADMINISTRATOR) {
            query.setUserId(user.getId());
        }
        Pagination pagination = this.weekService.findWeeks(query);
        return ResultUtils.createDefResult(pagination);
    }


    /**
     * 添加周报
     *
     * @param week 周报
     * @return
     */
    @ResponseBody
    @RequestMapping("createWeek")
    public RemoteResult createUser(Week week) {
        week.setUserId(super.getLoginerId());
        this.weekService.createWeek(week);
        return ResultUtils.createNullResult();
    }

    /**
     * 修改周报
     *
     * @param week 周报
     * @return
     */
    @ResponseBody
    @RequestMapping("updateWeek")
    public RemoteResult updateWeek(Week week) {
        if (week.getId() == null) {
            throw new CiBizException("请选择需要修改的周报");
        }
        this.weekService.updateWeek(week);
        return ResultUtils.createNullResult();
    }

    /**
     * 删除周报
     *
     * @param ids 周报ID
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteWeek")
    public RemoteResult deleteWeek(@RequestParam(value = "ids[]") String[] ids) {
        this.weekService.deleteWeek(ids);
        return ResultUtils.createNullResult();
    }

}
