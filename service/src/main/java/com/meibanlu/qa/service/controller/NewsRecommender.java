package com.meibanlu.qa.service.controller;

import com.meibanlu.qa.service.conf.RedisUtil;
import com.meibanlu.qa.service.entity.Audios;
import com.meibanlu.qa.service.entity.News;
import com.meibanlu.qa.service.service.NewsRecommenderService;
import com.meibanlu.utils.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/NewsRecommender", method = { RequestMethod.GET, RequestMethod.POST })
public class NewsRecommender {

    private static final int NEWS_NUM = 50;
    @Autowired
    private NewsRecommenderService newsRecommenderService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 更新关键词列表.
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/invert", method = RequestMethod.GET)
    public synchronized String invert() {
        newsRecommenderService.invert();
        return "down";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Resp search(int userId, String keywords) {
        System.out.println("输入keywords：" + keywords);
        redisUtil.set("queryLast_Type-" + userId, "news");
        List<Audios> resultAudios = new ArrayList<>(NEWS_NUM);
        List<News> results = new ArrayList<>(NEWS_NUM);
        String[] input = {keywords};
//        int lenthOfKeywords = keywords.length;
        if(userId==1&&(keywords == null||keywords.isEmpty())){
            results=newsRecommenderService.feverBasedrecommend(NEWS_NUM);
        } else if (keywords == null || keywords.isEmpty()) {
            results = newsRecommenderService.recommend(userId, NEWS_NUM);
        } else if ( newsRecommenderService.getClassesNames().containsKey(keywords)&&userId==1) {
            System.out.println("匹配类型:" + keywords);
            int classid = newsRecommenderService.getClassesNames().get(keywords);
            results = newsRecommenderService.searchNewsByClass(classid, NEWS_NUM);
        } else if ( newsRecommenderService.getClassesNames().containsKey(keywords)&&userId!=1) {
            int classid = newsRecommenderService.getClassesNames().get(keywords);
            results = newsRecommenderService.searchNewsByClass(classid, userId, NEWS_NUM);
        } else
            try {
                results = newsRecommenderService.searchByKeyWords(input, userId, NEWS_NUM);
            } catch (Exception e) {
                System.out.println("there's no result about keyword: " + keywords);
            }
        if (null == results||results.size()==0) {
            return Resp.error().setMsg("新闻推荐查询结果为空");
        }
        for (News temp : results) {
            Audios a = new Audios();
            a.setId(temp.getnewsid());
            a.setname(temp.getnewstitle());
            if("cnr_mp3".equals(temp.getWebsite())||"QingTing_FM".equals(temp.getWebsite())){
                a.setKuwourl(temp.getAudiosurl());
                a.setQianqian(temp.getAudiosurl());
            }else {
                a.setKuwourl("http://47.100.163.195/music/tts_" + temp.getnewsid() + ".wav");
                a.setQianqian("http://47.100.163.195/music/tts_" + temp.getnewsid() + ".wav");
            }
            a.setsinger(temp.getsource());
            a.setAudiosType("新闻");
            resultAudios.add(a);
        }
        return Resp.success().setData(resultAudios);
    }

    /**
     * 更新用户偏好.
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/updateUserPreferences", method = RequestMethod.GET)
    public synchronized String updateUserPreferences(int userId,int originalLength, int durationOfPlay, int newsid) {
        newsRecommenderService.updatePreferences(userId, originalLength, durationOfPlay, newsid);
        return "down";
    }
}



