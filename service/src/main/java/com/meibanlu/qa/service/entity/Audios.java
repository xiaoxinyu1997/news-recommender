package com.meibanlu.qa.service.entity;

public class Audios {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audios audios = (Audios) o;
        return id == audios.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    private int id;
    private String name;
    private String tags;
    private String qianqianurl;
    private String time;

    @Override
    public String toString() {

        return "Audios{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tags='" + tags + '\'' +
                ", qianqianurl='" + qianqianurl + '\'' +
                ", time='" + time + '\'' +
                ", image='" + image + '\'' +
                ", singer='" + singer + '\'' +
                ", album='" + album + '\'' +
                ", click='" + click + '\'' +
                ", collect='" + collect + '\'' +
                ", discuss='" + discuss + '\'' +
                ", shares='" + shares + '\'' +
                ", publishtime='" + publishtime + '\'' +
                ", lyric='" + lyric + '\'' +
                ", qianqian='" + qianqian + '\'' +
                ", kuwourl='" + kuwourl + '\'' +
                '}';
    }

    private String image;
    private String singer;
    private String album;
    private String click;
    private String collect;
    private String discuss;
    private String shares;
    private String publishtime;
    private String lyric;
    private String qianqian;
    private String kuwourl;
    /**
     * 音频类型
     * 音频
     * 相声
     * 新闻
     */
    private String audiosType = "";

    public String getQianqian() {
        return qianqian;
    }

    public void setQianqian(String qianqian) {
        this.qianqian = qianqian;
    }

    public String getKuwourl() {
        return kuwourl;
    }

    public void setKuwourl(String kuwourl) {
        this.kuwourl = kuwourl;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getqianqianurl() {
        return qianqianurl;
    }

    public void setqianqianurl(String qianqianurl) {
        this.qianqianurl = qianqianurl;
    }



    public String gettags() {
        return tags;
    }

    public void settags(String tags) {
        this.tags = tags;
    }

    public String gettime() {
        return time;
    }

    public void settime(String time) {
        this.time = time;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }

    public String getsinger() {
        return singer;
    }

    public void setsinger(String singer) {
        this.singer = singer;
    }


    public String getalbum() {
        return album;
    }

    public void setalbum(String album) {
        this.album = album;
    }

    public String getclick() {
        return click;
    }
    public void setclick(String click) {
        this.click = click;
    }

    public String getcollect() {
        return collect;
    }

    public void setcollect(String collect) {
        this.collect = collect;
    }


    public String getdiscuss() {
        return discuss;
    }

    public void setdiscuss(String discuss) {
        this.discuss = discuss;
    }

    public String getshares() {
        return shares;
    }

    public void setshares(String shares) {
        this.shares = shares;
    }

    public String getpublishtime() {
        return publishtime;
    }

    public void setpublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    public String getlyric() {
        return lyric;
    }

    public void setlyric(String lyric) {
        this.lyric = lyric;
    }

    public String getAudiosType() {
        return audiosType;
    }

    public void setAudiosType(String audiosType) {
        this.audiosType = audiosType;
    }
}
