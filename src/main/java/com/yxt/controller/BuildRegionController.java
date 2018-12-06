package com.yxt.controller;

import com.yxt.domain.entity.PageData;
import com.yxt.service.BuildRegionService;
import com.yxt.officialVehicl.core.base.message.JsonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author duanyong
 * @date 2018/12/3
 */
@RestController
@RequestMapping("buildRegion")
public class BuildRegionController {
    @Autowired
    BuildRegionService regionService;

    @RequestMapping(value = "queryRegionList")
    public JsonInfo queryRegionList() throws Exception{
        List<PageData> jsonInfo = regionService.regionList();
        return new JsonInfo(jsonInfo);
    }
}
