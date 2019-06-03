package com.meibanlu.qa.service.entity.home;

import com.meibanlu.qa.service.entity.CommendAudios;

public class AlbumProgramItemBean {
    /**
     * 音频ID
     */
    private String id;
    /**
     * 标题
     */
    private String audioName;
    /**
     * 作者
     */
    private String audioAuthor;
    /**
     * 图片URL
     */
    private String audioImg;
    /**
     * 音频URL
     */
    private String audioUrl;
    /**
     * 类型
     */
    private int type = -1;
    /**
     * 音频来源
     */
    private String from;
    /**
     * 类型-音频类
     */
    public static final int TYPE_GROUP = 0;
    /**
     * 类型-音频
     */
    public static final int TYPE_PARTICULAR = 1;
    /**
     * 类型-新闻联播
     */
    public static final String FROM_NEWS_CCTV = "新闻联播";

    public AlbumProgramItemBean() {
    }

    /**
     * 根据推荐的音乐生成需要的数据
     * 用于首页“猜你喜欢”
     */
    public AlbumProgramItemBean(CommendAudios commendAudios) {
        this.id = String.valueOf(commendAudios.getId());
        this.audioName = commendAudios.getTitle();
        if(commendAudios.getSinger() == null){
            this.audioAuthor = "";
        }else{
            this.audioAuthor = commendAudios.getSinger();
        }
        this.audioImg = commendAudios.getImage();
        this.audioUrl = commendAudios.getPlayurl();
        this.type = AlbumProgramItemBean.TYPE_PARTICULAR;
        this.from = "";
    }

    /**
     * 用于首页“推荐新闻”
     */
    public AlbumProgramItemBean(String id, String title, String imgUrl){
        this.id = id;
        this.audioName = title;
        this.audioImg = imgUrl;
        this.type = AlbumProgramItemBean.TYPE_GROUP;
        this.from = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public String getAudioAuthor() {
        return audioAuthor;
    }

    public void setAudioAuthor(String audioAuthor) {
        this.audioAuthor = audioAuthor;
    }

    public String getAudioImg() {
        return audioImg;
    }

    public void setAudioImg(String audioImg) {
        this.audioImg = audioImg;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}
