package com.spring.development.module.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.spring.development.module.user.entity.User;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author XuZhenkui
 * @since 2020-09-11
 */
public interface UserService extends IService<User> {

    Integer insertMaster(User user);

    Integer insertSlave(User user);

    Long insertUser(User user);

    User getUserById(Long id);

    Integer update(User user);

    Integer removeById(Long id);

    List<User> getAllUser();

    User getUser(User user);

    void toM(User user);

    void toS(User user);
}
