package com.meibanlu.qa.analysis.service;

import com.meibanlu.qa.analysis.entity.Intent;
import com.meibanlu.qa.analysis.entity.vo.LexerBean;

import java.util.List;

/**
 * 意图服务接口
 */
public interface IIntentService {

    /**
     * 获取匹配度最高的意图
     * @param lexerBean 用户输入文本分词结果
     * @return 候选意图集合
     */
    List<Intent> fetchSuitableIntent(LexerBean lexerBean);
}
