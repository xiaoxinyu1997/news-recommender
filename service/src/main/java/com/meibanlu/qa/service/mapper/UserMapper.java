package com.meibanlu.qa.service.mapper;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.meibanlu.qa.service.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public List<User> finduserByuserid(int userid);

    public List<User> finduserBynickname(String nickname);

    public User finduserByphonenumber(String phonenumber);

    public List<User> Listuser();

    public int insertuser(User user);

    public int registertuser(String phonenumber ,String password);

    public int delete(int userid);

    public int Update(User user);

    User getUser(int userId);

    List<User> getUserToBeUpdated(Map<String, Timestamp> input);


    void updateUserMusicPreferences(Map<String,Object> input);

    void updateUserNewsPreferences(Map<String, Object> input);
}
