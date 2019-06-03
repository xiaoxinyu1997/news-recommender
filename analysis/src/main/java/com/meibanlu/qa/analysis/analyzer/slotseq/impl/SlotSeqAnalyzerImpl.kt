package com.meibanlu.qa.analysis.analyzer.slotseq.impl

import com.meibanlu.qa.analysis.analyzer.slotseq.ISlotSeqAnalyzer
import com.meibanlu.qa.analysis.entity.ExtractContent
import com.meibanlu.qa.analysis.entity.SlotSeq
import com.meibanlu.qa.analysis.entity.SlotSeqRequireElement
import com.meibanlu.qa.analysis.entity.vo.LexerBean
import java.util.*

/**
 * 词槽序分析器
 * @param slotSeq 词槽序
 */
class SlotSeqAnalyzerImpl(private val slotSeq: SlotSeq) : ISlotSeqAnalyzer {

    /**
     * 计算词槽序匹配度
     * @return 词槽序匹配度
     */
    override fun score(lexerBean: LexerBean): Double {
        if(slotSeq.bufScore > -1.0){
//            System.out.println("=============================================")
//            System.out.println("(${this}, ${this.slotSeq})获取分数：${slotSeq.seqName}(${slotSeq.id}) -> ${slotSeq.bufScore}分")
//            System.out.println("=============================================")
            return slotSeq.bufScore
        }
        var score = slotSeq.slotSeq.count {
            it.fetchAnalyzer().score(lexerBean) >= 0.8
        } * 1.0
        score /= if(slotSeq.slotSeq.size > 0) slotSeq.slotSeq.size else 1
//        System.out.println("=============================================")
//        System.out.println("(${this}, ${this.slotSeq})计算分数：${slotSeq.seqName}(${slotSeq.id}) -> ${score}分")
//        System.out.println("=============================================")
        /**
         * 缓存分数
         */
        slotSeq.bufScore = score
        return score
    }

    /**
     * 词槽序内容抽取
     * @return 词槽序内容结果
     */
    override fun extract(lexerBean: LexerBean): ExtractContent? {
        val extractContentSet = slotSeq.slotSeq.map {
            it.fetchAnalyzer().extract(lexerBean)
        }.flatMap {
            it.contentItems
        }
        return if(slotSeq.slotSeqRequireElement.isEmpty()){
            requireElementIsEmpty(extractContentSet)
        }else{
            requireElementIsNotEmpty(extractContentSet)
        }
    }

    /**
     * 没有指定抽取内容
     */
    private fun requireElementIsEmpty(extractContentSet: List<ExtractContent.Item>): ExtractContent?{
        val result = ExtractContent()
        result.domain = slotSeq.domain
        result.requiredMore = ExtractContent.NO_MORE_REQUIRED
        result.contentItems.addAll(extractContentSet)
        return result
    }

    /**
     * 词槽序指定了要抽取的内容
     */
    private fun requireElementIsNotEmpty(extractContentSet: List<ExtractContent.Item>): ExtractContent?{
        val requireMap = HashMap<String, SlotSeqRequireElement>((slotSeq.slotSeqRequireElement.size/0.75).toInt())
        slotSeq.slotSeqRequireElement.forEach {
            requireMap[it.key] = it
        }
        extractContentSet.forEach {
            requireMap[it.key]?.let { element ->
                if(element.slotId != null && element.slotId.isNotEmpty()){
                    if(element.slotId == it.slotId){
                        element.value = it.value
                    }
                }else{
                    element.value = it.value
                }
            }
        }
        val result = ExtractContent()
        result.domain = slotSeq.domain
        result.requiredMore = ExtractContent.NO_MORE_REQUIRED
        val requiredElementSet = slotSeq.slotSeqRequireElement.asSequence().filter {
            it.mustElement() && (it.value == null || it.value.isEmpty())
        }.map {
            val item = ExtractContent.Item()
            item.key = it.key
            item.value = it.value
            item
        }.toList()
        if(requiredElementSet.isNotEmpty()){
            /**
             * 添加需要反问的内容
             */
            result.requiredMore = ExtractContent.MORE_REQUIRED
            result.contentItemsRequiredMore.addAll(requiredElementSet)
        }
        /**
         * 添加成功抽取的内容
         */
        result.contentItems.addAll(
                slotSeq.slotSeqRequireElement.filter {
                    it.value != null && it.value.isNotEmpty()
                }.map {
                    val item = ExtractContent.Item()
                    item.key = it.key
                    item.value = it.value
                    item
                }
        )
        return result
    }
}
