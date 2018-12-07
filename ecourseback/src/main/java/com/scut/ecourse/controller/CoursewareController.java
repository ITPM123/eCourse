package com.scut.ecourse.controller;

import com.scut.ecourse.service.Course;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/courseware")
public class CoursewareController {

    @Autowired
    private Course course;

    //获取该门课程所上传的课件列表长度
    @RequestMapping(value = "/getCoursewareListCount", method = RequestMethod.POST)
    @ResponseBody
    public int getCoursewareListCount(HttpServletRequest request){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String course_id=params.getParameter("course_id");
        return course.getCoursewareListCount(Long.parseLong(course_id));
    }

    //获取该门课程所上传的课件列表
    @RequestMapping(value = "/getCoursewareList", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getCoursewareList(HttpServletRequest request){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String course_id=params.getParameter("course_id");
        String pNumber=params.getParameter("pageNumber");
        String pSize=params.getParameter("pageSize");
        int pageNumber=Integer.parseInt(pNumber);
        int pageSize=Integer.parseInt(pSize);
        return course.getCoursewareList(Long.parseLong(course_id),pageNumber,pageSize);
    }

    //上传某一门课程的课件
    @RequestMapping(value = "/uploadCourseware", method = RequestMethod.POST)
    @ResponseBody
    public String uploadCourseware(HttpServletRequest request){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String course_id=params.getParameter("course_id");
        String name=params.getParameter("name");
        String visibility=params.getParameter("visibility");
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("file");
        Date date =new Date();
        course.uploadCourseware(Long.parseLong(course_id),name,date,Boolean.parseBoolean(visibility),files.get(0));
        return "upload success";
    }

    //更改课件的可见性
    @RequestMapping(value = "/modifyCourseware", method = RequestMethod.POST)
    @ResponseBody
    public void modiifyCourseware(HttpServletRequest request){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String visibility=params.getParameter("visibility");
        String courseware_id=params.getParameter("courseware_id");
        course.modifyCourseware(Long.parseLong(courseware_id),Boolean.parseBoolean(visibility));
    }

    //删除课件
    @RequestMapping(value = "/deleteCourseware", method = RequestMethod.POST)
    @ResponseBody
    public void deleteCourseware(HttpServletRequest request){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String courseware_id=params.getParameter("courseware_id");
        course.deleteCourseware(Long.parseLong(courseware_id));
    }

}
