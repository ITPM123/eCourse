package com.scut.ecourse.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;


public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper mapper = new ObjectMapper();
        UsernamePasswordAuthenticationToken authRequest = null;
        try (InputStream is = request.getInputStream()){
            AuthReq authenticationBean = mapper.readValue(is,AuthReq.class);
            authRequest = new UsernamePasswordAuthenticationToken(
                    authenticationBean.getUsername(), authenticationBean.getPassword());
        }catch (IOException e) {
            e.printStackTrace();
            authRequest = new UsernamePasswordAuthenticationToken(
                    "", "");
        }finally {
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }

    }


    public static class AuthReq {
        String username;
        String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
