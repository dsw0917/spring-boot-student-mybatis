package com.yxt.service;

import com.github.pagehelper.Page;
import com.yxt.domain.entity.PageData;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by yxt06 on 2018/12/4.
 */
public interface FloorService {
    /**
     * 分页查询
     * */
    Page<PageData> findByPage(int pageNo, int pageSize, PageData pd) throws Exception;

    /**
     * 根据主键id 获取记录
     * */
    PageData findByid(BigInteger id) throws Exception;

    /**
     * 修改记录
     * */
    boolean update(PageData pd) throws Exception;

    /**
     * 新增记录
     * */
    boolean save(PageData pd) throws Exception;

    /**
     * 根据主键id 删除记录
     * */
    boolean delete(PageData pd) throws Exception;

    List<PageData> findFlistByBid(PageData pd) throws Exception;
}
