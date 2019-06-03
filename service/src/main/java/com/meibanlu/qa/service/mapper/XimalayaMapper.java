package com.meibanlu.qa.service.mapper;
import java.util.List;
import com.meibanlu.qa.service.entity.Ximalaya;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XimalayaMapper {
    /**
     * 根据新闻ID集合查询喜马拉雅
     */
    List<Ximalaya> queryByIds(List<Integer> newsIds);

    public List<Ximalaya> findximalayaBytitle(String singer, String name, String audioTypeXs);

    public List<Ximalaya> findximalayaByAlbum(String singer, String name, String audioTypeXs);


    public List<Ximalaya> Listximalaya();

    public int insertximalaya(Ximalaya ximalaya);

    public List<Ximalaya> queryBySql(String sql);

    public int delete(int id);

    public int Update(Ximalaya ximalaya);

}
