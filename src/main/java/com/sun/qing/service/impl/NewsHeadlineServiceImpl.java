package com.sun.qing.service.impl;

import com.sun.qing.dao.NewsHeadLineDao;
import com.sun.qing.dao.NewsUserDao;
import com.sun.qing.dao.impl.NewsHeadlineDaoImpl;
import com.sun.qing.dao.impl.NewsUserDaoImpl;
import com.sun.qing.pojo.NewsHeadline;
import com.sun.qing.pojo.vo.HeadlineDetailVo;
import com.sun.qing.pojo.vo.HeadlineQueryVo;
import com.sun.qing.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsHeadlineServiceImpl implements NewsHeadlineService {
    NewsHeadLineDao headlineDao = new NewsHeadlineDaoImpl();
    @Override
    public Map<String, Object> findNewsPage(HeadlineQueryVo queryVo) {
        return headlineDao.findNewsPage(queryVo);
    }

    @Override
    public Map<String, Object> showHeadlineDetail(String hid) {
        HeadlineDetailVo headlineDetailVos = headlineDao.showHeadlineDetail(hid);
        HashMap<String, Object> map = new HashMap<>();
        map.put("headline",headlineDetailVos);
        return map;
    }

    @Override
    public int addNewsHeadline(NewsHeadline newsHeadline) {
        return headlineDao.addNewsHeadline(newsHeadline);
    }

    @Override
    public int updateHeadline(NewsHeadline newHeadline) {
        return headlineDao.updateHeadline(newHeadline);
    }

    @Override
    public int delHeadline(String hid) {
        return headlineDao.delHeadline(hid);
    }
}
