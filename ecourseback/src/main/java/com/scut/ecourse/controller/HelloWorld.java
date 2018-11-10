package com.scut.ecourse.controller;

import com.scut.ecourse.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class HelloWorld {
    private Logger logger= LoggerFactory.getLogger(HelloWorld.class);
    @RequestMapping("/hello")
    public String hello() {
        System.out.println("ok");
        return "Hello Spring Boot!";
    }


    /**
     * @param
     * @return 登录结果
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
   /* public String login(@RequestParam(value="username", required=true)String username,@RequestParam(value="password", required=true)String password){
        System.out.println(username+" "+password);
        return username;
    }*/

    public String login(User user){
        System.out.println(user.getUsername());
        System.out.println("hhh");
        return user.getUsername();
    }

}
