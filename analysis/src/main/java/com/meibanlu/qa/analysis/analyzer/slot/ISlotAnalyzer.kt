package com.meibanlu.qa.analysis.analyzer.slot

import com.meibanlu.qa.analysis.entity.ExtractContent
import com.meibanlu.qa.analysis.entity.vo.LexerBean

/**
 * 词槽分析器
 */
interface ISlotAnalyzer {

    /**
     * 计算词槽匹配度
     * @return 词槽匹配度
     */
    fun score(lexerBean: LexerBean): Double

    /**
     * 单词槽内容抽取
     * @return 单词槽内容结果
     */
    fun extract(lexerBean: LexerBean): ExtractContent

    /**
     * 在输入文本中删除已抽取内容对应的部分
     */
    fun stripExtractedContent(lexerBean: LexerBean, extractedContent: ExtractContent){
        val indexRecord = extractedContent.contentItems.maxBy {
            it.indexInRaw
        }?.indexInRaw ?: 0
        lexerBean.textWithBarrier = lexerBean.textWithBarrier.split(" ").asSequence().filterIndexed { index, _ ->
            index > indexRecord
        }.joinToString(" ")
        lexerBean.textWithPos = lexerBean.textWithPos.split(" ").asSequence().filterIndexed { index, _ ->
            index > indexRecord
        }.joinToString(" ")
        lexerBean.posWithBarrier = lexerBean.posWithBarrier.split(" ").asSequence().filterIndexed { index, _ ->
            index > indexRecord
        }.joinToString(" ")
        lexerBean.text = lexerBean.textWithBarrier.replace(" ", "")
    }
}
