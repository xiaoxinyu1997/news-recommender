package com.meibanlu.qa.service.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.meibanlu.qa.service.entity.NewsAudios;
import com.meibanlu.qa.service.service.NewsaudiosService;


@RestController
@RequestMapping(value = "/NewsaudiosCRUD", method = { RequestMethod.GET, RequestMethod.POST })
public class NewsaudiosCRUD {
    @Autowired
    private NewsaudiosService newsaudiosService;

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(int id) {
        int result = newsaudiosService.delete(id);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(NewsAudios newsaudios) {
        int result = newsaudiosService.Update(newsaudios);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }

    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public NewsAudios insert(NewsAudios newsaudios) {
        return newsaudiosService.insertnews(newsaudios);
    }

    @RequestMapping("/Listnewsaudios")
    @ResponseBody
    public List<NewsAudios> Listnewsaudios(){
        return newsaudiosService.Listnewsaudios();
    }

    @RequestMapping("/ListNewsBytime")
    @ResponseBody
    public List<NewsAudios> ListNewsBytime(String time){


        return newsaudiosService.findBytime(time);
    }
}
