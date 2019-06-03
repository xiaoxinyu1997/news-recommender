package com.meibanlu.qa.analysis.controller;

import com.meibanlu.qa.analysis.entity.vo.AnalysisResult;
import com.meibanlu.qa.analysis.service.IAnalysisService;
import com.meibanlu.qa.analysis.service.IQuestionService;
import com.meibanlu.utils.dto.Resp;
import com.meibanlu.utils.dto.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/question")
@RestController
public class QuestionController {

    private final IAnalysisService analysisService;
    private final IQuestionService questionService;

    @Autowired
    public QuestionController(IAnalysisService analysisService, IQuestionService questionService) {
        this.analysisService = analysisService;
        this.questionService = questionService;
    }

    @GetMapping
    @ResponseBody
    public Resp question(String text, String longitude, String latitude, String userId){
        if(text == null || text.isEmpty()){
            return Resp.error(State.REQUEST_WRONG);
        }
        if(longitude == null || longitude.isEmpty()){
            longitude = "0.0";
        }
        if(latitude == null || latitude.isEmpty()){
            latitude = "0.0";
        }
        Resp analysisResultResp = analysisService.analysis(text, longitude, latitude);
        AnalysisResult analysisResult = (AnalysisResult) analysisResultResp.getData();
        return questionService.question(analysisResult, longitude, latitude, userId);
    }
}
