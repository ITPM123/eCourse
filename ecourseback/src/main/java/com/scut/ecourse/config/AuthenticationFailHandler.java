package com.scut.ecourse.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scut.ecourse.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("failHandler")
public class AuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private ObjectMapper mapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        if(exception.getMessage().contains("坏的凭证")){
            response.getWriter().write(mapper.writeValueAsString(ResultUtil.resultBadReturner("用户名或密码错误")));
        }else{
            response.getWriter().write(mapper.writeValueAsString(ResultUtil.resultBadReturner("账号不存在")));
        }


    }
}
