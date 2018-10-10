package com.sjkjcrm.service.mail;

import com.sjkjcrm.bean.mail.EmailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.util.Pair;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author Zhang Qiao
 * @Date 2018-10-10 16:24
 **/
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 简单的发送邮件
     * @param sendTo 收件人地址
     * @param title 标题
     * @param content 邮件内容
     */
    @Override
    public void sendSimpleMail(String sendTo, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailConfig.getEmailFrom());
        message.setTo(sendTo);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
    }

    /**
     * 发送带附件的邮件
     * @param sendTo 收件人地址
     * @param titel  邮件标题
     * @param content 邮件内容
     * @param attachments<文件名，附件> 附件列表
     */
    @Override
    public void sendAttachmentsMail(String sendTo, String title, String content, List<Pair<String, File>> attachments) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(emailConfig.getEmailFrom());
            helper.setTo(sendTo);
            helper.setSubject(title);
            helper.setText(content);

            for (Pair<String, File> pair : attachments) {
                helper.addAttachment(pair.getFirst(), new FileSystemResource(pair.getSecond()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        mailSender.send(mimeMessage);
    }

    /**
     * 发送带页面脚本的邮件
     */
    public void sendInlineMail() {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(emailConfig.getEmailFrom());
            helper.setTo("286352250@163.com");
            helper.setSubject("主题：嵌入静态资源");
            helper.setText("<html><body><img src=\"cid:weixin\" ></body></html>", true);
            FileSystemResource file = new FileSystemResource(new File("weixin.jpg"));
            helper.addInline("weixin", file);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        mailSender.send(mimeMessage);
    }

    /**
     * 带模板的邮件类型（暂时不用）
     * @param sendTo 收件人地址
     * @param titel  邮件标题
     * @param content<key, 内容> 邮件内容
     * @param attachments<文件名，附件> 附件列表
     */
    @Override
    public void sendTemplateMail(String sendTo, String titel, Map<String, Object> content, List<Pair<String, File>> attachments) {
    }
}
