package com.meibanlu.qa.analysis.service.impl

import com.meibanlu.qa.analysis.analyzer.util.LexerAnalysis
import com.meibanlu.qa.analysis.entity.ExtractContent
import com.meibanlu.qa.analysis.entity.vo.AnalysisResult
import com.meibanlu.qa.analysis.service.IAnalysisService
import com.meibanlu.qa.analysis.service.ISlotSeqService
import com.meibanlu.utils.dto.Resp
import com.meibanlu.utils.dto.State
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 分析服务
 */
@Service
class AnalysisServiceImpl : IAnalysisService {

    @Autowired
    private lateinit var slotSeqService: ISlotSeqService

    /**
     * 语义分析
     * @param text      待分析文本
     * @param longitude 经度
     * @param latitude  纬度
     * @return 分析结果
     */
    override fun analysis(text: String, longitude: String, latitude: String): Resp {
        val lexerBean = LexerAnalysis.lexer(text)
        val suitableSeqSet = slotSeqService.fetchSuitableSeq(lexerBean)
        if (suitableSeqSet.isEmpty()) {
            return Resp.error(State.NOT_EXIST).setMsg("未找到合适的词槽序")
        }
//        val mostSuitableSeq = suitableSeqSet.maxBy {
//            it.slotSeq.size
//        }
        val mostSuitableSeq = suitableSeqSet.first()
        if(mostSuitableSeq.bufScore < 0.3){
            return Resp.error().setMsg("没有找到合适的词槽序")
        }
        val extractContent = mostSuitableSeq?.fetchSlotSeqAnalyzer()?.extract(lexerBean) ?: ExtractContent()
        extractContent.action = mostSuitableSeq?.action ?: ""
        val analysisResult = AnalysisResult(extractContent)
        analysisResult.slotSeqId = mostSuitableSeq?.id ?: ""
        analysisResult.slotSeqType = mostSuitableSeq?.seqType ?: ""
        analysisResult.targetUrl = mostSuitableSeq?.targetUrl ?: ""
        analysisResult.rawText = text
        return Resp.success().setData(analysisResult)
    }
}
