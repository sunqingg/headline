package com.sun.qing.service.impl;

import com.sun.qing.dao.NewsTypeDao;
import com.sun.qing.dao.impl.NewsTypeDaoImpl;
import com.sun.qing.service.NewsTypeService;

import java.util.List;

public class NewsTypeServiceImpl implements NewsTypeService {
    NewsTypeDao typeDao = new NewsTypeDaoImpl();
    @Override
    public List findAllTypes() {
        return typeDao.findAllTypes();
    }
}
