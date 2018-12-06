package com.yxt.controller;

import com.github.pagehelper.Page;
import com.yxt.base.BaseController;
import com.yxt.domain.entity.PageData;
import com.yxt.page.PageInfo;
import com.yxt.service.FloorService;
import com.yxt.officialVehicl.core.base.message.JsonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author yxt06
 * @date 2018/12/4
 */
@RestController
@RequestMapping("floor")
public class FloorController extends BaseController {
    @Autowired
    FloorService floorService;

    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo save() throws Exception{
        PageData pd = this.getPageData();
        Boolean bool = floorService.save(pd);
        return new JsonInfo(bool);
    }

    @RequestMapping(value = "/pageList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JsonInfo pageList(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) throws Exception{
        PageData pd = this.getPageData();
        Page<PageData> builds = floorService.findByPage(pageNum, pageSize, pd);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<PageData> pageInfo = new PageInfo<>(builds);
        return new JsonInfo(pageInfo);
    }


    @RequestMapping(value = "/findByid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JsonInfo findByid () throws Exception{
        PageData pd = this.getPageData();
        PageData build = floorService.findByid((BigInteger) pd.get("floor_id"));
        return new JsonInfo(build);
    }

    @RequestMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JsonInfo update() throws Exception{
        PageData pd = this.getPageData();
        Boolean bool = floorService.update(pd);
        return new JsonInfo(bool);
    }

    @RequestMapping(value = "/deleteByid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JsonInfo deleteByid() throws Exception{
        PageData pd = this.getPageData();
        Boolean bool  = floorService.delete(pd);
        return new JsonInfo(bool);
    }


    @RequestMapping(value = "/findFlistByBid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JsonInfo findFlistByBid() throws Exception{
        PageData pd = this.getPageData();
        List<PageData> list  = floorService.findFlistByBid(pd);
        return new JsonInfo(list);
    }
}
