package com.meibanlu.qa.service.controller;
import java.util.List;

import com.meibanlu.qa.service.entity.Audios;
import com.meibanlu.utils.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.meibanlu.qa.service.entity.News;
import com.meibanlu.qa.service.service.NewsService;


@RestController
@RequestMapping(value = "/NEWSCRUD", method = { RequestMethod.GET, RequestMethod.POST })
public class NewsCRUD {
    @Autowired
    private NewsService newsService;

    /**
     * 获取环球网新闻
     */
    @RequestMapping("/listHuanqiuwang")
    @ResponseBody
    public Resp listHuanqiuwang(){
        List<Audios> result = newsService.findHuanqiuwang();
        if(result != null && !result.isEmpty()){
            return Resp.success().setData(result);
        }else{
            return Resp.error().setMsg("获取环球网新闻失败");
        }
    }
    /**
     * 获取中国之声新闻
     */
    @RequestMapping("/listCnr")
    @ResponseBody
    public Resp listCnr(){
        List<Audios> result = newsService.findCnr();
        if(result != null && !result.isEmpty()){
            return Resp.success().setData(result);
        }else{
            return Resp.error().setMsg("获取中国之声新闻失败");
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(int newsid) {
        int result = newsService.delete(newsid);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(News news) {
        int result = newsService.Update(news);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }

    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public News insert(News news) {
        return newsService.insertnews(news);
    }

    @RequestMapping("/Listnews")
    @ResponseBody
    public List<News> Listnews(){
        return newsService.Listnews();
    }

    @RequestMapping("/ListNewsBykeywords")
    @ResponseBody
    public List<News> ListNewsBykeywords(String keywords){


        return newsService.findBykeywords(keywords);
    }

    @RequestMapping("/ListNewsByclassification")
    @ResponseBody
    public List<News> ListNewsByclassification(String classification){


        return newsService.findByclassification(classification);
    }
}
