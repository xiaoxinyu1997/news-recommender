package com.meibanlu.qa.analysis.dao;

import com.meibanlu.qa.analysis.entity.Slot;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SlotDao {
    /**
     * 根据词槽ID查询词槽
     * @param slotId 词槽ID
     * @return 词槽
     */
    Slot querySlotById(String slotId);

    /**
     * 根据词槽序ID查询词槽集合
     * @param seqId 词槽序ID
     * @return 词槽集合
     */
    List<Slot> querySlotBySeqId(String seqId);

    /**
     * 插入新词槽
     * @param slotId 词槽ID
     * @param type 词槽类型
     * @param patternMatcher 词槽匹配模式
     * @param matcherKey 匹配key
     * @param mainWord 主词
     */
    void insertSlot(String slotId, String type, String patternMatcher, String matcherKey, String mainWord);

    /**
     * 更新词槽
     * @param slotId 词槽ID
     * @param type 词槽类型
     * @param patternMatcher 词槽匹配模式
     * @param matcherKey 匹配key
     * @param mainWord 主词
     */
    void updateSlot(String slotId, String type, String patternMatcher, String matcherKey, String mainWord);

    /**
     * 删除词槽
     * @param slotId 词槽ID
     */
    void deleteSlotById(String slotId);
}
