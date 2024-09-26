package com.xmjd.info.controller.information;

import com.xmjd.info.controller.common.BaseController;
import com.xmjd.info.service.system.SysUserService;
import com.xmjd.info.service.system.UserMoneyService;
import com.xmjd.info.dao.information.AttenDao;
import com.xmjd.info.dao.system.SysUserDao;
import com.xmjd.info.bean.information.AttenDetail;
import com.xmjd.info.bean.system.AttenQuery;
import com.xmjd.info.bean.system.UserMoney;
import com.xmjd.info.bean.system.UserMoneyQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工
 * Created by admin on 2021/7/13
 */
@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {

    @Autowired
    private UserMoneyService userMoneyService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private AttenDao attenDao;

    /**
     * 员工工资界面
     *
     * @return
     */
    @RequestMapping("/toList")
    public String toUserMoneyList(Model model) {
        model.addAttribute("users", this.sysUserService.findAll());
        return "page/bank/reportList";
    }

    @RequestMapping("/print")
    public String print(UserMoneyQuery query, Model model) {
        model.addAttribute("user",this.sysUserDao.findSysUserById(query.getUserId()));
        List<UserMoney> monies = this.userMoneyService.findAll(query);
        model.addAttribute("list", monies);
        Map<String,List<AttenDetail>> data = new LinkedHashMap<String, List<AttenDetail>>();
        for(UserMoney money : monies){
            AttenQuery attenQuery =new AttenQuery();
            attenQuery.setUserId(query.getUserId());
            attenQuery.setMonth(money.getMonth());
            data.put(money.getMonth(),this.userMoneyService.tj(attenQuery));
        }
        model.addAttribute("attens",data);
        return "page/bank/reportPrint";
    }
}
