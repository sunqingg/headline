package com.sun.qing.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        NewsUser newsUser =  userService.login(user);
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
}
