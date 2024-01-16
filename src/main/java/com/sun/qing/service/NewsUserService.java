package com.sun.qing.service;

import com.sun.qing.pojo.NewsUser;

public interface NewsUserService {
    NewsUser findUserByUsername(NewsUser user);

    NewsUser findUserByUid(NewsUser user);

    NewsUser checkUserName(NewsUser user);

    int regist(NewsUser user);
}
