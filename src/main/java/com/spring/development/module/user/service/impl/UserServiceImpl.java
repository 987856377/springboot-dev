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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XuZhenkui
 * @since 2020-09-11
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @DS("master")
    @Override
    public Integer insertMaster(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Integer userFlag = userMapper.insert(user);
        Integer roleFlag = roleService.insertRole(new Role("admin"));

//        手动回滚事务
//        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return userFlag.equals(roleFlag) ? 1 : 0;
    }

    @DS("slave")
    @Override
    public Integer insertSlave(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Integer flag = userMapper.insert(user);
        return flag;
    }


    @Override
    public Integer update(User user) {
        if (user.getPassword() != null && !"".equals(user.getPassword())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            user.setPassword(passwordEncoder.encode("123456"));
        }
        return userMapper.updateUser(user);
    }

    @Override
    public User getUser(User user) {
        return userMapper.getUser(user);
    }


    @Override
    public Integer removeById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.selectList(new QueryWrapper<>());
    }

    @DS("master")
    @Override
    public void toM(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
    }

    @DS("slave")
    @Override
    public void toS(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
    }
}
