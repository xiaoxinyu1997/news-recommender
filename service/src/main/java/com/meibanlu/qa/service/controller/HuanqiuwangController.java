package com.meibanlu.qa.service.controller;

import com.meibanlu.qa.service.entity.Audios;
import com.meibanlu.qa.service.service.NewsService;
import com.meibanlu.utils.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 环球网新闻接口
 */
@RestController
@RequestMapping(value = "/huanqiuwang", method = { RequestMethod.GET, RequestMethod.POST })
public class HuanqiuwangController {

    @Autowired
    private NewsService newsService;

    /**
     * 获取环球网新闻
     */
    @Deprecated
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
}
