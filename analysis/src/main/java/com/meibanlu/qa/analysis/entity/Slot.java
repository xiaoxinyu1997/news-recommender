package com.meibanlu.qa.analysis.entity;

import com.meibanlu.qa.analysis.analyzer.slot.ISlotAnalyzer;
import com.meibanlu.qa.analysis.analyzer.slot.impl.EnumSlotAnalyzer;
import com.meibanlu.qa.analysis.analyzer.slot.impl.PosSlotAnalyzer;
import com.meibanlu.qa.analysis.analyzer.slot.impl.RegexSlotAnalyzer;

/**
 * 词槽
 */
public class Slot {
    /**
     * 词槽ID
     */
    private String id;
    /**
     * 词槽类型
     * 0：枚举
     * 100：词性
     * 200：正则表达式
     */
    private int type;
    /**
     * 枚举类型
     */
    public static final int TYPE_ENUM = 0;
    /**
     * 词性类型
     */
    public static final int TYPE_POS = 100;
    /**
     * 正则表达式类型
     */
    public static final int TYPE_REGEX = 200;
    /**
     * 匹配模式
     */
    private String patternMatcher;
    /**
     * 匹配key
     */
    private String matcherKey;
    /**
     * 主词
     */
    private String mainWord;
    /**
     * 词槽分析器
     */
    private ISlotAnalyzer analyzer;

    /**
     * 获取词槽分析器
     * @return 词槽分析器
     */
    public ISlotAnalyzer fetchAnalyzer(){
        if(analyzer != null){
            return analyzer;
        }
        switch (type){
            case TYPE_ENUM:
                analyzer = new EnumSlotAnalyzer(this);
                break;
            case TYPE_POS:
                analyzer = new PosSlotAnalyzer(this);
                break;
            default:
                analyzer = new RegexSlotAnalyzer(this);
        }
        return analyzer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPatternMatcher() {
        return patternMatcher;
    }

    public void setPatternMatcher(String patternMatcher) {
        this.patternMatcher = patternMatcher;
    }

    public String getMatcherKey() {
        return matcherKey;
    }

    public void setMatcherKey(String matcherKey) {
        this.matcherKey = matcherKey;
    }

    public String getMainWord() {
        return mainWord;
    }

    public void setMainWord(String mainWord) {
        this.mainWord = mainWord;
    }
}
