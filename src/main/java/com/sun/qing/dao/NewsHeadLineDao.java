package com.sun.qing.dao;

import com.sun.qing.pojo.vo.HeadlineQueryVo;

import java.util.Map;

public interface NewsHeadLineDao {
    Map<String, Object> findNewsPage(HeadlineQueryVo queryVo);
}
