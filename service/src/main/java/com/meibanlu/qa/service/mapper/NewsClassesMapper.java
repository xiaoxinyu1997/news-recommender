package com.meibanlu.qa.service.mapper;

import java.util.List;
import com.meibanlu.qa.service.entity.NewsClasses;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NewsClassesMapper {
    public List<NewsClasses> findclassByclassification(String classification);

    public List<NewsClasses> Listclasses();

    public int insertclasses(NewsClasses classes);

    public int delete(int classid);

    public int Update(NewsClasses classes);

    public List<NewsClasses> getAllClass();
}