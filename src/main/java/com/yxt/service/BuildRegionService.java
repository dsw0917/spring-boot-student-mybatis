package com.yxt.service;

import com.yxt.domain.entity.PageData;

import java.util.List;

/**
 *
 * @author yxt06
 * @date 2018/12/3
 */
public interface BuildRegionService {
    /**
     * 获取行政区划列表
    **/
    List<PageData> regionList() throws Exception;
}
