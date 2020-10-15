package com.spring.development.module.role.service;

import com.spring.development.module.role.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.concurrent.Future;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XuZhenkui
 * @since 2020-10-15
 */
public interface RoleService extends IService<Role> {
    Future<Integer> insertRole(Role role);
}
