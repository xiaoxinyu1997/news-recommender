package com.meibanlu.qa.analysis.analyzer.slot.impl

import com.meibanlu.qa.analysis.analyzer.slot.ISlotAnalyzer
import com.meibanlu.qa.analysis.entity.ExtractContent
import com.meibanlu.qa.analysis.entity.Slot
import com.meibanlu.qa.analysis.entity.vo.LexerBean
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

/**
 * 正则表达式类型词槽分析器
 */
@Component
class RegexSlotAnalyzer: ISlotAnalyzer {

    constructor()

    constructor(slot: Slot) {
        this.slot = slot
    }

    companion object {
        lateinit var instance: RegexSlotAnalyzer
    }

    @PostConstruct
    fun init() {
        instance = this
    }
    /**
     * 词槽
     */
    lateinit var slot: Slot

    private val regex: Regex by lazy {
        Regex(slot.patternMatcher)
    }

    /**
     * 计算词槽匹配度
     * @return 词槽匹配度
     */
    override fun score(lexerBean: LexerBean): Double {
        return if(regex.find(lexerBean.text)?.value?.isNotEmpty() == true){
            1.0
        }else{
            0.0
        }
    }

    /**
     * 单词槽内容抽取
     * @return 单词槽内容结果
     */
    override fun extract(lexerBean: LexerBean): ExtractContent {
        val result = ExtractContent()
        val key = slot.matcherKey
        /**
         * 获取当前剩下的文本
         */
        val currentText = lexerBean.text
        regex.find(currentText)?.let { value ->
            if(lexerBean.textWithBarrier.split(" ").contains(value.value)){
                val item = ExtractContent.Item()
                item.indexInRaw = lexerBean.textWithBarrier.split(" ").indexOf(value.value)
                if(item.indexInRaw != -1){
                    item.key = key
                    item.value = value.value
                    result.addContentItem(item)
                }
                stripExtractedContent(lexerBean, result)
            }else{
                /**
                 * 处理分词不准确的情况
                 */
                val item = ExtractContent.Item()
                var re = ""
                val buf = value.value.split("").filter {
                    it.isNotEmpty()
                }
                buf.forEachIndexed{ index, st ->
                    re += st
                    if(index != buf.size-1){
                        re += "\\s?"
                    }
                }
                val strInTextWithBarrier = Regex(re).find(lexerBean.textWithBarrier)?.value
                if(strInTextWithBarrier != null){
                    val indexOfEnd = lexerBean.textWithBarrier.indexOf(strInTextWithBarrier) + strInTextWithBarrier.length
                    val indexOfNext = lexerBean.textWithBarrier.indexOf(" ", indexOfEnd) + 1
                    if(indexOfNext == 0){
                        lexerBean.textWithBarrier = ""
                        lexerBean.textWithPos = ""
                        lexerBean.posWithBarrier = ""
                        lexerBean.text = ""
                    }else{
                        lexerBean.textWithBarrier = lexerBean.textWithBarrier.substring(indexOfNext)
                        val numOfLeft = lexerBean.textWithBarrier.count { it == ' ' } + 1
                        val size0 = lexerBean.textWithPos.split(" ").size
                        lexerBean.textWithPos = lexerBean.textWithPos.split(" ").asSequence().filterIndexed { index, _ ->
                            index >= size0-numOfLeft+1
                        }.joinToString(" ")
                        val size1 = lexerBean.posWithBarrier.split(" ").size
                        lexerBean.posWithBarrier = lexerBean.posWithBarrier.split(" ").asSequence().filterIndexed { index, _ ->
                            index >= size1-numOfLeft+1
                        }.joinToString(" ")
                    }
                    lexerBean.text = lexerBean.textWithBarrier.replace(" ", "")
                    item.key = key
                    item.value = strInTextWithBarrier.replace(" ", "")
                    result.addContentItem(item)
                }
            }
        }
        return result
    }
}
