package com.meibanlu.qa.analysis.entity;

/**
 * 词槽序需要的元素
 */
public class SlotSeqRequireElement {
    /**
     * ID
     */
    private String id;
    /**
     * 词槽序ID
     */
    private String slotSeqId;
    /**
     * 需要的元素键
     */
    private String key;
    /**
     * 需要的元素值
     */
    private String value;
    /**
     * key来源的词槽ID
     */
    private String slotId;
    /**
     * 标识是否必须需要此key的元素
     * 用于追问
     */
    private int must = FLAG_NOT_MUST;
    /**
     * 不是此key的元素不是必须的
     */
    public static int FLAG_NOT_MUST = 0;
    /**
     * 必须需要此key的元素
     */
    public static int FLAG_MUST = 1;

    /**
     * 判断是否是必须元素
     */
    public boolean mustElement(){
        return must == FLAG_MUST;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlotSeqId() {
        return slotSeqId;
    }

    public void setSlotSeqId(String slotSeqId) {
        this.slotSeqId = slotSeqId;
    }

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

    public int getMust() {
        return must;
    }

    public void setMust(int must) {
        this.must = must;
    }
}
