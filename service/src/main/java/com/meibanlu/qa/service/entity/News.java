package com.meibanlu.qa.service.entity;

public class News {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return newsid == news.newsid;
    }

    @Override
    public int hashCode() {
        return newsid;
    }

    private int newsid;
    private String newstitle;
    private String href;
    private String content;
    private int classid;
    private int ranking;
    private String source;
    private String time;
    private String place;
    private String keywords;
    private String terms;
    /**
     * 音频平台
     * 环球网
     * cnr
     * cnr_mp3
     */
    private String website;
    /**
     * 音频地址
     */
    private String audiosurl;
    /**
     * 摘要
     */
    private String abs;
    /**
     * 语音合成标志 0：未合成  1：已合成
     */
    private String ttsTag;


     //get set的名字一定要对应，否则返回的json数据中的键显示不对
    public int getnewsid() {
        return newsid;
    }

    public void setnewsid(int newsid) {
        this.newsid = newsid;
    }

    public String getnewstitle() {
        return newstitle;
    }

    public void setnewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public String gethref() {
        return href;
    }

    public void sethref(String href) {
        this.href = href;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getclassid() {
        return classid;
    }

    public void setclassid(int classid) {
        this.classid = classid;
    }

    public int getranking() {
        return ranking;
    }

    public void setranking(int ranking) {
        this.ranking = ranking;
    }

    public String getsource() {
        return source;
    }

    public void setsource(String source) {
        this.source = source;
    }

    public String gettime() {
        return time;
    }

    public void settime(String time) {
        this.time = time;
    }

    public String getplace() {
        return place;
    }

    public void setplace(String place) {
        this.place = place;
    }

    public String getkeywords() {
        return keywords;
    }

    public void setkeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getterms() {
        return terms;
    }

    public void terms(String terms) {
        this.terms = terms;
    }

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public String getTtsTag() {
        return ttsTag;
    }

    public void setTtsTag(String ttsTag) {
        this.ttsTag = ttsTag;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAudiosurl() {
        return audiosurl;
    }

    public void setAudiosurl(String audiosurl) {
        this.audiosurl = audiosurl;
    }
}
