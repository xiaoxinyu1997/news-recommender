package com.meibanlu.qa.service.service;

import java.util.LinkedList;
import java.util.List;

import com.meibanlu.qa.service.entity.Audios;
import org.springframework.stereotype.Service;

import com.meibanlu.qa.service.entity.News;
import com.meibanlu.qa.service.mapper.NewsMapper;

import javax.annotation.Resource;

@Service
public class NewsService {

    @Resource
    private NewsMapper newsMapper;

    /**
     * 根据新闻ID集合查询新闻
     */
    public List<Audios> queryByIds(List<Integer> newsIds){
        if(newsIds == null || newsIds.isEmpty()){
            return new LinkedList<Audios>();
        }
        List<News> newsResult = newsMapper.queryByIds(newsIds);
        if(newsResult == null || newsResult.isEmpty()){
            return new LinkedList<Audios>();
        }
        return convert2Audio(newsResult);
    }

    /**
     * 获取环球网新闻
     */
    public List<Audios> findHuanqiuwang() {
        List<Audios> result = new LinkedList<Audios>();
        List<News> newsResult = newsMapper.findHuanqiuwang();
        if(newsResult != null && !newsResult.isEmpty()){
            return convert2Audio(newsResult);
        }
        return result;
    }

    /**
     * 获取中国之声新闻
     */
    public List<Audios> findCnr() {
        List<Audios> result = new LinkedList<Audios>();
        List<News> newsResult = newsMapper.findCnr();
        if(newsResult != null && !newsResult.isEmpty()){
            return convert2Audio(newsResult);
        }
        return result;
    }

    /**
     * 将新闻集合转化为音频集合
     * @param newsList 新闻集合
     */
    private List<Audios> convert2Audio(List<News> newsList){
        List<Audios> result = new LinkedList<Audios>();
        for (News news : newsList) {
            if("环球网".equals(news.getWebsite())){
                Audios audios = new Audios();
                audios.setId(news.getnewsid());
                audios.settags("环球网");
                audios.setKuwourl("http://47.100.163.195/music/tts_" + news.getnewsid() + ".wav");
                audios.setQianqian("http://47.100.163.195/music/tts_" + news.getnewsid() + ".wav");
                audios.setname(news.getnewstitle());
                audios.setsinger(news.getsource());
                audios.setimage("http://pic.pc6.com/up/201401/175441_6099683463573.jpg");
                audios.setAudiosType("新闻");
                result.add(audios);
            }else{
                Audios audios = new Audios();
                audios.setId(news.getnewsid());
                audios.settags("中国之声");
                if("cnr".equals(news.getWebsite())){
                    audios.setKuwourl("http://47.100.163.195/music/tts_" + news.getnewsid() + ".wav");
                    audios.setQianqian("http://47.100.163.195/music/tts_" + news.getnewsid() + ".wav");
                }else{
                    audios.setKuwourl(news.getAudiosurl());
                    audios.setQianqian(news.getAudiosurl());
                }
                audios.setname(news.getnewstitle());
                audios.setsinger(news.getsource());
                audios.setimage("http://n.sinaimg.cn/ent/transform/265/w630h435/20180321/uXME-fyskeue2556735.jpg");
                audios.setAudiosType("新闻");
                result.add(audios);
            }
        }
        return result;
    }

    public List<News> findBykeywords(String keywords) {

        return newsMapper.findnewsBykeywords(keywords);
    }

    public List<News> findByclassification(String classification) {

        return newsMapper.findnewsByclassification(classification);
    }

    public News insertnews(News news) {
        newsMapper.insertnews(news);
        return news;
    }

    public List<News> Listnews(){
        return	newsMapper.Listnews();
    }


    public int Update(News news){
        return newsMapper.Update(news);
    }

    public int delete(int newsid){
        return newsMapper.delete(newsid);
    }
}
