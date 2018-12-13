package com.yxt.service.impl;

import com.github.pagehelper.Page;
import com.yxt.dao.DaoSupport;
import com.yxt.domain.entity.PageData;
import com.yxt.officialVehicl.core.base.constants.BaseConstants;
import com.yxt.officialVehicl.core.util.UrlInfoFactory;
import com.yxt.officialVehicl.management.resource.entity.ResourceRule;
import com.yxt.service.DeptBindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yxt06 on 2018/12/13.
 */
@Service
public class DeptBindServiceImpl implements DeptBindService{
    @Autowired
    private DaoSupport dao;

    @Override
    public List<PageData> deptList() throws Exception {
        List<PageData> list = null;
        ResourceRule r = UrlInfoFactory.get("dept");
        UrlInfoFactory.print();
        int i = r.getResourceRule();
        if(i == BaseConstants.RESOURCE_RULE_ALL){
            list = (List<PageData>) dao.findForList("DeptBindMapper.findAllDept", null);
        }else if(i == BaseConstants.RESOURCE_RULE_DEPT || i == BaseConstants.RESOURCE_RULE_DEPT_SUB){
            List strs = r.getDeptIds();
            list = (Page<PageData>) dao.findForList("DeptBindMapper.findListByDept", strs.toString());
        }else if(i == BaseConstants.RESOURCE_RULE_REGION|| i == BaseConstants.RESOURCE_RULE_REGION_SUB){
            List strs = r.getRegionIds();
            list = (Page<PageData>) dao.findForList("DeptBindMapper.findListByRegion", strs.toString());
        }else if(i == BaseConstants.RESOURCE_RULE_DEPT_CUSTOM){
            list = (Page<PageData>) dao.findForList("DeptBindMapper.findListByDept", r.getCustomRule());
        }else if(i == BaseConstants.RESOURCE_RULE_REGION_CUSTOM){
            list = (Page<PageData>) dao.findForList("DeptBindMapper.findListByRegion", r.getCustomRule());
        }
        return list;
    }

    @Override
    public List<PageData> buildList() throws Exception {
        List<PageData> list = null;
        ResourceRule r = UrlInfoFactory.get("build");
        UrlInfoFactory.print();
        int i = r.getResourceRule();
        if(i == BaseConstants.RESOURCE_RULE_ALL){
            list = (List<PageData>) dao.findForList("DeptBindMapper.findAllBuild", null);
        }else if(i == BaseConstants.RESOURCE_RULE_DEPT || i == BaseConstants.RESOURCE_RULE_DEPT_SUB){
            List strs = r.getDeptIds();
            list = (Page<PageData>) dao.findForList("DeptBindMapper.findBuildListByDept", strs.toString());
        }else if(i == BaseConstants.RESOURCE_RULE_REGION|| i == BaseConstants.RESOURCE_RULE_REGION_SUB){
            List strs = r.getRegionIds();
            list = (Page<PageData>) dao.findForList("DeptBindMapper.findBuildListByRegion", strs.toString());
        }else if(i == BaseConstants.RESOURCE_RULE_DEPT_CUSTOM){
            list = (Page<PageData>) dao.findForList("DeptBindMapper.findBuildListByDept", r.getCustomRule());
        }else if(i == BaseConstants.RESOURCE_RULE_REGION_CUSTOM){
            list = (Page<PageData>) dao.findForList("DeptBindMapper.findBuildListByRegion", r.getCustomRule());
        }
        return list;
    }

    @Override
    public List<PageData> deptBindRoom(PageData pd) throws Exception {
        dao.delete("DeptBindMapper.deleteByDept", pd);
        String rids = pd.getString("rids");
        if (rids != null && !"".equals(rids)){
            List<String> result = Arrays.asList(rids.split(","));
            for (String string : result) {
                PageData pdd = new PageData();
                pdd.put("dept_id", pd.getString("dept_id"));
                pdd.put("room_id", string);
                dao.save("DeptBindMapper.RoomBindDept", pdd);
            }
        }
        return null;
    }

    @Override
    public List<PageData> roomByDept(PageData pd) throws Exception {
        List<PageData> list = (List<PageData>) dao.findForList("DeptBindMapper.roomByDept", pd);
        return list;
    }
}
