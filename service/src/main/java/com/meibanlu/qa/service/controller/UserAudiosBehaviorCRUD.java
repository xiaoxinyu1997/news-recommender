package com.meibanlu.qa.service.controller;
import com.meibanlu.qa.service.service.MusicRecommenderService;
import com.meibanlu.qa.service.service.UserNewsBehaviorService;
import com.meibanlu.qa.service.service.UserAudiosBehaviorService;
import com.meibanlu.utils.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/UserAudiosBehaviorCRUD", method = { RequestMethod.GET, RequestMethod.POST })
public class UserAudiosBehaviorCRUD {
    @Autowired
    private UserAudiosBehaviorService userAudiosBehaviorService;
    @Autowired
    private UserNewsBehaviorService userNewsBehaviorService;
    @Autowired
    private MusicRecommenderService musicRecommenderService;

    @RequestMapping(value = "/shift", method = RequestMethod.GET)
    public Resp shift(HttpServletRequest request, int originalLength, int durationOfPlay,int audiosId, String audioType) {
        int userId = 1;
        String userIdObj = (String)request.getSession().getAttribute("userId");
        if(userIdObj != null){
            userId = Integer.valueOf(userIdObj);
        }
        int result = 0;
        if("audio".equals(audioType)){
            result = userAudiosBehaviorService.shift(userId,originalLength, durationOfPlay, audiosId);
            musicRecommenderService.updatePreferences(userId, originalLength, durationOfPlay, audiosId);
        }else if("news".equals(audioType)){
            result = userNewsBehaviorService.shift(userId,originalLength, durationOfPlay, audiosId);
        }
        if (result >= 1) {
            return Resp.success();
        } else {
            return Resp.error();
        }
    }

}
