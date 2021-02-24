package com.spring.development.module.role.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.development.module.role.entity.Role;
import com.spring.development.module.role.mapper.RoleMapper;
import com.spring.development.module.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XuZhenkui
 * @since 2020-10-15
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DS("slave")
    @Override
    public Integer insertRole(Role role) {
        Integer flag = roleMapper.insert(role);

//        设置异常
//        int a = 1/0;
        return flag;
    }
}
