package com.spring.development.module;

import com.spring.development.common.event.ApplicationNotifyEvent;
import com.spring.development.common.holder.ApplicationEventPublisherHolder;
import com.spring.development.common.holder.EnvironmentHolder;
import com.spring.development.common.holder.ResourceLoaderHolder;
import com.spring.development.module.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CommonController {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /*
    * https://blog.csdn.net/f112122/article/details/47372967
    * @cache（“something");这个相当于save（）操作，
    * @cachePut相当于Update（）操作，只要他标示的方法被调用，那么都会缓存起来，而@cache则是先看下有没已经缓存了，然后再选择是否执行方法。
    * @CacheEvict相当于Delete（）操作。用来清除缓存用的。
    * */

    @Cacheable("index")
    @RequestMapping("/")
    public User index(){
        User user = new User();
        user.setUsername("NIL");
        user.setPassword((String.valueOf(counter.incrementAndGet())));
        try {
            System.out.println(ResourceLoaderHolder.getLoader().getResource("application.properties").getFile());
            System.out.println(EnvironmentHolder.getEnvironment().getProperty("server.port"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @RequestMapping("/user")
    public String user(){
        ApplicationEventPublisherHolder.publishEvent(new ApplicationNotifyEvent(counter,true));
        applicationEventPublisher.publishEvent(new ApplicationNotifyEvent(counter,true));
        return "Greetings user from Spring Boot! " + counter.incrementAndGet();
    }

    @RequestMapping("/admin")
    public String admin(){
        return "Greetings admin from Spring Boot! " + counter.incrementAndGet();
    }
}
