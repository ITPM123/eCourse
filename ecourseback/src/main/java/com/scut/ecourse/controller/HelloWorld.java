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
        System.out.print("ok");
        return "Hello Spring Boot!";
    }


    /**
     * @param user
     * @return 登录结果
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestBody User user){
        System.out.print(user.getUsername());
        return user.getUsername();
    }

}
