package com.scut.ecourse.service;

import com.scut.ecourse.entity.*;
import com.scut.ecourse.jpa.*;
import com.scut.ecourse.entity.*;
import com.scut.ecourse.jpa.CourseJPA;
import com.scut.ecourse.jpa.PersonJPA;
import com.scut.ecourse.jpa.TakeJPA;
import com.scut.ecourse.jpa.TeachJPA;
import com.scut.ecourse.util.FileUtil;
import com.scut.ecourse.util.ResultUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class Course {
    @Autowired
    private CourseJPA courseJPA;
    @Autowired
    private TeachJPA teachJPA;
    @Autowired
    private TakeJPA takeJPA;
    @Autowired
    private PersonJPA personJPA;
    @Autowired
    private CoursewareOfCourseJPA coursewareOfCourseJPA;
    @Autowired
    private CoursewareJPA coursewareJPA;
    @Autowired
    private PersonJPA personJPA;
    @Autowired
    private TakeJPA takeJPA;
    @Autowired
    private TeachJPA teachJPA;

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
        if(image!=null){
            ResultEntity resultEntity=FileUtil.uploadSingleFile(image,"CourseImage");
            courseEntity.setImage_file_name((String) resultEntity.getData());
        }
        else{
            courseEntity.setImage_file_name(null);
        }

        System.out.println("course_id"+courseEntity.getCourse_id());
        courseJPA.save(courseEntity);
    }

    public ResultEntity getStudents(long courseId,String term){

        return ResultUtil.resultGoodReturner(personJPA.findAllStudentsById(courseId));
    }

    public ResultEntity addStudent(long courseId, String code,String term){
        PersonEntity p=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Optional<CourseEntity>optional=courseJPA.findById(courseId);
        if(!optional.isPresent()){
            return ResultUtil.resultBadReturner("课程不存在");
        }
        CourseEntity courseEntity=optional.get();
        if(term==null||term.equals("")){
            term=getCurrentTerm();
        }
        Teach teach=teachJPA.findByTeacherAndCourseAndTerm(p,courseEntity,term);
        if(teach==null){
            return ResultUtil.resultBadReturner("无权限");
        }

        PersonEntity personEntity=personJPA.findByCode(code);
        if(personEntity==null){
            return ResultUtil.resultBadReturner("查无此人");
        }
        Take t=takeJPA.findByStudentAndCourseAndTerm(personEntity,courseEntity,term);
        if(t!=null){
            return ResultUtil.resultBadReturner("重复添加");
        }
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
        PersonEntity p=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Optional<CourseEntity>optional=courseJPA.findById(courseId);
        if(!optional.isPresent()){
            return ResultUtil.resultBadReturner("课程不存在");
        }
        CourseEntity courseEntity=optional.get();
        if(term==null||term.equals("")){
            term=getCurrentTerm();
        }
        Teach teach=teachJPA.findByTeacherAndCourseAndTerm(p,courseEntity,term);
        if(teach==null){
            return ResultUtil.resultBadReturner("无权限");
        }
        Optional<PersonEntity>studentOptional=personJPA.findById(personId);
        if(!studentOptional.isPresent()){
            return ResultUtil.resultBadReturner("查无此人");
        }
        PersonEntity personEntity=studentOptional.get();
        Take t=takeJPA.findByStudentAndCourseAndTerm(personEntity,courseEntity,term);
        if(t==null){
            return ResultUtil.resultBadReturner("课程名单中找不到该学生");
        }
        takeJPA.delete(courseId,personId,term);
        return ResultUtil.resultGoodReturner();
    }

    public JSONArray getCourses(int pageNumber,int pageSize){
        //待完善--------------
        //需要获取登陆的用户的信息
        PersonEntity personEntity=personJPA.findById(2).get();
        //--------------------

        //分页
        PageRequest pageRequest=new PageRequest(pageNumber,pageSize);

        JSONArray result=new JSONArray();

        if(personEntity.getRole()==1){
            Page<Teach> page = teachJPA.getCoursesById(personEntity.getPersonId(),pageRequest);
            List<Teach> courses=page.getContent();
            for(int i=0;i<courses.size();i++){
                result.add(courses.get(i).getCourse());
            }
        }
        if(personEntity.getRole()==2){
            Page<Take> page = takeJPA.getCoursesById(personEntity.getPersonId(),pageRequest);
            List<Take> courses=page.getContent();
            for(int i=0;i<courses.size();i++){
                result.add(courses.get(i).getCourse());
            }
        }
        return result;
    }

    public int getCoursesCount(){
        //待完善--------------
        //需要获取登陆的用户的信息
        PersonEntity personEntity=personJPA.findById(2).get();
        //--------------------

        //分页
        PageRequest pageRequest=new PageRequest(0,100000);

        int result=0;

        if(personEntity.getRole()==1){
            Page<Teach> page = teachJPA.getCoursesById(personEntity.getPersonId(),pageRequest);
            List<Teach> courses=page.getContent();
            return courses.size();
        }
        if(personEntity.getRole()==2){
            Page<Take> page = takeJPA.getCoursesById(personEntity.getPersonId(),pageRequest);
            List<Take> courses=page.getContent();
            return courses.size();
        }
        return result;
    }

    public JSONObject getCourseInfoById(Long id){
        JSONArray temp =new JSONArray();
        temp.add(courseJPA.findById(id).get());
        return temp.getJSONObject(0);
    }

    public int getCoursewareListCount(Long course_id){

        //待完善--------------
        //需要获取登陆的用户的信息
        PersonEntity personEntity=personJPA.findById(2).get();
        //--------------------

        if(personEntity.getRole()==1){
            PageRequest pageRequest=new PageRequest(0,100000);
            Page<CoursewareOfCourse>page=coursewareOfCourseJPA.findByCourseId(course_id,pageRequest);
            return page.getContent().size();
        }
        if(personEntity.getRole()==2){
            PageRequest pageRequest=new PageRequest(0,100000);
            Page<CoursewareOfCourse>page=coursewareOfCourseJPA.findVisibleCoursewaresByCourseId(course_id,pageRequest);
            return page.getContent().size();
        }

        return -1;
    }

    public JSONArray getCoursewareList(Long course_id,int pageNumber,int pageSize){
        JSONArray result=new JSONArray();

        //待完善--------------
        //需要获取登陆的用户的信息
        PersonEntity personEntity=personJPA.findById(2).get();
        //--------------------

        if(personEntity.getRole()==1){
            PageRequest pageRequest=new PageRequest(pageNumber,pageSize);
            Page<CoursewareOfCourse>page=coursewareOfCourseJPA.findByCourseId(course_id,pageRequest);
            List<CoursewareOfCourse>list=page.getContent();
            for(int i=0;i<list.size();i++){
                result.add(list.get(i).getCourseware());
            }
        }
        if(personEntity.getRole()==2){
            PageRequest pageRequest=new PageRequest(pageNumber,pageSize);
            Page<CoursewareOfCourse>page=coursewareOfCourseJPA.findVisibleCoursewaresByCourseId(course_id,pageRequest);
            List<CoursewareOfCourse>list=page.getContent();
            for(int i=0;i<list.size();i++){
                result.add(list.get(i).getCourseware());
            }
        }
        return result;
    }

    public void uploadCourseware(Long course_id, String name, Date upload_date,boolean visibility,MultipartFile file){
        CourseEntity courseEntity=courseJPA.findById(course_id).get();
        CoursewareEntity coursewareEntity=new CoursewareEntity();
        coursewareEntity.setName(name);
        coursewareEntity.setUpload_date(upload_date);
        coursewareEntity.setVisibility(visibility);
        ResultEntity resultEntity=FileUtil.uploadSingleFile(file,"Courseware");
        coursewareEntity.setCourseware_name(file.getOriginalFilename());
        coursewareEntity.setFile_name((String)resultEntity.getData());
        coursewareJPA.save(coursewareEntity);
        CoursewareOfCourse coursewareOfCourse=new CoursewareOfCourse();
        coursewareOfCourse.setCourse(courseEntity);
        coursewareOfCourse.setCourseware(coursewareEntity);
        coursewareOfCourseJPA.save(coursewareOfCourse);
    }

    public void modifyCourseware(Long courseware_id,boolean visibility){
        coursewareJPA.modify(courseware_id,visibility);
    }

    public void deleteCourseware(Long courseware_id){
        coursewareOfCourseJPA.deleteByCoursewareId(courseware_id);
        coursewareJPA.deleteById(courseware_id);
    }

}
