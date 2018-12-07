package com.scut.ecourse.controller;

import com.scut.ecourse.service.Course;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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

    //申报某一门课程
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String createCourse(HttpServletRequest request) {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("image");
        String name=params.getParameter("name");
        String credit=params.getParameter("credit");
        String outline=params.getParameter("outline");
        String overview=params.getParameter("overview");
        String teaching_goal=params.getParameter("teaching_goal");
        String description=params.getParameter("description");
        if(files.size()>0){
            course.createCourse(name,credit,outline,overview,teaching_goal,description,files.get(0));
        }
        else{
            course.createCourse(name,credit,outline,overview,teaching_goal,description,null);
        }

        return "upload successful";
    }

    //获取课程列表（当前登陆用户为学生的话获取所学课程列表，为老师的话获取所交课程列表）
    @RequestMapping(value = "/getCoursesList", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getCourses(HttpServletRequest request){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String pNumber=params.getParameter("pageNumber");
        String pSize=params.getParameter("pageSize");
        int pageNumber=Integer.parseInt(pNumber);
        int pageSize=Integer.parseInt(pSize);
        return course.getCourses(pageNumber,pageSize);
    }
    //获取课程列表的长度
    @RequestMapping(value = "/getCoursesListCount", method = RequestMethod.POST)
    @ResponseBody
    public int getCoursesCount(){
        return course.getCoursesCount();
    }

    //获取课程信息
    @RequestMapping(value = "/getCourseInfoById", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getCourseInfoById(HttpServletRequest request){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String id=params.getParameter("course_id");
        Long course_id=Long.parseLong(id);
        return course.getCourseInfoById(course_id);
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
