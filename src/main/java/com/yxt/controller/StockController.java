package com.yxt.controller;

import com.yxt.officialVehicl.core.base.message.JsonInfo;
import com.yxt.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuhao.wang
 */
@RestController("stock")
public class StockController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "stock", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JsonInfo stock(long id) {
        //int result = personService.updateAge(id);
        return new JsonInfo("");
    }
}