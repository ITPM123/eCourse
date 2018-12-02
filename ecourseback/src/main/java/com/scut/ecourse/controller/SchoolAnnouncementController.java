package com.scut.ecourse.controller;

import com.scut.ecourse.entity.SchoolAnnouncementEntity;
import com.scut.ecourse.service.SchoolAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/schoolAnnouncement")
public class SchoolAnnouncementController {

    @Autowired
    private SchoolAnnouncementService schoolAnnouncementService;

    //获取公告列表
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Object list(@RequestParam(name = "page",defaultValue = "0")int page){
        return schoolAnnouncementService.list(page);
    }

    //获取某个教务员所创建的公告列表
    @RequestMapping(value = "listByAuthorId",method = RequestMethod.GET)
    public Object listByAuthorId(int authorId,int page){
        return schoolAnnouncementService.listByPersonId(authorId,page);
    }

    //上传附件，返回系统生成的文件名
    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    public Object uploadFile(@RequestParam(name = "file",required = true) MultipartFile file){
        return schoolAnnouncementService.uploadAttachment(file);
    }

    //新增公告
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public Object add(SchoolAnnouncementEntity schoolAnnouncementEntity,int personId){
        return schoolAnnouncementService.add(schoolAnnouncementEntity,personId);
    }

    //修改公告
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Object update(SchoolAnnouncementEntity schoolAnnouncementEntity){
        return schoolAnnouncementService.update(schoolAnnouncementEntity);
    }

    //删除公告
    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public Object delete(int schoolAnnouncementId){
        return schoolAnnouncementService.delete(schoolAnnouncementId);
    }
}
