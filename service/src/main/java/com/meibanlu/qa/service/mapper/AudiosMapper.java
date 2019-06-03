package com.meibanlu.qa.service.mapper;

import com.meibanlu.qa.service.entity.Audios;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AudiosMapper {

    /**
     * 根据ID集合查询
     * @param audioIds ID集合
     */
    List<Audios> queryByIds(List<Integer> audioIds);

    public List<Audios> findAudiosByName(String name);

    public List<Audios> findAudiosBysinger(String singer);

    public List<Audios> findAudiosBytags(String tags);

    public List<Audios> findAudiosByall(String sql);

    public List<Audios> Listaudios();

    public int insertaudios(Audios audios);

    public int delete(int id);

    public int Update(Audios audios);

    List<Audios> getAudioByName(String song);

    List<Audios> getAudioBySinger(String singer);

    List<Audios> getAudioByNameAndSinger( Map<String, String> input);

    List<Audios> hotMusic(int limit);

    List<Audios> feverBasedrecommend(Map<String, Integer> input);

    List<Audios> getAudiosByTagAndNumber(Map<String, Object> input);

    String getTagsByAudioID(int audiosid);

}
