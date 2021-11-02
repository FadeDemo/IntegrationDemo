package org.fade.demo.integrationdemo.wangeditor.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Snowflake;
import org.fade.demo.integrationdemo.wangeditor.dao.AttachmentRepository;
import org.fade.demo.integrationdemo.wangeditor.dto.AttachmentDTO;
import org.fade.demo.integrationdemo.wangeditor.entity.Attachment;
import org.fade.demo.integrationdemo.wangeditor.service.AttachmentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 附件Service实现类
 *
 * @author fade
 * @date 2021/11/02
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Value("${dev.upload.path}")
    private String uploadPath;

    @Resource
    private AttachmentRepository attachmentRepository;

    @Resource
    private Snowflake snowflake;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public List<AttachmentDTO> upload(List<MultipartFile> files, Long issueId) {
        if (CollectionUtil.isNotEmpty(files)) {
            List<AttachmentDTO> result = new ArrayList<>(16);
            files.forEach(file -> {
                String attachmentName = file.getOriginalFilename();
                String attachmentUrl = uploadPath + "/" + attachmentName;
                Attachment attachment = new Attachment();
                attachment.setAttachmentName(attachmentName);
                attachment.setAttachmentUrl(attachmentUrl);
                attachment.setIssueId(issueId);
                attachment.setId(snowflake.nextId());
                attachmentRepository.save(attachment);
                try {
                    InputStream in = file.getInputStream();
                    BufferedOutputStream out = FileUtil.getOutputStream(attachmentUrl);
                    long copySize = IoUtil.copy(in, out, IoUtil.DEFAULT_LARGE_BUFFER_SIZE);
                    Assert.isTrue(copySize == file.getSize(), "文件上传发生异常，请重试");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                AttachmentDTO attachmentDTO = new AttachmentDTO();
                attachmentDTO.setUrl("file://" + attachmentUrl);
                result.add(attachmentDTO);
            });
            return result;
        }
        return null;
    }

}
