package com.meibanlu.qa.service.mapper;

import com.meibanlu.qa.service.entity.CommendAudios;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommendAudiosMapper {
    public List<CommendAudios> findCommendAudiosBytype(String type);


    public List<CommendAudios> ListCommendaudios();


}
