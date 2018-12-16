package com.scut.ecourse.controller;

import com.scut.ecourse.entity.PersonEntity;
import com.scut.ecourse.service.AcdemicDeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/acdemicDean")
public class AcdemicDeanController {

    @Autowired
    private AcdemicDeanService acdemicDeanService;

    //获取教务员列表
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Object list(@RequestParam(name = "page",defaultValue = "0")int page){
        return acdemicDeanService.list(page);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object acdemicDean(PersonEntity personEntity){
        return acdemicDeanService.createAcdemicDean(personEntity);
    }

    @RequestMapping(value = "/removeAcdemicDean",method = RequestMethod.GET)
    public Object removeAcdemicDean(int personId){
        return acdemicDeanService.removeAcdemicDean(personId);
    }

}

