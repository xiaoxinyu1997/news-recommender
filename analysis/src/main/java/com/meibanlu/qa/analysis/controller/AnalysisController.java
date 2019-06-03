package com.meibanlu.qa.analysis.controller;

import com.meibanlu.qa.analysis.service.IAnalysisService;
import com.meibanlu.utils.dto.Resp;
import com.meibanlu.utils.dto.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/analysis")
@RestController
public class AnalysisController {

    private final IAnalysisService analysisService;

    @Autowired
    public AnalysisController(IAnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    @GetMapping
    @ResponseBody
    public Resp analysis(String text, String longitude, String latitude){
        if(text == null || text.isEmpty()){
            return Resp.error(State.REQUEST_WRONG);
        }
        if(longitude == null || longitude.isEmpty()){
            longitude = "0.0";
        }
        if(latitude == null || latitude.isEmpty()){
            latitude = "0.0";
        }
        return analysisService.analysis(text, longitude, latitude);
    }
}
