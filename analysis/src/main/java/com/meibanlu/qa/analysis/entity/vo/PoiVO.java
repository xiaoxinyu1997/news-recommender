package com.meibanlu.qa.analysis.entity.vo;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mitnick on 2019/5/3.
 * Description 兴趣点
 */
public class PoiVO {

    /**
     * status : 1
     * count : 889
     * info : OK
     * infocode : 10000
     * suggestion : {"keywords":[],"cities":[]}
     * pois : [{"id":"B001C92KIN","parent":"B001C7U8Q4","name":"电子科技大学沙河校区","tag":[],"type":"科教文化服务;学校;高等院校","typecode":"141201","biz_type":[],"address":"建设北路二段4号","location":"104.100227,30.675702","tel":"028-83201999","postcode":"610051","website":[],"email":[],"pcode":"510000","pname":"四川省","citycode":"028","cityname":"成都市","adcode":"510108","adname":"成华区","importance":[],"shopid":[],"shopinfo":"0","poiweight":[],"gridcode":"4604001800","distance":[],"navi_poiid":"H48F016017_194762","entr_location":"104.097414,30.674575","business_area":[],"exit_location":[],"match":"0","recommend":"3","timestamp":[],"alias":[],"indoor_map":"0","indoor_data":{"cpid":[],"floor":[],"truefloor":[],"cmsid":[]},"groupbuy_num":"0","discount_num":"0","biz_ext":{"rating":[],"cost":[]},"event":[],"children":[],"photos":[{"title":"外景图","url":"http://store.is.autonavi.com/showpic/f57a541eadd12b28f18d94492d12036f"},{"title":[],"url":"http://store.is.autonavi.com/showpic/ba6e9805748e5e819a808c1f93281aaa"},{"title":[],"url":"http://store.is.autonavi.com/showpic/c6de5899cfc0ba87390fbefe4a45b6a5"}]},{"id":"B001C8UQ4H","parent":[],"name":"电子科技大学清水河校区","tag":[],"type":"科教文化服务;学校;高等院校","typecode":"141201","biz_type":[],"address":"西源大道2006号","location":"103.931664,30.749142","tel":"028-61830539","postcode":"611731","website":[],"email":[],"pcode":"510000","pname":"四川省","citycode":"028","cityname":"成都市","adcode":"510117","adname":"郫都区","importance":[],"shopid":[],"shopinfo":"0","poiweight":[],"gridcode":"4603079421","distance":[],"navi_poiid":"H48F015016_121405","entr_location":"103.924845,30.743640","business_area":[],"exit_location":[],"match":"0","recommend":"3","timestamp":[],"alias":[],"indoor_map":"0","indoor_data":{"cpid":[],"floor":[],"truefloor":[],"cmsid":[]},"groupbuy_num":"0","discount_num":"0","biz_ext":{"rating":[],"cost":[]},"event":[],"children":[],"photos":[{"title":[],"url":"http://store.is.autonavi.com/showpic/f330a20f3211e97f5c6f00940428644e"},{"title":[],"url":"http://store.is.autonavi.com/showpic/c844bc28998e9835ee878dae1270d56e"},{"title":[],"url":"http://store.is.autonavi.com/showpic/376721788adbb9804f3671db9a47d626"}]},{"id":"B001C04BC8","parent":[],"name":"电子科技大学成都学院","tag":[],"type":"科教文化服务;学校;高等院校","typecode":"141201","biz_type":[],"address":"百叶路1号","location":"103.965166,30.728431","tel":"028-87825027;028-87825029","postcode":[],"website":[],"email":[],"pcode":"510000","pname":"四川省","citycode":"028","cityname":"成都市","adcode":"510117","adname":"郫都区","importance":[],"shopid":[],"shopinfo":"0","poiweight":[],"gridcode":"4603077710","distance":[],"navi_poiid":"H48F016016_121464;496900","entr_location":"103.968101,30.729146","business_area":[],"exit_location":[],"match":"0","recommend":"0","timestamp":[],"alias":[],"indoor_map":"0","indoor_data":{"cpid":[],"floor":[],"truefloor":[],"cmsid":[]},"groupbuy_num":"0","discount_num":"0","biz_ext":{"rating":[],"cost":[]},"event":[],"children":[],"photos":[{"title":[],"url":"http://store.is.autonavi.com/showpic/4ae84e084218e294e0eafcdd19d71761"},{"title":"外景图","url":"http://store.is.autonavi.com/showpic/c4568d3db7fc2c78ec1f377b5404e8ee"},{"title":"内景图","url":"http://store.is.autonavi.com/showpic/b861fb1c78dcf18e57631ca144ecf041"}]}]
     */
    private String status;
    private String count;
    private String info;
    private String infocode;
    private List<PoisBean> pois;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfocode() {
        return infocode;
    }

    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    public List<PoisBean> getPois() {
        if(pois == null){
            pois = new LinkedList<PoisBean>();
        }
        return pois;
    }

