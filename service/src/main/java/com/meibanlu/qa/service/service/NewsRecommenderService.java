package com.meibanlu.qa.service.service;

import com.meibanlu.qa.service.entity.*;
import com.meibanlu.qa.service.mapper.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NewsRecommenderService {

    private static final Double BASE_PREFERANCE_RATING = 1.0;
    private static final double LAMBDA = 0.8;
    @Resource
    private NewsMapper newsMapper;
    @Resource
    private InvertedIndexMapper invertedIndexMapper;
    @Resource
    private NewsClassesMapper newsClassesMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserNewsBehaviorMapper userNewsBehaviorMapper;
        /**
         * 建立关键词-新闻倒排索引,并存入数据库.
         *
         * @param
         * @return null
         */
        public void invert() {
            invertedIndexMapper.deleteAll();
            List<InvertedIndex> indexlist = new ArrayList<InvertedIndex>();
            Map<String, List<Integer>> invertedlist = new HashMap<String, List<Integer>>();
            List<News> newslist = newsMapper.getAllNewsByTime();
            for (News news : newslist) {
                String keywords = news.getkeywords();
                if(keywords==null){
                    continue;
                }
                for (String keyword : keywords.split(",")) {
                    if (invertedlist.containsKey(keyword)) {
                        invertedlist.get(keyword).add(news.getnewsid());
                    } else {
                        List<Integer> nulllist = new ArrayList<Integer>();
                        nulllist.clear();
                        nulllist.add(news.getnewsid());
                        invertedlist.put(keyword, nulllist);
                    }
                }
            }
            String news_id;
            for (String keyword : invertedlist.keySet()) {
                InvertedIndex index = new InvertedIndex();
                index.setKeyword(keyword);
                news_id = invertedlist.get(keyword).toString();
                index.setNews_id(news_id);
                indexlist.add(index);
            }
            if(indexlist.size()>30000){
                List<InvertedIndex> indexlist1 = indexlist.subList(0,29999);
                List<InvertedIndex> indexlist2 = indexlist.subList(30000,indexlist.size()-1);
                invertedIndexMapper.insertAll(indexlist1);
                invertedIndexMapper.insertAll(indexlist2);
            }else {
                invertedIndexMapper.insertAll(indexlist);
            }
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String TimeString = time.format(new java.util.Date());
            System.out.println(TimeString);

    }

    public List<News> recommend(int userId, int newsNum) {
        List<News> result;
        List<News> byFB = this.feverBasedrecommender(userId);
        List<News> byCB = this.contentBasedrecommend(userId, newsNum);
        // 合并并去掉两种推荐中重复的部分
        result = this.removal(byCB, byFB);
        if (result.size() > newsNum) {
            result = result.subList(0, newsNum - 1);
        }
        Collections.shuffle(result);
        return result;
    }


    /**
     * 不指定用户时的热度推荐.
     *
     * @param limit 新闻条数
     * @return 新闻
     */
    public List<News> feverBasedrecommend(int limit) {
        List<News> topNewsList;
        topNewsList = newsMapper.getTopNews();
        if(topNewsList.size()>limit){
            topNewsList = topNewsList.subList(0,limit-1);
        }
        Collections.shuffle(topNewsList);
        return topNewsList;
    }

    /**
     * 指定用户热度推荐.
     *
     * @param
     * @return 新闻
     */
    private List<News> feverBasedrecommender(int userId) {
        List<News> topNewsList;
        topNewsList = newsMapper.getFeverNews(userId);
        return topNewsList;
    }


    /**
     * 按用户喜好推荐.
     *
     * @param
     * @return 新闻
     */
    private List<News> contentBasedrecommend(int userId, int newsNum) {
        List<News> result = new ArrayList<>();
        User user = userMapper.getUser(userId);
        if (null == user.getNewspreferences()||"" == user.getNewspreferences()) {
            System.out.println("用户" + userId + "无历史兴趣记录");
            return result;
        }else {
            String preferences = user.getNewspreferences();
            String[] classes = preferences.split(",");
            Map<Integer, Double> preferenceratings = new HashMap<>();
            Map<Integer, Integer> class_rec_number = new HashMap<>();
            String[] one_preference;
            for (String one_class : classes) {
                one_preference = one_class.split(":");
                preferenceratings.put(Integer.valueOf(one_preference[0]), Double.valueOf(one_preference[1]));
            }
            double sum = 0;
            for (Integer class_id : preferenceratings.keySet()) {
                sum += preferenceratings.get(class_id);
            }
            for (Integer class_id : preferenceratings.keySet()) {
                if (preferenceratings.get(class_id) < BASE_PREFERANCE_RATING)
                    continue;
                class_rec_number.put(class_id, (int) (newsNum * preferenceratings.get(class_id) / sum));
            }
            Map<String, Integer> input = new HashMap<>();
            List<News> news;
            for (Integer temp : class_rec_number.keySet()) {
                input.put("classid", temp);
                input.put("limit", class_rec_number.get(temp));
                input.put("userid", userId);
                news = newsMapper.getNewsByClassesAndNumber(input);
                input.clear();
                result.addAll(news);
            }
            return result;
        }
    }

    /**
     * 返回所有分类名字.
     *
     * @param
     * @return 分类名字表
     */
    public Map<String, Integer> getClassesNames() {
        List<NewsClasses> classlist = new ArrayList<NewsClasses>();
        classlist = newsClassesMapper.getAllClass();
        Map<String, Integer> classes = new HashMap<String, Integer>();
        for (NewsClasses classification : classlist) {
            classes.put(classification.getclassification(), classification.getclassid());
        }
        return classes;
    }

    /**
     * 按分类查询新闻，若新闻在首页则按照热度排序；若不在首页，则按发布时间排序.
     *
     * @param classId
     *            分类id
     * @param userId
     *            用户id
     * @return
     */
    public List<News> searchNewsByClass(int classId, int userId, int limit) {
        Map<String, Integer> parameter = new HashMap<String, Integer>();
        parameter.put("classId", classId);
        parameter.put("limit", limit);
        parameter.put("userid", userId);
        List<News> newslist = newsMapper.getClassRankNewsAndFiltering(parameter);
        return newslist;
    }

    /**
     * 按分类查询新闻(游客登陆userid=1).
     * @return
     */
    public List<News> searchNewsByClass(int classId, int limit) {
        Map<String, Integer> parameter = new HashMap<String, Integer>();
        parameter.put("classId", classId);
        parameter.put("limit", limit);
        List<News> newslist = newsMapper.getClassRankNews(parameter);
        return newslist;
    }

    public List<News> searchByKeyWords(String[] keywords, int userId, int newsNum) throws Exception{
        Map<String, List<Integer>> invertedlist = new HashMap<String, List<Integer>>();
        List<Integer> results = new ArrayList<Integer>();
        List<News> news;
        for (String keyword : keywords) {
            List<Integer> newslist;
            newslist = this.transferInvert(keyword);
            invertedlist.put(keyword, newslist);
        }
            if (invertedlist.containsKey(keywords[0])) {
            results.addAll(invertedlist.get(keywords[0]));
        }
        List<Integer> toBeRemoved = new ArrayList<Integer>();
        for (String keyword : keywords) {
            if (invertedlist.containsKey(keyword)) {
                List<Integer> newslist = invertedlist.get(keyword);
                for (Integer onenews : results) {
                    if (!newslist.contains(onenews)) {
                        toBeRemoved.add(onenews);
                    }
                }
            } else {
                continue;
            }
        }
        if (0 != toBeRemoved.size()) {
            for (Integer temp : toBeRemoved) {
                results.remove(temp);
            }
        }
        if (results.size() > newsNum) {
            results = results.subList(0, newsNum - 1);
        }
        news = newsMapper.getNewsListByID(results);
        System.out.print("Results of search by keywords: ");
        for (News onenews : news) {
            System.out.print(onenews.getnewsid() + ",");
        }
        System.out.println("");
        return news;
    }

    private List<Integer> transferInvert(String keyword) {
        List<Integer> newslist = new ArrayList<Integer>();
        InvertedIndex a = invertedIndexMapper.find(keyword);
        if (null == a) {
            return null;
        }
        String i = a.getNews_id();
        String news_id = i.substring(1, i.length() - 1);
        String[] result = news_id.split(", ");
        for (String temp : result) {
            if (null != temp) {
                newslist.add(Integer.valueOf(temp));
            }
        }
        return newslist;
    }

    /**
     * 去掉两个新闻集合中重复的.
     *
     * @param newsa
     * @param newsb
     * @return news去重后的新闻列表
     */
    public List<News> removal(List<News> newsa, List<News> newsb) {
        Set<News> set = new HashSet<>();
        set.addAll(newsa);
        set.addAll(newsb);
        List<News> temp = new ArrayList<>(set);
        return temp;
    }

    /**
     * 更新用户喜好.
     *
     * @param
     * @return
     */
    public void updatePreferences(int userId, int originalLength, int durationOfPlay, int newsid) {
            User userToBeUpdated = userMapper.getUser(userId);
            String newspreferences;
            Map<String,Double> rate;
            if(userToBeUpdated.getNewspreferences()==null||userToBeUpdated.getNewspreferences()==""){
                rate = new HashMap<>();
            }else {
                newspreferences = userToBeUpdated.getNewspreferences();
                rate = stringToRatings(newspreferences);
            }
            String classes = String.valueOf(newsMapper.getClassByNewsID(newsid));
            double newRating = (double) durationOfPlay/originalLength;
            if(rate.containsKey(classes)){
                double oldRating = rate.get(classes);
                rate.replace(classes,(oldRating+newRating)/2);
            }else{
                rate.put(classes,newRating);
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
            input.put("newspreferences",toBeUpdated);
            userMapper.updateUserNewsPreferences(input);
    }


    private String ratingsToString(Map<String, Double> ratings) {
        StringBuilder userBehaviorString = new StringBuilder("");
        // 保留小数位后两位
        DecimalFormat df = new DecimalFormat("#.00");
        for (String temp : ratings.keySet()) {
            String str = df.format(ratings.get(temp));
            userBehaviorString.append(temp.toString() + ":" + str + ",");
        }
        return userBehaviorString.toString();
    }


    private Map<String,Double> stringToRatings(String ratings) {
        Map<String, Double> results = new HashMap<>();
        String[] class_rating = ratings.split(",");
        String[] mid;
        for (String temp : class_rating) {
            mid = temp.split(":");
            results.put(mid[0], Double.valueOf(mid[1]));
        }
        return results;
    }

    /**
     * 更新用户喜好.
     *
     * @param news
     * @param userId
     * @return 过滤后的新闻
     */
    private List<News> filterBrowsedNews(List<News> news, int userId) {
        List<UserNewsBehavior> historylist = userNewsBehaviorMapper
                .getUserNewsBehaviorByUserId(userId);
        for (UserNewsBehavior temp : historylist) {
            for (int i = 0; i < news.size(); i++) {
                if (news.get(i).getnewsid() == temp.getNewsid()) {
                    news.remove(i);
                }
            }
        }
        return news;
    }


}
