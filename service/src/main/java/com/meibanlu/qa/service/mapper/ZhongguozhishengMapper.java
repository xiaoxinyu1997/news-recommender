package com.meibanlu.qa.service.mapper;
import java.util.List;
import com.meibanlu.qa.service.entity.Zhongguozhisheng;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZhongguozhishengMapper {
    public List<Zhongguozhisheng> findZhongguozhishengBytime(String time);

    public List<Zhongguozhisheng> ListZhongguozhisheng();

    public int insertZhongguozhisheng(Zhongguozhisheng zhongguozhisheng);

    public int delete(int id);

    public int Update(Zhongguozhisheng zhongguozhisheng);

}
