package com.meibanlu.qa.analysis.service;

import com.meibanlu.qa.analysis.entity.SlotSeq;
import com.meibanlu.qa.analysis.entity.vo.LexerBean;

import java.util.List;

/**
 * 词槽序服务接口
 */
public interface ISlotSeqService {
    /**
     * 查找匹配度最高的词槽序
     * @param lexerBean 用户输入文本分词结果
     * @return 匹配度最高的词槽序集合
     */
    List<SlotSeq> fetchSuitableSeq(LexerBean lexerBean);

}
