package com.spring.development.module.user.controller;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.spring.development.common.ResultCode;
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
            integer = userService.insertMaster(user).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResultJson.failure(ResultCode.INTERNAL_SERVER_ERROR);
        }
        return ResultJson.success(integer);
    }

    @RequestMapping("/update")
    public ResultJson update(@RequestBody User user){
        Integer integer = null;
        try {
            integer = userService.update(user).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return ResultJson.success(integer);
    }

    @RequestMapping("/getUser")
    public ResultJson get(@RequestBody User user){
        User user1 = null;
        try {
            user1 = userService.getUser(user).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return ResultJson.success(user1);
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

    @RequestMapping("/toM")
    @DS("master")
    public ResultJson toM(){
        for ( int i = 0; i < 100; i++){
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    userService.toM(new User("xzk","123"));
                }
            },"Thread- toM - " + i + " -Running: ").start();
        }
        return ResultJson.success();
    }

    @RequestMapping("/toS")
    @DS("slave")
    public ResultJson toS(){
        for ( int i = 0; i < 100; i++){
             new Thread(() -> {
                 for (int j = 0; j < 100; j++) {
                     userService.toS(new User("xzk","123"));
                 }
             },"Thread- toS - " + i + " -Running: ").start();
        }
        return ResultJson.success();
    }

}
