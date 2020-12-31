package com.spring.development.module.user.controller;

import com.spring.development.module.user.entity.User;
import com.spring.development.module.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @program: springboot-dev
 * @package com.spring.development.module.user.controller
 * @description
 * @author: XuZhenkui
 * @create: 2020-12-07 15:34
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private UserService userService;

    @Test
    void get() {
        User user = new User();
        user.setUsername("xzk");
        User user1 = userService.getUser(user);
        System.out.println("user1 = " + user1);
    }

    @Test
    void list() {
    }
}