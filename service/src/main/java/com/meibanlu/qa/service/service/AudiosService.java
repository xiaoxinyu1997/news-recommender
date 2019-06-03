package com.meibanlu.qa.service.service;


import com.meibanlu.qa.service.conf.RedisUtil;
import com.meibanlu.qa.service.entity.Audios;
import com.meibanlu.qa.service.mapper.AudiosMapper;
import com.meibanlu.qa.service.mapper.XimalayaMapper;
import com.meibanlu.qa.service.util.DealXCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service
public class AudiosService {
    @Resource
    private AudiosMapper audiosMapper;
    @Autowired
    MusicRecommenderService musicRecommenderService;
    private DealXCode dealXCode = new DealXCode();
    @Resource
    private XimalayaMapper ximalayaMapper;
    /*... List<Audios> reult表示result是auidos型数据的集合，Audios audio表示auidos是个auiods的对象。
               audio.setimage(ximalaya.getImage());
                audio.setname(ximalaya.getTitle());
                audio.setKuwourl(ximalaya.getSrc());
                audio.settags(ximalaya.getTags())这样使得ximalay返回的字段从titile变到了name;....*/
    public List<Audios> find(RedisUtil redisUtil, String userId, String name, String singer, String tags, String audioType) {
        List<Audios> result = new ArrayList<Audios>();
        if(name==null&&singer==null&&tags==null&&audioType==null){
            result.addAll(musicRecommenderService.recommend(Integer.valueOf(userId), 36));
        }else {
            StringBuilder sql = new StringBuilder("select * from Audios where 1=1");
            if (name != null && !name.isEmpty()) {
                sql.append(" and name = '" + name + "'");
            }
            if (singer != null && !singer.isEmpty()) {
                sql.append(" and singer = '" + singer + "'");
            }
            if (tags != null && !tags.isEmpty()) {
                sql.append(" and tags like '%" + tags + "%'");
            }
            if (audioType != null && !audioType.isEmpty()) {
                sql.append(" and tags like '%" + audioType + "%'");
            }
            sql.append(" limit 36");
            result = audiosMapper.findAudiosByall(sql.toString());
        }
            /*... 语义解析的时候，原本希望歌手的歌名来查询，但如果是隐形的翅膀这首歌的话，予以解析不对，所以下面的第二种方法就
            就如果没有查到，且歌手和歌名不为空，就将name为name+singer，然后再查询name。....*/
        if(result==null || result.isEmpty()){
            result = new ArrayList<Audios>();
            if(name != null && !name.isEmpty()&&singer != null && !singer.isEmpty()){
                List<Audios> buf = audiosMapper.findAudiosByall("select * from Audios where name = '" + singer + "的" + name + "'");
                if(buf != null && !buf.isEmpty()){
                    result.addAll(buf);
                    // 修改上一次请求接口的参数
                    redisUtil.set("queryAudioLast_singer-" + userId, null);
                    redisUtil.set("queryAudioLast_name-" + userId, singer + "的" + name);
                }
            }
        }
        for(Audios item: result){
            dealXCode.fetchXcode(item);
            item.setAudiosType("音乐");
        }
        return result;
    }

    /**
     * 获取更多音频
     * @param name 音频名称
     * @param singer 歌手
     * @param tags 音频标签
     * @param lastCount 已经返回过的数量
     *  /**
     * 获取更多音频接口，用歌名和歌手查询，将此记录到session里面，再用findmore接口查询该歌手的更多歌曲，且每次查询的顺序一样，但起点不一样。
     */

