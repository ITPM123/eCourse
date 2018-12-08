package com.scut.ecourse.controller;

import com.scut.ecourse.entity.PersonEntity;
import com.scut.ecourse.service.AcdemicDeanService;
import com.scut.ecourse.service.StudentService;
import com.scut.ecourse.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object student(PersonEntity personEntity){
        return studentService.createStudent(personEntity);
    }

    @RequestMapping(value = "/removeStudent",method = RequestMethod.GET)
    public Object removeStudent(int personId){
        return studentService.removeStudent(personId);
    }

}

