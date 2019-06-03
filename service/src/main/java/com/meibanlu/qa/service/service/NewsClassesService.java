package com.meibanlu.qa.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.meibanlu.qa.service.mapper.NewsClassesMapper;
import com.meibanlu.qa.service.entity.NewsClasses;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NewsClassesService {
    @Resource
    private NewsClassesMapper classesmapper;

    public List<NewsClasses> findByclassification(String classification) {

        return classesmapper.findclassByclassification(classification);
    }

    public NewsClasses insertclasses(NewsClasses classes) {
        classesmapper.insertclasses(classes);
        return classes;
    }

    public List<NewsClasses> Listclasses(){
        return	classesmapper.Listclasses();
    }


    public int Update(NewsClasses classes){
        return classesmapper.Update(classes);
    }

    public int delete(int classid){
        return classesmapper.delete(classid);
    }
}
