package com.meibanlu.qa.analysis.service.impl

import com.meibanlu.qa.analysis.dao.SynonymDao
import com.meibanlu.qa.analysis.entity.SlotSeq
import com.meibanlu.qa.analysis.entity.Synonym
import com.meibanlu.qa.analysis.entity.vo.AnalysisResult
import com.meibanlu.qa.analysis.entity.vo.Constence
import com.meibanlu.qa.analysis.service.IQuestionService
import com.meibanlu.utils.dto.Resp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import javax.annotation.Resource

@Service
class QuestionServiceImpl: IQuestionService {

    @Autowired
    private lateinit var restTemplate: RestTemplate
    @Resource
    private lateinit var synonymDao: SynonymDao

    /**
     * 分析并返回查询结果
     * @param analysisResult 分析结果
     * @param longitude 经度
     * @param latitude 纬度
     * @return 查询结果
     */
    override fun question(analysisResult: AnalysisResult, longitude: String, latitude: String, userId: String): Resp {
//        return if(analysisResult.extractContent.moreRequired()){
//            Resp.success().setMsg("还有以下信息需要确认").setData(analysisResult)
//        }else{
//            when(analysisResult.slotSeqType){
//                SlotSeq.TYPE_QUERY_URL -> executeQuery(analysisResult, longitude, latitude)
//                SlotSeq.TYPE_COMMAND -> Resp.success().setMsg("查询完成").setData(analysisResult)
//                else -> Resp.error().setMsg("未找到合适的查询器").setData(analysisResult)
//            }
//        }
        return when(analysisResult.slotSeqType){
            SlotSeq.TYPE_QUERY_URL -> executeQuery(analysisResult, longitude, latitude, userId)
            SlotSeq.TYPE_COMMAND_NAVIGATION -> executePOIQuery(analysisResult, longitude, latitude, userId)
            SlotSeq.TYPE_COMMAND -> Resp.success().setMsg("查询完成").setData(analysisResult)
            SlotSeq.TYPE_COMMAND_ANSWER_INDEX -> executeFetchIndex(analysisResult, longitude, latitude, userId)
            SlotSeq.TYPE_TYPEDNEWS_QUERY_URL -> executeNewsType(analysisResult, longitude, latitude, userId)
            else -> Resp.error().setMsg("未找到合适的查询器").setData(analysisResult)
        }
    }

    /**
     * 执行查询逻辑
     * @param analysisResult 分析结果
     * @param longitude 经度
     * @param latitude 纬度
     * @param userId 用户ID
     * @return 查询结果
     */
    private fun executeQuery(analysisResult: AnalysisResult, longitude: String, latitude: String, userId: String): Resp{
        val queryUrl = "${Constence.URL_QUERY}${analysisResult.targetUrl}?${generateQueryParams(analysisResult)}&longitude=$longitude&latitude=$latitude&userId=$userId"
        val queryResult = restTemplate.getForEntity(queryUrl, String::class.java)
        return if(queryResult.statusCode == HttpStatus.OK){
            analysisResult.queryResultJson = queryResult.body ?: ""
            Resp.success().setMsg("查询完成").setData(analysisResult)
        }else{
            Resp.error().setMsg("查询失败（${queryResult.statusCode}）").setData(analysisResult)
        }
    }

    /**
     * 生成查询参数
     * @param analysisResult 分析结果
     */
    private fun generateQueryParams(analysisResult: AnalysisResult): String{
        val params = analysisResult.extractContent.contentItems.asSequence().map {
            "${it.key}=${it.value}"
        }.joinToString(separator = "&")
        return params
    }

    /**
     * 查找导航目的地
     * @param analysisResult 分析结果
     * @param longitude 经度
     * @param latitude 纬度
     * @param userId 用户ID
     * @return 查询结果
     */
    private fun executePOIQuery(analysisResult: AnalysisResult, longitude: String, latitude: String, userId: String): Resp{
        val address = analysisResult.extractContent?.contentItems?.firstOrNull { it.key == "address" }?.value ?: ""
        if(address.isEmpty()){
            return Resp.error().setMsg("POI查询失败（导航目的地为空}）").setData(analysisResult)
        }
        val queryUrl = "${Constence.URL_POI}?keywords=$address&offset=3&extensions=all&key=b0091735695b24626207c18ee9eef600"
        val queryResult = restTemplate.getForEntity(queryUrl, String::class.java)
        return if(queryResult.statusCode == HttpStatus.OK){
            analysisResult.queryResultJson = queryResult.body?.replace("\"address\":[],", "\"address\":\"\",") ?: ""
            Resp.success().setMsg("POI查询完成").setData(analysisResult)
        }else{
            Resp.error().setMsg("POI查询失败（${queryResult.statusCode}）").setData(analysisResult)
        }
    }

    /**
     * 抽取“第几个”中的数量
     * @param analysisResult 分析结果
     * @param longitude 经度
     * @param latitude 纬度
     * @param userId 用户ID
     * @return 查询结果
     */
    private fun executeFetchIndex(analysisResult: AnalysisResult, longitude: String, latitude: String, userId: String): Resp{
        var targetIndex: String = analysisResult.extractContent?.contentItems?.firstOrNull {
            it.key == "targetIndex"
        }?.value ?: return Resp.error().setMsg("在 第几个 中抽取数量失败").setData(analysisResult)
        val synonymSet = synonymDao.querySynonym("第")
        synonymSet.add(0, Synonym("第", "第"))
        synonymSet.addAll(synonymDao.querySynonym("个"))
        synonymSet.add(0, Synonym("个", "个"))
        val str = synonymSet.joinToString("|") {
            it.synonymWord
        }
        targetIndex = targetIndex.replace(Regex("($str)"), "")
        var index = targetIndex
        if(!(targetIndex > "0" && targetIndex < "9")){
            index = map2Number(targetIndex)
        }
        if(index == "null"){
            return Resp.error().setMsg("在 第几个 中抽取数量失败，index范围不是1-3")
        }
        analysisResult.extractContent?.contentItems?.firstOrNull {
            it.key == "targetIndex"
        }?.value = index
        return Resp.success().setMsg("索引抽取成功").setData(analysisResult)
    }

    /**
     * 抽取“任意类型新闻”中的类型
     * @param analysisResult 分析结果
     * @param longitude 经度
     * @param latitude 纬度
     * @param userId 用户ID
     * @return 查询结果
     */
    private fun executeNewsType(analysisResult: AnalysisResult, longitude: String, latitude: String, userId: String): Resp{
        var keywords: String = analysisResult.extractContent?.contentItems?.firstOrNull {
            it.key == "keywords"
        }?.value ?: return Resp.error().setMsg("在 任意类型新闻 中抽取类型失败").setData(analysisResult)
        if(keywords.endsWith("的新闻")){
            keywords = keywords.dropLast(3)
        }else if(keywords.endsWith("新闻")){
            keywords = keywords.dropLast(2)
        }else if(keywords.endsWith("的")){
            keywords = keywords.dropLast(1)
        }
        analysisResult.extractContent?.contentItems?.firstOrNull {
            it.key == "keywords"
        }?.value = keywords
        return executeQuery(analysisResult, longitude, latitude, userId)
    }

    /**
     * 处理汉字
     */
    private fun map2Number(str: String): String{
        return when(str){
            "一" -> "1"
            "二" -> "2"
            "三" -> "3"
            else -> "null"
        }
    }
}