package com.meibanlu.qa.service.service;


import java.util.List;


import org.springframework.stereotype.Service;

import com.meibanlu.qa.service.entity.CommendAudios;
import com.meibanlu.qa.service.mapper.CommendAudiosMapper;

import javax.annotation.Resource;


@Service
public class CommendAudiosService {
    @Resource
    private CommendAudiosMapper commendAudiosMapper;



    public List<CommendAudios> findCommendAudiosBytype(String type) {

        return commendAudiosMapper.findCommendAudiosBytype(type);
    }



    public List<CommendAudios> ListCommendaudios(){
        return	commendAudiosMapper.ListCommendaudios();
    }


}
