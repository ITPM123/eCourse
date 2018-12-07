package com.scut.ecourse.controller;

import com.scut.ecourse.entity.PersonEntity;
import com.scut.ecourse.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/information")
public class InformationController {

    @Autowired
    InformationService informationService;

    //获取个人资料
    @RequestMapping(value = "/getInformation",method = RequestMethod.GET)
    public Object getInformation(int personId){
        return informationService.getByPersonId();
    }

    //修改个人资料
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public Object update(PersonEntity personEntity){
        return informationService.update(personEntity);
    }

    //修改密码
    @RequestMapping(value = "/updatePassword",method = RequestMethod.GET)
    public Object updatePassword(String oldPassword,String newPassword){
        return  informationService.updatePassword(oldPassword,newPassword);
    }
}
