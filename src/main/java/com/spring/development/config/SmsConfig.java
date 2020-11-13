package com.spring.development.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
