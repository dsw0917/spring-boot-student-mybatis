package com.yxt.controller;

import com.yxt.base.BaseController;
import com.yxt.domain.entity.PageData;
import com.yxt.officialVehicl.core.base.message.JsonInfo;
import com.yxt.service.DeptBindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author yxt06
 * @date 2018/12/12
 */
@RestController
@RequestMapping("bind")
public class DeptBindController extends BaseController {
    @Autowired
    DeptBindService deptBindService;

    /**
     *
     */
    @RequestMapping(value = "/deptList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo deptList() throws Exception{
        PageData pd = this.getPageData();
        List<PageData> list = deptBindService.deptList();
        return new JsonInfo(list);
    }


    @RequestMapping(value = "/buildList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo buildList() throws Exception{
        PageData pd = this.getPageData();
        List<PageData> list = deptBindService.buildList();
        return new JsonInfo(list);
    }

    @RequestMapping(value = "/deptBindRoom", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo deptBindRoom() throws Exception{
        PageData pd = this.getPageData();
        List<PageData> list = deptBindService.deptBindRoom(pd);
        return new JsonInfo(list);
    }

    @RequestMapping(value = "/roomByDept", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo roomByDept() throws Exception{
        PageData pd = this.getPageData();
        List<PageData> list = deptBindService.roomByDept(pd);
        return new JsonInfo(list);
    }
}
