package com.meibanlu.qa.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.meibanlu.qa.service.entity.CommendAudios;
import com.meibanlu.qa.service.service.CommendAudiosService;


@RestController
@RequestMapping(value = "/CommendAudiosCRUD", method = { RequestMethod.GET, RequestMethod.POST })
public class CommendAudiosCRUD {

    @Autowired
    private CommendAudiosService commendAudiosService;

    @RequestMapping("/ListCommendaudios")
    @ResponseBody
    public List<CommendAudios> ListCommendaudios(){
        return commendAudiosService.ListCommendaudios();
    }

    @RequestMapping("/ListCommendAudiosBytype")
    @ResponseBody
    public List<CommendAudios> ListCommendAudiosBytype(String type){


        return commendAudiosService.findCommendAudiosBytype(type);
    }





}
