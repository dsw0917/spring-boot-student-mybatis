package com.yxt.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yxt.dao.DaoSupport;
import com.yxt.domain.entity.PageData;
import com.yxt.officialVehicl.core.base.constants.BaseConstants;
import com.yxt.officialVehicl.core.util.UrlInfoFactory;
import com.yxt.officialVehicl.management.resource.entity.ResourceRule;
import com.yxt.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 * @author yxt06
 * @date 2018/12/13
 */
public class ReportServiceImpl implements ReportService {
    @Autowired
    private DaoSupport dao;

    @Override
    public List<PageData> regionList() throws Exception {
        List<PageData> list = null;
        ResourceRule r = UrlInfoFactory.get("dept");
        UrlInfoFactory.print();
        int i = r.getResourceRule();
        if(i == BaseConstants.RESOURCE_RULE_ALL){
            list = (List<PageData>) dao.findForList("ReportMapper.findAllRegion", null);
        }else if(i == BaseConstants.RESOURCE_RULE_REGION|| i == BaseConstants.RESOURCE_RULE_REGION_SUB){
            List strs = r.getRegionIds();
            list = (Page<PageData>) dao.findForList("ReportMapper.findListByRegion", strs.toString());
        }else if(i == BaseConstants.RESOURCE_RULE_REGION_CUSTOM){
            list = (Page<PageData>) dao.findForList("ReportMapper.findListByRegion", r.getCustomRule());
        }
        return list;
    }

    @Override
    public Page<PageData> buildListByRegionid(PageData pd) throws Exception {
        PageHelper.startPage((int)pd.get("pageNo"), (int)pd.get("pageSize"));
        Page<PageData> page = new Page<>();
        page = (Page<PageData>) dao.findForList("ReportMapper.buildListByRegionid", pd);
        return page;
    }

    @Override
    public Page<PageData> roomListByBuildid(PageData pd) throws Exception {
        PageHelper.startPage((int)pd.get("pageNo"), (int)pd.get("pageSize"));
        Page<PageData> page = new Page<>();
        page = (Page<PageData>) dao.findForList("ReportMapper.roomListByBuildid", pd);
        return page;
    }
}
