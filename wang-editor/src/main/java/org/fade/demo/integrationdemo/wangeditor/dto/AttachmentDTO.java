package org.fade.demo.integrationdemo.wangeditor.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 附件dto
 *
 * @author fade
 * @date 2021/11/02
 */
@Data
public class AttachmentDTO {

    /**
     * 图片地址，必填
     * */
    private String url;

    /**
     * 图片文字说明，非必填
     * */
    private String alt;

    /**
     * 跳转链接，非必填
     * */
    private String href;

    /**
     * 工单id
     * */
    private Long issueId;

    /**
     * 附件列表
     * */
    private List<MultipartFile> files;

}
