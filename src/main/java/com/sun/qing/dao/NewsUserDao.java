package com.sun.qing.dao;

import com.sun.qing.pojo.NewsUser;

public interface NewsUserDao {

    NewsUser login(NewsUser user);

    NewsUser findUserByUid(NewsUser user);
}
