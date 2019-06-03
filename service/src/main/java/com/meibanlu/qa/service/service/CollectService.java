package com.meibanlu.qa.service.service;

import java.util.LinkedList;
import java.util.List;
import com.meibanlu.qa.service.entity.Audios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.meibanlu.qa.service.entity.Collect;
import com.meibanlu.qa.service.mapper.CollectMapper;

import javax.annotation.Resource;

@Service
public class CollectService {

    @Resource
    private CollectMapper collectMapper;
    @Autowired
    private AudiosService audiosService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private XimalayaService ximalayaService;

    public static final String COLLECT_TYPE_AUDIO = "音乐";
    public static final String COLLECT_TYPE_NEWS = "新闻";
    public static final String COLLECT_TYPE_XS = "相声";
    public static final String COLLECT_TYPE_XP = "小品";

    public int insertCollect(Collect collect) {
        return collectMapper.insertCollect(collect);
    }

    public List<Collect> listCollect(){
        return	collectMapper.listCollect();
    }

    /**
     * 按条件查找收藏信息
     * @param userId 用户ID
     * @param collectionType 类型
     * @param typeId 类型音频ID
     */
    public List<Audios> queryCollected(int userId, String collectionType, int typeId, String typeTags){
        List<Collect> collects = collectMapper.queryCollected(userId, collectionType, typeId, typeTags);
        if(collects == null || collects.isEmpty()){
            return new LinkedList<Audios>();
        }else{
            return convert2Audios(collects);
        }
    }

    /**
     * 将收藏列表中的记录转为音频对象
     * @param collects 收藏列表
     * @return 音频列表
     */
    private List<Audios> convert2Audios(List<Collect> collects){
        LinkedList<Integer> audioIds = new LinkedList<Integer>();
        LinkedList<Integer> newsIds = new LinkedList<Integer>();
        LinkedList<Integer> ximalayaIds = new LinkedList<Integer>();
        for (Collect collect : collects) {
            if(COLLECT_TYPE_AUDIO.equals(collect.getType())){
                audioIds.add(collect.getTypeId());
            }else if(COLLECT_TYPE_NEWS.equals(collect.getType())){
                newsIds.add(collect.getTypeId());
            }else{
                ximalayaIds.add(collect.getTypeId());
            }
        }
        List<Audios> audioList = audiosService.queryByIds(audioIds);
        List<Audios> newsList = newsService.queryByIds(newsIds);
        List<Audios> xsList = ximalayaService.queryByIds(ximalayaIds);
        List<Audios> result = new LinkedList<>();
        result.addAll(audioList);
        result.addAll(newsList);
        result.addAll(xsList);
        return result;
    }

}

