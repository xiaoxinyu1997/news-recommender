package com.meibanlu.qa.service.mapper;
import java.util.List;
import com.meibanlu.qa.service.entity.NewsAudios;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsAudiosMapper {
    public List<NewsAudios> findnewsaudiosBytime(String time);

    public List<NewsAudios> Listnewsaudios();

    public int insertnewsaudios(NewsAudios newsaudios);

    public int delete(int id);

    public int Update(NewsAudios newsaudios);

}