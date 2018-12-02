package com.scut.ecourse.controller;

import com.scut.ecourse.service.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@RestController
@RequestMapping(value = "/course")
public class CourseController {

    @Autowired
    private Course course;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    //申报某一门课程
    public String createCourse(HttpServletRequest request) {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("image");
        String name=params.getParameter("name");
        System.out.println("name:"+name);
        String credit=params.getParameter("credit");
        System.out.println("credit:"+credit);
        String outline=params.getParameter("outline");
        System.out.println("outline:"+outline);
        String overview=params.getParameter("overview");
        System.out.println("overview:"+overview);
        String teaching_goal=params.getParameter("teaching_goal");
        System.out.println("teaching_goal:"+teaching_goal);
        String description=params.getParameter("description");
        System.out.println("description:"+description);
        System.out.println("FileCount:"+(1));

        course.createCourse(name,credit,outline,overview,teaching_goal,description,files.get(0));

        return "upload successful";
    }

    @RequestMapping(value = "/listStudent",method = RequestMethod.GET)
    public Object listStudent(@RequestParam(name = "courseId")long courseId,
                              @RequestParam(name = "term",required = false,defaultValue = "")String term){
        return course.getStudents(courseId,term);
    }

    @RequestMapping(value = "/addStudent",method = RequestMethod.POST)
    public Object addStudent(@RequestParam(name = "code")String code,@RequestParam(name = "courseId")long courseId,
                             @RequestParam(name = "term",required = false,defaultValue = "")String term){
        return course.addStudent(courseId,code,term);
    }

    @RequestMapping(value = "/removeStudent",method = RequestMethod.GET)
    public Object removeStudent(long courseId,int personId,
                                @RequestParam(name = "term",required = false,defaultValue = "")String term){
        return course.removeStudent(courseId,personId,term);
    }
}
