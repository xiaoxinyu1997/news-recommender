package com.meibanlu.qa.analysis.entity;

/**
 * 词槽-词槽序 关系
 */
public class SlotSeqRel {
    /**
     * 关系ID
     */
    private String id;
    /**
     * 词槽ID
     */
    private String slotId;
    /**
     * 词槽序ID
     */
    private String slotSeqId;
    /**
     * 词槽位置，0开始
     */
    private int slotPosition;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getSlotSeqId() {
        return slotSeqId;
    }

    public void setSlotSeqId(String slotSeqId) {
        this.slotSeqId = slotSeqId;
    }

    public int getSlotPosition() {
        return slotPosition;
    }

    public void setSlotPosition(int slotPosition) {
        this.slotPosition = slotPosition;
    }
}
