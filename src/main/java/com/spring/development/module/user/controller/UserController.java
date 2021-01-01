package com.spring.development.module.user.controller;


import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.spring.development.common.ResultJson;
import com.spring.development.module.user.entity.User;
import com.spring.development.module.user.service.UserService;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
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
    public ResultJson add(@RequestBody User user) {
        return ResultJson.success(userService.insertMaster(user));
    }

    @RequestMapping("/update")
    public ResultJson update(@RequestBody User user) {
        return ResultJson.success(userService.update(user));
    }

    @RequestMapping("/getUser")
    public ResultJson get(@RequestBody User user) {
        return ResultJson.success(userService.getUser(user));
    }

    @RequestMapping("/list")
    public ResultJson list() {
        return ResultJson.success(userService.getAllUser());
    }

    @RequestMapping("/insertUser")
    public ResultJson insertUser(@RequestBody User user) {
        return ResultJson.success(userService.insertUser(user));
    }

    @RequestMapping("/getUserById")
    public ResultJson getUserById(@RequestBody User user) {
        return ResultJson.success(userService.getUserById(user.getId()));
    }

    @RequestMapping("/delete")
    public ResultJson delete(@RequestBody User user) {
        return ResultJson.success(userService.removeById(user.getId()));
    }

    @RequestMapping("/toM")
    @DS("master")
    public ResultJson toM() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    userService.toM(new User("xzk", "123"));
                }
            }, "Thread- toM - " + i + " -Running: ").start();
        }
        return ResultJson.success();
    }

    @RequestMapping("/toS")
    @DS("slave")
    public ResultJson toS() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    userService.toS(new User("xzk", "123"));
                }
            }, "Thread- toS - " + i + " -Running: ").start();
        }
        return ResultJson.success();
    }

    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = userService.getAllUser();

        ExcelWriter writer = null;
        ServletOutputStream out = null;
        try {
            if (!CollectionUtils.isEmpty(users)) {
                // 通过工具类创建writer，默认创建xls格式
                writer = ExcelUtil.getWriter(true);
                // 标题重命名
                writer.addHeaderAlias("id", "账号");
                writer.addHeaderAlias("username", "用户名");
                writer.addHeaderAlias("password", "密码");
                // 合并表头
                writer.merge(2, "账户信息");
                // 一次性写出内容，使用默认样式，强制输出标题
                writer.write(users, true);

                //设置内容字体
                Font font = writer.createFont();
                font.setBold(true);
                font.setColor(Font.COLOR_NORMAL);
                font.setItalic(true);
                //第二个参数表示是否忽略头部样式
                writer.getStyleSet().setFont(font, true);
                writer.autoSizeColumnAll();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(new Date());

                response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + date + ".xlsx");

                //out为OutputStream，需要写出到的目标流
                out = response.getOutputStream();

                writer.flush(out, true);
                // 关闭writer，释放内存
                writer.close();
                //此处记得关闭输出Servlet流
                IoUtil.close(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
