package com.sun.qing.service.impl;

import com.sun.qing.dao.NewsUserDao;
import com.sun.qing.dao.impl.NewsUserDaoImpl;
import com.sun.qing.pojo.NewsUser;
import com.sun.qing.service.NewsUserService;
import com.sun.qing.util.JwtHelper;

public class NewsUserServiceImpl implements NewsUserService {
    NewsUserDao userDao = new NewsUserDaoImpl();
    @Override
    public NewsUser login(NewsUser user) {
        NewsUser newsUser =  userDao.login(user);
        return newsUser;
    }

    @Override
    public NewsUser findUserByUid(NewsUser user) {
        NewsUser newsUser =userDao.findUserByUid(user);
        return newsUser;
    }
}
