package com.scut.ecourse.service;

import com.scut.ecourse.entity.CourseEntity;
import com.scut.ecourse.entity.ResultEntity;
import com.scut.ecourse.jpa.CourseJPA;
import com.scut.ecourse.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Course {
    @Autowired
    private CourseJPA courseJPA;

    public void createCourse(String name,String credit,String outline,
                             String overview,String teaching_goal,String description,
                             MultipartFile image){
        int creditInt=Integer.parseInt(credit);

        CourseEntity courseEntity=new CourseEntity();

        //待完善--------------
        //需要获取登陆的用户的信息
        courseEntity.setPerson_id(new Long(-1));
        //--------------------

        courseEntity.setName(name);
        courseEntity.setCredit(creditInt);
        courseEntity.setOutline(outline);
        courseEntity.setOverview(overview);
        courseEntity.setTeaching_goal(teaching_goal);
        courseEntity.setDescription(description);
        //save image file
        ResultEntity resultEntity=FileUtil.uploadSingleFile(image,"CourseImage");
        courseEntity.setImage_file_name((String) resultEntity.getData());

        System.out.println("course_id"+courseEntity.getCourse_id());
        courseJPA.save(courseEntity);
    }
}
