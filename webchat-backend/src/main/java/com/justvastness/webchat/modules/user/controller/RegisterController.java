package com.justvastness.webchat.modules.user.controller;

import com.justvastness.webchat.config.aspect.SysLog;
import com.justvastness.webchat.config.result.Result;
import com.justvastness.webchat.modules.user.dto.RegisterDTO;
import com.justvastness.webchat.modules.user.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class RegisterController {

    @Autowired
    private UserService userService;

    @SysLog("用户注册")
    @PostMapping("/register")
    @ApiOperation(value = "注册", httpMethod = "POST")
    public Result register(@RequestParam String username, @RequestParam String password) {
        RegisterDTO dto = new RegisterDTO();
        dto.setUsername(username);
        dto.setPassword(password);
        return userService.register(dto);
    }
}
