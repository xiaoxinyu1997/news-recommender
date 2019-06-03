package com.meibanlu.qa.service.controller;

import com.meibanlu.qa.service.entity.Audios;
import com.meibanlu.qa.service.mapper.AudiosMapper;
import com.meibanlu.qa.service.service.AudiosService;
import com.meibanlu.qa.service.service.MusicRecommenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/MusicRecommender", method = { RequestMethod.GET, RequestMethod.POST })
public class MusicRecommender {
    private static final int MUSIC_NUM = 50;
    @Autowired
    MusicRecommenderService musicRecommenderService;
    @RequestMapping(value = "/bySingerOrName", method = RequestMethod.GET)
    public List<Audios> bySingerOrName(String singer, String songname){
        if (null == singer && null == songname) {
            return null;
        } else if (null == singer) {
            return musicRecommenderService.getAudioByName(songname);
        } else if (null == songname) {
            return musicRecommenderService.getAudioBySinger(singer);
        } else {
            return musicRecommenderService.getAudioByNameAndSinger(songname, singer);
        }
    }

    @RequestMapping(value = "/byTags", method = RequestMethod.GET)
    public List<Audios> byTags(String tag){
        return musicRecommenderService.getAudiosByTags(tag);
    }

    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public List<Audios> recommend(int userId){
        if(1==userId){
            return musicRecommenderService.hotMusic(MUSIC_NUM);
        }else{
            return musicRecommenderService.recommend(userId, MUSIC_NUM);
        }
    }

    /**
     * 更新用户偏好.
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updateUserPreferences", method = RequestMethod.GET)
    public synchronized String updateUserPreferences(int userId,int originalLength, int durationOfPlay, int audiosid) {
        musicRecommenderService.updatePreferences(userId, originalLength, durationOfPlay, audiosid);
        return "down";
    }
}
