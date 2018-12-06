package com.scut.ecourse.service;

import com.scut.ecourse.entity.PersonEntity;
import com.scut.ecourse.entity.ResultEntity;
import com.scut.ecourse.jpa.PersonJPA;
import com.scut.ecourse.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InformationService {
    @Autowired
    PersonJPA personJPA;

    //获取个人资料
    public ResultEntity getByPersonId(){
        //获取当前登录用户
        PersonEntity personEntity=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return ResultUtil.resultGoodReturner(personEntity);
    }

    //修改个人资料
    public ResultEntity update(PersonEntity personEntity){
        PersonEntity p=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        p.setNickname(personEntity.getNickname());
        p.setMotto(personEntity.getMotto());
        p.setEmail(personEntity.getEmail());
        p.setContact(personEntity.getContact());
        personJPA.save(p);
        return ResultUtil.resultGoodReturner(p);
    }

    //修改密码
    public ResultEntity updatePassword(String oldPassword,String newPassword){
        PersonEntity p=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if(!p.getPassword().equals(oldPassword)){
            return ResultUtil.resultBadReturner("密码错误,修改失败");
        }
        p.setPassword(newPassword);
        personJPA.save(p);
        return ResultUtil.resultGoodReturner(p);
    }
}
