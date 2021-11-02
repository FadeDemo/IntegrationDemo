package org.fade.demo.integrationdemo.wangeditor.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.ReUtil;
import org.fade.demo.integrationdemo.wangeditor.dao.IssueRepository;
import org.fade.demo.integrationdemo.wangeditor.entity.Issue;
import org.fade.demo.integrationdemo.wangeditor.service.IssueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

/**
 * 工单Service实现类
 *
 * @author fade
 * @date 2021/11/02
 */
@Service
public class IssueServiceImpl implements IssueService {

    @Resource
    private IssueRepository issueRepository;

    @Resource
    private Snowflake snowflake;

    @Override
    public void save(Issue issue) {
        issue.setId(snowflake.nextId());
        issueRepository.save(issue);
    }

    @Override
    public Issue findById(Long id) {
        Issue issue = issueRepository.findById(id).get();
        String description = new String(issue.getDescription(), StandardCharsets.UTF_8);
        // 更换img标签url地址
        description = ReUtil.replaceFirst(Pattern.compile("<img src=\"[\\s\\S]*\""),
                description,
                "<img src=\"https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png\"");
        issue.setDescription(description.getBytes(StandardCharsets.UTF_8));
        return issue;
    }

}