    public void setPois(List<PoisBean> pois) {
        this.pois = pois;
    }

    public static class PoisBean {
        /**
         * id : B001C92KIN
         * parent : B001C7U8Q4
         * name : 电子科技大学沙河校区
         * tag : []
         * type : 科教文化服务;学校;高等院校
         * typecode : 141201
         * biz_type : []
         * address : 建设北路二段4号
         * location : 104.100227,30.675702
         * tel : 028-83201999
         * postcode : 610051
         * website : []
         * email : []
         * pcode : 510000
         * pname : 四川省
         * citycode : 028
         * cityname : 成都市
         * adcode : 510108
         * adname : 成华区
         * importance : []
         * shopid : []
         * shopinfo : 0
         * poiweight : []
         * gridcode : 4604001800
         * distance : []
         * navi_poiid : H48F016017_194762
         * entr_location : 104.097414,30.674575
         * business_area : []
         * exit_location : []
         * match : 0
         * recommend : 3
         * timestamp : []
         * alias : []
         * indoor_map : 0
         * indoor_data : {"cpid":[],"floor":[],"truefloor":[],"cmsid":[]}
         * groupbuy_num : 0
         * discount_num : 0
         * biz_ext : {"rating":[],"cost":[]}
         * event : []
         * children : []
         * photos : [{"title":"外景图","url":"http://store.is.autonavi.com/showpic/f57a541eadd12b28f18d94492d12036f"},{"title":[],"url":"http://store.is.autonavi.com/showpic/ba6e9805748e5e819a808c1f93281aaa"},{"title":[],"url":"http://store.is.autonavi.com/showpic/c6de5899cfc0ba87390fbefe4a45b6a5"}]
         */

        private String id;
        private String parent;
        private String name;
        private String type;
        private String typecode;
        private String address;
        private String location;
        private String tel;
        private String postcode;
        private String pcode;
        private String pname;
        private String citycode;
        private String cityname;
        private String adcode;
        private String adname;
        private String shopinfo;
        private String gridcode;
        private String navi_poiid;
        private String entr_location;
        private String match;
        private String recommend;
        private String indoor_map;
        private String groupbuy_num;
        private String discount_num;
        private List<PhotosBean> photos;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypecode() {
            return typecode;
        }

        public void setTypecode(String typecode) {
            this.typecode = typecode;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getPcode() {
            return pcode;
        }

        public void setPcode(String pcode) {
            this.pcode = pcode;
        }

        public String getPname() {
            return pname;
        }

        public void setPname(String pname) {
            this.pname = pname;
        }

        public String getCitycode() {
            return citycode;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public String getAdcode() {
            return adcode;
        }

        public void setAdcode(String adcode) {
            this.adcode = adcode;
        }

        public String getAdname() {
            return adname;
        }

        public void setAdname(String adname) {
            this.adname = adname;
        }

        public String getShopinfo() {
            return shopinfo;
        }

        public void setShopinfo(String shopinfo) {
            this.shopinfo = shopinfo;
        }

        public String getGridcode() {
            return gridcode;
        }

        public void setGridcode(String gridcode) {
            this.gridcode = gridcode;
        }

        public String getNavi_poiid() {
            return navi_poiid;
        }

        public void setNavi_poiid(String navi_poiid) {
            this.navi_poiid = navi_poiid;
        }

        public String getEntr_location() {
            return entr_location;
        }

        public void setEntr_location(String entr_location) {
            this.entr_location = entr_location;
        }

        public String getMatch() {
            return match;
        }

        public void setMatch(String match) {
            this.match = match;
        }

        public String getRecommend() {
            return recommend;
        }

        public void setRecommend(String recommend) {
            this.recommend = recommend;
        }

        public String getIndoor_map() {
            return indoor_map;
        }

        public void setIndoor_map(String indoor_map) {
            this.indoor_map = indoor_map;
        }

        public String getGroupbuy_num() {
            return groupbuy_num;
        }

        public void setGroupbuy_num(String groupbuy_num) {
            this.groupbuy_num = groupbuy_num;
        }

        public String getDiscount_num() {
            return discount_num;
        }

        public void setDiscount_num(String discount_num) {
            this.discount_num = discount_num;
        }

        public List<PhotosBean> getPhotos() {
            return photos;
        }

        public void setPhotos(List<PhotosBean> photos) {
            this.photos = photos;
        }

        public static class PhotosBean {
            /**
             * title : 外景图
             * url : http://store.is.autonavi.com/showpic/f57a541eadd12b28f18d94492d12036f
             */

            private String title;
            private String url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
