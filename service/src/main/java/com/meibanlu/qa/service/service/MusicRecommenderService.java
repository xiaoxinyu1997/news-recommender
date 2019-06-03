package com.meibanlu.qa.service.service;

import com.meibanlu.qa.service.entity.Audios;
import com.meibanlu.qa.service.entity.User;
import com.meibanlu.qa.service.entity.UserAudiosBehavior;
import com.meibanlu.qa.service.entity.UserNewsBehavior;
import com.meibanlu.qa.service.mapper.AudiosMapper;
import com.meibanlu.qa.service.mapper.UserAudiosBehaviorMapper;
import com.meibanlu.qa.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class MusicRecommenderService {
    private static final Double BASE_PREFERANCE_RATING = 1.0;
    private static final double LAMBDA = 0.97;

    @Resource
    private AudiosMapper audiosMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserAudiosBehaviorMapper userAudiosBehaviorMapper;
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
     * 根据歌名和歌手名获取音频，按照音频点击率高低排序.
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

    /**
     * 根据歌名和歌手名获取音频，按照音频点击率高低排序.
     *
     * @param tag
     *            歌曲标签
     * @return 歌曲列表
     */
    public List<Audios> getAudiosByTags(String tag) {
        return audiosMapper.findAudiosBytags("%" + tag + "%");
    }

    /**
     * 为用户推荐音频.
     *
     * @param userid
     *            用户id
     * @return 歌曲列表
     */
    public List<Audios> recommend(int userid, int music_num) {
        List<Audios> result;
        List<Audios> byFB = this.feverBasedrecommend(userid, music_num/5);
        List<Audios> byCB = this.contentBasedrecommend(userid, (music_num*4)/5);
        // 合并并去掉两种推荐中重复的部分
        result = this.removal(byCB, byFB);
        if (result.size() > music_num) {
            result = result.subList(0, music_num - 1);
        }
        Collections.shuffle(result);
        return result;
    }

    private List<Audios> removal(List<Audios> musicsa, List<Audios> musicsb) {
        List<Audios> result = new ArrayList<>();
        if (musicsa.size()==0) {
            return musicsb;
        }
        if (0==musicsb.size()) {
            return musicsa;
        }
        HashSet<Audios> temp = new HashSet<>();
        for(Audios a:musicsa){
            temp.add(a);
        }
        for(Audios b:musicsb){
            temp.add(b);
        }
        for(Audios c:temp){
            result.add(c);
        }
        return result;
    }

    private List<Audios> contentBasedrecommend(int userid, int music_num) {
        User user = userMapper.getUser(userid);
        List<Audios> results = new ArrayList<>();
        String preferences = user.getMusicpreferences();
        Map<String, Integer> tag_rec_num = new HashMap<>();
        if (preferences==""||null == preferences) {
            return results;
        }
        Map<String, Double> preferenceratings = this.stringToRatings(preferences);
        double sum = 0;
        for (String tag : preferenceratings.keySet()) {
            sum += preferenceratings.get(tag);
        }
        for (String tag : preferenceratings.keySet()) {
            tag_rec_num.put(tag, (int) (music_num * preferenceratings.get(tag) / sum));
        }
        Map<String, Object> input = new HashMap<>();
        List<Audios> musics;
        List<Audios> result = new ArrayList<>();
        for (String tag : tag_rec_num.keySet()) {
            input.put("tags", "%" + tag + "%");
            input.put("limit", tag_rec_num.get(tag));
            musics = audiosMapper.getAudiosByTagAndNumber(input);
            input.clear();
            result.addAll(musics);
        }
        results = result;
        return results;
    }

    private Map<String,Double> stringToRatings(String preferences) {
        Map<String, Double> results = new HashMap<>();
        String[] class_rating = preferences.split(",");
        String[] mid;
        for (String temp : class_rating) {
            mid = temp.split(":");
            results.put(mid[0], Double.valueOf(mid[1]));
        }
        return results;
    }

    // 根据音乐点击率获取音乐,并筛选掉用户听过的
    private List<Audios> feverBasedrecommend(int userid, int music_num) {
        Map<String,Integer> input = new HashMap<>();
        input.put("userid",userid);
        input.put("limit",music_num);
        return audiosMapper.feverBasedrecommend(input);
    }

    public List<Audios> hotMusic(int music_num) {
        return audiosMapper.hotMusic(music_num);
    }

    private String ratingsToString(Map<String, Double> ratings) {
        StringBuilder userBehaviorString = new StringBuilder("");
        // 保留小数位后两位
        DecimalFormat df = new DecimalFormat("#.00");
        for (String temp : ratings.keySet()) {
            String str = df.format(ratings.get(temp));
            userBehaviorString.append(temp+ ":" + str + ",");
        }
        return userBehaviorString.toString();
    }

    public void updatePreferences(int userId, int originalLength, int durationOfPlay, int audiosid) {
        User userToBeUpdated = userMapper.getUser(userId);
        String musicpreferences;
        Map<String,Double> rate;
        if(userToBeUpdated.getMusicpreferences()==null||userToBeUpdated.getMusicpreferences()==""){
            musicpreferences = "";
            rate = new HashMap<>();
        }else {
            musicpreferences = userToBeUpdated.getMusicpreferences();
            rate = stringToRatings(musicpreferences);
        }
        String tag = audiosMapper.getTagsByAudioID(audiosid);
        String input2 = tag.substring(1, tag.length()-1);
        for(String a:input2.split(",")){
            String[] tags = a.split("'");
            String one = tags[1];
            double newRating = (double) durationOfPlay/originalLength;
            if(rate.containsKey(one)){
                double oldRating = rate.get(one);
                rate.replace(one,(oldRating+newRating)/2);
            }else{
                rate.put(one,newRating);
            }
        }
        Map<String,Double> newrate = new HashMap<>();
        for(String temp:rate.keySet()){
            if(rate.get(temp)>Double.valueOf(0.01).doubleValue()){
                newrate.put(temp,rate.get(temp));
            }
        }
        if(newrate.size()==0){
            return;
        }
        String toBeUpdated = ratingsToString(newrate);
        Map<String,Object> input = new HashMap<>();
        input.put("userid",userId);
        input.put("musicpreferences",toBeUpdated);
        userMapper.updateUserMusicPreferences(input);
    }


}
