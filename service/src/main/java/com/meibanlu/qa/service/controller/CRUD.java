package com.meibanlu.qa.service.controller;

import java.util.List;

import com.meibanlu.qa.service.conf.RedisUtil;
import com.meibanlu.qa.service.service.XimalayaService;
import com.meibanlu.utils.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.meibanlu.qa.service.entity.Audios;
import com.meibanlu.qa.service.service.AudiosService;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/CRUD", method = { RequestMethod.GET, RequestMethod.POST })
public class CRUD {

    @Autowired
    private AudiosService audiosService;
    @Autowired
    private XimalayaService ximalayaService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(int id) {
        int result = audiosService.delete(id);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Audios audios) {
        int result = audiosService.Update(audios);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }

    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Audios insert(Audios audios) {
        return audiosService.insertaudios(audios);
    }

    @RequestMapping("/Listaudios")
    @ResponseBody
    public List<Audios> Listaudios(){
        return audiosService.Listaudios();
    }

    @RequestMapping("/ListAudiosByname")
    @ResponseBody
    public List<Audios> ListAudiosByname(String name){


        return audiosService.findByName(name);
    }


    @RequestMapping("/ListAudiosBysinger")
    @ResponseBody
    public List<Audios> ListAudiosBysinger(String singer){


        return audiosService.findBysinger(singer);
    }


    @RequestMapping("/ListAudiosBytags")
    @ResponseBody
    public List<Audios> ListAudiosBytags(String tags){


        return audiosService.findBytags(tags);
    }
    //这里不能忘session里面直接写属性，因为/ListAudiosByall实在语音分析里面使用的，而/requiredMore实在客户端使用的，两个session不一样。所以用的是相当于全局变量的map。
    @RequestMapping("/ListAudiosByall")
    @ResponseBody
    public Resp ListAudiosByall(HttpServletRequest request, String name, String singer, String tags, String audioType, String userId){
        if(userId == null){
            userId = "1";
        }
        redisUtil.set("queryLast_Type-" + userId, null);
        if(name != null && !name.isEmpty()){
            redisUtil.set("queryAudioLast_name-" + userId, name);
        }else{
            redisUtil.del("queryAudioLast_name-" + userId);
        }
        if(singer != null && !singer.isEmpty()){
            redisUtil.set("queryAudioLast_singer-" + userId, singer);
        }else{
            redisUtil.del("queryAudioLast_singer-" + userId);
        }
        if(tags != null && !tags.isEmpty()){
            redisUtil.set("queryAudioLast_tags-" + userId, tags);
        }else{
            redisUtil.del("queryAudioLast_tags-" + userId);
        }
        if(audioType != null && !audioType.isEmpty()){
            redisUtil.set("queryAudioLast_audioType-" + userId, audioType);
        }else{
            redisUtil.del("queryAudioLast_audioType-" + userId);
        }
        List<Audios> result = audiosService.find(redisUtil,userId,name,singer,tags,audioType);
        if(result == null || result.isEmpty()){
            redisUtil.set("queryAudioLast_count-" + userId, 0);
            return Resp.error().setMsg("查询失败");
        }
        redisUtil.set("queryAudioLast_count-" + userId, result.size());
        return Resp.success().setData(result);
    }

    /**
     * 获取更多音频接口、用歌名和歌手查询，将此记录到session里面，再用findmore接口查询该歌手的更多歌曲，且每次查询的顺序一样，但起点不一样。
     */
    @RequestMapping("/requiredMore")
    @ResponseBody
    public Resp requiredMoreAudio(HttpServletRequest request){
        String userId = (String) request.getSession().getAttribute("userId");
        if(userId == null){
            // 默认为1号用户
            userId = "1";
        }
        String lastType = (String) redisUtil.get("queryLast_Type-" + userId);
        if(lastType != null){
            if("ximalaya".equals(lastType)){
                return queryMoreXimalaya(userId);
            }else if("news".equals(lastType)){
                return queryMoreNews(userId);
            }else{
                return queryMoreAudio(userId);
            }
        }else{
            return queryMoreAudio(userId);
        }
    }

    private Resp queryMoreAudio(String userId){
        // 获取上一次请求接口的参数
        String name = (String) redisUtil.get("queryAudioLast_name-" + userId);
        String singer = (String) redisUtil.get("queryAudioLast_singer-" + userId);
        String tags = (String) redisUtil.get("queryAudioLast_tags-" + userId);
        String audioType = (String) redisUtil.get("queryAudioLast_audioType-" + userId);
        int lastCount = 0;
        Object lastCountObj = redisUtil.get("queryAudioLast_count-" + userId);
        if(lastCountObj != null){
            lastCount = (int) lastCountObj;
        }
        // 查找更多歌曲
        List<Audios> resultQuery = audiosService.findMore(name, singer, tags, lastCount, audioType);
        // 更新已返回条目的数量,当不为0时，lastcount加上这个数字，再将这个数字存入queryAudioLast_countkey里面。
        if(resultQuery.size() == 0){
            redisUtil.set("queryAudioLast_count-" + userId, 0);
        }else{
            lastCount += resultQuery.size();
            redisUtil.set("queryAudioLast_count-" + userId, lastCount);
        }
        return Resp.success().setData(resultQuery);
    }

    private Resp queryMoreXimalaya(String userId){
        String singer = (String) redisUtil.get("queryXimalayaLast_singer-" + userId);
        int lastCount = 0;
        Object lastCountObj = redisUtil.get("queryXimalayaLast_count-" + userId);
        if(lastCountObj != null){
            lastCount = (int) lastCountObj;
        }
        List<Audios> resultQuery = ximalayaService.findMore(singer, lastCount);
        if(resultQuery.size() == 0){
            redisUtil.set("queryXimalayaLast_count-" + userId, 0);
        }else{
            lastCount += resultQuery.size();
            redisUtil.set("queryXimalayaLast_count-" + userId, lastCount);
        }
        return Resp.success().setData(resultQuery);
    }

    private Resp queryMoreNews(String userId){
        int lastCount = 0;
        Object lastCountObj = redisUtil.get("queryAudioLast_count-" + userId);
        if(lastCountObj != null){
            lastCount = (int) lastCountObj;
        }
        // 查找更多新闻
        ResponseEntity<Resp> respEntity = restTemplate.getForEntity(
                "http://localhost:30002/qa-service/NewsRecommender/search?userId" + userId + "&lastCount=" + lastCount,
                Resp.class
        );
        Resp resp = respEntity.getBody();
        if(resp == null){
            return Resp.error().setMsg("获取更多新闻失败");
        }
        List<Object> resultList = (List<Object>) resp.getData();
        if(resultList == null || resultList.isEmpty()){
            redisUtil.set("queryAudioLast_count-" + userId, 0);
        }else{
            lastCount += resultList.size();
            redisUtil.set("queryAudioLast_count-" + userId, lastCount);
        }
        return respEntity.getBody();
    }
}
