package com.meibanlu.qa.service.mapper;


import com.meibanlu.qa.service.entity.User;
import com.meibanlu.qa.service.entity.UserAudiosBehavior;
import com.meibanlu.qa.service.entity.UserNewsBehavior;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserNewsBehaviorMapper {
    List<UserNewsBehavior> getDurationUserBehavior(Map<String, Timestamp> input);

    void updateAllUserNewsBehavior(List<User> toBeUpdatedData);

    List<UserNewsBehavior> getUserNewsBehaviorByUserId(int userId);

    int shift(@Param("userid")int userid, @Param("originalLength")long originalLength, @Param("durationOfPlay")long durationOfPlay, @Param("newsid")int newsid);
}
