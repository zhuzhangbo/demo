package com.controller;


import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*
    用户注册
     */
    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    @ResponseBody
    public String register(HttpServletRequest request, HttpServletResponse response){
        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            //response.setHeader("Access-Control-Allow-Origin","http://172.17.255.27:8020");
            response.setHeader("Access-Control-Allow-Origin","*");
            System.out.println("register:"+username+" And "+password);
            String i = userService.register(username,password);
            return i;

        }
        catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    /*
    用户登录
     */
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest request,HttpServletResponse response,
                        HttpSession session){
        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            //response.setHeader("Access-Control-Allow-Origin","http://172.17.255.27:8020");
            response.setHeader("Access-Control-Allow-Origin","*");
            //response.setHeader("Access-Control-Allow-Headers", "*");
            //response.setHeader("Access-Control-Allow-Method", "*");
            //response.setHeader("Access-Control-Allow-Credentials","true");
            System.out.println("login:"+username+" And "+password);
            User user = userService.find(username,password);
            if (user == null){
                return "error";
            }else {
                session.setAttribute("user",user);
                return "success";
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    /*
    从session中获取用户的信息
     */
    @RequestMapping(value = "/getUser" , method = RequestMethod.GET)
    @ResponseBody
    public User getUser(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        try{
            //response.setHeader("Access-Control-Allow-Origin","http://172.17.255.27:8020");
            response.setHeader("Access-Control-Allow-Origin","*");
            response.setHeader("Access-Control-Allow-Credentials","true");
            User user = (User) session.getAttribute("user");
            System.out.println("getUser:"+user);
            return user;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
