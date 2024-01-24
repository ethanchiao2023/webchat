package com.justvastness.webchat.user.controller;


import com.justvastness.webchat.config.aspect.SysLog;
import com.justvastness.webchat.config.enums.GlobalResultEnum;
import com.justvastness.webchat.config.exception.CustomException;
import com.justvastness.webchat.user.dto.LoginDTO;
import com.justvastness.webchat.user.dto.RegisterDTO;
import com.justvastness.webchat.user.entity.UserEntity;
import com.justvastness.webchat.user.services.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    @SysLog(value = "登录")
    @ApiOperation(value = "登录", httpMethod = "POST")
    public String login(@RequestParam String username, @RequestParam String password) {
        try {
            LoginDTO dto = new LoginDTO();
            dto.setUsername(username);
            dto.setPassword(password);
            UserEntity user = userService.login(dto);
            if (user == null) {
                throw new CustomException("Login failed!");
            }
            return "Login successful!";
        } catch (CustomException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getErrorMsg());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
    }

    @PostMapping(value = "/register")
    @SysLog(value = "注册")
    @ApiOperation(value = "注册", httpMethod = "POST")
    public String register(@RequestParam String username, @RequestParam String password) {
        try {
            RegisterDTO dto = new RegisterDTO();
            dto.setUsername(username);
            dto.setPassword(password);
            boolean result = userService.register(dto);
            if (!result) {
                throw new CustomException("Registration failed!");
            }
            return "Registration successful!";
        } catch (CustomException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getErrorMsg());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
        }
    }

    @PostMapping(value = "/logout")
    @SysLog(value = "退出登录")
    @ApiOperation(value = "退出登录", httpMethod = "POST")
    public void logout() {
        // 此处仅用于文档展示, 不做相关处理
    }
}
