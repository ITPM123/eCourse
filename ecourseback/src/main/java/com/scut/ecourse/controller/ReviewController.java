package com.scut.ecourse.controller;

import com.scut.ecourse.service.Review;
import net.sf.json.JSONArray;
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
    @RequestMapping(value = "/unexaminedCourses", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getUnexaminedCourse() {
        return review.getUnexaminedCourse();
    }

    //为某一门课程添加审核信息
    @RequestMapping(value = "/addReview", method = RequestMethod.POST)
    @ResponseBody
    public String addReview(HttpServletRequest request) {
        MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
        String feedback=params.getParameter("feedback");
        String result=params.getParameter("result");
        String course_id=params.getParameter("course_id");
        review.addReview(feedback,result,course_id);
        return "review upload success";
    }
}
