package com.meibanlu.qa.analysis.analyzer.intent;

import com.meibanlu.qa.analysis.entity.ExtractContent;

/**
 * 意图分析器
 */
public interface IIntentAnalyzer {
    /**
     * 计算意图匹配度
     * @return 意图匹配度
     */
    double score();

    /**
     * 意图内容抽取
     * @return 意图内容结果
     */
    ExtractContent extract();
}
