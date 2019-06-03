package com.meibanlu.qa.service.controller;
import java.util.List;

import com.meibanlu.utils.MD5UTIL;
import com.meibanlu.utils.dto.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.meibanlu.qa.service.entity.User;
import com.meibanlu.qa.service.service.UserService;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(value = "/UserCRUD", method = { RequestMethod.GET, RequestMethod.POST })
public class UserCRUD {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(int userid) {
        int result = userService.delete(userid);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(User user) {
        int result = userService.Update(user);
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }

    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public User insert(User user) {
        return userService.insertuser(user);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Resp register(String phonenumber, String password) {
        if (findByphonenumber(phonenumber)!=null) {
            return Resp.error().setMsg("用户电话已存在");
        }else{
            password = MD5UTIL.encode(password);
            int result= userService.registertuser(phonenumber,password);
            if (result >= 1) {
                return Resp.success().setMsg("注册成功");
            } else {
                return Resp.error().setMsg("注册失败");
            }
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Resp login(HttpServletRequest request, String phonenumber, String password) {
        User user = findByphonenumber(phonenumber);
        password = MD5UTIL.encode(password);
        if(user != null && user.getPassword().equals(password)){
            request.getSession().setAttribute("userId", String.valueOf(user.getUserid()));
            return Resp.success().setData(user);
        }else{
            return Resp.error();
        }
    }

    @RequestMapping(value = "/findByPhonenumber", method = RequestMethod.GET)
    public User findByphonenumber(String phonenumber){
        return userService.findByphonenumber(phonenumber);
    }



    @RequestMapping("/Listuser")
    @ResponseBody
    public List<User> Listuser(){
        return userService.Listuser();
    }

    @RequestMapping("/findByuserid")
    @ResponseBody
    public List<User> findByuserid(int userid){


        return userService.findByuserid(userid);
    }

    @RequestMapping("/findBynickname")
    @ResponseBody
    public List<User> findBynickname(String nickname){
        return userService.findBynickname(nickname);
    }
}