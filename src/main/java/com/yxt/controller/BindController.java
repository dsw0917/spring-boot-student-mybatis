package com.yxt.controller;

import com.github.pagehelper.Page;
import com.yxt.base.BaseController;
import com.yxt.domain.entity.PageData;
import com.yxt.officialVehicl.core.base.message.JsonInfo;
import com.yxt.service.BindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author yxt06
 * @date 2018/12/7
 */
@RestController
@RequestMapping("bind")
public class BindController extends BaseController{
    @Autowired
    BindService bindService;
    /**
     * 获取分配的管理的机构列表 - 不分页
     */
    @RequestMapping(value = "/deptList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo deptList() throws Exception{

        return null;
    }

    /**
     * 获取机构列表下绑定的房间信息 - 分页
     */
    @RequestMapping(value = "/roomListBydeptid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo roomListBydeptid() throws Exception{
        PageData pd = this.getPageData();
        Page<PageData> page = bindService.roomListBydeptid(pd);
        return new JsonInfo(page);
    }

    /**
     * 获取机构下得人员信息 - 不分页
     */
    @RequestMapping(value = "/personListBydeptid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo personListBydeptid() throws Exception{
        PageData pd = this.getPageData();
        List<PageData> list = bindService.personListBydeptid(pd);
        return new JsonInfo(list);
    }

    /**
     * 获取该房间下得办公人员
     */
    @RequestMapping(value = "/personsByRoom", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo personsByRoom() throws Exception{
        PageData pd = this.getPageData();
        List<PageData> list = bindService.personsByRoom(pd);
        return new JsonInfo(list);
    }

    /**
     * 保存办公人员绑定到办公室
     */
    @RequestMapping(value = "/personBindRoom", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo personBindRoom() throws Exception{
        PageData pd = this.getPageData();
        bindService.personBindRoom(pd);
        return new JsonInfo(true);
    }
}
