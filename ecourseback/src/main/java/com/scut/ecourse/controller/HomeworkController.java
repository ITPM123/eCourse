package com.scut.ecourse.controller;

import com.scut.ecourse.service.Homework;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
@RequestMapping(value = "/homework")
public class HomeworkController {

    @Autowired
    private Homework homework;

    //教师发布课程作业
    @RequestMapping(value = "/uploadCourseHomework", method = RequestMethod.POST)
    @ResponseBody
    public String uploadCourseHomework(HttpServletRequest request){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String course_id=params.getParameter("course_id");
        String homework_title=params.getParameter("homework_title");
        String description=params.getParameter("description");
        String deadline=params.getParameter("deadline");
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("attachment");
        Date release_date=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        deadline=deadline+" 23:59:59";
        try {
            Date temp=sdf.parse(deadline);
            if(files.size()>0){
                return homework.uploadCourseHomework(Long.parseLong(course_id),homework_title,description,temp,release_date,files.get(0));
            }
            else{
                return homework.uploadCourseHomework(Long.parseLong(course_id),homework_title,description,temp,release_date,null);
            }
        } catch (ParseException e) {
            return e.getMessage();
        }
    }

    //获取某一门课程的作业列表长度
    @RequestMapping(value = "/getCourseHomeworkListCount", method = RequestMethod.POST)
    @ResponseBody
    public int getCourseHomeworkListCount(HttpServletRequest request){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String course_id=params.getParameter("course_id");
        return homework.getCourseHomeworkListCount(Long.parseLong(course_id));
    }
    //获取某一门课程的作业列表
    @RequestMapping(value = "/getCourseHomeworkList", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getCourseHomeworkList(HttpServletRequest request){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String course_id=params.getParameter("course_id");
        String pNumber=params.getParameter("pageNumber");
        String pSize=params.getParameter("pageSize");
        return homework.getCourseHomeworkList(Long.parseLong(course_id),Integer.parseInt(pNumber),Integer.parseInt(pSize));
    }

    //教师删除某一门课程的作业
    @RequestMapping(value = "/deleteCourseHomework", method = RequestMethod.POST)
    @ResponseBody
    public String deleteCourseHomework(HttpServletRequest request){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String homework_id=params.getParameter("homework_id");
        return homework.deleteCourseHomeworkById(Long.parseLong(homework_id));
    }

    //获取学生所提交的作业列表的长度
    @RequestMapping(value = "/getStudentHomeworkListCount", method = RequestMethod.POST)
    @ResponseBody
    public int getStudentHomeworkListCount(HttpServletRequest request){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String homework_id=params.getParameter("homework_id");
        return homework.getStudentHomeworkListCount(Long.parseLong(homework_id));
    }

    //获取学生所提交的作业列表
    @RequestMapping(value = "/getStudentHomeworkList", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getStudentHomeworkList(HttpServletRequest request){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String homework_id=params.getParameter("homework_id");
        String pageNumber=params.getParameter("pageNumber");
        String pageSize=params.getParameter("pageSize");
        return homework.getStudentHomeworkList(Long.parseLong(homework_id),Integer.parseInt(pageNumber),Integer.parseInt(pageSize));
    }

    //教师批改某一个提交的作业
    @RequestMapping(value = "/reviewStudentHomework", method = RequestMethod.POST)
    @ResponseBody
    public String reviewStudentHomework(HttpServletRequest request){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String id=params.getParameter("do_homework_id");
        String score=params.getParameter("score");
        String comment=params.getParameter("comment");
        return homework.reviewStudentHomework(Long.parseLong(id),Integer.parseInt(score),comment);
    }

    //学生上传某一门作业的答案
    @RequestMapping(value = "/uploadStudentHomework", method = RequestMethod.POST)
    @ResponseBody
    public String uploadStudentHomework(HttpServletRequest request){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String homework_id=params.getParameter("homework_id");
        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
                .getFiles("attachment");
        return homework.uploadStudentHomework(Long.parseLong(homework_id),files.get(0));
    }

    //获取课程人数和已交作业人数
    @RequestMapping(value = "/getStudentHomeworkSubmitData", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getStudentHomeworkSubmitData(HttpServletRequest request){
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String homework_id=params.getParameter("homework_id");
        return homework.getStudentHomeworkSubmitData(Long.parseLong(homework_id));
    }
}
