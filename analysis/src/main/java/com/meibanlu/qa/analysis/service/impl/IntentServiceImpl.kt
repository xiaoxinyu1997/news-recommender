package com.meibanlu.qa.analysis.service.impl

import com.meibanlu.qa.analysis.dao.IntentDao
import com.meibanlu.qa.analysis.entity.Intent
import com.meibanlu.qa.analysis.entity.vo.LexerBean
import com.meibanlu.qa.analysis.service.IIntentService
import org.springframework.stereotype.Service
import javax.annotation.Resource

@Service
class IntentServiceImpl : IIntentService {

    @Resource
    private lateinit var intentDao: IntentDao

    /**
     * 获取匹配度最高的意图（未完成）
     * @param lexerBean 用户输入文本分词结果
     * @return 候选意图集合
     */
    override fun fetchSuitableIntent(lexerBean: LexerBean?): MutableList<Intent> {
        return intentDao.listIntent()
    }
}