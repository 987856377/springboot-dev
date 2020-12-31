package com.spring.development.module.elasticsearch.Entity;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @program: springboot-dev
 * @package com.spring.development.module.elasticsearch
 * @description
 * @author: XuZhenkui
 * @create: 2020-12-31 16:17
 **/
@Document(indexName = "test")
public class User {

    private Long id;

    private String username;

    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
