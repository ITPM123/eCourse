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
public class TeacherService {

    @Autowired
    private PersonJPA personJPA;

    public ResultEntity createTeacher(PersonEntity personEntity) {

        PersonEntity p=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if(p.getRole()!=0){
            return ResultUtil.resultBadReturner("需要教务员权限");
        }
        personJPA.save(personEntity);
        return ResultUtil.resultGoodReturner(personEntity);
    }


    public ResultEntity removeTeacher(int personId) {
        PersonEntity p = (PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if(p.getRole()!=0){
            return ResultUtil.resultBadReturner("需要教务员权限");
        }
        Optional<PersonEntity> optional = personJPA.findById(personId);
        if(!optional.isPresent()) {
            return ResultUtil.resultBadReturner("教师不存在");
        }
        personJPA.delete(personId);
        return ResultUtil.resultGoodReturner();
    }
}