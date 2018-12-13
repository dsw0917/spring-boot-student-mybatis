package com.yxt.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yxt.dao.DaoSupport;
import com.yxt.domain.entity.PageData;
import com.yxt.officialVehicl.core.base.constants.BaseConstants;
import com.yxt.officialVehicl.core.util.UrlInfoFactory;
import com.yxt.officialVehicl.management.resource.entity.ResourceRule;
import com.yxt.service.BindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yxt06 on 2018/12/7.
 */
@Service
public class BindServiceImpl implements BindService {
    @Autowired
    private DaoSupport dao;

    @Override
    public List<PageData> deptList() throws Exception {
        PageData pd = null;
        List<PageData> list = null;
        ResourceRule r = UrlInfoFactory.get("dept");
        UrlInfoFactory.print();
        int i = r.getResourceRule();
        if(i == BaseConstants.RESOURCE_RULE_ALL){
            list = (List<PageData>) dao.findForList("BindMapper.findAllDept", pd);
        }else if(i == BaseConstants.RESOURCE_RULE_DEPT || i == BaseConstants.RESOURCE_RULE_DEPT_SUB){
            List strs = r.getDeptIds();
            pd.put("deptids", strs.toString());
            list = (Page<PageData>) dao.findForList("BindMapper.findListByDept", pd);
        }else if(i == BaseConstants.RESOURCE_RULE_REGION|| i == BaseConstants.RESOURCE_RULE_REGION_SUB){
            List strs = r.getRegionIds();
            pd.put("regionids", strs.toString());
            list = (Page<PageData>) dao.findForList("BindMapper.findListByRegion", pd);
        }else if(i == BaseConstants.RESOURCE_RULE_DEPT_CUSTOM){
            pd.put("deptids", r.getCustomRule());
            list = (Page<PageData>) dao.findForList("BindMapper.findListByDept", pd);
        }else if(i == BaseConstants.RESOURCE_RULE_REGION_CUSTOM){
            pd.put("regionids" ,r.getCustomRule());
            list = (Page<PageData>) dao.findForList("BindMapper.findListByRegion", pd);
        }
        return list;
    }

    @Override
    public Page<PageData> roomListBydeptid(PageData pd) throws Exception {
        PageHelper.startPage((int)pd.get("pageNo"), (int)pd.get("pageSize"));
        Page<PageData> page = new Page<>();
        page = (Page<PageData>) dao.findForList("BindMapper.roomListBydeptid", pd);
        return page;
    }

    @Override
    public List<PageData> personListBydeptid(PageData pd) throws Exception {
        List<PageData> list = (List<PageData>) dao.findForList("BindMapper.personListBydeptid", pd);
        return list;
    }

    @Override
    public List<PageData> personsByRoom(PageData pd) throws Exception {
        List<PageData> list = (List<PageData>) dao.findForList("BindMapper.personsByRoom", pd);
        return list;
    }

    @Override
    public void personBindRoom(PageData pd) throws Exception {
        dao.delete("BindMapper.deleteByRoomid", pd);
        String pids = pd.getString("pids");
        if (pids != null && !"".equals(pids)){
            List<String> result = Arrays.asList(pids.split(","));
            for (String string : result) {
                PageData pdd = new PageData();
                pdd.put("room_id", pd.getString("rid"));
                pdd.put("person_id", string);
                dao.save("BindMapper.personBindRoom", pdd);
            }
        }
    }
}
