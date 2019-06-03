package com.meibanlu.qa.service.service;

import com.meibanlu.qa.service.entity.CommendAudios;
import com.meibanlu.qa.service.entity.home.AlbumProgramItemBean;
import com.meibanlu.qa.service.entity.home.HomeAudioItemVO;
import com.meibanlu.qa.service.mapper.CommendAudiosMapper;
import com.meibanlu.qa.service.util.DealXCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 首页数据服务
 */
@Service
public class HomeDataService {

    @Resource
    private CommendAudiosMapper commendAudiosMapper;
    private DealXCode dealXCode = new DealXCode();

    /**
     * 获取首页数据
     */
    public List<HomeAudioItemVO> fetchHomeData(){
        List<HomeAudioItemVO> result = new LinkedList<HomeAudioItemVO>();
        /**
         * 构造“猜你喜欢”数据
         */
        fetchDataByType("0", "音乐", "猜你喜欢", result);
        /**
         * 构造“推荐新闻”数据
         */
        HomeAudioItemVO homeAudioItemVO1 = new HomeAudioItemVO();
        homeAudioItemVO1.setId("1");
        homeAudioItemVO1.setTitle("推荐新闻");
        homeAudioItemVO1.getHomeAudioItemTargets().add(new AlbumProgramItemBean("100", "新闻联播", "http://photocdn.sohu.com/20131120/Img390454138.jpg"));
        homeAudioItemVO1.getHomeAudioItemTargets().add(new AlbumProgramItemBean("101", "中国之声", "http://n.sinaimg.cn/ent/transform/265/w630h435/20180321/uXME-fyskeue2556735.jpg"));
        homeAudioItemVO1.getHomeAudioItemTargets().add(new AlbumProgramItemBean("102", "环球网", "http://pic.pc6.com/up/201401/175441_6099683463573.jpg"));
        result.add(homeAudioItemVO1);
        /**
         * 构造“有声书”数据
         */
        fetchDataByType("1", "有声书", "有声书", result);
        /**
         * 构造“儿童”数据
         */
        fetchDataByType("2", "儿童", "儿童", result);
        /**
         * 构造“娱乐”数据
         */
        fetchDataByType("3", "娱乐", "娱乐", result);
        /**
         * 构造“商业财经”数据
         */
        fetchDataByType("4", "商业财经", "商业财经", result);
        /**
         * 构造“情感生活”数据
         */
        fetchDataByType("5", "情感生活", "情感生活", result);
        /**
         * 构造“历史”数据
         */
        fetchDataByType("6", "历史", "历史", result);
        return result;
    }

    /**
     * 根据type构造数据
     * @param id 类ID
     * @param type 类型
     * @param title 显示标题
     * @param result 结果集
     */
    private void fetchDataByType(String id, String type, String title, List<HomeAudioItemVO> result){
        HomeAudioItemVO homeAudioItemVO = new HomeAudioItemVO();
        homeAudioItemVO.setId(id);
        homeAudioItemVO.setTitle(title);
        List<CommendAudios> commandMusic = commendAudiosMapper.findCommendAudiosBytype(type);
        if(commandMusic != null && !commandMusic.isEmpty()){
            for(CommendAudios item: commandMusic){
                dealXCode.fetchXcode(item);
                homeAudioItemVO.getHomeAudioItemTargets().add(new AlbumProgramItemBean(item));
            }
        }
        result.add(homeAudioItemVO);
    }
}
