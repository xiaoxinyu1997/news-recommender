package com.meibanlu.qa.analysis.analyzer.util

import com.baidu.aip.nlp.AipNlp
import com.google.gson.Gson
import com.meibanlu.qa.analysis.entity.vo.LexerBean

/**
 * 分词器
 */
object LexerAnalysis{

    /**
     * 百度分词客户端
     */
    private val client: AipNlp by lazy {
        val instance = AipNlp("11715837", "u1s8PT7Rjy87WRS6AxZz3uGH", "2jgxarpSm8zu7c0eGTdf06nvNvlmlPtk")
        instance.setConnectionTimeoutInMillis(2000)
        instance.setSocketTimeoutInMillis(60000)
        instance
    }

    /**
     * 分词
     * @param text 用户输入的原始文本
     */
    fun lexer(text: String): LexerBean{
        val lexerBean = Gson().fromJson<LexerBean>(
                client.lexerCustom(text, null).toString(),
                LexerBean::class.java
        )
        lexerBean.text = lexerBean.text
        generateTextWithPos(lexerBean)
        generateTextWithBarrier(lexerBean)
        generatePosWithBarrier(lexerBean)
        return lexerBean
    }

    /**
     * 生成带有词性标注的分词结果
     * @param lexerBean 百度分词结果
     */
    private fun generateTextWithPos(lexerBean: LexerBean): LexerBean{
        val textWithPos = lexerBean.items.joinToString(" "){
            "${it.item}[${if(it.pos == null || it.pos.isEmpty()) it.ne else it.pos}]"
        }
        lexerBean.textWithPos = textWithPos
        return lexerBean
    }

    /**
     * 生成分词结果，不带词性标注
     */
    private fun generateTextWithBarrier(lexerBean: LexerBean): LexerBean{
        val textWithBarrier = lexerBean.items.joinToString(" "){
            it.item
        }
        lexerBean.textWithBarrier = textWithBarrier
        return lexerBean
    }

    /**
     * 生成词性序列
     */
    private fun generatePosWithBarrier(lexerBean: LexerBean): LexerBean{
        val posWithBarrier = lexerBean.items.joinToString(" "){
            if(it.pos == null || it.pos.isEmpty()){
                it.ne
            } else {
                it.pos
            }
        }
        lexerBean.posWithBarrier = posWithBarrier
        return lexerBean
    }
}
