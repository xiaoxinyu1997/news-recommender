package com.meibanlu.qa.analysis.analyzer.slot.impl

import com.meibanlu.qa.analysis.analyzer.slot.ISlotAnalyzer
import com.meibanlu.qa.analysis.dao.SynonymDao
import com.meibanlu.qa.analysis.entity.ExtractContent
import com.meibanlu.qa.analysis.entity.Slot
import com.meibanlu.qa.analysis.entity.Synonym
import com.meibanlu.qa.analysis.entity.vo.LexerBean
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import javax.annotation.Resource

/**
 * 枚举类型词槽分析器
 */
@Component
class EnumSlotAnalyzer : ISlotAnalyzer {

    constructor()

    constructor(slot: Slot) {
        this.slot = slot
    }

    @Resource
    private lateinit var synonymDao: SynonymDao
    companion object {
        lateinit var instance: EnumSlotAnalyzer
    }

    @PostConstruct
    fun init() {
        instance = this
        instance.synonymDao = this.synonymDao
    }
    /**
     * 词槽
     */
    lateinit var slot: Slot

    private val regex: Regex by lazy {
        val mainWord = slot.mainWord
        val synonymSet = instance.synonymDao.querySynonym(mainWord)
//        val synonymSet = arrayListOf<Synonym>()
        synonymSet.add(0, Synonym(mainWord, mainWord))
        val str = synonymSet.joinToString("|") {
            it.synonymWord
        }
        Regex("($str)")
    }

    /**
     * 计算词槽匹配度
     * @return 词槽匹配度
     */
    override fun score(lexerBean: LexerBean): Double {
        /**
         * 获取当前剩下的文本
         */
        val currentText = lexerBean.textWithBarrier.split(" ").joinToString("")
        return if(regex.find(currentText)?.value?.isNotEmpty() == true){
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
        val currentText = lexerBean.textWithBarrier.split(" ").joinToString("")
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
//                    val indexOfNext = lexerBean.textWithBarrier.indexOf(" ", indexOfEnd) + 1
                    val indexOfNext = indexOfEnd
//                    var numOfSpace = 0
//                    for(i in 0 until indexOfNext){
//                        if(lexerBean.textWithBarrier[i] == ' '){
//                            numOfSpace++
//                        }
//                    }
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
                            index >= size0-numOfLeft
                        }.joinToString(" ")
                        val size1 = lexerBean.posWithBarrier.split(" ").size
                        lexerBean.posWithBarrier = lexerBean.posWithBarrier.split(" ").asSequence().filterIndexed { index, _ ->
                            index >= size1-numOfLeft
                        }.joinToString(" ")
                    }
                    lexerBean.text = lexerBean.textWithBarrier.replace(" ", "")
                    item.key = key
                    item.value = strInTextWithBarrier.replace(" ", "")
                    result.addContentItem(item)
                }
            }
        }
//        regex.find(lexerBean.textWithBarrier)?.let { value ->
//            val item = ExtractContent.Item()
//            item.indexInRaw = lexerBean.textWithBarrier.split(" ").indexOf(value.value)
//            if(item.indexInRaw != -1){
//                item.key = key
//                item.value = value.value
//                result.addContentItem(item)
//            }
//        }
//        stripExtractedContent(lexerBean, result)
        return result
    }
}
