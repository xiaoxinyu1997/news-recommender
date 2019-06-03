package com.meibanlu.qa.service.controller;
import java.util.List;

import com.meibanlu.qa.service.entity.Audios;
import com.meibanlu.utils.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.meibanlu.qa.service.entity.Zhongguozhisheng;
import com.meibanlu.qa.service.service.ZhongguozhishengService;


@RestController
@RequestMapping(value = "/ZhongguozhishengCRUD", method = { RequestMethod.GET, RequestMethod.POST })
public class ZhongguozhishengCRUD {
    @Autowired
    private ZhongguozhishengService zhongguozhishengService;

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(int id) {
        int result = zhongguozhishengService.delete(id);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Zhongguozhisheng zhongguozhisheng) {
        int result = zhongguozhishengService.Update(zhongguozhisheng);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }

    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Zhongguozhisheng insert(Zhongguozhisheng zhongguozhisheng) {
        return zhongguozhishengService.insertzhongguozhisheng(zhongguozhisheng);
    }

    @RequestMapping("/Listzhongguozhisheng")
    @ResponseBody
    public Resp Listzhongguozhisheng(){
        List<Audios> result = zhongguozhishengService.Listzhongguozhisheng();
        if(result != null && !result.isEmpty()){
            return Resp.success().setData(result);
        }else{
            return Resp.error().setMsg("获取中国之声新闻失败");
        }
    }

    @RequestMapping("/ListzhongguozhishengBytime")
    @ResponseBody
    public List<Zhongguozhisheng> Listzhongguozhishengbytime(String time){


        return zhongguozhishengService.findBytime(time);
    }
}
