package com.scut.ecourse.service;

import com.scut.ecourse.entity.CourseAnnouncementEntity;
import com.scut.ecourse.entity.CourseEntity;
import com.scut.ecourse.entity.ResultEntity;
import com.scut.ecourse.entity.SchoolAnnouncementEntity;
import com.scut.ecourse.jpa.CourseAnnouncementJPA;
import com.scut.ecourse.jpa.CourseJPA;
import com.scut.ecourse.util.DateFormatUtil;
import com.scut.ecourse.util.FileUtil;
import com.scut.ecourse.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;

@Service
public class CourseAnnouncementService {
    @Autowired
    private CourseAnnouncementJPA courseAnnouncementJPA;
    @Autowired
    private CourseJPA courseJPA;
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
        //TODO 判断当前用户是否有权限新增
        CourseEntity courseEntity=courseJPA.findById(courseId).get();
        courseAnnouncement.setCreateTime(DateFormatUtil.format(new Date()));
        courseAnnouncement.setCourse(courseEntity);
        courseAnnouncementJPA.save(courseAnnouncement);
        return ResultUtil.resultGoodReturner(courseAnnouncement);
    }

    //修改公告
    public ResultEntity updateCourseAnnouncement(CourseAnnouncementEntity courseAnnouncementEntity){
        //TODO 判断当前用户是否有权限更新
        CourseAnnouncementEntity newEntity=courseAnnouncementJPA.findById(courseAnnouncementEntity.getCourseAnnouncementId()).get();
        newEntity.setCreateTime(DateFormatUtil.format(new Date()));
        newEntity.setContent(courseAnnouncementEntity.getContent());
        newEntity.setTitle(courseAnnouncementEntity.getTitle());
        courseAnnouncementJPA.save(newEntity);
        return ResultUtil.resultGoodReturner(newEntity);
    }

    //删除公告
    public ResultEntity deleteCourseAnnouncement(int courseAnnouncementId){
        //TODO 判断当前用户是否有权限删除
        courseAnnouncementJPA.deleteById(courseAnnouncementId);
        return ResultUtil.resultGoodReturner();
    }
}
