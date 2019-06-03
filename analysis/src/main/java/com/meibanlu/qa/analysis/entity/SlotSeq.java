package com.meibanlu.qa.analysis.entity;

import com.meibanlu.qa.analysis.analyzer.slotseq.ISlotSeqAnalyzer;
import com.meibanlu.qa.analysis.analyzer.slotseq.impl.SlotSeqAnalyzerImpl;

import java.util.LinkedList;
import java.util.List;

/**
 * 词槽序
 */
public class SlotSeq {

    /**
     * 类型-查找URL
     */
    public static final String TYPE_QUERY_URL = "query_url";

    /**
     * 类型-命令
     */
    public static final String TYPE_COMMAND = "command";
    /**
     * 类型-命令-导航到地址
     */
    public static final String TYPE_COMMAND_NAVIGATION = "commandNavigation";
    /**
     * 类型-命令-回答“第几个”
     */
    public static final String TYPE_COMMAND_ANSWER_INDEX = "commandAnswerIndex";
    /**
     * 类型-命令-先处理“任意类型新闻”的类型再查询
     */
    public static final String TYPE_TYPEDNEWS_QUERY_URL = "typedNews_query_url";

    /**
     * 词槽序ID
     */
    private String id;
    /**
     * 意图ID
     */
    private String intentId;
    /**
     * 词槽序名称
     */
    private String seqName;
    /**
     * 域（数据来自于意图）
     */
    private String domain;
    /**
     * 动作
     */
    private String action;
    /**
     * 词槽序类型
     */
    private String seqType;
    /**
     * 词槽序查询目标URL
     */
    private String targetUrl;
    /**
     * 词槽序优先级（同seqName的词槽序的优先级），数值越大代表优先级越高
     */
    private float seqPriority;
    /**
     * seqName优先级，数值越大代表优先级越高
     */
    private float seqNamePriority;
    /**
     * 分值（只用与缓存匹配度以防多次计算，无其它任何用途）
     */
    public double bufScore = -10.0;
    /**
     * 词槽集合（有顺序）
     */
    private List<Slot> slotSeq;
    /**
     * 词槽序需要的元素
     */
    private List<SlotSeqRequireElement> slotSeqRequireElement;
    /**
     * 词槽序分析器
     */
    private ISlotSeqAnalyzer slotSeqAnalyzer;

    /**
     * 获取词槽序分析器
     * @return 词槽序分析器
     */
    public ISlotSeqAnalyzer fetchSlotSeqAnalyzer(){
        if(slotSeqAnalyzer == null){
            slotSeqAnalyzer = new SlotSeqAnalyzerImpl(this);
        }
        return slotSeqAnalyzer;
    }

    /**
     * 添加词槽
     * @param slot 词槽
     */
    public void addSlot(Slot slot){
        if(slotSeq == null){
            slotSeq = new LinkedList<Slot>();
        }
        slotSeq.add(slot);
    }

    /**
     * 添加词槽
     * @param slot 词槽
     * @param position 词槽位置
     */
    public void addSlot(Slot slot, int position){
        if(slotSeq == null){
            slotSeq = new LinkedList<Slot>();
        }
        slotSeq.add(position, slot);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntentId() {
        return intentId;
    }

    public void setIntentId(String intentId) {
        this.intentId = intentId;
    }

    public String getSeqName() {
        return seqName;
    }

    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSeqType() {
        return seqType;
    }

    public void setSeqType(String seqType) {
        this.seqType = seqType;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public float getSeqPriority() {
        return seqPriority;
    }

    public void setSeqPriority(float seqPriority) {
        this.seqPriority = seqPriority;
    }

    public float getSeqNamePriority() {
        return seqNamePriority;
    }

    public void setSeqNamePriority(float seqNamePriority) {
        this.seqNamePriority = seqNamePriority;
    }

    public List<Slot> getSlotSeq() {
        if(slotSeq == null){
            slotSeq = new LinkedList<Slot>();
        }
        return slotSeq;
    }

    public void setSlotSeq(List<Slot> slotSeq) {
        this.slotSeq = slotSeq;
    }

    public List<SlotSeqRequireElement> getSlotSeqRequireElement() {
        if(slotSeqRequireElement == null){
            slotSeqRequireElement = new LinkedList<SlotSeqRequireElement>();
        }
        return slotSeqRequireElement;
    }

    public void setSlotSeqRequireElement(List<SlotSeqRequireElement> slotSeqRequireElement) {
        this.slotSeqRequireElement = slotSeqRequireElement;
    }
}
