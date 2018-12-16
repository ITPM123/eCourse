package com.scut.ecourse.controller;

import com.scut.ecourse.service.Review;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/reviewCourse")
public class ReviewController {
    @Autowired
    private Review review;

    //获取未审核的课程列表
    @RequestMapping(value = "/unexaminedCourseList", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getUnexaminedCourse(HttpServletRequest request) {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String pNumber=params.getParameter("pageNumber");
        String pSize=params.getParameter("pageSize");
        int pageNumber=Integer.parseInt(pNumber);
        int pageSize=Integer.parseInt(pSize);
        return review.getUnexaminedCourse(pageNumber,pageSize);
    }
    //获取列表长度
    @RequestMapping(value = "/unexaminedCourseListCount", method = RequestMethod.POST)
    @ResponseBody
    public int getUnexaminedCourseCount(){
        return review.getUnexaminedCourseCount();
    }

    //获取已审核的课程列表
    @RequestMapping(value = "/examinedCourseList", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getExaminedCourse(HttpServletRequest request) {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String pNumber=params.getParameter("pageNumber");
        String pSize=params.getParameter("pageSize");
        int pageNumber=Integer.parseInt(pNumber);
        int pageSize=Integer.parseInt(pSize);
        return review.getExaminedCourse(pageNumber,pageSize);
    }
    //获取列表长度
    @RequestMapping(value = "/examinedCourseListCount", method = RequestMethod.POST)
    @ResponseBody
    public int getExaminedCourseCount(){
        return review.getExaminedCourseCount();
    }

    //为某一门课程添加审核信息
    @RequestMapping(value = "/addReview", method = RequestMethod.POST)
    @ResponseBody
    public String addReview(HttpServletRequest request) {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String feedback=params.getParameter("feedback");
        String result=params.getParameter("result");
        String course_id=params.getParameter("course_id");
        return review.addReview(feedback,result,course_id);
    }

    //获取已审核课程的审核信息
    @RequestMapping(value = "/getReview", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject getReview(HttpServletRequest request) {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String review_id=params.getParameter("review_id");
        System.out.println(review_id);
        return review.getReview(Long.parseLong(review_id));
    }

    //
    @RequestMapping(value = "/getCoursesByPerson", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getCoursesByPerson(HttpServletRequest request){
        return review.getCoursesByPerson();
    }
}
