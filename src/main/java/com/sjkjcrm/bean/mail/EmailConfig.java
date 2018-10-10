package com.sjkjcrm.bean.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * email配置类
 * @author Zhang Qiao
 */
@Component
public class EmailConfig {

    /**
     * 发件邮箱
     */
    @Value("${spring.mail.username}")
    private String emailFrom;

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }
}
