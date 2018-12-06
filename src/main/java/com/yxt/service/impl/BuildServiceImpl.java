package com.yxt.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yxt.dao.DaoSupport;
import com.yxt.domain.entity.PageData;
import com.yxt.service.BuildService;
import com.yxt.officialVehicl.core.base.constants.BaseConstants;
import com.yxt.officialVehicl.core.jwt.UserInfoFactory;
import com.yxt.officialVehicl.core.util.UrlInfoFactory;
import com.yxt.officialVehicl.management.resource.entity.ResourceRule;
import com.yxt.officialVehicl.management.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by yxt06 on 2018/11/28.
 */
@Service
public class BuildServiceImpl implements BuildService{
    @Autowired
    private DaoSupport dao;

    @Override
    public Page<PageData> findByPage(int pageNo, int pageSize, PageData pd) throws Exception{
        ResourceRule r = UrlInfoFactory.get("lou");
        UrlInfoFactory.print();
        int i = r.getResourceRule();
        PageHelper.startPage(pageNo, pageSize);
        Page<PageData> page = new Page<>();
        if(i == BaseConstants.RESOURCE_RULE_ALL){
            page = (Page<PageData>) dao.findForList("BuildMapper.findByPage", pd);
        }else if(i == BaseConstants.RESOURCE_RULE_DEPT || i == BaseConstants.RESOURCE_RULE_DEPT_SUB){
            List strs = r.getDeptIds();
            pd.put("deptids", strs.toString());
            page = (Page<PageData>) dao.findForList("BuildMapper.findListByDept", pd);
        }else if(i == BaseConstants.RESOURCE_RULE_REGION|| i == BaseConstants.RESOURCE_RULE_REGION_SUB){
            List strs = r.getRegionIds();
            pd.put("regionids", strs.toString());
            page = (Page<PageData>) dao.findForList("BuildMapper.findListByRegion", pd);
        }else if(i == BaseConstants.RESOURCE_RULE_DEPT_CUSTOM){
            pd.put("deptids", r.getCustomRule());
            page = (Page<PageData>) dao.findForList("BuildMapper.findListByDept", pd);
        }else if(i == BaseConstants.RESOURCE_RULE_REGION_CUSTOM){
            pd.put("regionids" ,r.getCustomRule());
            page = (Page<PageData>) dao.findForList("BuildMapper.findListByRegion", pd);
        }
        return page;
    }

    @Override
    public PageData findByid(BigInteger id) throws Exception{
        return (PageData) dao.findForObject("BuildMapper.selectByPrimaryKey", id);
    }

    @Override
    public boolean update(PageData pd) throws Exception{
        int a = (int) dao.update("BuildMapper.updateByPrimaryKey", pd);
        if(a == 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean save(PageData pd) throws Exception{
        User u = UserInfoFactory.getCurrentUser();
        pd.put("logic_delete", (byte)0);
        pd.put("created_by", u.getId());
        pd.put("created_time", new Date());
        //System.out.println(pd.toString());
        int a = (int) dao.save("BuildMapper.insert", pd);
        if(a == 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean delete(PageData pd) throws Exception{
        User u = UserInfoFactory.getCurrentUser();
        pd.put("updated_time", new Date());
        pd.put("updated_by", u.getId());
        int var = (int) dao.update("BuildMapper.deleteByPrimaryKey", pd);
        if(var == 1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<PageData> showBuildList() throws Exception {
        PageData pd = null;
        List<PageData> list = null;
        ResourceRule r = UrlInfoFactory.get("lou");
        int i = r.getResourceRule();
        if(i == BaseConstants.RESOURCE_RULE_ALL){
            list = (List<PageData>) dao.findForList("BuildMapper.findByPage", pd);
        }else if(i == BaseConstants.RESOURCE_RULE_DEPT || i == BaseConstants.RESOURCE_RULE_DEPT_SUB){
            List strs = r.getDeptIds();
            pd.put("deptids", strs.toString());
            list = (Page<PageData>) dao.findForList("BuildMapper.findListByDept", pd);
        }else if(i == BaseConstants.RESOURCE_RULE_REGION|| i == BaseConstants.RESOURCE_RULE_REGION_SUB){
            List strs = r.getRegionIds();
            pd.put("regionids", strs.toString());
            list = (Page<PageData>) dao.findForList("BuildMapper.findListByRegion", pd);
        }else if(i == BaseConstants.RESOURCE_RULE_DEPT_CUSTOM){
            pd.put("deptids", r.getCustomRule());
            list = (Page<PageData>) dao.findForList("BuildMapper.findListByDept", pd);
        }else if(i == BaseConstants.RESOURCE_RULE_REGION_CUSTOM){
            pd.put("regionids" ,r.getCustomRule());
            list = (Page<PageData>) dao.findForList("BuildMapper.findListByRegion", pd);
        }
        return list;
    }
}
