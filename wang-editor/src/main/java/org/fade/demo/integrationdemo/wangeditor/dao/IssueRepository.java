package org.fade.demo.integrationdemo.wangeditor.dao;

import org.fade.demo.integrationdemo.wangeditor.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fade
 * @date 2021/11/02
 */
@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
}
