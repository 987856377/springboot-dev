package com.spring.development.module.user.controller;


import com.spring.development.common.ResultJson;
import com.spring.development.module.user.entity.User;
import com.spring.development.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XuZhenkui
 * @since 2020-09-11
 */
@RestController
@RequestMapping("/user/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public ResultJson add(@RequestBody User user){
        Integer integer = null;
        try {
            integer = userService.insert(user).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return ResultJson.success(integer);
    }

    @RequestMapping("/list")
    public ResultJson list(){
        Future<List<User>> allUser = userService.getAllUser();
        List<User> users = null;
        try {
            users = allUser.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return ResultJson.success(users);
    }

    @RequestMapping("/delete")
    public ResultJson delete(@RequestBody User user){
        Future<Integer> future = userService.removeById(user.getId());
        Integer integer = null;
        try {
            integer = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return ResultJson.success(integer);
    }

}
