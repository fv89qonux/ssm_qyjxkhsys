package com.xmjd.info.dao.information;

import com.xmjd.info.bean.information.Week;
import com.xmjd.info.bean.information.WeekQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2018/4/11.
 * 平台意见反馈
 */
@Repository
public interface WeekDao {
    /**
     * 创建系统意见反馈
     *
     * @param week 平台意见反馈数据
     */

    void createWeek(Week week);

    /**
     * 修改意见反馈
     *
     * @param week 平台意见反馈数据
     */
    void updateWeek(Week week);

    /**
     * 删除意见反馈
     *
     * @param ids 意见反馈id
     */
    void deleteWeek(@Param("ids") String[] ids);

    /**
     * 根据名称、id查看，主要是判断名称唯一性的时候使用的
     *
     * @param week 主键ID，名称
     * @return 结果
     */
    List<Week> findWeekByName(Week week);

    /**
     * 获取系统意见反馈列表，分页
     *
     * @return 结果
     */
    List<Week> findWeeks(WeekQuery query);

    /**
     * 获取系统意见反馈总数
     *
     * @return 结果
     */
    Long findWeekCount(WeekQuery query);

    /**
     * 获取所有的系统意见反馈
     *
     * @return
     */
    List<Week> getWeeks();


}
