package org.fade.demo.integrationdemo.wangeditor.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;


/**
 * 简化的工单实体类
 *
 * @author fade
 * @date 2021/11/02
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "wang_editor_issue")
public class Issue {

    @Id
    private Long id;

    @Column(columnDefinition = "blob")
    private byte[] description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Issue issue = (Issue) o;
        return id != null && Objects.equals(id, issue.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
