package com.meibanlu.qa.service.mapper;
import com.meibanlu.qa.service.entity.Collect;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface CollectMapper {
    public List<Collect> listCollect();

    public int insertCollect(Collect collect);

    /**
     * 按条件查找收藏信息
     * @param userId 用户ID
     * @param collectionType 类型
     * @param typeId 类型音频ID
     */
    List<Collect> queryCollected(int userId, String collectionType, int typeId, String typeTags);

}
