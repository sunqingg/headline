package com.sun.qing.dao;

import com.sun.qing.pojo.NewsUser;

public interface NewsUserDao {

    NewsUser findUserByUsername(NewsUser user);

    NewsUser findUserByUid(NewsUser user);

    int regist(NewsUser user);
}
