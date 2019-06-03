package com.meibanlu.qa.service.entity.home;

import java.util.ArrayList;

public class HomeAudioItemVO {
    /**
     * 条目ID
     */
    private String id;
    /**
     * 条目标题
     */
    private String title;
    /**
     * 具体的音频类或音频
     */
    private ArrayList<AlbumProgramItemBean> homeAudioItemTargets = new ArrayList<AlbumProgramItemBean>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<AlbumProgramItemBean> getHomeAudioItemTargets() {
        return homeAudioItemTargets;
    }

    public void setHomeAudioItemTargets(ArrayList<AlbumProgramItemBean> homeAudioItemTargets) {
        this.homeAudioItemTargets = homeAudioItemTargets;
    }
}
