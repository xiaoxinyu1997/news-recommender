package com.meibanlu.qa.service.service;

import java.util.LinkedList;
import java.util.List;

import com.meibanlu.qa.service.entity.Audios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meibanlu.qa.service.entity.Zhongguozhisheng;
import com.meibanlu.qa.service.mapper.ZhongguozhishengMapper;

import javax.annotation.Resource;

@Service
public class ZhongguozhishengService {
    @Resource
    private ZhongguozhishengMapper zhongguozhishengMapper;

    public List<Zhongguozhisheng> findBytime(String time) {

        return zhongguozhishengMapper.findZhongguozhishengBytime(time);
    }

    public Zhongguozhisheng insertzhongguozhisheng(Zhongguozhisheng zhongguozhisheng) {
        zhongguozhishengMapper.insertZhongguozhisheng(zhongguozhisheng);
        return zhongguozhisheng;
    }

    public List<Audios> Listzhongguozhisheng() {
        List<Audios> result = new LinkedList<Audios>();
        List<Zhongguozhisheng> zhongguozhishengList = zhongguozhishengMapper.ListZhongguozhisheng();
        if(zhongguozhishengList != null && !zhongguozhishengList.isEmpty()){
            for(Zhongguozhisheng zhongguozhisheng: zhongguozhishengList){
                Audios audios = new Audios();
                audios.setId(zhongguozhisheng.getId());
                audios.settags("中国之声");
                audios.setKuwourl(zhongguozhisheng.getMp3_url());
                audios.setQianqian(zhongguozhisheng.getMp3_url());
                audios.setname(zhongguozhisheng.getTitle());
                audios.setsinger("中国之声");
                audios.setimage("http://n.sinaimg.cn/ent/transform/265/w630h435/20180321/uXME-fyskeue2556735.jpg");
                result.add(audios);
            }
        }
        return result;
    }


    public int Update(Zhongguozhisheng zhongguozhisheng) {
        return zhongguozhishengMapper.Update(zhongguozhisheng);
    }

    public int delete(int id) {
        return zhongguozhishengMapper.delete(id);
    }
}