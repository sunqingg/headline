package com.sun.qing.dao;

import com.sun.qing.pojo.vo.HeadlineDetailVo;
import com.sun.qing.pojo.vo.HeadlineQueryVo;

import java.util.List;
import java.util.Map;

public interface NewsHeadLineDao {
    /**
     * 找到相关的文章
     * @param queryVo 搜索条件
     * @return 返回搜索的结果。
     */
    Map<String, Object> findNewsPage(HeadlineQueryVo queryVo);

    /**
     * 通过文章的ID查询出文件的详情。
     * @param hid 文章ID
     * @return 返回map格式封装的文章数据
     */
    HeadlineDetailVo showHeadlineDetail(String hid);
}
