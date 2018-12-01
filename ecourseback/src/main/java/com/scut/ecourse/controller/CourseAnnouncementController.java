package com.scut.ecourse.controller;

import com.scut.ecourse.entity.CourseAnnouncementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.scut.ecourse.service.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/courseAnnouncement")
public class CourseAnnouncementController {
    
    @Autowired
    private CourseAnnouncementService courseAnnouncementService;

    //获取课程的公告列表
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Object getCourseAnnouncementByCourseId(@RequestParam(name = "courseId",required = true)long courseId){
       return courseAnnouncementService.getCourseAnnouncementByCourseId(courseId);
    }

    //上传附件，返回系统生成的文件名
    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    public Object uploadFile(@RequestParam(name = "file",required = true)MultipartFile file){
        return courseAnnouncementService.uploadAttachment(file);
    }

    //新增公告
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public Object addCourseAnnouncement(CourseAnnouncementEntity courseAnnouncementEntity,
            @RequestParam(name = "courseId",required = true)long courseId){
        return courseAnnouncementService.addCourseAnnouncementByCourseId(courseAnnouncementEntity,courseId);
    }

    //修改公告
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public Object updateCourseAnnouncement(CourseAnnouncementEntity courseAnnouncementEntity){
        return courseAnnouncementService.updateCourseAnnouncement(courseAnnouncementEntity);
    }

    //删除公告
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Object deleteCourseAnnouncement(@RequestParam(name = "courseAnnouncementId",required = true)int id){
        return courseAnnouncementService.deleteCourseAnnouncement(id);
    }
}
