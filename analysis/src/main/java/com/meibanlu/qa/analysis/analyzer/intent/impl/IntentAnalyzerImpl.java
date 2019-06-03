package com.meibanlu.qa.analysis.analyzer.intent.impl;

import com.meibanlu.qa.analysis.analyzer.intent.IIntentAnalyzer;
import com.meibanlu.qa.analysis.entity.ExtractContent;
import com.meibanlu.qa.analysis.entity.Intent;

/**
 * 意图分析器
 */
public class IntentAnalyzerImpl implements IIntentAnalyzer {
    /**
     * 意图
     */
    private Intent intent;

    public IntentAnalyzerImpl(Intent intent) {
        this.intent = intent;
    }

    /**
     * 计算意图匹配度
     * @return 意图匹配度
     */
    @Override
    public double score() {
        return 0;
    }

    /**
     * 意图内容抽取
     * @return 意图内容结果
     */
    @Override
    public ExtractContent extract() {
        return null;
    }
}
