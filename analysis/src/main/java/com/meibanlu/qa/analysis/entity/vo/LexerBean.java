package com.meibanlu.qa.analysis.entity.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LexerBean implements Serializable {

    private long log_id;
    /**
     * 原始文本
     */
    private String text;
    /**
     * 原始文本备份
     */
    private String textRaw;
    /**
     * 分词后文本，不带词性标注
     */
    private String textWithBarrier;
    /**
     * 分词后文本，不带词性标注备份
     */
    private String textWithBarrierRaw;
    /**
     * 带有词性标注的文本
     */
    private String textWithPos;
    /**
     * 带有词性标注的文本备份
     */
    private String textWithPosRaw;
    /**
     * 词性序列
     */
    private String posWithBarrier;
    /**
     * 词性序列备份
     */
    private String posWithBarrierRaw;
    /**
     * 分词结果
     */
    private List<ItemsBean> items = new ArrayList<ItemsBean>();

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        if(textRaw == null || textRaw.isEmpty()){
            textRaw = text;
        }
    }

    public String getTextWithBarrier() {
        return textWithBarrier;
    }

    public void setTextWithBarrier(String textWithBarrier) {
        this.textWithBarrier = textWithBarrier;
        if(textWithBarrierRaw == null || textWithBarrierRaw.isEmpty()){
            textWithBarrierRaw = textWithBarrier;
        }
    }

    public String getTextWithPos() {
        return textWithPos;
    }

    public void setTextWithPos(String textWithPos) {
        this.textWithPos = textWithPos;
        if(textWithPosRaw == null || textWithPosRaw.isEmpty()){
            textWithPosRaw = textWithPos;
        }
    }

    public List<ItemsBean> getItems() {
//        int index = 0;
//        ArrayList<Integer> indexDelete = new ArrayList<Integer>();
//        while (index < items.size()){
//            ItemsBean item = items.get(index);
//            StringBuilder str = new StringBuilder(item.getItem());
//            index++;
//            while (index < items.size() && items.get(index).getPos().equals(item.getPos())){
//                indexDelete.add(index);
//                str.append(items.get(index).item);
//                index++;
//            }
//            item.setItem(str.toString());
//        }
//        //升序排序
//        Collections.sort(indexDelete);
//        for(int i = indexDelete.size() - 1 ; i > -1 ; i--){
//            items.remove(indexDelete.get(i).intValue());
//        }
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public String getTextRaw() {
        return textRaw;
    }

    public String getTextWithBarrierRaw() {
        return textWithBarrierRaw;
    }

    public String getTextWithPosRaw() {
        return textWithPosRaw;
    }

    public String getPosWithBarrier() {
        return posWithBarrier;
    }

    public void setPosWithBarrier(String posWithBarrier) {
        this.posWithBarrier = posWithBarrier;
        if(posWithBarrierRaw == null || posWithBarrierRaw.isEmpty()){
            posWithBarrierRaw = posWithBarrier;
        }
    }

    public String getPosWithBarrierRaw() {
        return posWithBarrierRaw;
    }

    public static class ItemsBean implements Serializable {
        /**
         * formal :
         * loc_details : []
         * item : 都江堰
         * pos :
         * ne : LOC
         * basic_words : ["都江堰"]
         * byte_length : 6
         * byte_offset : 0
         * uri :
         */

        private String formal;
        private String item;
        private String pos;
        private String ne;
        private int byte_length;
        private int byte_offset;
        private String uri;
        private List<String> loc_details;
        private List<String> basic_words;

    public String getFormal() {
        return formal;
    }

    public void setFormal(String formal) {
        this.formal = formal;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getByte_length() {
        return byte_length;
    }

    public void setByte_length(int byte_length) {
        this.byte_length = byte_length;
    }

    public int getByte_offset() {
        return byte_offset;
    }

    public void setByte_offset(int byte_offset) {
        this.byte_offset = byte_offset;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<String> getLoc_details() {
        return loc_details;
    }

    public void setLoc_details(List<String> loc_details) {
        this.loc_details = loc_details;
    }

    public List<String> getBasic_words() {
        return basic_words;
    }

    public void setBasic_words(List<String> basic_words) {
        this.basic_words = basic_words;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public void setNe(String ne) {
        this.ne = ne;
    }

    public String getPos() {
            if (pos.isEmpty()) {
                return ne;
            } else {
                //ns 也表示地名
                return "ns".equals(pos) ? "LOC" : pos;
            }
        }

        public String getNe() {
            if (ne.isEmpty()) {
                return pos;
            } else {
                return ne;
            }
        }

    }
}
