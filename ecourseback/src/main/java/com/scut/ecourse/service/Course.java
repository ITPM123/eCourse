package com.scut.ecourse.service;

import com.scut.ecourse.entity.CourseEntity;
import com.scut.ecourse.entity.PersonEntity;
import com.scut.ecourse.entity.ResultEntity;
import com.scut.ecourse.entity.Take;
import com.scut.ecourse.jpa.CourseJPA;
import com.scut.ecourse.jpa.PersonJPA;
import com.scut.ecourse.jpa.TakeJPA;
import com.scut.ecourse.util.FileUtil;
import com.scut.ecourse.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class Course {
    @Autowired
    private CourseJPA courseJPA;
    @Autowired
    private PersonJPA personJPA;
    @Autowired
    private TakeJPA takeJPA;
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

    public ResultEntity getStudents(long courseId,String term){

        return ResultUtil.resultGoodReturner(personJPA.findAllStudentsById(courseId));
    }

    public ResultEntity addStudent(long courseId, String code,String term){
        //TODO 判断权限
        //TODO 判断是否重复添加
        if(term==null||term.equals("")){
            term=getCurrentTerm();
        }
        PersonEntity personEntity=personJPA.findByCode(code);
        if(personEntity==null){
            return ResultUtil.resultBadReturner("查无此人");
        }
        Optional<CourseEntity>optional=courseJPA.findById(courseId);
        if(!optional.isPresent()){
            return ResultUtil.resultBadReturner("课程不存在");
        }
        CourseEntity courseEntity=optional.get();
        Take take=new Take();
        take.setCourse(courseEntity);
        take.setStudent(personEntity);
        take.setTerm(term);
        takeJPA.save(take);
        return ResultUtil.resultGoodReturner();
    }

    private String getCurrentTerm(){
        return "2018-2019学年第一学期";
    }

    public ResultEntity removeStudent(long courseId,int personId,String term){
        //TODO 判断权限
        if(term==null||term.equals("")){
            term=getCurrentTerm();
        }
        takeJPA.delete(courseId,personId,term);
        return ResultUtil.resultGoodReturner();
    }
}
