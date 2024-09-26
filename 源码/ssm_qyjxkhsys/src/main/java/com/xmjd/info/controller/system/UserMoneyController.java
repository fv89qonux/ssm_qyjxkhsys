package com.xmjd.info.controller.system;

import com.xmjd.info.controller.common.BaseController;
import com.xmjd.info.service.system.SysUserService;
import com.xmjd.info.service.system.UserMoneyService;
import com.xmjd.info.bean.system.UserMoney;
import com.xmjd.info.bean.system.UserMoneyQuery;
import com.xmjd.spring.data.query.result.Pagination;
import com.xmjd.spring.web.servlet.RemoteResult;
import com.xmjd.spring.web.servlet.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 员工
 * Created by admin on 2021/7/13
 */
@Controller
@RequestMapping("/money")
public class UserMoneyController extends BaseController {

    @Autowired
    private UserMoneyService userMoneyService;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 员工工资界面
     *
     * @return
     */
    @RequestMapping("/toList")
    public String toUserMoneyList(Model model) {
        model.addAttribute("users", this.sysUserService.findAll());
        return "page/bank/moneyList";
    }

    /**
     * 数据列表
     *
     * @param query 分页数据
     * @return 结果
     */
    @ResponseBody
    @RequestMapping("findMonies")
    public RemoteResult findMonies(UserMoneyQuery query) {
        Pagination pagination = this.userMoneyService.findMonies(query);
        return ResultUtils.createDefResult(pagination);
    }

    /**
     * 添加员工工资证件人
     *
     * @param userMoney 员工工资
     * @return
     */
    @ResponseBody
    @RequestMapping("saveUserMoney")
    public RemoteResult saveUserMoney(UserMoney userMoney) {
        this.userMoneyService.saveUserMoney(userMoney);
        return ResultUtils.createNullResult();
    }

    @RequestMapping("/print")
    public String print(UserMoneyQuery query, Model model) {
        model.addAttribute("list", this.userMoneyService.findAll(query));
        return "page/bank/moneyPrint";
    }
}
