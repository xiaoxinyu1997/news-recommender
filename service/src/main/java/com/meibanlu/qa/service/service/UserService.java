package com.meibanlu.qa.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meibanlu.qa.service.entity.User;
import com.meibanlu.qa.service.mapper.UserMapper;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public List<User> findByuserid(int userid) {
        return userMapper.finduserByuserid(userid);
    }

    public List<User> findBynickname(String nickname) {

        return userMapper.finduserBynickname(nickname);
    }

    public User insertuser(User user) {
        userMapper.insertuser(user);
        return user;
    }

    public int registertuser(String phonenumber,String password) {
       return userMapper.registertuser(phonenumber,password);
    }

    public User findByphonenumber(String phonenumber) {

        return userMapper.finduserByphonenumber(phonenumber);
    }


    public List<User> Listuser(){
        return	userMapper.Listuser();
    }


    public int Update(User user){
        return userMapper.Update(user);
    }

    public int delete(int newsid){
        return userMapper.delete(newsid);
    }
}
