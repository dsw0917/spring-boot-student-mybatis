package com.yxt.controller;

import com.github.pagehelper.Page;
import com.yxt.base.BaseController;
import com.yxt.domain.entity.PageData;
import com.yxt.officialVehicl.core.base.message.JsonInfo;
import com.yxt.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author yxt06
 * @date 2018/12/13
 */
@RestController
@RequestMapping("report")
public class ReportController extends BaseController {
    @Autowired
    ReportService reportService;

    /**
     * 获取当前用户可视的区划列表
     */
    @RequestMapping(value = "/regionList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo deptList() throws Exception{
        PageData pd = this.getPageData();
        List<PageData> list = reportService.regionList();
        return new JsonInfo(list);
    }


    /**
     * 根据区划 region_id 获取超标的大楼列表
     */
    @RequestMapping(value = "/buildListByRegionid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo buildListByRegionid() throws Exception{
        PageData pd = this.getPageData();
        Page<PageData> list = reportService.buildListByRegionid(pd);
        return new JsonInfo(list);
    }

    /**
     * 根据大楼 build_id 获取超标的房间列表
     */
    @RequestMapping(value = "/roomListByBuildid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo roomListByBuildid() throws Exception{
        PageData pd = this.getPageData();
        Page<PageData> list = reportService.roomListByBuildid(pd);
        return new JsonInfo(list);
    }
}
