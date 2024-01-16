package com.sun.qing.dao.impl;

import com.sun.qing.dao.BaseDao;
import com.sun.qing.dao.NewsTypeDao;
import com.sun.qing.pojo.NewsType;
import com.sun.qing.service.NewsTypeService;

import java.util.List;

public class NewsTypeDaoImpl implements NewsTypeDao {
    BaseDao dao = new BaseDao();
    @Override
    public List findAllTypes() {
        String sql = "select  tid,tname from news_type;";
        return dao.baseQuery(NewsType.class, sql);
    }
}
