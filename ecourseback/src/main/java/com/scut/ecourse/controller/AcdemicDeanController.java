package com.scut.ecourse.controller;

import com.scut.ecourse.entity.PersonEntity;
import com.scut.ecourse.service.AcdemicDeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/acdemicDean")
public class AcdemicDeanController {

    @Autowired
    private AcdemicDeanService acdemicDeanService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String createAcdemicDean(HttpServletRequest request) {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);

        String personId = params.getParameter("personId");
        System.out.println("personId:"+personId);
        String username = params.getParameter("username");
        System.out.println("username:"+username);
        String password = params.getParameter("password");
        System.out.println("password:"+password);
        String sex = params.getParameter("sex");
        System.out.println("sex:"+sex);
        String school = params.getParameter("school");
        System.out.println("school:"+school);
        String realName = params.getParameter("realName");
        System.out.println("realName:"+realName);
        String code = params.getParameter("code");
        System.out.println("code:"+code);
        String title = params.getParameter("title");
        System.out.println("title:"+title);
        String major = params.getParameter("major");
        System.out.println("major:"+major);
        String grade = params.getParameter("grade");
        System.out.println("grade:"+grade);
        String classs = params.getParameter("classs");
        System.out.println("classs:"+classs);
        String address = params.getParameter("address");
        System.out.println("address:"+address);
        String email = params.getParameter("email");
        System.out.println("email:"+email);
        String motto = params.getParameter("motto");
        System.out.println("motto:"+motto);

        acdemicDeanService.createAcdemicDean(Integer.parseInt(personId),username,password,Integer.parseInt(sex),school,
                realName,code,title,major,grade,classs,address,email,motto);

        return "upload successful";

    }

    @RequestMapping(value = "/removeAcdemicDean",method = RequestMethod.GET)
    public Object removeAcdemicDean(int personId){
        return acdemicDeanService.removeAcdemicDean(personId);
    }

}

