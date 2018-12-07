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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

@Service
public class SchoolAnnouncementService {

    @Autowired
    private SchoolAnnouncementJPA schoolAnnouncementJPA;
    @Autowired
    private PersonJPA personJPA;
    private String attachmentSubDir="/attachment/";
    private int elementPerPage=10;
    //获取公告列表
    public ResultEntity list(int page){
        Page<SchoolAnnouncementEntity> p=schoolAnnouncementJPA.findAll(PageRequest.of(page,elementPerPage));
        HashMap<String,Object>map=new HashMap<>();
        map.put("total",p.getTotalPages());
        map.put("list",p.getContent());
        return ResultUtil.resultGoodReturner(map);
    }

    //获取某个教务员所创建的公告列表
    public ResultEntity listByPersonId(int personId,int page){
        Page<SchoolAnnouncementEntity> p=schoolAnnouncementJPA.findByAuthorId(personId,PageRequest.of(page,elementPerPage));
        HashMap<String,Object>map=new HashMap<>();
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
    public ResultEntity add(SchoolAnnouncementEntity schoolAnnouncement){
        PersonEntity author=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
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
        Optional<SchoolAnnouncementEntity>optional=schoolAnnouncementJPA.findById(
                schoolAnnouncementEntity.getSchoolAnnouncementId());
        if(!optional.isPresent()){
            return ResultUtil.resultBadReturner("公告不存在");
        }
        SchoolAnnouncementEntity newEntity=optional.get();
        PersonEntity author=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if(newEntity.getAuthor().getPersonId()!=author.getPersonId()){
            return ResultUtil.resultBadReturner("无权限");
        }
        schoolAnnouncementEntity.setAuthor(newEntity.getAuthor());
        schoolAnnouncementEntity.setCreateTime(DateFormatUtil.format(new Date()));
        schoolAnnouncementJPA.save(schoolAnnouncementEntity);
        return ResultUtil.resultGoodReturner(schoolAnnouncementEntity);
    }

    //删除公告
    public ResultEntity delete(int schoolAnnouncementId){

        Optional<SchoolAnnouncementEntity>optional=schoolAnnouncementJPA.findById(
                schoolAnnouncementId);
        if(!optional.isPresent()){
            return ResultUtil.resultBadReturner("公告不存在");
        }
        SchoolAnnouncementEntity entity=optional.get();
        PersonEntity author=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if(entity.getAuthor().getPersonId()!=author.getPersonId()){
            return ResultUtil.resultBadReturner("无权限");
        }
        schoolAnnouncementJPA.delete(entity);
        return ResultUtil.resultGoodReturner();
    }


}
