package com.meibanlu.qa.analysis

import com.meibanlu.qa.analysis.analyzer.slot.impl.EnumSlotAnalyzer
import com.meibanlu.qa.analysis.analyzer.util.LexerAnalysis
import com.meibanlu.qa.analysis.entity.ExtractContent
import com.meibanlu.qa.analysis.entity.Slot
import com.meibanlu.qa.analysis.entity.SlotSeq
import com.meibanlu.qa.analysis.entity.SlotSeqRequireElement
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

//@RunWith(SpringRunner::class)
//@SpringBootTest
class AnalysisTests {

    @Test
    fun testEnumIndex(){
        val str = "播放下一首是的音乐阿斯顿"
        var strWithBarrir = "播算法放 下是发放 一首是的 音乐阿斯顿"
        val target = "发放 一首"
//        val regex: Regex = Regex("是\\s?的\\s?音\\s?乐\\s?阿")
        var re = ""
        val buf = target.split("").filter {
            it.isNotEmpty()
        }
        buf.forEachIndexed{ index, st ->
            re += st
            if(index != buf.size-1){
                re += "\\s?"
            }
        }
        val regex: Regex = Regex(
                re
        )
        val strInTextWithBarrier = regex.find(strWithBarrir)?.value
        if(strInTextWithBarrier != null){
            val indexOfEnd = strWithBarrir.indexOf(strInTextWithBarrier) + strInTextWithBarrier.length
            val indexOfNext = strWithBarrir.indexOf(" ", indexOfEnd) + 1
//            var numOfSpace = 0
//            for(i in 0 until indexOfNext){
//                if(strWithBarrir[i] == ' '){
//                    numOfSpace++
//                }
//            }
//            strWithBarrir = strWithBarrir.split(" ").asSequence().filterIndexed { index, _ ->
//                index >= numOfSpace
//            }.joinToString(" ")
            if(indexOfNext == 0){
                strWithBarrir = ""
            }else{
                strWithBarrir = strWithBarrir.substring(indexOfNext)
            }
        }
        val k = 0
    }

    @Test
    fun testSlot() {
        val text = "给我播放一首音乐"
        val slot = Slot()
        slot.type = Slot.TYPE_ENUM
        slot.patternMatcher = "#我<主语>"
        val enumSlotAnalyzer = EnumSlotAnalyzer(slot)
        val lexerResult = LexerAnalysis.lexer(text)
        val score = enumSlotAnalyzer.score(lexerResult)
        val extractContent = enumSlotAnalyzer.extract(lexerResult)
        val i = 0
    }

    @Test
    fun testSlotSeq(){
        val text = "给我播放一首音乐，好听的"
        val lexerResult = LexerAnalysis.lexer(text)
        val slotSeq = SlotSeq()
        val slot0 = Slot()
        slot0.type = Slot.TYPE_ENUM
        slot0.patternMatcher = "#我<主语>"
        slotSeq.slotSeq.add(slot0)
        val slot1 = Slot()
        slot1.type = Slot.TYPE_POS
        slot1.patternMatcher = "@v<动作>"
        slotSeq.slotSeq.add(slot1)
        val slot2 = Slot()
        slot2.type = Slot.TYPE_ENUM
        slot2.patternMatcher = "#流行<类型>"
        slotSeq.slotSeq.add(slot2)
        val slot3 = Slot()
        slot3.type = Slot.TYPE_ENUM
        slot3.patternMatcher = "#音乐<目标>"
        slotSeq.slotSeq.add(slot3)
        /**
         * 添加词槽序内容抽取要求
         */
//        val requirement0 = SlotSeqRequireElement()
//        requirement0.must = SlotSeqRequireElement.FLAG_MUST
//        requirement0.key = "类型"
//        slotSeq.slotSeqRequireElement.add(requirement0)
//        val requirement1 = SlotSeqRequireElement()
//        requirement1.must = SlotSeqRequireElement.FLAG_MUST
//        requirement1.key = "目标"
//        slotSeq.slotSeqRequireElement.add(requirement1)
        val score = slotSeq.fetchSlotSeqAnalyzer().score(lexerResult)
        val extractContent = slotSeq.fetchSlotSeqAnalyzer().extract(lexerResult)
        val i = 0
    }
}
