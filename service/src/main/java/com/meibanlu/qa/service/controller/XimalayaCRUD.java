package com.meibanlu.qa.service.controller;

import java.util.List;

import com.meibanlu.qa.service.conf.RedisUtil;
import com.meibanlu.qa.service.entity.Audios;
import com.meibanlu.utils.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.meibanlu.qa.service.entity.Ximalaya;
import com.meibanlu.qa.service.service.XimalayaService;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/XimalayaCRUD", method = { RequestMethod.GET, RequestMethod.POST })
public class XimalayaCRUD {

    @Autowired
    private XimalayaService ximalayaService;
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(int id) {
        int result = ximalayaService.delete(id);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Ximalaya ximalaya) {
        int result = ximalayaService.Update(ximalaya);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }

    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Ximalaya insert(Ximalaya ximalaya) {
        return ximalayaService.insertximalaya(ximalaya);
    }

    @RequestMapping("/Listximalaya")
    @ResponseBody
    public List<Ximalaya> Listaudios(){
        return ximalayaService.Listximalaya();
    }

    @RequestMapping("/ListximalayaBytitle")
    @ResponseBody
    public Resp ListximalayaBytitle(HttpServletRequest request, String singer, String xsName, String audioTypeXs){
        String userId = (String) request.getSession().getAttribute("userId");
        if(userId == null){
            // 默认为1号用户
            userId = "1";
        }
        redisUtil.set("queryLast_Type-" + userId, "ximalaya");
        if(singer != null && !singer.isEmpty()){
            redisUtil.set("queryXimalayaLast_singer-" + userId, singer);
        }
        List<Audios> result = ximalayaService.findximalayaBytitle(singer, xsName, audioTypeXs);
        if(result.isEmpty()){
            return Resp.error();
        }else{
            return Resp.success().setData(result);
        }
    }

//    @RequestMapping("/ListximalayaByName")
//    @ResponseBody
//    public Resp ListximalayaByName(HttpServletRequest request, String xsName, String audioTypeXs){
//        String userId = (String) request.getSession().getAttribute("userId");
//        if(userId == null){
//            // 默认为1号用户
//            userId = "1";
//        }
//        redisUtil.set("queryLast_Type-" + userId, "ximalaya");
//        redisUtil.del("queryXimalayaLast_singer-" + userId);
//        List<Audios> result = ximalayaService.findximalayaByName(xsName, audioTypeXs);
//        if(result.isEmpty()){
//            return Resp.error();
//        }else{
//            return Resp.success().setData(result);
//        }
//    }
}
