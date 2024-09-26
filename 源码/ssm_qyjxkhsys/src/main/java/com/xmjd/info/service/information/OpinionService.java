package com.xmjd.info.service.information;

import com.xmjd.info.commons.exception.CiBizException;
import com.xmjd.info.commons.util.UUIDUtil;
import com.xmjd.info.dao.information.OpinionDao;
import com.xmjd.info.bean.information.Opinion;
import com.xmjd.info.bean.information.OpinionQuery;
import com.xmjd.spring.data.query.result.Pagination;
import com.xmjd.spring.data.query.util.PaginUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 2021/7/11.
 * 意见反馈业务类
 */
@Service
public class OpinionService {

    @Autowired
    private OpinionDao opinionDao;


    /**
     * 验证意见反馈内容
     *
     * @param name 内容
     */
    private void validName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new CiBizException("请输入意见反馈内容");
        }
        if (name.length() > 200) {
            throw new CiBizException("意见反馈内容长度不超出200个字符");
        }
    }

    /**
     * 创建平台意见反馈
     *
     * @param opinion
     */
    @Transactional
    public void createOpinion(Opinion opinion) {
        this.validName(opinion.getContent());
        opinion.setId(UUIDUtil.genRandomUUID());
        this.opinionDao.createOpinion(opinion);
    }

    /**
     * 修改
     *
     * @param opinion 平台意见反馈
     */
    @Transactional
    public void updateOpinion(Opinion opinion) {
        if (StringUtils.isEmpty(opinion.getId())) {
            throw new CiBizException("请选择需要修改的意见反馈");
        }
        this.validName(opinion.getContent());
        this.opinionDao.updateOpinion(opinion);
    }

    /**
     * 删除
     *
     * @param ids 平台意见反馈ID
     */
    @Transactional
    public void deleteOpinion(String[] ids) {
        if (ids.length == 0) {
            throw new CiBizException("请选择需要删除的意见反馈");
        }
        this.opinionDao.deleteOpinion(ids);
    }

    /**
     * 分页查询平台意见反馈
     *
     * @return 结果
     */
    public Pagination findOpinions(OpinionQuery query) {

        List<Opinion> opinions = this.opinionDao.findOpinions(query);
        Long total = this.opinionDao.findOpinionCount(query);

        Pagination pagination = PaginUtils.createPagination(query);
        pagination.setData(opinions);
        pagination.setTotal(total);

        return pagination;
    }

    /**
     * 获取某个平台下所有的意见反馈
     *
     * @return 结果
     */
    public List<Opinion> getOpinions() {
        return this.opinionDao.getOpinions();
    }


}
