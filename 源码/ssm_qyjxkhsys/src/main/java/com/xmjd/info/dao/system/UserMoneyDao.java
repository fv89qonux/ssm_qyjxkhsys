package com.xmjd.info.dao.system;

import com.xmjd.info.bean.system.UserMoney;
import com.xmjd.info.bean.system.UserMoneyQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 员工月工资相关的操作
 * <p>
 * Created by Administrator on 2016/5/25.
 */
@Repository
public interface UserMoneyDao {

    /**
     * 创建
     *
     * @param member 员工月工资数据
     */
    void createMoney(UserMoney member);

    /**
     * 修改员工月工资
     *
     * @param member 员工月工资数据
     */
    void updateMoney(UserMoney member);

    /**
     * 根据主键ID查询员工月工资
     *
     * @param id 主键
     * @return 员工月工资
     */
    UserMoney find(@Param("id") String id);

    /**
     * 根据名称、id查看员工月工资，主要是判断名称唯一性的时候使用的
     *
     * @param member 名称、id、酒店ID
     * @return 结果
     */
    List<UserMoney> findByUserId(UserMoney member);

    List<UserMoney> findMonies(UserMoneyQuery moneyQuery);

    List<UserMoney> findAll(UserMoneyQuery query);

    long findMoneyCount(UserMoneyQuery query);

}
