package com.meibanlu.qa.service.controller;

import java.util.List;

import com.meibanlu.qa.service.conf.RedisUtil;
import com.meibanlu.qa.service.entity.Audios;
import com.meibanlu.qa.service.entity.Collect;
import com.meibanlu.utils.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.meibanlu.qa.service.service.CollectService;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/CollectCRUD", method = { RequestMethod.GET, RequestMethod.POST })
public class CollectCRUD {

    @Autowired
    private CollectService collectService;

    @RequestMapping(value = "/insert")
    public Resp insert(HttpServletRequest request, Collect collect) {
        String userId = (String) request.getSession().getAttribute("userId");
        if(userId == null){
            // 默认为1号用户
            userId = "1";
        }
        collect.setUserId(Integer.valueOf(userId));
        int result= collectService.insertCollect(collect);
        if (result >= 1) {
            return Resp.success().setMsg("收藏成功");
        } else {
            return Resp.error().setMsg("收藏失败");
        }
    }

    @RequestMapping("/ListCollect")
    @ResponseBody
    public List<Collect> listCollect(){
        return collectService.listCollect();
    }

    /**
     * 按条件查询收藏列表
     * @param collectionType 类型
     * @param typeId 类型音频ID
     */
    @RequestMapping(value = "/queryCollected")
    public Resp queryCollected(HttpServletRequest request, String userId, String collectionType, String typeId, String typeTags) {
        if(userId == null){
            // 默认为-1号用户，未登录状态即默认没有收藏
            userId = "-1";
        }
        int typeIdInt = -1;
        if(typeId != null){
            typeIdInt = Integer.valueOf(typeId);
        }
        List<Audios> result= collectService.queryCollected(Integer.valueOf(userId), collectionType, typeIdInt, typeTags);
        if (result != null && !result.isEmpty()) {
            return Resp.success().setMsg("查询成功").setData(result);
        } else {
            return Resp.error().setMsg("暂无数据");
        }
    }
}
