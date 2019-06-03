package com.meibanlu.qa.analysis.entity;

import com.meibanlu.qa.analysis.analyzer.intent.IIntentAnalyzer;
import com.meibanlu.qa.analysis.analyzer.intent.impl.IntentAnalyzerImpl;

import java.util.LinkedList;
import java.util.List;

/**
 * 意图
 */
public class Intent {
    /**
     * 意图ID
     */
    private String id;
    /**
     * 意图名称
     */
    private String intentName;
    /**
     * 域
     */
    private String domain;
    /**
     * 词槽序集合
     */
    private List<SlotSeq> slotSeqList;
    /**
     * 意图分析器
     */
    private IIntentAnalyzer intentAnalyzer;

    /**
     * 添加词槽序
     * @param slotSeq 词槽序
     */
    public void addSlotSeq(SlotSeq slotSeq){
        if(slotSeqList == null){
            slotSeqList = new LinkedList<SlotSeq>();
        }
        slotSeqList.add(slotSeq);
    }

    /**
     * 获取意图分析器
     * @return 意图分析器
     */
    public IIntentAnalyzer fetchIntentAnalyzer(){
        if(intentAnalyzer == null){
            intentAnalyzer = new IntentAnalyzerImpl(this);
        }
        return intentAnalyzer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntentName() {
        return intentName;
    }

    public void setIntentName(String intentName) {
        this.intentName = intentName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public List<SlotSeq> getSlotSeqList() {
        if(slotSeqList == null){
            slotSeqList = new LinkedList<SlotSeq>();
        }
        return slotSeqList;
    }

    public void setSlotSeqList(List<SlotSeq> slotSeqList) {
        this.slotSeqList = slotSeqList;
    }
}
