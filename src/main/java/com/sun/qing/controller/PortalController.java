package com.sun.qing.controller;

import com.sun.qing.common.Result;
import com.sun.qing.pojo.NewsUser;
import com.sun.qing.pojo.vo.HeadlineQueryVo;
import com.sun.qing.service.NewsHeadlineService;
import com.sun.qing.service.NewsTypeService;
import com.sun.qing.service.impl.NewsHeadlineServiceImpl;
import com.sun.qing.service.impl.NewsTypeServiceImpl;
import com.sun.qing.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/portal/*")
public class PortalController extends BaseController{
    NewsTypeService typeService = new NewsTypeServiceImpl();
    NewsHeadlineService headlineService = new NewsHeadlineServiceImpl();
    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List list = new ArrayList<>();
        List list =  typeService.findAllTypes();
        WebUtil.writeJson(resp, Result.ok(list));
    }


    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HeadlineQueryVo queryVo = WebUtil.readJson(req, HeadlineQueryVo.class);
        Map<String,Object> pageInfo = headlineService.findNewsPage(queryVo);
        Map<String,Object> map = new HashMap<>();
        map.put("pageInfo",pageInfo);
        WebUtil.writeJson(resp,Result.ok(map));
    }

    protected void showHeadlineDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String hid = req.getParameter("hid");
        Map<String,Object> headlineDetailMap = headlineService.showHeadlineDetail(hid);
        WebUtil.writeJson(resp,Result.ok(headlineDetailMap));
    }
}
