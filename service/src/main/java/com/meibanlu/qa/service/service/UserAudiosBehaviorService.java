package com.meibanlu.qa.service.service;

import java.util.List;



import org.springframework.stereotype.Service;
import com.meibanlu.qa.service.entity.UserAudiosBehavior;
import com.meibanlu.qa.service.mapper.UserAudiosBehaviorMapper;

import javax.annotation.Resource;
@Service
public class UserAudiosBehaviorService {
    @Resource
    private UserAudiosBehaviorMapper userAudiosBehaviorMapper;

    public int shift(int userId, int originalLength, int durationOfPlay,int audiosId) {
        return userAudiosBehaviorMapper.shift(userId,originalLength,durationOfPlay,audiosId);

    }



}

