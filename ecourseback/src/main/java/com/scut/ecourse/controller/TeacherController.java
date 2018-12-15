package com.scut.ecourse.controller;

import com.scut.ecourse.entity.PersonEntity;
import com.scut.ecourse.service.AcdemicDeanService;
import com.scut.ecourse.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    //获取教师列表
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Object list(@RequestParam(name = "page",defaultValue = "0")int page){
        return teacherService.list(page);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object teacher(PersonEntity personEntity){
        return teacherService.createTeacher(personEntity);
    }


    @RequestMapping(value = "/removeTeacher",method = RequestMethod.GET)
    public Object removeTeacher(int personId){
        return teacherService.removeTeacher(personId);
    }

}

