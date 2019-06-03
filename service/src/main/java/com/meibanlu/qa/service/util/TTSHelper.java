package com.meibanlu.qa.service.util;

import com.iflytek.cloud.speech.*;

/**
 * 语音合成帮助类
 */
public class TTSHelper {

    /**
     * 语音合成
     * @param text 待合成文本
     * @return 语音文件路径
     */
    public String tts(String name, String text){
        //合成监听器
        SynthesizeToUriListener synthesizeToUriListener = XunfeiLib.getSynthesize();
        String fileName = XunfeiLib.getFileName("tts_" + name + ".pcm");
        XunfeiLib.delDone(fileName);
        XunfeiLib.delError(fileName);
        //1.创建SpeechSynthesizer对象
        SpeechSynthesizer mTts= SpeechSynthesizer.createSynthesizer( );
        //2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyuan");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速，范围0~100
        mTts.setParameter(SpeechConstant.PITCH, "50");//设置语调，范围0~100
        mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围0~100
        //3.开始合成
        //设置合成音频保存位置（可自定义保存位置），默认保存在“./tts_test.pcm”
        mTts.synthesizeToUri(text, fileName, synthesizeToUriListener);
        //设置最长时间
        int timeOut=30;
        int star=0;
        //校验文件是否生成
        while(!XunfeiLib.checkDone(fileName)){
            try {
                Thread.sleep(1000);
                star++;
                if(star>timeOut){
                    throw new Exception("合成超过"+timeOut+"秒！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
        if(XunfeiLib.checkError(fileName)){
            return fileName;
        }else{
            return XunfeiLib.fetchError(fileName);
        }
    }
}
