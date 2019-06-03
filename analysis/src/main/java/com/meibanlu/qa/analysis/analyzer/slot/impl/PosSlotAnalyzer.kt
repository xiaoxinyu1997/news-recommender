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
 * 词性类型词槽分析器
 */
@Component
class PosSlotAnalyzer : ISlotAnalyzer {

    constructor()

    constructor(slot: Slot) {
        this.slot = slot
    }

    @Resource
    private lateinit var synonymDao: SynonymDao
    companion object {
        lateinit var instance: PosSlotAnalyzer
    }

    @PostConstruct
    fun init() {
        instance = this
        instance.synonymDao = this.synonymDao
    }
    /**
     * 词槽
     */
    private lateinit var slot: Slot

//    private var tagIndex = -1
//    private var labelStartIndex = -1
//    private var labelEndIndex = -1

    private val regex: Regex by lazy {
//        tagIndex = slot.patternMatcher.indexOf("@")
//        labelStartIndex = slot.patternMatcher.indexOf("<")
//        labelEndIndex = slot.patternMatcher.indexOf(">", labelStartIndex)
//        val mainWord = slot.patternMatcher.substring(tagIndex + 1, labelStartIndex)
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
        return if(regex.find(lexerBean.posWithBarrier)?.value?.isNotEmpty() == true){
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
//        if(labelEndIndex == labelStartIndex + 1){
//            return result
//        }
//        val key = slot.patternMatcher.substring(labelStartIndex + 1, labelEndIndex)
        val key = slot.matcherKey
        regex.find(lexerBean.posWithBarrier)?.let { value ->
            val item = ExtractContent.Item()
            item.indexInRaw = lexerBean.posWithBarrier.split(" ").indexOf(value.value)
            if(item.indexInRaw != -1){
                item.key = key
                item.value = lexerBean.textWithBarrier.split(" ")[item.indexInRaw]
                result.addContentItem(item)
            }
        }
        stripExtractedContent(lexerBean, result)
        return result
    }
}
