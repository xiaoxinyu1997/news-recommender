package com.meibanlu.qa.analysis.dao;

import com.meibanlu.qa.analysis.entity.SlotSeqRequireElement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SlotSeqRequireElementDao {

    /**
     * 根据词槽序ID查询该词槽序需要的元素集合
     * @param slotSeqId 词槽序ID
     */
    List<SlotSeqRequireElement> querySlotSeqRequiredElement(String slotSeqId);

    /**
     * 添加词槽序需要的元素
     * @param id ID
     * @param slotSeqId 词槽序ID
     * @param key 需要的元素key
     * @param value 需要的元素value（默认为空）
     * @param slotId key来源的词槽ID
     * @param must 标识是否必须需要此key的元素，用于追问
     */
    void insertSlotSeqRequiredElement(String id, String slotSeqId, String key, String value, String slotId, int must);

    /**
     * 根据ID更新词槽序需要的元素
     * @param id ID
     * @param slotSeqId 词槽序ID
     * @param key 需要的元素key
     * @param value 需要的元素value（默认为空）
     * @param slotId key来源的词槽ID
     * @param must 标识是否必须需要此key的元素，用于追问
     */
    void updateSlotSeqRequiredElement(String id, String slotSeqId, String key, String value, String slotId, int must);

    /**
     * 删除词槽序需要的元素
     * @param id ID
     */
    void deleteSlotSeqRequiredElement(String id);
}
