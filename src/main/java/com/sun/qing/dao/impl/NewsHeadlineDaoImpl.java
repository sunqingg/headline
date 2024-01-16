package com.sun.qing.dao.impl;

import com.sun.qing.dao.BaseDao;
import com.sun.qing.dao.NewsHeadLineDao;
import com.sun.qing.pojo.NewsHeadline;
import com.sun.qing.pojo.vo.HeadlineQueryVo;
import com.sun.qing.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsHeadlineDaoImpl implements NewsHeadLineDao {
    BaseDao dao = new BaseDao();
    @Override
    public Map<String, Object> findNewsPage(HeadlineQueryVo queryVo) {
//        String sql = "select hid,title,type,page_views pageViews,TIMESTAMPDIFF(HOUR,create_time,NOW()) pastHours,publisher from news_headline where is_deleted=0";
        String sql = "select hid,title,type,page_views pageViews,TIMESTAMPDIFF(HOUR,create_time,NOW()) pastHours,publisher from news_headline where is_deleted=0 and title like ? ";
        Map<String,Object> map = new HashMap<>();
        StringBuffer stringBuffer = new StringBuffer("'%");
        stringBuffer.append(queryVo.getKeyWords());
        stringBuffer.append("%'");
        System.out.println(stringBuffer);
        List<NewsHeadline> list = dao.baseQuery(NewsHeadline.class, sql, stringBuffer.toString());
        map.put("pageData",list);
        map.put("pageNum",1);
        int size = list.size();
        int pageSize = 10;
        map.put("pageSize",pageSize);
        map.put("totalSize",size);
        map.put("totalPage",size/pageSize);
        return map;
    }
}
