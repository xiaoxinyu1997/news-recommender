package com.meibanlu.qa.analysis.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SlotSeqRelDao {

    /**
     * 插入词槽-词槽序关系
     * @param relId 词槽-词槽序关系ID
     * @param slotId 词槽ID
     * @param slotSeqId 词槽序ID
     * @param slotPosition 词槽在词槽序中的位置，0开始
     */
    void insertSlotSeqRel(String relId, String slotId, String slotSeqId, int slotPosition);

    /**
     * 删除词槽-词槽序关系
     * @param relId 词槽-词槽序关系ID
     */
    void deleteSlotSeqRelById(String relId);

    /**
     * 更新词槽-词槽序关系
     * @param relId 词槽-词槽序关系ID
     * @param slotPosition 词槽在词槽序中的位置，0开始
     */
    void updateSlotSeqRel(String relId, int slotPosition);
}
