package com.meibanlu.qa.service.mapper;

import com.meibanlu.qa.service.entity.InvertedIndex;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InvertedIndexMapper {
    public void deleteAll() ;

    public void insertAll(List<InvertedIndex> indexlist) ;

    InvertedIndex find(String keyword);
}
