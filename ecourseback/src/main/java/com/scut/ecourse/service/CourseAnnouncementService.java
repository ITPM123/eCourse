package com.scut.ecourse.service;

import com.scut.ecourse.entity.*;
import com.scut.ecourse.jpa.CourseAnnouncementJPA;
import com.scut.ecourse.jpa.CourseJPA;
import com.scut.ecourse.jpa.TeachJPA;
import com.scut.ecourse.util.DateFormatUtil;
import com.scut.ecourse.util.FileUtil;
import com.scut.ecourse.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@Service
public class CourseAnnouncementService {
    @Autowired
    private CourseAnnouncementJPA courseAnnouncementJPA;
    @Autowired
    private CourseJPA courseJPA;
    @Autowired
    private TeachJPA teachJPA;
    private String attachmentSubDir="/attachment/";
    private int elementPerPage=10;
    //获取课程公告列表
    public ResultEntity getCourseAnnouncementByCourseId(long courseId,int page){
        Page<CourseAnnouncementEntity> p=courseAnnouncementJPA.findByCourseId(courseId,PageRequest.of(page,elementPerPage));
        HashMap<String,Object> map=new HashMap<>();
        map.put("total",p.getTotalPages());
        map.put("list",p.getContent());
        return ResultUtil.resultGoodReturner(map);
    }

    //上传附件
    public ResultEntity uploadAttachment(MultipartFile file){
        ResultEntity result = FileUtil.uploadSingleFile(file,attachmentSubDir,null);
        return result;
    }

    //新增公告
    public ResultEntity addCourseAnnouncementByCourseId(CourseAnnouncementEntity courseAnnouncement,long courseId){
        PersonEntity p=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Optional<CourseEntity> optional=courseJPA.findById(courseId);
        if(!optional.isPresent()){
            return ResultUtil.resultBadReturner("课程不存在");
        }
        CourseEntity courseEntity=optional.get();
        Teach teach=teachJPA.findByTeacherAndCourse(p,courseEntity);
        if(teach==null){
            return ResultUtil.resultBadReturner("无权限");
        }
        courseAnnouncement.setCreateTime(DateFormatUtil.format(new Date()));
        courseAnnouncement.setCourse(courseEntity);
        courseAnnouncementJPA.save(courseAnnouncement);
        return ResultUtil.resultGoodReturner(courseAnnouncement);
    }

    //修改公告
    public ResultEntity updateCourseAnnouncement(CourseAnnouncementEntity courseAnnouncementEntity){
        Optional<CourseAnnouncementEntity> optional=courseAnnouncementJPA.findById(courseAnnouncementEntity.getCourseAnnouncementId());
        if(!optional.isPresent()){
            return ResultUtil.resultBadReturner("公告不存在");
        }
        CourseAnnouncementEntity newEntity=optional.get();
        PersonEntity p=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        CourseEntity courseEntity=newEntity.getCourse();
        Teach teach=teachJPA.findByTeacherAndCourse(p,courseEntity);
        if(teach==null){
            return ResultUtil.resultBadReturner("无权限");
        }
        newEntity.setCreateTime(DateFormatUtil.format(new Date()));
        newEntity.setContent(courseAnnouncementEntity.getContent());
        newEntity.setTitle(courseAnnouncementEntity.getTitle());
        courseAnnouncementJPA.save(newEntity);
        return ResultUtil.resultGoodReturner(newEntity);
    }

    //删除公告
    public ResultEntity deleteCourseAnnouncement(int courseAnnouncementId){
        Optional<CourseAnnouncementEntity> optional=courseAnnouncementJPA.findById(courseAnnouncementId);
        if(!optional.isPresent()){
            return ResultUtil.resultBadReturner("公告不存在");
        }
        CourseAnnouncementEntity newEntity=optional.get();
        PersonEntity p=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        CourseEntity courseEntity=newEntity.getCourse();
        Teach teach=teachJPA.findByTeacherAndCourse(p,courseEntity);
        if(teach==null){
            return ResultUtil.resultBadReturner("无权限");
        }
        courseAnnouncementJPA.deleteById(courseAnnouncementId);
        return ResultUtil.resultGoodReturner();
    }
}
