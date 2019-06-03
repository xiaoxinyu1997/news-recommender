package com.meibanlu.qa.analysis.analyzer.slotseq;

import com.meibanlu.qa.analysis.entity.ExtractContent;
import com.meibanlu.qa.analysis.entity.vo.LexerBean;

/**
 * 词槽序分析器
 */
public interface ISlotSeqAnalyzer {
    /**
     * 计算词槽序匹配度
     * @return 词槽序匹配度
     */
    double score(LexerBean lexerBean);

    /**
     * 词槽序内容抽取
     * @return 词槽序内容结果
     */
    ExtractContent extract(LexerBean lexerBean);
}
