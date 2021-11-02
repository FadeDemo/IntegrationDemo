package org.fade.demo.integrationdemo.wangeditor.service;

import org.fade.demo.integrationdemo.wangeditor.entity.Issue;

/**
 * 工单Service
 *
 * @author fade
 * @date 2021/11/02
 */
public interface IssueService {

    /**
     * 保存工单
     * @param issue see {@link org.fade.demo.integrationdemo.wangeditor.entity.Issue}
     * */
    void save(Issue issue);

    /**
     * 根据id查找工单
     * @param id 工单id
     * @return see {@link org.fade.demo.integrationdemo.wangeditor.entity.Issue}
     * */
    Issue findById(Long id);

}
