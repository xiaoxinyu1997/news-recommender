package com.meibanlu.qa.analysis.service.impl

import com.meibanlu.qa.analysis.entity.SlotSeq
import com.meibanlu.qa.analysis.entity.vo.LexerBean
import com.meibanlu.qa.analysis.service.IIntentService
import com.meibanlu.qa.analysis.service.ISlotSeqService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SlotSeqServiceImpl : ISlotSeqService {

    @Autowired
    private lateinit var intentService: IIntentService

    /**
     * 查找匹配度最高的词槽序
     * @param lexerBean 用户输入文本分词结果
     * @return 匹配度最高的词槽序集合
     */
    override fun fetchSuitableSeq(lexerBean: LexerBean): List<SlotSeq>? {
        val intentSet = intentService.fetchSuitableIntent(lexerBean)
        var maxScore = Double.MIN_VALUE
        val suitableSlotSeqSet = intentSet.asSequence().flatMap {
            it.slotSeqList.forEach { slotSeq ->
                slotSeq.domain = it.domain
            }
            it.slotSeqList.asSequence()
        }
        return suitableSlotSeqSet.asSequence().sortedByDescending {
            val itemScore = it.fetchSlotSeqAnalyzer().score(lexerBean)
            if(itemScore > maxScore){
                maxScore = itemScore
            }
            itemScore
        }.filter {
            it.bufScore >= maxScore
        }.sortedByDescending {
            it.seqNamePriority
        }.sortedByDescending {
            it.seqPriority
        }.sortedByDescending {
            it.slotSeq.size
        }.toList()
    }
}
