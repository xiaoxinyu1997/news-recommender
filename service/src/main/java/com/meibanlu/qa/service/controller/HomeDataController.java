package com.meibanlu.qa.service.controller;

import com.meibanlu.qa.service.entity.home.HomeAudioItemVO;
import com.meibanlu.qa.service.service.HomeDataService;
import com.meibanlu.utils.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/homeData", method = { RequestMethod.GET, RequestMethod.POST })
public class HomeDataController {

    @Autowired
    private HomeDataService homeDataService;

    @RequestMapping("/fetchHomeData")
    @ResponseBody
    public Resp fetchHomeData(){
        List<HomeAudioItemVO> result = homeDataService.fetchHomeData();
        return Resp.success().setData(result);
    }
}
