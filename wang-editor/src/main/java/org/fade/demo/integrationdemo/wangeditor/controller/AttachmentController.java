package org.fade.demo.integrationdemo.wangeditor.controller;

import cn.hutool.core.lang.Assert;
import org.fade.demo.integrationdemo.wangeditor.dto.AttachmentDTO;
import org.fade.demo.integrationdemo.wangeditor.service.AttachmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 附件Controller
 *
 * @author fade
 * @date 2021/11/02
 */
@RestController
@RequestMapping("/wangEditor/attachment")
public class AttachmentController {

    @Resource(name = "attachmentServiceImpl")
    private AttachmentService attachmentService;

    @PostMapping("upload")
    public ResultContext<List<AttachmentDTO>> upload(AttachmentDTO attachmentDTO) {
        Assert.notNull(attachmentDTO, "附件信息不能为空");
        Assert.notNull(attachmentDTO.getIssueId(), "附件所属工单id不能为空");
        List<AttachmentDTO> result = attachmentService.upload(attachmentDTO.getFiles(), attachmentDTO.getIssueId());
        return ResultContext.success(result);
    }

}
