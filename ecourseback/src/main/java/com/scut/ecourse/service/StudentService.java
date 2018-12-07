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
public class StudentService {

    @Autowired
    private PersonJPA personJPA;

    public void createStudent(int personId, String username, String password, int sex, String school,
                              String realName, String code, String title, String major,
                              String grade, String classs, String address, String email, String motto) {

        PersonEntity personEntity = new PersonEntity();

        //------------
        personEntity.setPersonId(-1);
        //------------

        personEntity.setUsername(username);
        personEntity.setPassword(password);
        personEntity.setSex(sex);
        personEntity.setSchool(school);
        personEntity.setRole(2);
        personEntity.setRealName(realName);
        personEntity.setCode(code);
        personEntity.setTitle(title);
        personEntity.setMajor(major);
        personEntity.setGrade(grade);
        personEntity.setClasss(classs);
        personEntity.setAddress(address);
        personEntity.setEmail(email);
        personEntity.setMotto(motto);
    }


    public ResultEntity removeStudent(int personId) {
        PersonEntity p = (PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        Optional<PersonEntity> optional = personJPA.findById(personId);
        if(!optional.isPresent()) {
            return ResultUtil.resultBadReturner("学生不存在");
        }
        personJPA.delete(personId);
        return ResultUtil.resultGoodReturner();

    }


}