package com.meibanlu.qa.service.service;

import org.springframework.stereotype.Service;
import com.meibanlu.qa.service.entity.UserNewsBehavior;
import com.meibanlu.qa.service.mapper.UserNewsBehaviorMapper;

import javax.annotation.Resource;
@Service
public class UserNewsBehaviorService {
    @Resource
    private UserNewsBehaviorMapper UserNewsBehaviorMapper;

    public int shift(int userid, long originalLength, long durationOfPlay,int newsid) {
        return UserNewsBehaviorMapper.shift(userid,originalLength,durationOfPlay,newsid);

    }



}

