package com.meibanlu.qa.service.util;

import com.meibanlu.qa.service.entity.Audios;
import com.meibanlu.qa.service.entity.CommendAudios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * 处理Audios的Xcode
 */
public class DealXCode {
    /**
     * 保存xCode集合
     */
    private static ArrayList<String> xcodeList = new ArrayList<String>();

    /**
     * 更新xcode
     */
    public void updateXcode(){
        try {
            xcodeList.clear();
            FileReader fr = new FileReader("/usr/Audios/xcode.txt");
            BufferedReader bf = new BufferedReader(fr);
            String xcode;
            // 按行读取字符串
            while ((xcode = bf.readLine()) != null) {
                if(xcodeList.contains(xcode)){
                    return;
                }
                xcodeList.add(xcode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取xcode
     * @return xcode
     */
    public String fetchXcode(){
        if(xcodeList.isEmpty()){
            updateXcode();
        }
        if(xcodeList.isEmpty()){
            return "";
        }
        int index = new Random().nextInt(xcodeList.size());
        if(index < 0){
            index = index * -1;
        }
        if(index >= xcodeList.size()){
            index = index % xcodeList.size();
        }
        return xcodeList.get(index);
    }

    /**
     * 将xcode放到Audios里
     * @param audios 音频类
     */
    public void fetchXcode(Audios audios){
        String xcode = fetchXcode();
        String qianqianUrl = audios.getQianqian();
        if(qianqianUrl != null && qianqianUrl.contains("xcode=")){
            String url = qianqianUrl.split("xcode=")[0];
            audios.setQianqian(url + "xcode=" + xcode);
        }
    }

    /**
     * 将xcode放到Audios里
     * @param commendAudios 推荐音频类
     */
    public void fetchXcode(CommendAudios commendAudios){
        String xcode = fetchXcode();
        String audioUrl = commendAudios.getPlayurl();
        if(audioUrl != null && audioUrl.contains("xcode=")){
            String url = audioUrl.split("xcode=")[0];
            commendAudios.setPlayurl(url + "xcode=" + xcode);
        }
    }
}
