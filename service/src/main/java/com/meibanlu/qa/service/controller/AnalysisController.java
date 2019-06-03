package com.meibanlu.qa.service.controller;

import com.meibanlu.utils.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/analysis", method = { RequestMethod.GET, RequestMethod.POST })
public class AnalysisController {

    @Autowired
    private RestTemplate restTemplate;
    /**
     * 语义解析URL
     */
    private String URL_QUERY = "http://localhost:30001/qa-analysis/question";

    @RequestMapping("/analysisQuestion")
    @ResponseBody
    public Resp question(HttpServletRequest request, String text, String longitude, String latitude){
        String userId = (String) request.getSession().getAttribute("userId");
        if(userId == null){
            // 默认为1号用户
            userId = "1";
        }
        ResponseEntity<Resp> respEntity = restTemplate.getForEntity(
                URL_QUERY + "?text=" + text + "&userId=" + userId + "&longitude=" + longitude + "&latitude" + latitude,
                Resp.class
        );
        if(HttpStatus.OK.equals(respEntity.getStatusCode()) && respEntity.getBody() != null){
            return respEntity.getBody();
        }else{
            return Resp.error().setMsg("语义解析失败");
        }
    }
}
