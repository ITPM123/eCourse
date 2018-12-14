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

    //获取学生列表
    public ResultEntity list(int page){
        Page<PersonEntity> p=personJPA.findAll(PageRequest.of(page,elementPerPage));
        HashMap<String,Object>map=new HashMap<>();
        map.put("total",p.getTotalPages());
        map.put("list",p.getContent());
        return ResultUtil.resultGoodReturner(map);

    public ResultEntity createStudent(PersonEntity personEntity) {

        PersonEntity p=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if(p.getRole()!=0){
            return ResultUtil.resultBadReturner("需要教务员权限");
        }
        personJPA.save(personEntity);
        return ResultUtil.resultGoodReturner(personEntity);
    }


    public ResultEntity removeStudent(int personId) {
        PersonEntity p = (PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if(p.getRole()!=0){
            return ResultUtil.resultBadReturner("需要教务员权限");
        }
        Optional<PersonEntity> optional = personJPA.findById(personId);
        if(!optional.isPresent()) {
            return ResultUtil.resultBadReturner("学生不存在");
        }
        personJPA.delete(personId);
        return ResultUtil.resultGoodReturner();
    }


}