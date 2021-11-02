package org.fade.demo.integrationdemo.wangeditor.service;

import org.fade.demo.integrationdemo.wangeditor.dto.AttachmentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 附件Service
 *
 * @author fade
 * @date 2021/11/02
 */
public interface AttachmentService {

    /**
     * 上传图片
     * @param files 文件列表
     * @param issueId 对应工单的id
     * @return see {@link org.fade.demo.integrationdemo.wangeditor.dto.AttachmentDTO}
     * */
    List<AttachmentDTO> upload(List<MultipartFile> files, Long issueId);

}
