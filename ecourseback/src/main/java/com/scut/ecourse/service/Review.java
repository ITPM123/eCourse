package com.scut.ecourse.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.scut.ecourse.entity.CourseEntity;
import com.scut.ecourse.entity.CourseReview;
import com.scut.ecourse.entity.PersonEntity;
import com.scut.ecourse.entity.Teach;
import com.scut.ecourse.jpa.CourseJPA;
import com.scut.ecourse.jpa.CourseReviewJPA;
import com.scut.ecourse.jpa.PersonJPA;
import com.scut.ecourse.jpa.TeachJPA;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class Review {
    @Autowired
    private CourseJPA courseJPA;
    @Autowired
    private CourseReviewJPA courseReviewJPA;
    @Autowired
    private TeachJPA teachJPA;
    @Autowired
    private PersonJPA personJPA;

    public JSONArray getUnexaminedCourse(int pageNumber,int pageSize){
        JSONArray result=new JSONArray();

        PersonEntity personEntity=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if(personEntity.getRole()!=0){
            return result;
        }

        //分页
        PageRequest pageRequest=new PageRequest(pageNumber,pageSize);
        //
        Page<BigInteger> page=courseReviewJPA.getUnexaminedCourseId(pageRequest);
        List<BigInteger> unexaminedCourseId=page.getContent();
        for(int i=0;i<unexaminedCourseId.size();i++){
            Long id=Long.valueOf(unexaminedCourseId.get(i).toString());
            CourseEntity entity=courseJPA.findById(id).get();
            PersonEntity temp0=personJPA.findById(entity.getPerson_id().intValue()).get();
            JSONObject temp=new JSONObject();
            temp.put("person_name",temp0.getRealName());
            Map<String,Object>map=new HashMap<>();
            map.put("course",entity);
            map.put("personName",temp0.getRealName());
            //result.add(temp);
            result.add(map);
        }
        return result;
    }
    public int getUnexaminedCourseCount(){

        PersonEntity personEntity=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if(personEntity.getRole()!=0){
            return -1;
        }

        PageRequest pageRequest=new PageRequest(0,100000);
        Page<BigInteger> page=courseReviewJPA.getUnexaminedCourseId(pageRequest);
        List<BigInteger> unexaminedCourseId=page.getContent();
        return unexaminedCourseId.size();
    }

    public JSONArray getExaminedCourse(int pageNumber,int pageSize){
        JSONArray result=new JSONArray();

        PersonEntity personEntity=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if(personEntity.getRole()!=0){
            return result;
        }

        //分页
        PageRequest pageRequest=new PageRequest(pageNumber,pageSize);
        //

        Page<CourseReview>pages= courseReviewJPA.findAll(pageRequest);
        List<CourseReview>reviews=pages.getContent();

        for(int i=0;i<reviews.size();i++){
            JSONObject temp=new JSONObject();
            temp.put("course_info",reviews.get(i).getCourse());
            temp.put("review_id",reviews.get(i).getId());
            result.add(temp);
        }

        return result;
    }
    public int getExaminedCourseCount(){

        PersonEntity personEntity=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if(personEntity.getRole()!=0){
            return -1;
        }

        PageRequest pageRequest=new PageRequest(0,100000);
        Page<CourseReview>pages= courseReviewJPA.findAll(pageRequest);
        List<CourseReview>reviews=pages.getContent();
        return reviews.size();
    }

    public String addReview(String feedback,String result,String course_id){
        boolean resultBoolean=Boolean.parseBoolean(result);
        CourseEntity courseEntity=courseJPA.findById(Long.parseLong(course_id)).get();
        //需要获取当前登陆的教务员的ID
        PersonEntity personEntity=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if(personEntity.getRole()!=0){
            return "invalid role";
        }
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
            PersonEntity p=personJPA.findById(courseEntity.getPerson_id().intValue()).get();
            teach.setTeacher(p);
            teachJPA.save(teach);
        }
        return "success";
    }

    public JSONObject getReview(Long review_id){
        CourseReview courseReview= courseReviewJPA.getReviewById(review_id);
        JSONObject result=new JSONObject();

        PersonEntity personEntity=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if(personEntity.getRole()!=0){
            return result;
        }

        result.put("course",courseReview.getCourse());
        result.put("person_id",courseReview.getPerson().getPersonId());
        result.put("feedback",courseReview.getFeedback());
        result.put("result",courseReview.isResult());
        return  result;
    }

    public JSONArray getCoursesByPerson(){
        JSONArray result=new JSONArray();
        PersonEntity personEntity=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if(personEntity.getRole()!=1){
            return result;
        }
        List<CourseEntity> list=courseJPA.getCoursesByPersonId(Long.parseLong(personEntity.getPersonId()+""));
        for(int i=0;i<list.size();i++){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("courseInfo",list.get(i));
            List<CourseReview> tempList=courseReviewJPA.getReviewByCourseId(list.get(i).getCourse_id());
            if(tempList.size()==0){
                jsonObject.put("review_status","审核中");
            }
            else{
                if(tempList.get(0).isResult()){
                    jsonObject.put("review_status","已通过");
                }
                else{
                    jsonObject.put("review_status","未通过");
                }
            }
            result.add(jsonObject);
        }
        return result;
    }

}
