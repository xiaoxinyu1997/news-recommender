package com.meibanlu.qa.service.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.meibanlu.qa.service.entity.Audios;
import org.springframework.stereotype.Service;

import com.meibanlu.qa.service.entity.Ximalaya;
import com.meibanlu.qa.service.mapper.XimalayaMapper;

import javax.annotation.Resource;

@Service
public class XimalayaService {
    @Resource
    private XimalayaMapper ximalayaMapper;

    /**
     * 根据喜马拉雅ID集合查询新闻
     */
    public List<Audios> queryByIds(List<Integer> ximalayaIds){
        if(ximalayaIds == null || ximalayaIds.isEmpty()){
            return new LinkedList<Audios>();
        }
        List<Ximalaya> ximalayaResult = ximalayaMapper.queryByIds(ximalayaIds);
        if(ximalayaResult == null || ximalayaResult.isEmpty()){
            return new LinkedList<Audios>();
        }
        return convert2Audio(ximalayaResult);
    }

    /**
     * 转为音频
     * @param ximalayaList 喜马拉雅列表
     */
    private List<Audios> convert2Audio(List<Ximalaya> ximalayaList){
        List<Audios> result = new LinkedList<Audios>();
        for (Ximalaya ximalaya : ximalayaList) {
            Audios audio = new Audios();
            audio.setId(ximalaya.getId());
            String imageUrl = ximalaya.getImage();
            if(imageUrl.startsWith("//")){
                imageUrl = "http:" + imageUrl;
            }
            audio.setimage(imageUrl);
            audio.setname(ximalaya.getTitle());
            audio.setKuwourl(ximalaya.getSrc());
            audio.settags(ximalaya.getTags());
            audio.setAudiosType(ximalaya.getCategory());
            result.add(audio);
        }
        return result;
    }

    public List<Audios> findximalayaBytitle(String singer, String name, String audioTypeXs) {
        List<Audios> result = new LinkedList<Audios>();
        List<Ximalaya> ximalayaResult = new ArrayList<Ximalaya>();
        ximalayaResult = ximalayaMapper.findximalayaByAlbum(singer, name, audioTypeXs);
        if(ximalayaResult == null || ximalayaResult.isEmpty()){
            ximalayaResult = ximalayaMapper.findximalayaBytitle(singer, name, audioTypeXs);
        }
        if(ximalayaResult != null && !ximalayaResult.isEmpty()){
            return convert2Audio(ximalayaResult);
        }
        return result;
    }

    public List<Audios> findMore(String singer, int lastCount){
        List<Audios> result = new ArrayList<Audios>();
        String sql = "select * from Ximalaya where 1=1";
        if(singer != null && !singer.isEmpty()){
            sql = sql + " and title like '%" + singer + "%'";
        }
        sql = sql + " limit 36 offset " + lastCount;
        List<Ximalaya> ximalayaResult = ximalayaMapper.queryBySql(sql);
        if(ximalayaResult != null && !ximalayaResult.isEmpty()){
            return convert2Audio(ximalayaResult);
        }
        return result;
    }


    public Ximalaya insertximalaya(Ximalaya ximalaya) {
        ximalayaMapper.insertximalaya(ximalaya);
        return ximalaya;
    }

    public List<Ximalaya> Listximalaya(){
        return	ximalayaMapper.Listximalaya();
    }


    public int Update(Ximalaya ximalaya){
        return ximalayaMapper.Update(ximalaya);
    }

    public int delete(int id){
        return ximalayaMapper.delete(id);
    }
}
