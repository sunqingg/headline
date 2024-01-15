package com.sun.qing.service;

import com.sun.qing.pojo.NewsUser;

public interface NewsUserService {
    NewsUser login(NewsUser user);

    NewsUser findUserByUid(NewsUser user);
}
