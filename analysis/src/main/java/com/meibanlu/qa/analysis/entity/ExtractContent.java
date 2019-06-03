package com.meibanlu.qa.analysis.entity;

import java.util.LinkedList;
import java.util.List;

/**
 * 单词槽内容抽取结果
 */
public class ExtractContent {

    /**
     * 内容抽取结果Item集合
     */
    private List<Item> contentItems;
    /**
     * 需要反问的内容
     */
    private List<Item> contentItemsRequiredMore;
    /**
     * 标识是否需要反问
     */
    private int requiredMore = NO_MORE_REQUIRED;
    /**
     * 不需要反问
     */
    public static int NO_MORE_REQUIRED = 0;
    /**
     * 需要反问
     */
    public static int MORE_REQUIRED = 1;
    /**
     * 域（来自于意图）
     */
    private String domain;
    /**
     * 动作（来自于词槽序）
     */
    private String action;

    /**
     * 是否需要反问
     */
    public boolean moreRequired(){
        return requiredMore == MORE_REQUIRED;
    }

    public List<Item> getContentItems() {
        if(contentItems == null){
            contentItems = new LinkedList<Item>();
        }
        return contentItems;
    }

    public void setContentItems(List<Item> contentItems) {
        this.contentItems = contentItems;
    }

    /**
     * 添加内容抽取结果Item
     * @param item 内容抽取结果Item
     */
    public void addContentItem(Item item){
        if(contentItems == null){
            contentItems = new LinkedList<Item>();
        }
        contentItems.add(item);
    }

    public int getRequiredMore() {
        return requiredMore;
    }

    public void setRequiredMore(int requiredMore) {
        this.requiredMore = requiredMore;
    }

    public List<Item> getContentItemsRequiredMore() {
        if(contentItemsRequiredMore == null){
            contentItemsRequiredMore = new LinkedList<Item>();
        }
        return contentItemsRequiredMore;
    }

    public void setContentItemsRequiredMore(List<Item> contentItemsRequiredMore) {
        this.contentItemsRequiredMore = contentItemsRequiredMore;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 单词槽内容抽取结果Item
     */
    public static class Item{
        /**
         * 内容key
         */
        private String key;
        /**
         * 内容value
         */
        private String value;
        /**
         * 内容来源词槽ID
         */
        private String slotId;
        /**
         * 内容来源在原句中的下标
         */
        private int indexInRaw;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getSlotId() {
            return slotId;
        }

        public void setSlotId(String slotId) {
            this.slotId = slotId;
        }

        public int getIndexInRaw() {
            return indexInRaw;
        }

        public void setIndexInRaw(int indexInRaw) {
            this.indexInRaw = indexInRaw;
        }
    }
}
