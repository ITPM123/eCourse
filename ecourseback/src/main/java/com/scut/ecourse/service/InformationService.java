package com.scut.ecourse.service;

import com.scut.ecourse.entity.PersonEntity;
import com.scut.ecourse.entity.ResultEntity;
import com.scut.ecourse.jpa.PersonJPA;
import com.scut.ecourse.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InformationService {
    @Autowired
    PersonJPA personJPA;

    //获取个人资料
    public ResultEntity getByPersonId(int personId){
        Optional<PersonEntity> optional=personJPA.findById(personId);
        if(!optional.isPresent()){
            return ResultUtil.resultBadReturner("查无此人");
        }
        PersonEntity personEntity=optional.get();
        return ResultUtil.resultGoodReturner(personEntity);
    }

    //修改个人资料
    public ResultEntity update(PersonEntity personEntity){
        //TODO 判断权限
        return null;
    }

    //修改密码
    public ResultEntity updatePassword(String oldPassword,String newPassword){
        //TODO 获取当前登录用户
        return null;
    }
}
