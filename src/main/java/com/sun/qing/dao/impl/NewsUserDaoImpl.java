package com.sun.qing.dao.impl;

import com.sun.qing.dao.BaseDao;
import com.sun.qing.dao.NewsUserDao;
import com.sun.qing.pojo.NewsUser;
import com.sun.qing.service.NewsUserService;

import java.util.List;

public class NewsUserDaoImpl implements NewsUserDao {
    BaseDao dao = new BaseDao();
    @Override
    public NewsUser login(NewsUser user) {
        String sql = "select uid,username,user_pwd userPwd ,nick_name nickName from news_user where username = ?";
        List<NewsUser> objects = dao.baseQuery(NewsUser.class, sql, user.getUsername());
        return objects.size() >0 ? objects.get(0) : null;

    }

    @Override
    public NewsUser findUserByUid(NewsUser user) {
        String sql = "select uid,username,user_pwd userPwd ,nick_name nickName from news_user where uid = ?";
        List<NewsUser> objects = dao.baseQuery(NewsUser.class, sql, user.getUid());
        return objects.size() >0 ? objects.get(0) : null;
    }
}
