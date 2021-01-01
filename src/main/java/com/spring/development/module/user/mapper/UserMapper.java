package com.spring.development.module.user.mapper;

import com.spring.development.module.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author XuZhenkui
 * @since 2020-09-11
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    Integer updateUser(User user);

    User getUser(User user);

    Long insertUser(User user);

    User getUserById(Long id);
}
