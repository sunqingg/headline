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

    /**
     * 添加头条
     *
     * @param newsHeadline 入参
     * @return 返回成功的行数
     */
    @Override
    public int addNewsHeadline(NewsHeadline newsHeadline) {
        String sql = "INSERT INTO news_headline VALUES(DEFAULT,?,?,?,?,0,NOW(),NOW(),0)";
        List<Object> argList = new LinkedList();
        // 标题
        argList.add(newsHeadline.getTitle());
        // 文章
        argList.add(newsHeadline.getArticle());
        // 新闻类型
        argList.add(newsHeadline.getType());
        // 发布者
        argList.add(newsHeadline.getPublisher());
        //列表转为数组 才能配置Object ... args 使用
        Object[] toArray = argList.toArray();
        return dao.baseUpdate(sql, toArray);
    }

    @Override
    public int updateHeadline(NewsHeadline news) {
        String  sql = "update news_headline set title = ?,article = ?,type =? where hid = ?";
        List<Object> list = new LinkedList<>();
        list.add(news.getTitle());
        list.add(news.getArticle());
        list.add(news.getType());
        list.add(news.getHid());
        Object[] array = list.toArray();
        return dao.baseUpdate(sql,array);
    }

    @Override
    public int delHeadline(String hid) {
        String sql = "delete from news_headline where hid = ?";
        return dao.baseUpdate(sql, hid);
    }
}
