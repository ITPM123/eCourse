package com.scut.ecourse.service;

import com.scut.ecourse.entity.ResultEntity;
import com.scut.ecourse.jpa.CourseAnnouncementJPA;
import com.scut.ecourse.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseAnnouncementService {
    @Autowired
    private CourseAnnouncementJPA courseAnnouncementJPA;

    public ResultEntity getCourseAnnouncementByCourseId(long courseId){
        return ResultUtil.resultGoodReturner(courseAnnouncementJPA.findByCourseId(courseId));
    }
}
