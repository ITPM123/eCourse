package com.scut.ecourse.controller;

import com.scut.ecourse.entity.ResultEntity;
import com.scut.ecourse.util.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
@RestController
public class SecurityController {

    @RequestMapping("/authentication/require")
    public ResultEntity requireAuthentication(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(200);
        return ResultUtil.resultBadReturner("未登录");
    }
}
