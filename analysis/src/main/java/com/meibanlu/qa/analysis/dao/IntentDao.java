package com.meibanlu.qa.analysis.dao;

import com.meibanlu.qa.analysis.entity.Intent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IntentDao {

    /**
     * 根据意图ID查找意图
     * @param intentId 意图ID
     * @return 意图
     */
    Intent queryIntentById(String intentId);

    /**
     * 列出所有意图
     * @return 意图集合
     */
    List<Intent> listIntent();

    /**
     * 插入意图
     * @param intentId 意图ID
     * @param intentName 意图名称
     * @param domain 域
     */
    void insertIntent(String intentId, String intentName, String domain);

    /**
     * 更新意图
     * @param intentId 意图ID
     * @param intentName 意图名称
     */
    void updateIntent(String intentId, String intentName);

    /**
     * 删除意图
     * @param intentId 意图ID
     */
    void deleteIntent(String intentId);
}
