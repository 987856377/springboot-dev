package com.spring.development.module.role.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.spring.development.module.role.entity.Role;
import com.spring.development.module.role.mapper.RoleMapper;
import com.spring.development.module.role.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.concurrent.Future;

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
    @Async
    @Override
    public Future<Integer> insertRole(Role role) {
        int insert = 0;
        insert = roleMapper.insert(role);
//        try {
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//        }
        int a = 1/0;
        return new AsyncResult<>(insert);
    }
}
