package com.yxt.controller;

import com.github.pagehelper.Page;
import com.yxt.domain.entity.Person;
import com.yxt.officialVehicl.core.base.message.JsonInfo;
import com.yxt.page.PageInfo;
import com.yxt.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yxt06 on 2018/11/27.
 */
@RestController("user")
public class UserController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/PageforUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public JsonInfo PageforUser(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize){

        return new JsonInfo("aaa");
    }
}
