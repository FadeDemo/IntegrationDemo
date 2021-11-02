package org.fade.demo.integrationdemo.wangeditor.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * 附件
 *
 * @author fade
 * @date 2021/11/02
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "wang_editor_attachment")
public class Attachment {

    @Id
    private Long id;

    private String attachmentName;

    private String attachmentUrl;

    private Long issueId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Attachment that = (Attachment) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
