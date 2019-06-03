package com.meibanlu.qa.analysis.dao;

import com.meibanlu.qa.analysis.entity.SlotSeq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SlotSeqDao {

    /**
     * 根据词槽序ID查找词槽序
     * @param seqId 词槽序ID
     * @return 词槽序
     */
    SlotSeq querySlotSeqById(String seqId);

    /**
     * 根据意图ID查找词槽序集合
     * @param intentId 意图ID
     * @return 词槽序集合
     */
    List<SlotSeq> querySlotSeqByIntentId(String intentId);

    /**
     * 插入新词槽序
     * @param id 词槽序ID
     * @param intentId 意图ID
     * @param seqName 词槽序名称
     * @param action 动作
     * @param seqType 词槽序类型
     * @param targetUrl 词槽序查询目标URL
     * @param seqPriority 词槽序优先级
     * @param seqNamePriority 词槽序名称优先级
     */
    void insertSlotSeq(String id, String intentId, String seqName, String action, String seqType, String targetUrl, float seqPriority, float seqNamePriority);

    /**
     * 更新词槽序
     * @param intentId 意图ID
     * @param seqName 词槽序名称
     * @param action 动作
     * @param seqType 词槽序类型
     * @param targetUrl 词槽序查询目标URL
     * @param seqPriority 词槽序优先级
     * @param seqNamePriority 词槽序名称优先级
     */
    void updateSlotSeq(String intentId, String seqName, String action, String seqType, String targetUrl, float seqPriority, float seqNamePriority);

    /**
     * 删除词槽序
     * @param id 词槽序ID
     */
    void deleteSlotSeqById(String id);
}
