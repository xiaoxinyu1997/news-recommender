package com.meibanlu.qa.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserAudiosBehaviorMapper {

    int shift(@Param("userId")int userId,@Param("originalLength")long originalLength,@Param("durationOfPlay")long durationOfPlay,@Param("audiosId")int audiosId);

}
