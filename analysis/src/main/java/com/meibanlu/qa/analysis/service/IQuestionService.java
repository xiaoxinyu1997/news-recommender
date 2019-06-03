package com.meibanlu.qa.analysis.service;

import com.meibanlu.qa.analysis.entity.vo.AnalysisResult;
import com.meibanlu.utils.dto.Resp;

/**
 * 问句响应服务接口
 */
public interface IQuestionService {

    /**
     * 分析并返回查询结果
     * @param analysisResult 分析结果
     * @param longitude 经度
     * @param latitude 纬度
     * @param userId 用户ID
     * @return 查询结果
     */
    Resp question(AnalysisResult analysisResult, String longitude, String latitude, String userId);
}
