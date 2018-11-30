package com.scut.ecourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.scut.ecourse.service.*;

@RestController
@RequestMapping(value = "/courseAnnouncement")
public class CourseAnnouncementController {
    
    @Autowired
    private CourseAnnouncementService courseAnnouncementService;

    @RequestMapping(value = "/getByCourseId",method = RequestMethod.GET)
    public Object getCourseAnnouncementByCourseId(@RequestParam(name = "courseId",required = true)long courseId){
       return courseAnnouncementService.getCourseAnnouncementByCourseId(courseId);
    }
}
