package com.meibanlu.qa.service.controller;

import com.meibanlu.qa.service.util.TTSHelper;
import com.meibanlu.qa.service.util.audio.AudioUtil;
import com.meibanlu.utils.dto.Resp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 语音合成接口
 */
@RestController
@RequestMapping(value = "/tts", method = { RequestMethod.GET, RequestMethod.POST })
public class TTSController {

    private TTSHelper ttsHelper = new TTSHelper();

    /**
     * 新闻语音合成
     * @param newsId 新闻ID
     * @param abs 摘要
     */
    @RequestMapping("/generateAudio")
    @ResponseBody
    public Resp question(String newsId, String abs) throws Exception {
        if(abs == null || abs.isEmpty()){
            return Resp.error().setData(newsId + "新闻摘要为空");
        }
        String result = ttsHelper.tts(newsId, abs);
        if(result == null || result.startsWith("0-")){
            return Resp.error().setData("文字转语音失败").setMsg(result);
        }else{
            String wavPath = result.replace(".pcm", ".wav");
            AudioUtil.convertPCM2WAV(result, wavPath);
            return Resp.success().setData(wavPath);
        }
    }
}
