package com.spring.development.module.elasticsearch.repository;

import com.spring.development.module.elasticsearch.Entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @program: springboot-dev
 * @package com.spring.development.module.elasticsearch
 * @description
 * @author: XuZhenkui
 * @create: 2020-12-31 16:22
 **/
@Component
public interface UserRepository extends ElasticsearchRepository<User, Long> {
    User findUserByUsername(String username);
}
