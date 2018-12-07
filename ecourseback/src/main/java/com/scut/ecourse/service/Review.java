package com.scut.ecourse.service;

import com.scut.ecourse.entity.CourseEntity;
import com.scut.ecourse.entity.CourseReview;
import com.scut.ecourse.entity.PersonEntity;
import com.scut.ecourse.entity.Teach;
import com.scut.ecourse.jpa.CourseJPA;
import com.scut.ecourse.jpa.CourseReviewJPA;
import com.scut.ecourse.jpa.TeachJPA;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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

    public JSONArray getUnexaminedCourse(int pageNumber,int pageSize){
        JSONArray result=new JSONArray();
        //分页
        PageRequest pageRequest=new PageRequest(pageNumber,pageSize);
        //
        Page<BigInteger> page=courseReviewJPA.getUnexaminedCourseId(pageRequest);
        List<BigInteger> unexaminedCourseId=page.getContent();
        for(int i=0;i<unexaminedCourseId.size();i++){
            Long id=Long.valueOf(unexaminedCourseId.get(i).toString());
            CourseEntity entity=courseJPA.findById(id).get();
            result.add(entity);
        }
        return result;
    }
    public int getUnexaminedCourseCount(){
        PageRequest pageRequest=new PageRequest(0,100000);
        Page<BigInteger> page=courseReviewJPA.getUnexaminedCourseId(pageRequest);
        List<BigInteger> unexaminedCourseId=page.getContent();
        return unexaminedCourseId.size();
    }

    public JSONArray getExaminedCourse(int pageNumber,int pageSize){
        JSONArray result=new JSONArray();

        //分页
        PageRequest pageRequest=new PageRequest(pageNumber,pageSize);
        //

        Page<CourseReview>pages= courseReviewJPA.findAll(pageRequest);
        List<CourseReview>reviews=pages.getContent();

        for(int i=0;i<reviews.size();i++){
            result.add(reviews.get(i).getCourse());
        }

        return result;
    }
    public int getExaminedCourseCount(){
        PageRequest pageRequest=new PageRequest(0,100000);
        Page<CourseReview>pages= courseReviewJPA.findAll(pageRequest);
        List<CourseReview>reviews=pages.getContent();
        return reviews.size();
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

    public JSONObject getReview(Long review_id){
        CourseReview courseReview= courseReviewJPA.getReviewById(review_id);
        JSONObject result=new JSONObject();
        result.put("course",courseReview.getCourse());
        result.put("person",courseReview.getPerson());
        result.put("feedback",courseReview.getFeedback());
        result.put("result",courseReview.isResult());
        return  result;
    }

}
