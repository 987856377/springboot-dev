package com.spring.development.module.webflux;

import com.spring.development.module.user.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: springboot-dev
 * @package com.spring.development.module.webflux
 * @description
 * @author: XuZhenkui
 * @create: 2021-02-05 10:06
 **/
@RestController
public class WebFluxController {

    @GetMapping("/test/single")
    public Mono<User> getUser() {
        User user = new User();
        user.setUsername("犬小哈");
        user.setPassword("123");
        return Mono.just(user);
    }

    @GetMapping("/test/multiple")
    public Flux<List<User>> getUserList() {
        User user1 = new User();
        user1.setUsername("asd");
        user1.setPassword("2323");

        User user2 = new User();
        user2.setUsername("犬小哈");
        user2.setPassword("123");
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        return Flux.just(userList);
    }
}
