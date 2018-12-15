package com.scut.ecourse.service;

import com.scut.ecourse.entity.PersonEntity;
import com.scut.ecourse.entity.ResultEntity;
import com.scut.ecourse.jpa.PersonJPA;
import com.scut.ecourse.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AcdemicDeanService {

    @Autowired
    private PersonJPA personJPA;
    private int elementPerPage=10;
    //获取教务员列表
    public ResultEntity list(int page){
        Page<PersonEntity> p=personJPA.findAllByRole(0,PageRequest.of(page,elementPerPage));
        HashMap<String,Object>map=new HashMap<>();
        map.put("total",p.getTotalPages());
        map.put("list",p.getContent());
        return ResultUtil.resultGoodReturner(map);
    }

    public ResultEntity createAcdemicDean(PersonEntity personEntity) {

        PersonEntity p=(PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if(p.getRole()!=3){
            return ResultUtil.resultBadReturner("需要系统管理员权限");
        }
        personJPA.save(personEntity);
        return ResultUtil.resultGoodReturner(personEntity);
    }



    public ResultEntity removeAcdemicDean(int personId) {
        PersonEntity p = (PersonEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if(p.getRole()!=3){
            return ResultUtil.resultBadReturner("需要系统管理员权限");
        }
        Optional<PersonEntity>optional = personJPA.findById(personId);
        if(!optional.isPresent()) {
            return ResultUtil.resultBadReturner("教务员不存在");
        }
        personJPA.delete(personId);
        return ResultUtil.resultGoodReturner();

    }




}
