package com.meibanlu.qa.analysis.dao;

import com.meibanlu.qa.analysis.entity.Synonym;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SynonymDao {

    /**
     * 插入同义词
     * @param mainWord 主词
     * @param synonymWord 同义词
     */
    void insertSynonym(String mainWord, String synonymWord);

    /**
     * 根据主词查询同义词列表
     * @param mainWord 主词
     */
    List<Synonym> querySynonym(String mainWord);
}
