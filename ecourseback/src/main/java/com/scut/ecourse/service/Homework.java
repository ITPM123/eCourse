package com.scut.ecourse.service;

import com.scut.ecourse.entity.*;
import com.scut.ecourse.jpa.*;
import com.scut.ecourse.util.FileUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class Homework {
    @Autowired
    private HomeworkJPA homeworkJPA;
    @Autowired
    private CourseJPA courseJPA;
    @Autowired
    private DoHomeworkJPA doHomeworkJPA;
    @Autowired
    private PersonJPA personJPA;
    @Autowired
    private TakeJPA takeJPA;

    public void uploadCourseHomework(Long course_id, String homework_title, String description, Date deadline, Date release_date, MultipartFile file){
        CourseEntity courseEntity=courseJPA.findById(course_id).get();
        HomeworkEntity homeworkEntity=new HomeworkEntity();
        homeworkEntity.setCourse(courseEntity);
        homeworkEntity.setDeadline(deadline);
        homeworkEntity.setDescription(description);
        homeworkEntity.setHomework_title(homework_title);
        homeworkEntity.setRelease_time(release_date);
        if(file!=null){
            ResultEntity resultEntity= FileUtil.uploadSingleFile(file,"HomeworkAttachment");
            homeworkEntity.setAttachment((String)resultEntity.getData());
            homeworkEntity.setAttachment_filename(file.getOriginalFilename());
        }
        else{
            homeworkEntity.setAttachment("null");
            homeworkEntity.setAttachment_filename("null");
        }
        homeworkJPA.save(homeworkEntity);
    }

    public void deleteCourseHomeworkById(Long homework_id){
        doHomeworkJPA.deleteByHomeworkId(homework_id);
        homeworkJPA.deleteById(homework_id);
    }

    public int getStudentHomeworkListCount(Long homework_id){
        PageRequest pageRequest=new PageRequest(0,1000000);
        Page<DoHomework> page=doHomeworkJPA.findByHomeworkId(homework_id,pageRequest);
        return page.getContent().size();
    }

    public JSONArray getStudentHomeworkList(Long homework_id,int pageNumber,int pageSize){
        JSONArray result = new JSONArray();
        PageRequest pageRequest=new PageRequest(pageNumber,pageSize);
        Page<DoHomework> page=doHomeworkJPA.findByHomeworkId(homework_id,pageRequest);
        List<DoHomework> list=page.getContent();
        for(int i=0;i<list.size();i++){
            result.add(list.get(i));
        }
        return result;
    }

    public void reviewStudentHomework(Long do_homework_id,int score,String comment){
        doHomeworkJPA.review(do_homework_id,score,comment);
    }

    public String uploadStudentHomework(Long homework_id,MultipartFile attachment){
        //待完善--------------
        //需要获取登陆的用户的信息
        PersonEntity personEntity=personJPA.findById(2).get();
        //--------------------

        /*
        判断是否超过提交期限
        HomeworkEntity temp=homeworkJPA.findById(homework_id).get();
        Date d=new Date();
        if(d.after(temp.getDeadline())){
            return "after Deadline";
        }
        */

        List<DoHomework>temp=doHomeworkJPA.findByPersonIdAndHomeworkId(personEntity.getPersonId(),homework_id);

        if(temp.size()==0){
            DoHomework doHomework=new DoHomework();
            doHomework.setPerson(personEntity);
            HomeworkEntity homeworkEntity=homeworkJPA.findById(homework_id).get();
            doHomework.setHomework(homeworkEntity);
            doHomework.setMarked(false);
            doHomework.setSubmit_time(new Date());
            doHomework.setAttachment_filename(attachment.getOriginalFilename());
            ResultEntity resultEntity=FileUtil.uploadSingleFile(attachment,"StudentHomeworkAttachment");
            doHomework.setAttachment((String)resultEntity.getData());
            doHomeworkJPA.save(doHomework);
        }
        if(temp.size()==1){
            return "already uploaded";
        }

        return "upload success";
    }

    public JSONObject getStudentHomeworkSubmitData(Long homework_id){
        JSONObject result=new JSONObject();
        HomeworkEntity homeworkEntity=homeworkJPA.findById(homework_id).get();
        int takeCount=-1;
        takeCount = takeJPA.getCourseStudentCount(homeworkEntity.getCourse().getCourse_id());
        result.put("CourseStudentCount",takeCount);
        int homeworkCount=-1;
        homeworkCount=doHomeworkJPA.getDoHomeworkCount(homework_id);
        result.put("SubmittedStudentHomeworkCount",homeworkCount);
        return result;
    }
}
