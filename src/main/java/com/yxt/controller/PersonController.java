package com.yxt.controller;

import com.github.pagehelper.Page;
import com.yxt.base.BaseController;
import com.yxt.domain.entity.PageData;
import com.yxt.officialVehicl.core.base.message.JsonInfo;
import com.yxt.page.PageInfo;
import com.yxt.service.PersonService;
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
 * @date 2018/12/6
 */
@RestController
@RequestMapping("person")
public class PersonController extends BaseController{
    @Autowired
    PersonService personService;

    @RequestMapping(value = "/save", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo save() throws Exception{
        PageData pd = this.getPageData();
        Boolean bool = personService.save(pd);
        return new JsonInfo(bool);
    }

    @RequestMapping(value = "/pageList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo pageList(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) throws Exception{
        PageData pd = this.getPageData();
        Page<PageData> builds = personService.findByPage(pageNum, pageSize, pd);
        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<PageData> pageInfo = new PageInfo<>(builds);
        return new JsonInfo(pageInfo);
    }


    @RequestMapping(value = "/findByid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo findByid () throws Exception{
        PageData pd = this.getPageData();
        PageData build = personService.findByid((BigInteger) pd.get("floor_id"));
        return new JsonInfo(build);
    }

    @RequestMapping(value = "/update", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo update() throws Exception{
        PageData pd = this.getPageData();
        Boolean bool = personService.update(pd);
        return new JsonInfo(bool);
    }

    @RequestMapping(value = "/deleteByid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo deleteByid() throws Exception{
        PageData pd = this.getPageData();
        Boolean bool  = personService.delete(pd);
        return new JsonInfo(bool);
    }

    @RequestMapping(value = "/pageListByDeptid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo pageListByRid(){
        PageData pd = this.getPageData();
        Page<PageData> page = null;
        try {
            page = personService.pageListByDeptid(pd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonInfo(page);
    }

    @RequestMapping(value = "/personListByDept", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo personListByDept(){
        PageData pd = this.getPageData();
        Page<PageData> page = null;
        try {
            page = personService.personListByDept(pd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonInfo(page);
    }
}
