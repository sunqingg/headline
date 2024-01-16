package com.sun.qing.service.impl;

import com.sun.qing.dao.NewsHeadLineDao;
import com.sun.qing.dao.NewsUserDao;
import com.sun.qing.dao.impl.NewsHeadlineDaoImpl;
import com.sun.qing.dao.impl.NewsUserDaoImpl;
import com.sun.qing.pojo.vo.HeadlineQueryVo;
import com.sun.qing.service.NewsHeadlineService;

import java.util.Map;

public class NewsHeadlineServiceImpl implements NewsHeadlineService {
    NewsHeadLineDao headlineDao = new NewsHeadlineDaoImpl();
    @Override
    public Map<String, Object> findNewsPage(HeadlineQueryVo queryVo) {
        return headlineDao.findNewsPage(queryVo);
    }
}
