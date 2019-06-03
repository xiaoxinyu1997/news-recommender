package com.meibanlu.qa.service.mapper;
import java.util.List;
import java.util.Map;

import com.meibanlu.qa.service.entity.News;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsMapper {

    /**
     * 根据新闻ID集合查询新闻
     */
    List<News> queryByIds(List<Integer> newsIds);

    /**
     * 获取环球网新闻
     */
    List<News> findHuanqiuwang();

    /**
     * 获取中国之声新闻
     */
    List<News> findCnr();

    public List<News> findnewsBykeywords(String keywords);

    public List<News> findnewsByclassification(String classification);

    public List<News> Listnews();

    public int insertnews(News news);

    public int delete(int newsid);

    public int Update(News news);

    public List<News> getAllNewsByTime();

    List<News> getClassRankNews(Map<String,Integer> input);

    List<News> getClassRankNewsAndFiltering(Map<String,Integer> input);

    List<News> getNewsListByID(List<Integer> results);

    List<News> getTopNews();

    List<News> getNewsByClassesAndNumber( Map<String, Integer> input);

    int getClassByNewsID(int newsid);

    /**
     * 根据新闻ID获取新闻
     * @param newsId 新闻ID
     * @return 新闻内容
     */
    News getNewsById(String newsId);

    List<News> getFeverNews(int userid);
}
