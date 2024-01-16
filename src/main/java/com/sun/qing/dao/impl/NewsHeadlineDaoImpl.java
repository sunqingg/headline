package com.sun.qing.dao.impl;

import com.sun.qing.dao.BaseDao;
import com.sun.qing.dao.NewsHeadLineDao;
import com.sun.qing.pojo.NewsHeadline;
import com.sun.qing.pojo.vo.HeadlineDetailVo;
import com.sun.qing.pojo.vo.HeadlinePageVo;
import com.sun.qing.pojo.vo.HeadlineQueryVo;
import com.sun.qing.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NewsHeadlineDaoImpl implements NewsHeadLineDao {
    BaseDao dao = new BaseDao();
    @Override
    public Map<String, Object> findNewsPage(HeadlineQueryVo queryVo) {
//        String sql = "select hid,title,type,page_views pageViews,TIMESTAMPDIFF(HOUR,create_time,NOW()) pastHours,publisher from news_headline where is_deleted=0";
        String sql = "select hid,title,type,page_views pageViews,HOUR(NOW()) - HOUR(create_time) pastHours,publisher from news_headline where is_deleted=0 and type = ? and (title like ? or article like ?)";
        Map<String,Object> map = new HashMap<>();
//        StringBuilder stringBuilder = new StringBuilder("%");
//        stringBuilder.append(queryVo.getKeyWords());
//        stringBuilder.append("%");
//        System.out.println(stringBuffer);
        StringBuilder stringBuilder = new StringBuilder("%" + queryVo.getKeyWords() + "%");
        StringBuilder sqlBuilder = new StringBuilder(sql);

        // 前面是开始，后面是步长。
        sqlBuilder.append("limit ?,?");
//        List argList = new LinkedList();
//        argList.add((queryVo.getPageNum()-1)*queryVo.getPageSize());
//        argList.add(queryVo.getPageSize());
        List<NewsHeadline> list = dao.baseQuery(HeadlinePageVo.class, sqlBuilder.toString(), queryVo.getType(),stringBuilder.toString(),stringBuilder.toString(),(queryVo.getPageNum()-1)*queryVo.getPageSize(),queryVo.getPageSize());
        map.put("pageData",list);
        map.put("pageNum",queryVo.getPageNum());
        int size = list.size();
        int pageSize = queryVo.getPageSize();
        map.put("pageSize",pageSize);
        map.put("totalSize",size);
        map.put("totalPage",size/pageSize);
        return map;
    }

    @Override
    public HeadlineDetailVo showHeadlineDetail(String hid) {
        String sql = """
                SELECT a.hid,a.title,a.article,a.type,b.tname typeName,a.page_views pageViews,HOUR(NOW()) - HOUR(create_time) pastHours
                ,a.publisher,c.nick_name author FROM news_headline a
                JOIN news_type b ON a.type = b.tid
                JOIN news_user c ON a.publisher = c.uid
                where a.hid = ?
                """;
        List<HeadlineDetailVo> headlineDetailVos = dao.baseQuery(HeadlineDetailVo.class, sql, hid);
        return headlineDetailVos.size() >0 ? headlineDetailVos.get(0) : null;
    }
}
