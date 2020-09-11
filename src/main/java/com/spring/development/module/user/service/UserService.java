package com.spring.development.module.user.service;

import com.spring.development.module.user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.concurrent.Future;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XuZhenkui
 * @since 2020-09-11
 */
public interface UserService extends IService<User> {

    Future<Integer> insert(User user);

    Future<Integer> removeById(Long id);

    Future<List<User>> getAllUser();

}
