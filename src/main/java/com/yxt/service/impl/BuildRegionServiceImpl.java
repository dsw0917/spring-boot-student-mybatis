package com.yxt.service.impl;

import com.github.pagehelper.Page;
import com.yxt.dao.DaoSupport;
import com.yxt.domain.entity.PageData;
import com.yxt.service.BuildRegionService;
import com.yxt.officialVehicl.core.base.constants.BaseConstants;
import com.yxt.officialVehicl.core.base.message.JsonInfo;
import com.yxt.officialVehicl.core.util.UrlInfoFactory;
import com.yxt.officialVehicl.management.dept.entity.Dept;
import com.yxt.officialVehicl.management.resource.entity.ResourceRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yxt06
 * @date 2018/12/3
 */
@Service
public class BuildRegionServiceImpl implements BuildRegionService {
    @Autowired
    private DaoSupport dao;


    @Override
    public List<PageData> regionList() throws Exception{
        List<PageData> lists = new ArrayList<>();
        ResourceRule r = UrlInfoFactory.get("region");
        UrlInfoFactory.print();
        int i = r.getResourceRule();
        if(i == BaseConstants.RESOURCE_RULE_ALL){
            lists = (List<PageData>) dao.findForList("RegionMapper.findList", null);
        }else if(i == BaseConstants.RESOURCE_RULE_DEPT || i == BaseConstants.RESOURCE_RULE_DEPT_SUB){
            lists  = (List<PageData>) dao.findForList("RegionMapper.findListByDept", r.getDeptIds().toString());
        }else if(i == BaseConstants.RESOURCE_RULE_REGION|| i == BaseConstants.RESOURCE_RULE_REGION_SUB){
            lists  = (List<PageData>) dao.findForList("RegionMapper.findListByRegion", r.getRegionIds().toString());
        }else if(i == BaseConstants.RESOURCE_RULE_DEPT_CUSTOM){
            lists  = (List<PageData>) dao.findForList("RegionMapper.findListByDept", r.getCustomRule());
        }else if(i == BaseConstants.RESOURCE_RULE_REGION_CUSTOM){
            lists  = (List<PageData>) dao.findForList("RegionMapper.findListByRegion", r.getCustomRule());
        }
        return lists;
    }
}
