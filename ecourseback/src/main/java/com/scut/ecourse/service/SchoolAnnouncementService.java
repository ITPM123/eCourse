package com.scut.ecourse.service;

import com.scut.ecourse.entity.PersonEntity;
import com.scut.ecourse.entity.ResultEntity;
import com.scut.ecourse.entity.SchoolAnnouncementEntity;
import com.scut.ecourse.jpa.PersonJPA;
import com.scut.ecourse.jpa.SchoolAnnouncementJPA;
import com.scut.ecourse.util.DateFormatUtil;
import com.scut.ecourse.util.FileUtil;
import com.scut.ecourse.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class SchoolAnnouncementService {

    @Autowired
    private SchoolAnnouncementJPA schoolAnnouncementJPA;
    @Autowired
    private PersonJPA personJPA;
    private String attachmentSubDir="/attachment/";

    //获取公告列表
    public ResultEntity list(){
        return ResultUtil.resultGoodReturner(schoolAnnouncementJPA.findAll());
    }

    //获取某个教务员所创建的公告列表
    public ResultEntity listByPersonId(int personId){
        return ResultUtil.resultGoodReturner(schoolAnnouncementJPA.findByAuthorId(personId));
    }

    //上传附件
    public ResultEntity uploadAttachment(MultipartFile file){
        ResultEntity result = FileUtil.uploadSingleFile(file,attachmentSubDir,null);
        return result;
    }


    //新增公告
    public ResultEntity add(SchoolAnnouncementEntity schoolAnnouncement,int personId){

        //TODO 判断身份
        PersonEntity author=personJPA.findById(personId).get();
        if(author.getRole()!=0){
            return ResultUtil.resultBadReturner("需要教务员权限");
        }
        schoolAnnouncement.setCreateTime(DateFormatUtil.format(new Date()));
        schoolAnnouncement.setAuthor(author);
        schoolAnnouncementJPA.save(schoolAnnouncement);
        return ResultUtil.resultGoodReturner(schoolAnnouncement);
    }

    //修改公告
    public ResultEntity update(SchoolAnnouncementEntity schoolAnnouncementEntity){

        //TODO 判断权限
        SchoolAnnouncementEntity newEntity=schoolAnnouncementJPA.findById(
                schoolAnnouncementEntity.getSchoolAnnouncementId()).get();
        schoolAnnouncementEntity.setAuthor(newEntity.getAuthor());
        schoolAnnouncementEntity.setCreateTime(DateFormatUtil.format(new Date()));
        schoolAnnouncementJPA.save(schoolAnnouncementEntity);
        return ResultUtil.resultGoodReturner(schoolAnnouncementEntity);
    }

    //删除公告
    public ResultEntity delete(int schoolAnnouncementId){

        //TODO 判断权限
        SchoolAnnouncementEntity entity=schoolAnnouncementJPA.findById(schoolAnnouncementId).get();
        schoolAnnouncementJPA.delete(entity);
        return ResultUtil.resultGoodReturner();
    }


}
