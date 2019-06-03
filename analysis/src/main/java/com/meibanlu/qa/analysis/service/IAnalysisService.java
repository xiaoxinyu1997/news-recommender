package com.meibanlu.qa.analysis.service;

import com.meibanlu.utils.dto.Resp;

/**
 * 分析服务接口
 */
public interface IAnalysisService {

    /**
     * 分析
     * @param text 待分析文本
     * @param longitude 经度
     * @param latitude 纬度
     * @return 分析结果
     */
    Resp analysis(String text, String longitude, String latitude);
}
