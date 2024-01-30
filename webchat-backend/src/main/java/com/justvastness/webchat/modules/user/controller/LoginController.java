package com.justvastness.webchat.modules.user.controller;


import com.justvastness.webchat.config.aspect.SysLog;
import com.justvastness.webchat.config.result.Result;
import com.justvastness.webchat.modules.user.dto.LoginDTO;
import com.justvastness.webchat.modules.user.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class LoginController {

    @Autowired
    private UserService userService;

    @SysLog("用户登录")
    @PostMapping(value = "/login")
    @ApiOperation(value = "登录", httpMethod = "POST")
    public Result login(@RequestParam String username, @RequestParam String password) {
//        System.out.println(String.format("Logged in user is {%s}", username));
        LoginDTO dto = new LoginDTO();
        dto.setUsername(username);
        dto.setPassword(password);
        return userService.login(dto);
    }

    @PostMapping(value = "/logout")
    @SysLog(value = "退出登录")
    @ApiOperation(value = "退出登录", httpMethod = "POST")
    public void logout() {
        // 此处仅用于文档展示, 不做相关处理
    }
}