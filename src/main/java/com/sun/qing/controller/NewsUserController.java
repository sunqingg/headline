package com.sun.qing.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.qing.common.Result;
import com.sun.qing.common.ResultCodeEnum;
import com.sun.qing.pojo.NewsUser;
import com.sun.qing.service.NewsUserService;
import com.sun.qing.service.impl.NewsUserServiceImpl;
import com.sun.qing.util.JwtHelper;
import com.sun.qing.util.MD5Util;
import com.sun.qing.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class NewsUserController extends BaseController{
    NewsUserService userService =  new NewsUserServiceImpl();
    ObjectMapper objectMapper= new ObjectMapper();

    Result result = null;
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsUser user = WebUtil.readJson(req, NewsUser.class);
        NewsUser newsUser =  userService.findUserByUsername(user);
        if (newsUser != null){
            if (newsUser.getUserPwd().equals(MD5Util.encrypt(user.getUserPwd()))) {
                Map<String,Object> data = new HashMap<>();
//                String token = JwtHelper.createToken(newsUser.getUid().longValue());
                String token = JwtHelper.createToken(newsUser.getUid().longValue());
                data.put("token", token);
                result = Result.ok(data);
            }else {
                result = Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
            }
        }else {
            result = Result.build(null,ResultCodeEnum.USERNAME_ERROR);
        }
        WebUtil.writeJson(resp,result);
    }

    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");
        Long userId = JwtHelper.getUserId(token);
        NewsUser user = new NewsUser();
        if (userId != null) {
            user.setUid(Integer.parseInt(userId.toString()) );
//            user = objectMapper.readValue(userId.toString(), NewsUser.class);
        }
        NewsUser newsUser = userService.findUserByUid(user);
        Map<String,Object> map = new HashMap<>();
        map.put("loginUser",newsUser);
        WebUtil.writeJson(resp,Result.ok(map));
    }

    protected void checkUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        // 只能转json字符串,直接转username=xx是会报错的.

//        NewsUser user =  new NewsUser();
//        user.setUsername(username);
//        NewsUser newsUser = userService.checkUserName(user);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",username);
        String jsonString = jsonObject.toJSONString();
        NewsUser user = objectMapper.readValue(jsonString, NewsUser.class);
        NewsUser newsUser = userService.checkUserName(user);
        if (newsUser == null){
            WebUtil.writeJson(resp,Result.ok(null));
        }else {
            WebUtil.writeJson(resp,Result.build(null,ResultCodeEnum.USERNAME_USED));
        }
    }


    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsUser user = WebUtil.readJson(req, NewsUser.class);
        int num = userService.regist(user);
        if (num >0 ){
            WebUtil.writeJson(resp,Result.ok(null));
        }else {
            WebUtil.writeJson(resp,Result.build(null,ResultCodeEnum.USERNAME_USED));
        }
    }


    protected void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");
        boolean expiration = JwtHelper.isExpiration(token);
        if (!expiration && token != null){
            WebUtil.writeJson(resp,Result.ok(null));
        }else{
            WebUtil.writeJson(resp,Result.build(null,ResultCodeEnum.NOTLOGIN));
        }
    }
}
