package com.spring.development.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: springboot-gradle
 * @package com.spring.development.config
 * @description
 * @author: XuZhenkui
 * @create: 2020-11-13 15:44
 **/
@Component
@ConfigurationProperties(prefix = "sms")
public class SmsConfig {
    private Boolean enabled;
    private String url;
    private String username;
    private String password;
    private ContentConfig content;

    public static class ContentConfig {
        private Integer prizeAmount;
        private List<String> recipients;

        public Integer getPrizeAmount() {
            return prizeAmount;
        }

        public void setPrizeAmount(Integer prizeAmount) {
            this.prizeAmount = prizeAmount;
        }

        public List<String> getRecipients() {
            return recipients;
        }

        public void setRecipients(List<String> recipients) {
            this.recipients = recipients;
        }

        @Override
        public String toString() {
            return "ContentConfig{" +
                    "prizeAmount=" + prizeAmount +
                    ", recipients=" + recipients +
                    '}';
        }
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ContentConfig getContent() {
        return content;
    }

    public void setContent(ContentConfig content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SmsConfig{" +
                "enabled=" + enabled +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", content=" + content +
                '}';
    }
}
