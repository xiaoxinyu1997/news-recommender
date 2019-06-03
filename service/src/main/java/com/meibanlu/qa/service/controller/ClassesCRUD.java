package com.meibanlu.qa.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.meibanlu.qa.service.entity.NewsClasses;
import com.meibanlu.qa.service.service.NewsClassesService;


@RestController
@RequestMapping(value = "/CalssesCRUD", method = { RequestMethod.GET, RequestMethod.POST })
public class ClassesCRUD {

    @Autowired
    private NewsClassesService classesService;

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(int classid) {
        int result = classesService.delete(classid);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(NewsClasses classes) {
        int result = classesService.Update(classes);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }

    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public NewsClasses insert(NewsClasses classes) {
        return classesService.insertclasses(classes);
    }


    @RequestMapping("/Listclasses")
    @ResponseBody
    public List<NewsClasses> Listclasses(){
        return classesService.Listclasses();
    }

    @RequestMapping("/ListClassesByfication")
    @ResponseBody
    public List<NewsClasses> ListClassesByfication(String fication){


        return classesService.findByclassification(fication);
    }




}