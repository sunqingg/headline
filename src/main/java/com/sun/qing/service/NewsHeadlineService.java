package com.sun.qing.service;

import com.sun.qing.pojo.NewsHeadline;
import com.sun.qing.pojo.vo.HeadlineQueryVo;

import java.util.Map;

public interface NewsHeadlineService {
    Map<String, Object> findNewsPage(HeadlineQueryVo queryVo);

    Map<String, Object> showHeadlineDetail(String hid);

    int addNewsHeadline(NewsHeadline newsHeadline);

    int updateHeadline(NewsHeadline newHeadline);

    int delHeadline(String hid);
}
