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

    Integer insertMaster(User user);

    Integer insertSlave(User user);

    Integer update(User user);

    Integer removeById(Long id);

    List<User> getAllUser();

    User getUser(User user);

    void toM(User user);

    void toS(User user);
}
