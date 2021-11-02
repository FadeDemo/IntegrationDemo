package org.fade.demo.integrationdemo.wangeditor.controller;

import cn.hutool.core.lang.Assert;
import org.fade.demo.integrationdemo.wangeditor.entity.Issue;
import org.fade.demo.integrationdemo.wangeditor.service.IssueService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 工单Controller
 *
 * @author fade
 * @date 2021/11/02
 */
@RestController
@RequestMapping("/wangEditor/issue")
public class IssueController {

    @Resource(name = "issueServiceImpl")
    private IssueService issueService;

    @PostMapping("save")
    public ResultContext<Void> save(Issue issue) {
        Assert.notNull(issue, "工单信息不能为空");
        Assert.notNull(issue.getDescription(), "工单信息描述不能为空");
        issueService.save(issue);
        return ResultContext.success();
    }

    @PostMapping("findById")
    public ResultContext<Issue> findById(Long id) {
        Assert.notNull(id, "工单信息id不能为空");
        return ResultContext.success(issueService.findById(id));
    }

}
