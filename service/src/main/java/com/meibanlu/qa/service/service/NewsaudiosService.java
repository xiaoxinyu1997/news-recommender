package com.meibanlu.qa.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meibanlu.qa.service.entity.NewsAudios;
import com.meibanlu.qa.service.mapper.NewsAudiosMapper;

import javax.annotation.Resource;

@Service
public class NewsaudiosService {
    @Resource
    private NewsAudiosMapper newsaudiosMapper;

    public List<NewsAudios> findBytime(String time) {

        return newsaudiosMapper.findnewsaudiosBytime(time);
    }

    public NewsAudios insertnews(NewsAudios newsaudios) {
        newsaudiosMapper.insertnewsaudios(newsaudios);
        return newsaudios;
    }

    public List<NewsAudios> Listnewsaudios() {
        return newsaudiosMapper.Listnewsaudios();
    }


    public int Update(NewsAudios newsaudios) {
        return newsaudiosMapper.Update(newsaudios);
    }

    public int delete(int id) {
        return newsaudiosMapper.delete(id);
    }
}