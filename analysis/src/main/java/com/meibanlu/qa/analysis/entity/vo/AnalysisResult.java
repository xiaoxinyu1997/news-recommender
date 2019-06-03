package com.meibanlu.qa.analysis.entity.vo;

import com.meibanlu.qa.analysis.entity.ExtractContent;

/**
 * 分析结果
 */
public class AnalysisResult {
    /**
     * 内容抽取结果
     */
    private ExtractContent extractContent;
    /**
     * 使用的词槽序ID
     */
    private String slotSeqId;
    /**
     * 使用的词槽序规定的解析结果处理方式
     */
    private String slotSeqType;
    /**
     * 目标URL
     */
    private String targetUrl;
    /**
     * 原始输入文本
     */
    private String rawText;
    /**
     * 查询结果json
     */
    private String queryResultJson = "";

    public AnalysisResult() {
    }

    public AnalysisResult(ExtractContent extractContent) {
        this.extractContent = extractContent;
    }

    public ExtractContent getExtractContent() {
        return extractContent;
    }

    public void setExtractContent(ExtractContent extractContent) {
        this.extractContent = extractContent;
    }

    public String getSlotSeqId() {
        return slotSeqId;
    }

    public void setSlotSeqId(String slotSeqId) {
        this.slotSeqId = slotSeqId;
    }

    public String getSlotSeqType() {
        return slotSeqType;
    }

    public void setSlotSeqType(String slotSeqType) {
        this.slotSeqType = slotSeqType;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public String getQueryResultJson() {
        return queryResultJson;
    }

    public void setQueryResultJson(String queryResultJson) {
        this.queryResultJson = queryResultJson;
    }
}
