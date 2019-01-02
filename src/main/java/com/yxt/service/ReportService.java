package com.yxt.service;

import com.github.pagehelper.Page;
import com.yxt.domain.entity.PageData;

import java.util.List;

/**
 * Created by yxt06 on 2018/12/13.
 */
public interface ReportService {

    List<PageData> regionList() throws Exception;

    Page<PageData> buildListByRegionid(PageData pd) throws Exception;

    Page<PageData> roomListByBuildid(PageData pd) throws Exception;
}
