package com.spring.development.module.user.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.spring.development.module.role.entity.Role;
import com.spring.development.module.role.service.RoleService;
import com.spring.development.module.user.entity.User;
import com.spring.development.module.user.mapper.UserMapper;
import com.spring.development.module.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XuZhenkui
 * @since 2020-09-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Transactional
    @DS("master")
    @Async
    @Override
    public Future<Integer> insertMaster(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int insert = 0;
        Integer admin = 0;
        try {
            insert = userMapper.insert(user);
            admin = roleService.insertRole(new Role("admin")).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
//        try {
//            insert = userMapper.insert(user);
//            if (insert > 0){
//                admin = roleService.insertRole(new Role("admin")).get();
//                if (admin.equals(0)){
//                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//        }

        return new AsyncResult<>(admin);
    }

    @DS("slave")
    @Async
    @Override
    public Future<Integer> insertSlave(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        int insert = 0;
        try {
            insert = userMapper.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

        return new AsyncResult<>(insert);
    }


    @Override
    public Future<Integer> update(User user) {
        if (user.getPassword() != null && !"".equals(user.getPassword())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(passwordEncoder.encode("123456"));
        }
        return new AsyncResult<>(userMapper.updateUser(user));
    }

    @Override
    public Future<User> getUser(User user) {
        return new AsyncResult<>(userMapper.getUser(user));
    }


    @Async
    @Override
    public Future<Integer> removeById(Long id) {
        return new AsyncResult<>(userMapper.deleteById(id));
    }

    @Async
    @Override
    public Future<List<User>> getAllUser() {
        return new AsyncResult<>(userMapper.selectList(new QueryWrapper<>()));
    }

    @Transactional
    @DS("master")
    @Async
    @Override
    public void toM(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
    }

    @Transactional
    @DS("slave")
    @Async
    @Override
    public void toS(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
    }
}
