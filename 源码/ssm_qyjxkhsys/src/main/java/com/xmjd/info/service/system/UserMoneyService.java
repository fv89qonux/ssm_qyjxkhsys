package com.xmjd.info.service.system;

import com.xmjd.info.commons.util.UUIDUtil;
import com.xmjd.info.dao.information.AttenDao;
import com.xmjd.info.dao.system.SysConfigDao;
import com.xmjd.info.dao.system.SysUserDao;
import com.xmjd.info.dao.system.UserMoneyDao;
import com.xmjd.info.bean.information.Atten;
import com.xmjd.info.bean.information.AttenDetail;
import com.xmjd.info.bean.system.AttenQuery;
import com.xmjd.info.bean.system.SysConfig;
import com.xmjd.info.bean.system.UserMoney;
import com.xmjd.info.bean.system.UserMoneyQuery;
import com.xmjd.spring.data.query.result.Pagination;
import com.xmjd.spring.data.query.util.PaginUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * 业务逻辑层
 */
@Service
public class UserMoneyService {

    @Autowired
    private UserMoneyDao userMoneyDao;

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private AttenDao attenDao;

    @Autowired
    private SysConfigDao sysConfigDao;

    private double to(double a) {
        BigDecimal bg = new BigDecimal(a);
        return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public List<UserMoney> findAll(UserMoneyQuery query) {
        List<UserMoney> userMoneyList = this.userMoneyDao.findAll(query);
        this.jsgz(userMoneyList);
        return userMoneyList;
    }

    public void jsgz(List<UserMoney> userMoneyList) {
        for (UserMoney money : userMoneyList) {
            money.setUserName(this.sysUserDao.findSysUserById(money.getUserId()).getName());

            //计算
            AttenQuery attenQuery = new AttenQuery();
            attenQuery.setUserId(money.getUserId());
            attenQuery.setMonth(money.getMonth());
            List<Atten> attens = this.attenDao.find(attenQuery);

            double kk = 0d;//koukuan
            //需要扣的
            for (Atten atten : attens) {
                if (atten.getType().equals(SysConfig.CHIDAO)
                        || atten.getType().equals(SysConfig.ZAOTUI)
                        || atten.getType().equals(SysConfig.YEWUPILOU)
                        || atten.getType().equals(SysConfig.LINSHIQIANTUI)) {
                    String value = this.sysConfigDao.getConfigByCode(atten.getType()).getValue();
                    if (StringUtils.isNoneEmpty(value)) {
                        kk += this.to(Double.parseDouble(value));
                    }
                } else if (atten.getType().equals(SysConfig.BINGJIA)
                        || atten.getType().equals(SysConfig.SHIJIA)) {
                    String value = this.sysConfigDao.getConfigByCode(atten.getType()).getValue();
                    if (StringUtils.isNoneEmpty(value)) {
                        //看扣几天
                        String days = this.sysConfigDao.getConfigByCode(SysConfig.YIGEYUE).getValue();
                        double day = money.getJbgz() / Double.parseDouble(days);
                        kk += this.to(day * Double.parseDouble(value));
                    }
                }
            }

            money.setYkgz(kk);
            double yf = (money.getJbgz() + money.getJj()) - kk;
            if (yf < 0) {
                money.setYkgz(0);
            } else {
                money.setYfgz(yf);
            }
        }
    }

    public Pagination<UserMoney> findMonies(UserMoneyQuery query) {
        List<UserMoney> userMoneyList = this.userMoneyDao.findMonies(query);
        this.jsgz(userMoneyList);

        Long total = this.userMoneyDao.findMoneyCount(query);

        Pagination pagination = PaginUtils.createPagination(query.getOffset(), query.getLimit());
        pagination.setData(userMoneyList);
        pagination.setTotal(total);
        return pagination;
    }

    public void saveUserMoney(UserMoney money) {
        if (StringUtils.isEmpty(money.getId())) {
            List<UserMoney> monies = this.findByUserId(money);
            if (monies.size() == 0) {
                money.setId(UUIDUtil.genRandomUUID());
                this.userMoneyDao.createMoney(money);
            } else {
                money.setId(monies.get(0).getId());
                this.userMoneyDao.updateMoney(money);
            }
        } else {
            this.userMoneyDao.updateMoney(money);
        }
    }

    public List<AttenDetail> tj(AttenQuery query) {
        List<AttenDetail> details = this.attenDao.tj(query);
        for (AttenDetail atten : details) {
            if (atten.getType().equals(SysConfig.CHIDAO)
                    || atten.getType().equals(SysConfig.ZAOTUI)
                    || atten.getType().equals(SysConfig.YEWUPILOU)
                    || atten.getType().equals(SysConfig.LINSHIQIANTUI)) {
                String value = this.sysConfigDao.getConfigByCode(atten.getType()).getValue();
                if (StringUtils.isNoneEmpty(value)) {
                    atten.setPrice(this.to(Double.parseDouble(value)));
                }
            } else if (atten.getType().equals(SysConfig.BINGJIA)
                    || atten.getType().equals(SysConfig.SHIJIA)) {
                String value = this.sysConfigDao.getConfigByCode(atten.getType()).getValue();
                if (StringUtils.isNoneEmpty(value)) {
                    //看扣几天
                    String days = this.sysConfigDao.getConfigByCode(SysConfig.YIGEYUE).getValue();
                    UserMoney money = new UserMoney();
                    money.setMonth(query.getMonth());
                    money.setUserId(query.getUserId());
                    List<UserMoney> moneyList = this.findByUserId(money);
                    if (moneyList.size() > 0) {
                        money = moneyList.get(0);
                        double day = money.getJbgz() / Double.parseDouble(days);
                        atten.setPrice(this.to(day * Double.parseDouble(value)));
                    }
                }
            }
        }
        return details;
    }

    /**
     * 根据主键ID查询员工月工资
     *
     * @param id 主键
     * @return 员工月工资
     */
    public UserMoney find(String id) {
        return this.userMoneyDao.find(id);
    }

    /**
     * 根据名称、id查看员工月工资，
     *
     * @param money 名称、id、酒店ID
     * @return 结果
     */
    public List<UserMoney> findByUserId(UserMoney money) {
        return this.userMoneyDao.findByUserId(money);
    }
}
