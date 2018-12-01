package com.scut.ecourse.service;

import com.scut.ecourse.entity.CourseEntity;
import com.scut.ecourse.entity.CourseReview;
import com.scut.ecourse.entity.PersonEntity;
import com.scut.ecourse.entity.Teach;
import com.scut.ecourse.jpa.CourseJPA;
import com.scut.ecourse.jpa.CourseReviewJPA;
import com.scut.ecourse.jpa.TeachJPA;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class Review {
    @Autowired
    private CourseJPA courseJPA;
    @Autowired
    private CourseReviewJPA courseReviewJPA;
    @Autowired
    private TeachJPA teachJPA;

    public JSONArray getUnexaminedCourse(){
        JSONArray result=new JSONArray();
        List<BigInteger>unexaminedCourseId=courseReviewJPA.getUnexaminedCourseId();
        for(int i=0;i<unexaminedCourseId.size();i++){
            Long id=Long.valueOf(unexaminedCourseId.get(i).toString());
            CourseEntity entity=courseJPA.findById(id).get();
            result.add(entity);
        }
        return result;
    }

    public void addReview(String feedback,String result,String course_id){
        boolean resultBoolean=Boolean.parseBoolean(result);
        CourseEntity courseEntity=courseJPA.findById(Long.parseLong(course_id)).get();
        //需要获取当前登陆的教务员的ID
        PersonEntity personEntity=new PersonEntity();
        personEntity.setPersonId(-1);
        //--------------
        CourseReview courseReview=new CourseReview();
        courseReview.setCourse(courseEntity);
        courseReview.setPerson(personEntity);
        courseReview.setFeedback(feedback);
        courseReview.setResult(resultBoolean);
        courseReviewJPA.save(courseReview);
        //申报成功，添加教课这个关系
        if(resultBoolean){
            Teach teach=new Teach();
            teach.setCourse(courseEntity);
            teach.setTeacher(personEntity);
            teachJPA.save(teach);
        }
    }
}
