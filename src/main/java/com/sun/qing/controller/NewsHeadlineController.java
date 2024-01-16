package com.sun.qing.controller;

import com.sun.qing.pojo.NewsHeadline;
import com.sun.qing.pojo.NewsUser;
import com.sun.qing.pojo.vo.HeadlineDetailVo;
import com.sun.qing.service.NewsHeadlineService;
import com.sun.qing.service.NewsUserService;
import com.sun.qing.service.impl.NewsHeadlineServiceImpl;
import com.sun.qing.service.impl.NewsUserServiceImpl;
import com.sun.qing.util.JwtHelper;
import com.sun.qing.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/headline/*")
public class NewsHeadlineController extends BaseController{
    NewsHeadlineService headlineService =  new NewsHeadlineServiceImpl();

    protected void publish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsHeadline newsHeadline = WebUtil.readJson(req, NewsHeadline.class);
//        NewsHeadline newsHeadline = new NewsHeadline();
        String token = req.getHeader("token");
        Long id = JwtHelper.getUserId(token);
//        NewsUser newsUser = new NewsUser();
//        newsUser.setUid(Integer.parseInt(id.toString()) );
//        NewsUser userByUid = userService.findUserByUid(newsUser);
//        // 填充数据
////        newsHeadline.setHid();
//        newsHeadline.setTitle(detailVo.getTitle());
//        newsHeadline.setArticle(detailVo.getArticle());
//        newsHeadline.setType(detailVo.getType());
//        newsHeadline.setPublisher(userByUid.getUid());
//        newsHeadline.setPageViews(0);
//        newsHeadline.setCreateTime();
//    }
        newsHeadline.setPublisher(id.intValue());
        headlineService.addNewsHeadline(newsHeadline);
    }
}