    public List<Audios>findMore(String name, String singer, String tags, int lastCount, String audioType){
        List<Audios> result = new ArrayList<Audios>();
        //当查询只有name的时候，查询更多的时候，先根据那个name的singer，再通过singer来查询歌曲。String.valueOf(lastCount+1)为值，如果直接lastCount+1，为字符串。
        if(name != null && !name.isEmpty() && (singer == null || singer.isEmpty()) && (tags == null || tags.isEmpty()) && (audioType == null || audioType.isEmpty())){
            String sql = "select * from Audios where singer in (select singer from Audios where name = '" + name + "') limit 36 offset " + String.valueOf(lastCount+1);
            List<Audios> buf = audiosMapper.findAudiosByall(sql);
            if(buf != null && !buf.isEmpty()){
                result.addAll(buf);
            }
        }else{//这里name singer tags都有，用来查询更多信息。
            StringBuilder sql = new StringBuilder("select * from Audios where 1=1");
            if(name != null && !name.isEmpty()){
                sql.append(" and name = '" + name + "'");
            }
            if(singer != null && !singer.isEmpty()){
                sql.append(" and singer = '" + singer + "'");
            }
            if(tags != null && !tags.isEmpty()){
                sql.append(" and tags like '%" + tags + "%'");
            }
            if(audioType != null && !audioType.isEmpty()){
                sql.append(" and tags like '%" + audioType + "%'");
            }
            sql.append(" limit 36 offset " + String.valueOf(lastCount+1));
            result = audiosMapper.findAudiosByall(sql.toString());
            //如果result为空，可能是name=singer+name的问题。下面在解决
            if(result==null || result.isEmpty()){
                result = new ArrayList<Audios>();
                if(name != null && !name.isEmpty()&&singer != null && !singer.isEmpty())
                {
                    List<Audios> buf = audiosMapper.findAudiosByall("select * from Audios where name = '" + singer + "的" + name + "'");
                    if(buf != null && !buf.isEmpty()){
                        result.addAll(buf);
                    }
                }
            }//如果还为空，就直接查询歌手。
            if(result==null || result.isEmpty()) {
                result = new ArrayList<Audios>();
                if(name != null && !name.isEmpty()&&singer != null && !singer.isEmpty()){
                    result=audiosMapper.findAudiosByall("select * from Audios where singer = '" + singer + "' limit 36 offset " + String.valueOf(lastCount+1));
                }
            }//如果还为空，就直接查询标签。
            if(result==null || result.isEmpty()) {
                result = new ArrayList<Audios>();
                if(tags != null && !tags.isEmpty()){
                    result=audiosMapper.findAudiosByall("select * from Audios where tags like '%" + tags + "%' limit 36 offset " + String.valueOf(lastCount+1));
                }
            }
        }
        for(Audios item: result){
            dealXCode.fetchXcode(item);
            item.setAudiosType("音乐");
        }
        return result;
    }

    /**
     * 根据ID集合查询音乐音频
     * @param audioIds 音乐ID集合
     */
    public List<Audios> queryByIds(List<Integer> audioIds){
        if(audioIds == null || audioIds.isEmpty()){
            return new LinkedList<>();
        }
        List<Audios> result = audiosMapper.queryByIds(audioIds);
        if(result == null){
            return new LinkedList<>();
        }
        return result;
    }

    public List<Audios> findByName(String name) {

        return audiosMapper.findAudiosByName(name);
    }

    public List<Audios> findBysinger(String singer) {

        return audiosMapper.findAudiosBysinger(singer);
    }

    public List<Audios> findBytags(String tags) {

        return audiosMapper.findAudiosBytags(tags);
    }

    public Audios insertaudios(Audios audios) {
        audiosMapper.insertaudios(audios);
        return audios;
    }

    public List<Audios> Listaudios(){
        return	audiosMapper.Listaudios();
    }


    public int Update(Audios audios){
        return audiosMapper.Update(audios);
    }

    public int delete(int id){
        return audiosMapper.delete(id);
    }


    /**
     * 根据歌名获取音频，按照音频点击率高低排序.
     *
     * @param  song
     *            歌名
     * @return 歌曲列表
     */
    public List<Audios> getAudioByName(String song) {
        return audiosMapper.getAudioByName("%" + song + "%");
    }

    /**
     * 根据歌手名称获取音频，按照音频点击率高低排序.
     *
     * @param singer
     *            歌手名称
     * @return 歌曲列表
     */
    public List<Audios> getAudioBySinger(String singer) {
        return audiosMapper.getAudioBySinger(singer);
    }

    /**
     * 根据歌名和歌手名获取音频(模糊查询)，按照音频点击率高低排序.
     *
     * @param name
     *            歌名
     *  @param singer
     *            歌手名
     * @return 歌曲列表
     */
    public List<Audios> getAudioByNameAndSinger(String name, String singer) {
        Map<String, String> input = new HashMap<String, String>();
        input.put("name", "%" + name + "%");
        input.put("singer", singer);
        return audiosMapper.getAudioByNameAndSinger(input);
    }

    public List<Audios> getAudiosByTags(String tag) {
        return audiosMapper.findAudiosBytags("%" + tag + "%");
    }
}
